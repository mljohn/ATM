/**
 * File: InsufficientFundsExceptionTest
 * Author: Michelle John
 * Date: 19 Nov 2017
 * Purpose: Week 4 ATM project
 */
package atm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Tests for {@link InsufficientFundsException}
 */
public class InsufficientFundsExceptionTest {
  
  private InsufficientFundsException testException;
  
  @Test
  public void testConstructor() {
    // test
    testException = new InsufficientFundsException();
    
    // verify
    assertThat(testException, notNullValue());
  }

  @Test
  public void testConstructor_reason() {
    // test
    testException = new InsufficientFundsException("Reason");
    
    // verify
    assertThat(testException.getMessage(), equalTo("Reason"));
  }
  
  @Test
  public void testConstructor_cause() {
    // test
    testException = new InsufficientFundsException(new Exception());
    
    // verify
    assertThat(testException.getCause(), instanceOf(Exception.class));
  }
  
  @Test
  public void testConstructor_reasonCause() {
    // test
    testException = new InsufficientFundsException("Reason", new Exception());
    
    // verify
    assertThat(testException.getMessage(), equalTo("Reason"));
    assertThat(testException.getCause(), instanceOf(Exception.class));
  }
}
