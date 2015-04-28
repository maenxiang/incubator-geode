/*=========================================================================
 * Copyright Copyright (c) 2000-2014 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * more patents listed at http://www.pivotal.io/patents.
 * $Id: CompiledNegation.java,v 1.1 2005/01/27 06:26:33 vaibhav Exp $
 *=========================================================================
 */

package com.gemstone.gemfire.cache.query.internal;

import java.util.*;

import com.gemstone.gemfire.cache.query.*;
import com.gemstone.gemfire.internal.i18n.LocalizedStrings;


/**
 * Class Description
 *
 * @version     $Revision: 1.1 $
 * @author      ericz
 */


public class CompiledNegation extends AbstractCompiledValue {
  private CompiledValue _value;
  
  public CompiledNegation(CompiledValue value) {
    _value = value;
  }
  
  @Override
  public List getChildren() {
    return Collections.singletonList(this._value);
  }
  
  public int getType() {
    return LITERAL_not;
  }
  
  public Object evaluate(ExecutionContext context)
  throws FunctionDomainException, TypeMismatchException, NameResolutionException,
          QueryInvocationTargetException {
    return negateObject(_value.evaluate(context));
  }
  
  @Override
  public Set computeDependencies(ExecutionContext context)
  throws TypeMismatchException, AmbiguousNameException, NameResolutionException {
    return context.addDependencies(this, this._value.computeDependencies(context));
  }  
  
  private Object negateObject(Object obj)
  throws TypeMismatchException {
    if (obj instanceof Boolean)
      return Boolean.valueOf(!((Boolean)obj).booleanValue());
    if (obj == null || obj == QueryService.UNDEFINED)
      return QueryService.UNDEFINED;
    throw new TypeMismatchException(LocalizedStrings.CompiledNegation_0_CANNOT_BE_NEGATED.toLocalizedString(obj.getClass()));
  }
  
  @Override
  public void generateCanonicalizedExpression(StringBuffer clauseBuffer, ExecutionContext context)
  throws AmbiguousNameException, TypeMismatchException, NameResolutionException {
    clauseBuffer.insert(0, ')');
    _value.generateCanonicalizedExpression(clauseBuffer, context);
    clauseBuffer.insert(0, "NOT(");
  }
  
}
