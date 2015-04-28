/*=========================================================================
 * Copyright (c) 2010-2014 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * one or more patents listed at http://www.pivotal.io/patents.
 *=========================================================================
 */
/**
 * 
 */
package com.gemstone.gemfire.pdx;

/**
 * Marker interface for an object that GemFire creates and returns
 * from {@link PdxReader#readUnreadFields() readUnreadFields}.
 * If you call readUnreadFields then you must also call
 * {@link PdxWriter#writeUnreadFields(PdxUnreadFields) writeUnreadFields} when
 * that object is reserialized. If you do not call {@link PdxWriter#writeUnreadFields(PdxUnreadFields) writeUnreadFields}
 * but you did call {@link PdxReader#readUnreadFields() readUnreadFields} the unread fields will not be written.
 * <p>Unread fields are those that are not explicitly read with a {@link PdxReader} readXXX method.
 * This should only happen when a domain class has changed by adding or removing one or more fields.
 * Unread fields will be preserved automatically (unless you turn this feature off using
 * {@link com.gemstone.gemfire.cache.CacheFactory#setPdxIgnoreUnreadFields(boolean) setPdxIgnoreUnreadFields}
 * or {@link com.gemstone.gemfire.cache.client.ClientCacheFactory#setPdxIgnoreUnreadFields(boolean) client setPdxIgnoreUnreadFields})
 * but to reduce the performance and memory overhead of automatic preservation it is recommended
 * that use {@link PdxReader#readUnreadFields() readUnreadFields} if possible.
 * 
 * @author darrel
 * @since 6.6
 *
 */
public interface PdxUnreadFields {
}
