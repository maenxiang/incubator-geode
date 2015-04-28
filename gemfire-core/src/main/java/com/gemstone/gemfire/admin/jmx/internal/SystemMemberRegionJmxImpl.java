/*=========================================================================
 * Copyright (c) 2002-2014 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * more patents listed at http://www.pivotal.io/patents.
 *========================================================================
 */
package com.gemstone.gemfire.admin.jmx.internal;

import com.gemstone.gemfire.admin.internal.SystemMemberCacheImpl;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.internal.admin.GemFireVM;

import javax.management.ObjectName;
import javax.management.modelmbean.ModelMBean;

/**
 * MBean representation of {@link 
 * com.gemstone.gemfire.admin.SystemMemberRegion}.
 *
 * @author    Darrel Schneider
 * @author    Kirk Lund
 * @since     3.5
 */
public class SystemMemberRegionJmxImpl 
extends com.gemstone.gemfire.admin.internal.SystemMemberRegionImpl
implements com.gemstone.gemfire.admin.jmx.internal.ManagedResource {

  /** The object name of this managed resource */
  private ObjectName objectName;

  // -------------------------------------------------------------------------
  //   Constructor(s)
  // -------------------------------------------------------------------------

  /** 
   * Constructs an instance of SystemMemberRegionJmxImpl.
   *
   * @param cache   the cache this region belongs to
   * @param region  internal region to delegate real work to
   */
  public SystemMemberRegionJmxImpl(SystemMemberCacheImpl cache, 
                                   Region region)
                            throws com.gemstone.gemfire.admin.AdminException {
    super(cache, region);
    initializeMBean(cache);
  }

  /** Create and register the MBean to manage this resource */
  private void initializeMBean(SystemMemberCacheImpl cache)
  throws com.gemstone.gemfire.admin.AdminException {
    
    GemFireVM vm = cache.getVM();
    this.mbeanName = new StringBuffer("GemFire.Cache:")
        .append("path=")
        .append(MBeanUtil.makeCompliantMBeanNameProperty(getFullPath()))
        .append(",name=")
        .append(MBeanUtil.makeCompliantMBeanNameProperty(cache.getName()))
        .append(",id=")
        .append(cache.getId())
        .append(",owner=")
        .append(MBeanUtil.makeCompliantMBeanNameProperty(vm.getId().toString()))
        .append(",type=Region").toString();
      
    this.objectName = MBeanUtil.createMBean(this);
  }
  
  // -------------------------------------------------------------------------
  //   ManagedResource implementation
  // -------------------------------------------------------------------------
  
  /** The name of the MBean that will manage this resource */
  private String mbeanName;

  /** The ModelMBean that is configured to manage this resource */
  private ModelMBean modelMBean;
  
	public String getMBeanName() {
		return this.mbeanName;
	}
  
	public ModelMBean getModelMBean() {
		return this.modelMBean;
	}
	public void setModelMBean(ModelMBean modelMBean) {
		this.modelMBean = modelMBean;
	}
  
  public ObjectName getObjectName() {
    return this.objectName;
  }

  public ManagedResourceType getManagedResourceType() {
    return ManagedResourceType.SYSTEM_MEMBER_REGION;
  }
  
  public void cleanupResource() {}

  /**
   * Checks equality of the given object with <code>this</code> based on the
   * type (Class) and the MBean Name returned by <code>getMBeanName()</code>
   * methods.
   * 
   * @param obj
   *          object to check equality with
   * @return true if the given object is if the same type and its MBean Name is
   *         same as <code>this</code> object's MBean Name, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if ( !(obj instanceof SystemMemberRegionJmxImpl) ) {
      return false;
    }
    
    SystemMemberRegionJmxImpl other = (SystemMemberRegionJmxImpl) obj; 
    
    return this.getMBeanName().equals(other.getMBeanName());
  }

  /**
   * Returns hash code for <code>this</code> object which is based on the MBean 
   * Name generated. 
   * 
   * @return hash code for <code>this</code> object
   */
  @Override
  public int hashCode() {
    return this.getMBeanName().hashCode();
  }
}

