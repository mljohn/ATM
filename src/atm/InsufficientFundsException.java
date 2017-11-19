/**
 * File: InsufficientFundsException
 * Author: Michelle John
 * Date: 19 Nov 2017
 * Purpose: Week 4 ATM project
 */
package atm;

/**
 * Exception that is thrown if an account has insufficient funds for withdrawal or transfer. 
 */
public class InsufficientFundsException extends Exception {

  private static final long serialVersionUID = 1L;
  
  /**
   * Default constructor.
   */
  public InsufficientFundsException() {
    super();
  }
  
  /**
   * Constructor.
   * 
   * @param reason the reason the exception was thrown
   */
  public InsufficientFundsException(String reason) {
    super(reason);
  }
  
  /**
   * Constructor.
   * 
   * @param throwable the exception that was caught
   */
  public InsufficientFundsException(Throwable throwable) {
    super(throwable);
  }
  
  /**
   * Constructor.
   * 
   * @param reason the reason the exception was thrown
   * @param throwable the exception that was caught
   */
  public InsufficientFundsException(String reason, Throwable throwable) {
    super(reason, throwable);
  }
}
