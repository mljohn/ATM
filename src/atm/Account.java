/**
 * File: Account
 * Author: Michelle John
 * Date: 19 Nov 2017
 * Purpose: Week 4 ATM project
 */
package atm;

import java.text.DecimalFormat;

/**
 * Class that holds account information. 
 */
public class Account {

  private String accountType;
  private DecimalFormat df = new DecimalFormat("#0.00");
  private double accountBalance;

  private static int withdrawalCount = 0;
  private static final int FREE_WITHDRAWAL_COUNT = 4;
  private static final double SERVICE_FEE = 1.5;

  /**
   * Constructor.
   * 
   * @param accountType the type of account
   * @param accountBalance the starting balance of the account
   */
  public Account(String accountType, double accountBalance) {
    this.accountType = accountType;
    this.accountBalance = accountBalance;
  }

  /**
   * Subtracts the withdrawal amount from the balance, if there are sufficient funds.
   * 
   * @param withdrawalAmount the amount to be withdrawn
   * @throws InsufficientFundsException if the balance is less than the amount to withdraw
   */
  public void withdraw(double withdrawalAmount) throws InsufficientFundsException {
    double debitAmount = withdrawalCount >= FREE_WITHDRAWAL_COUNT ? withdrawalAmount + SERVICE_FEE : withdrawalAmount;

    if (debitAmount > accountBalance) {
      throw new InsufficientFundsException("You have insufficient funds to withdraw $" + df.format(withdrawalAmount)
          + ".");
    }
    if (debitAmount > 0) {
      accountBalance -= debitAmount;
      withdrawalCount++;
    }
  }

  /**
   * Adds the deposit amount to the balance.
   * 
   * @param depositAmount the amount to add to the balance
   */
  public void deposit(double depositAmount) {
    accountBalance += depositAmount;
  }

  /**
   * Transfers the given amount from one account to the other.
   * 
   * @param from the account from which to transfer the funds
   * @param transferAmount the amount to transfer
   * @throws InsufficientFundsException if the balance of the account from which to transfer funds is 
   *         less than the amount to transfer
   */
  public void transfer(Account from, double transferAmount) throws InsufficientFundsException {
    if (transferAmount > from.getBalance()) {
      throw new InsufficientFundsException("You have insufficient funds to transfer $" + df.format(transferAmount)
          + " from " + from.getAccountType() + " to " + this.getAccountType() + ".");
    }

    // If withdrawal count is over 4, subtract the service fee that will get added.
    double debitAmount = withdrawalCount >= FREE_WITHDRAWAL_COUNT ? transferAmount - SERVICE_FEE : transferAmount;
    from.withdraw(debitAmount);
    this.deposit(transferAmount);
    // Transfers don't count against number of withdrawals, so set it back.
    withdrawalCount --;
  }

  /**
   * @return the account balance
   */
  public double getBalance() {
    return accountBalance;
  }

  /**
   * @return the type of account
   */
  public String getAccountType() {
    return accountType;
  }

  /**
   * For testing purposes.
   * 
   * @return the withdrawal count
   */
  static int getWithdrawalCount() {
    return withdrawalCount;
  }
  
  /**
   * For testing purposes.
   */
  static void setWithdrawalCount(int count) {
    withdrawalCount = count;
  }

  @Override
  public String toString() {
    return "Your " + accountType + " account has a balance of $" + df.format(accountBalance) + ".";
  }
}
