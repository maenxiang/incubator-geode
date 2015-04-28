/*=========================================================================
 * Copyright (c) 2010-2014 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * one or more patents listed at http://www.pivotal.io/patents.
 *=========================================================================
 */
package com.gemstone.gemfire.internal.cache;

import org.apache.logging.log4j.Logger;

import com.gemstone.gemfire.CancelException;
import com.gemstone.gemfire.cache.CommitConflictException;
import com.gemstone.gemfire.cache.TransactionDataRebalancedException;
import com.gemstone.gemfire.cache.TransactionException;
import com.gemstone.gemfire.cache.TransactionInDoubtException;
import com.gemstone.gemfire.cache.client.internal.ServerRegionDataAccess;
import com.gemstone.gemfire.distributed.DistributedMember;
import com.gemstone.gemfire.distributed.internal.ReliableReplyException;
import com.gemstone.gemfire.distributed.internal.ReliableReplyProcessor21;
import com.gemstone.gemfire.distributed.internal.ReplyException;
import com.gemstone.gemfire.distributed.internal.membership.InternalDistributedMember;
import com.gemstone.gemfire.internal.cache.TXRemoteCommitMessage.RemoteCommitResponse;
import com.gemstone.gemfire.internal.cache.tx.DistributedTXRegionStub;
import com.gemstone.gemfire.internal.cache.tx.PartitionedTXRegionStub;
import com.gemstone.gemfire.internal.cache.tx.TXRegionStub;
import com.gemstone.gemfire.internal.cache.tx.TransactionalOperation.ServerRegionOperation;
import com.gemstone.gemfire.internal.i18n.LocalizedStrings;
import com.gemstone.gemfire.internal.logging.LogService;

public class PeerTXStateStub extends TXStateStub {

  private static final Logger logger = LogService.getLogger();
  
  private InternalDistributedMember originatingMember = null;
  private TXCommitMessage commitMessage = null;

  public PeerTXStateStub(TXStateProxy stateProxy, DistributedMember target,InternalDistributedMember onBehalfOfClient) {
    super(stateProxy, target);
    this.originatingMember = onBehalfOfClient;
  }
  
  
  /* (non-Javadoc)
   * @see com.gemstone.gemfire.internal.cache.TXStateInterface#rollback()
   */
  @Override
  public void rollback() {
    /*
     * txtodo: work this into client realm
     */
    ReliableReplyProcessor21 response = TXRemoteRollbackMessage.send(
                                                          this.proxy.getCache(),
                                                          this.proxy.getTxId().getUniqId(),
                                                          getOriginatingMember(),
                                                          this.target);
    if (this.internalAfterSendRollback != null) {
      this.internalAfterSendRollback.run();
    }
    
    try {
      response.waitForReplies();
    } catch(PrimaryBucketException pbe) {
      // ignore this
    } catch (ReplyException e) {
      this.proxy.getCache().getCancelCriterion().checkCancelInProgress(e);
      if (e.getCause() != null && e.getCause() instanceof CancelException) {
        // other cache must have closed (bug #43649), so the transaction is lost
        if (this.internalAfterSendRollback != null) {
          this.internalAfterSendRollback.run();
        }
      } else {
        throw new TransactionException(LocalizedStrings.
            TXStateStub_ROLLBACK_ON_NODE_0_FAILED.toLocalizedString(target), e);
      }
    } catch(Exception e) {
      this.getCache().getCancelCriterion().checkCancelInProgress(e);
      throw new TransactionException(LocalizedStrings.
          TXStateStub_ROLLBACK_ON_NODE_0_FAILED.toLocalizedString(target), e);
    } finally {
      cleanup();
    }
  }

