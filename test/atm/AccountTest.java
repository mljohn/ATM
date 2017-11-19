/**
 * File: AccountTest
 * Author: Michelle John
 * Date: 19 Nov 2017
 * Purpose: Week 4 ATM project
 */
package atm;

import static atm.Account.getWithdrawalCount;
import static atm.Account.setWithdrawalCount;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import atm.Account;
import atm.InsufficientFundsException;

public class AccountTest {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  private Account testAccount;

  @Before
  public void setUp() {
    testAccount = new Account("test", 200);
    setWithdrawalCount(0);
  }

  @Test
  public void testGetters() {
    // verify
    assertThat(testAccount.getAccountType(), equalTo("test"));
    assertThat(testAccount.getBalance(), equalTo(200.0));
  }

  @Test
  public void testWithdraw_success() throws Exception {
    // test
    testAccount.withdraw(20);

    // verify
    assertThat(testAccount.getBalance(), equalTo(180.0));
    assertThat(getWithdrawalCount(), equalTo(1));
  }

  @Test
  public void testWithdraw_insufficientFunds() throws Exception {
    // expect
    exception.expect(InsufficientFundsException.class);

    // test
    testAccount.withdraw(220);
  }

  @Test
  public void testWithdraw_serviceFee() throws Exception {
    // setup
    setWithdrawalCount(4);
    // test
    testAccount.withdraw(100);

    // verify
    assertThat(testAccount.getBalance(), equalTo(98.5));
    assertThat(getWithdrawalCount(), equalTo(5));
  }

  @Test
  public void testWithdraw_zeroAmount() throws Exception {
    // test
    testAccount.withdraw(0);

    // verify
    assertThat(testAccount.getBalance(), equalTo(200.0));
    assertThat(getWithdrawalCount(), equalTo(0));
  }

  @Test
  public void testDeposit() {
    // test
    testAccount.deposit(200);

    // verify
    assertThat(testAccount.getBalance(), equalTo(400.0));
  }

  @Test
  public void testTransfer_success() throws Exception {
    // setup
    Account secondAccount = new Account("notTest", 200);

    // test
    testAccount.transfer(secondAccount, 50);

    // verify
    assertThat(secondAccount.getBalance(), equalTo(150.0));
    assertThat(testAccount.getBalance(), equalTo(250.0));
    assertThat(getWithdrawalCount(), equalTo(0));
  }

  @Test
  public void testTransfer_insufficientFunds() throws Exception {
    // setup
    Account secondAccount = new Account("notTest", 50);

    // expect
    exception.expect(InsufficientFundsException.class);

    // test
    testAccount.transfer(secondAccount, 100);
  }

  @Test
  public void testTransfer_serviceFee() throws Exception {
    // setup
    Account secondAccount = new Account("notTest", 200);
    setWithdrawalCount(5);
    
    // test
    testAccount.transfer(secondAccount, 100);
    
    // verify
    assertThat(secondAccount.getBalance(), equalTo(100.0));
    assertThat(testAccount.getBalance(), equalTo(300.0));
    assertThat(getWithdrawalCount(), equalTo(5));
  }
  
  @Test
  public void testToString() {
    // verify
    assertThat(testAccount.toString(), equalTo("Your test account has a balance of $200.00."));
  }
}
