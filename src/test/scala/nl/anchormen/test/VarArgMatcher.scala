package nl.anchormen.test

import org.mockito.ArgumentMatcher
import org.apache.commons.lang.builder.EqualsBuilder
import org.mockito.internal.matchers.VarargMatcher

/**
 * Used for matching variable arguments. Adapted to Scala from http://stackoverflow.com/a/24295695/543720
 * 
 * @author Jeroen Vlek <j.vlek@anchormen.nl>
 */
class VarArgMatcher[T](expectedValues : T*) extends ArgumentMatcher[T] with VarargMatcher {
  
  override def matches(varargArgument : AnyRef) : Boolean = {
    new EqualsBuilder().append(expectedValues, varargArgument).isEquals()
  }
}