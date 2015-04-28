/*=========================================================================
 * Copyright (c) 2002-2014 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * more patents listed at http://www.pivotal.io/patents.
 *=========================================================================
 */
package com.gemstone.gemfire.internal.cache.tier.sockets.command;

import java.io.IOException;

import com.gemstone.gemfire.cache.TransactionDataNodeHasDepartedException;
import com.gemstone.gemfire.distributed.internal.DistributionConfig;
import com.gemstone.gemfire.distributed.internal.DistributionManager;
import com.gemstone.gemfire.distributed.internal.InternalDistributedSystem;
import com.gemstone.gemfire.distributed.internal.ReplyException;
import com.gemstone.gemfire.distributed.internal.WaitForViewInstallation;
import com.gemstone.gemfire.distributed.internal.membership.InternalDistributedMember;
import com.gemstone.gemfire.internal.Assert;
import com.gemstone.gemfire.internal.cache.FindRemoteTXMessage;
import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.internal.cache.PeerTXStateStub;
import com.gemstone.gemfire.internal.cache.TXId;
import com.gemstone.gemfire.internal.cache.TXManagerImpl;
import com.gemstone.gemfire.internal.cache.TXState;
import com.gemstone.gemfire.internal.cache.TXStateProxy;
import com.gemstone.gemfire.internal.cache.TXStateProxyImpl;
import com.gemstone.gemfire.internal.cache.FindRemoteTXMessage.FindRemoteTXMessageReplyProcessor;
import com.gemstone.gemfire.internal.cache.tier.Command;
import com.gemstone.gemfire.internal.cache.tier.sockets.BaseCommand;
import com.gemstone.gemfire.internal.cache.tier.sockets.Message;
import com.gemstone.gemfire.internal.cache.tier.sockets.ServerConnection;
import com.gemstone.gemfire.internal.i18n.LocalizedStrings;

/**
 * Used for bootstrapping txState/PeerTXStateStub on the server.
 * This command is send when in client in a transaction is about
 * to failover to this server
 * @author sbawaska
 */
public class TXFailoverCommand extends BaseCommand {

  private static final Command singleton = new TXFailoverCommand();
  
  public static Command getCommand() {
    return singleton;
  }

  private TXFailoverCommand() {
  }
  
  @Override
  public void cmdExecute(Message msg, ServerConnection servConn, long start)
      throws IOException, ClassNotFoundException, InterruptedException {
    servConn.setAsTrue(REQUIRES_RESPONSE);
    // Build the TXId for the transaction
    InternalDistributedMember client = (InternalDistributedMember) servConn.getProxyID().getDistributedMember();
    int uniqId = msg.getTransactionId();
    if (logger.isDebugEnabled()) {
      logger.debug("TX: Transaction {} from {} is failing over to this server", uniqId, client);
    }
    TXId txId = new TXId(client, uniqId);
    TXManagerImpl mgr = (TXManagerImpl) servConn.getCache().getCacheTransactionManager();
    mgr.waitForCompletingTransaction(txId); // in case it's already completing here in another thread
    if (mgr.isHostedTxRecentlyCompleted(txId)) {
      writeReply(msg, servConn);
      servConn.setAsTrue(RESPONDED);
      mgr.removeHostedTXState(txId);
      return;
    }
    boolean wasInProgress = mgr.setInProgress(true); // fixes bug 43350
    TXStateProxy tx = mgr.getTXState();
    Assert.assertTrue(tx != null);
    if (!tx.isRealDealLocal()) {
      // send message to all peers to find out who hosts the transaction
      FindRemoteTXMessageReplyProcessor processor = FindRemoteTXMessage.send(servConn.getCache(), txId);
      try {
        processor.waitForRepliesUninterruptibly();
      } catch (ReplyException e) {
        e.handleAsUnexpected();
      }
      // if hosting member is not null, bootstrap PeerTXStateStub to that member
      // if hosting member is null, rebuild TXCommitMessage from partial TXCommitMessages
      InternalDistributedMember hostingMember = processor.getHostingMember();
      if (hostingMember != null) {
        if (logger.isDebugEnabled()) {
          logger.debug("TX: txState is not local, bootstrapping PeerTXState stub for targetNode: {}", hostingMember);
        }
        // inject the real deal
        tx.setLocalTXState(new PeerTXStateStub(tx, hostingMember, client));
      } else {
        // bug #42228 and bug #43504 - this cannot return until the current view
        // has been installed by all members, so that dlocks are released and
        // the same keys can be used in a new transaction by the same client thread
        GemFireCacheImpl cache = (GemFireCacheImpl)servConn.getCache();
        try {
          WaitForViewInstallation.send((DistributionManager)cache.getDistributionManager());
        } catch (InterruptedException e) {
          cache.getDistributionManager().getCancelCriterion().checkCancelInProgress(e);
          Thread.currentThread().interrupt();
        }
        // tx host has departed, rebuild the tx
        if (processor.getTxCommitMessage() != null) {
          if (logger.isDebugEnabled()) {
            logger.debug("TX: for txId: {} rebuilt a recently completed tx", txId);
          }
          mgr.saveTXCommitMessageForClientFailover(txId, processor.getTxCommitMessage());
        } else {
          writeException(msg, new TransactionDataNodeHasDepartedException("Could not find transaction host for "+txId), false, servConn);
          servConn.setAsTrue(RESPONDED);
          mgr.removeHostedTXState(txId);
          return;
        }
      }
    }
    if (!wasInProgress) {
      mgr.setInProgress(false);
    }
    writeReply(msg, servConn);
    servConn.setAsTrue(RESPONDED);
  }

}