  @Override
  public void commit() throws CommitConflictException {
    assert target != null;
    /*
     * txtodo: Going to need to deal with client here
     */
    RemoteCommitResponse message = 
          TXRemoteCommitMessage.send(this.proxy.getCache(),
                                      this.proxy.getTxId().getUniqId(),
                                      this.getOriginatingMember(),
                                      target);
    
    if (this.internalAfterSendCommit != null) {
      this.internalAfterSendCommit.run();
    }
    
    try {
      commitMessage = message.waitForResponse();
    }  catch (CommitConflictException e) {
      throw e;
    } catch(TransactionException te) {
      throw te;
    } catch (ReliableReplyException e) {
      if(e.getCause()!=null) {
        throw new TransactionInDoubtException(e.getCause());
      } else {
        throw new TransactionInDoubtException(e);
      }
    } catch(ReplyException e) {
      if(e.getCause() instanceof CommitConflictException) {
        throw (CommitConflictException)e.getCause();
      } else if(e.getCause() instanceof TransactionException) {
        throw (TransactionException)e.getCause();
      }
      /*
      if(e.getCause()!=null) {
        throw new CommitConflictException(e.getCause());
      } else {
        throw new CommitConflictException(e);
      } */
      if(e.getCause()!=null) {
        throw new TransactionInDoubtException(e.getCause());
      } else {
        throw new TransactionInDoubtException(e);
      }
    } catch (Exception e) {
      this.getCache().getCancelCriterion().checkCancelInProgress(e);
      if (e.getCause()!=null) {
    	if (e.getCause() instanceof ForceReattemptException) {
    	  Throwable e2 = e.getCause();
    	  if (e2.getCause()!=null && e2.getCause() instanceof PrimaryBucketException) {
    	      // data rebalanced
    	      TransactionDataRebalancedException tdnce =  new TransactionDataRebalancedException(e2.getCause().getMessage());
	      tdnce.initCause(e2.getCause());
	      throw tdnce;
    	  } else {
    	    // We cannot be sure that the member departed starting to process commit request,
    	    // so throw a TransactionInDoubtException rather than a TransactionDataNodeHasDeparted. fixes 44939
   	    TransactionInDoubtException tdnce =  new TransactionInDoubtException(e.getCause().getMessage());
	    tdnce.initCause(e.getCause());
	    throw tdnce;
    	  }
    	}
        throw new TransactionInDoubtException(e.getCause());
      } else {
        throw new TransactionInDoubtException(e);
      }
    } finally {
      cleanup();
    }
  }


  private void cleanup() {
    for (TXRegionStub regionStub : regionStubs.values()) {
      regionStub.cleanup();
    }
  }


  @Override
  protected TXRegionStub generateRegionStub(LocalRegion region) {
      TXRegionStub stub = null;
      if(region.getPartitionAttributes()==null) {
        // This is a dist region
        stub = new DistributedTXRegionStub(this,region);
      } else {
        stub = new PartitionedTXRegionStub(this,region);
      }
      return stub;
  }




  @Override
  protected void validateRegionCanJoinTransaction(LocalRegion region)
      throws TransactionException {
    /*
     * Ok is this region legit to enter into tx?
     */
    if(region.hasServerProxy()) {
      /*
       * This is a c/s region in a peer tx. nope!
       */
      throw new TransactionException("Can't involve c/s region in peer tx");
    }
    
  }


  @Override
  public void afterCompletion(int status) {
    RemoteCommitResponse response = JtaAfterCompletionMessage.send(this.proxy.getCache(),
        this.proxy.getTxId().getUniqId(),getOriginatingMember(), status, this.target);
    try {
      this.proxy.getTxMgr().setTXState(null);
      this.commitMessage = response.waitForResponse();
      if (logger.isDebugEnabled()) {
        logger.debug("afterCompletion received commit response of {}", this.commitMessage);
      }
      
    } catch (Exception e) {
      throw new TransactionException(e);
      //TODO throw a better exception
    } finally {
      cleanup();
    }
  }


  public InternalDistributedMember getOriginatingMember() {
    /*
     * This needs to be set to the clients member id if the client originated the tx
     */
    return originatingMember;
  }

  public void setOriginatingMember(InternalDistributedMember clientMemberId) {
    /*
     * This TX is on behalf of a client, so we have to send the client's member id around
     */
    this.originatingMember = clientMemberId;
  }

  public boolean isMemberIdForwardingRequired() {
    return getOriginatingMember()!=null;
  }


  public TXCommitMessage getCommitMessage() {
    return commitMessage;
  }


  public void suspend() {
    // no special tasks to perform
  }


  public void resume() {
    // no special tasks to perform
  }

  public void recordTXOperation(ServerRegionDataAccess region, ServerRegionOperation op, Object key, Object arguments[]) {
    // no-op here
  }
}
