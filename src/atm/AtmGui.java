/**
 * File: AtmGui
 * Author: Michelle John
 * Date: 19 Nov 2017
 * Purpose: Week 4 ATM project
 */
package atm;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.WEST;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import atmcomponents.AtmFrame;
import atmcomponents.AtmLabel;
import atmcomponents.AtmTextArea;

/**
 * Main class for the ATM Gui project 
 */
public class AtmGui {
  
  public static void main(String[] args) {
    AtmGui atmGui = new AtmGui();
    atmGui.getAtmGui();
  }
  
  /**
   * Method that builds and displays the ATM gui.
   */
  private void getAtmGui() {
    Account checkingAccount = new Account("checking", 1500);
    Account savingsAccount = new Account("savings", 2500);

    AtmFrame atmFrame = new AtmFrame("ATM", 300, 200);

    JPanel atmButtonPanel = new JPanel();
    JPanel atmTextAreaPanel = new JPanel(new GridLayout(2, 1, 5, 5));
    JPanel moneyActionPanel = new JPanel(new GridLayout(2, 2, 5, 5));

    JButton withdrawButton = new JButton("Withdraw");
    JButton depositButton = new JButton("Deposit");
    JButton transferButton = new JButton("Transfer");
    JButton balanceButton = new JButton("Balance");

    AtmTextArea entryArea = new AtmTextArea(true);

    AtmLabel entryLabel = new AtmLabel("Enter dollar amount");
    
    JRadioButton checkingButton = new JRadioButton("Checking", true);
    JRadioButton savingsButton = new JRadioButton("Savings");
    ButtonGroup accountButtons = new ButtonGroup();
    
    accountButtons.add(checkingButton);
    accountButtons.add(savingsButton);

    atmTextAreaPanel.add(entryLabel);
    atmTextAreaPanel.add(entryArea);

    withdrawButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        double amount = getAmountEntered(entryArea, atmFrame);
        if (amount % 20 != 0) {
          showMessageDialog(atmFrame, "Please enter a multiple of $20");
          entryArea.setText(null);
          amount = 0;
        }
        if (checkingButton.isSelected() && amount > 0) {
          try {
            checkingAccount.withdraw(amount);
            showMessageDialog(atmFrame, checkingAccount);
          } catch (InsufficientFundsException ex) {
            showMessageDialog(atmFrame, ex.getMessage());
          } finally {
            entryArea.setText(null);
          }
        } else if (savingsButton.isSelected() && amount > 0) {
          try {
            savingsAccount.withdraw(amount);
            showMessageDialog(atmFrame, savingsAccount);
          } catch (InsufficientFundsException ex) {
            showMessageDialog(atmFrame, ex.getMessage());
          } finally {
            entryArea.setText(null);
          }
        }
      }
    });

    depositButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        double amount = getAmountEntered(entryArea, atmFrame);
        if (checkingButton.isSelected() && amount > 0) {
          checkingAccount.deposit(amount);
          showMessageDialog(atmFrame, checkingAccount);
        } else if (savingsButton.isSelected() && amount > 0) {
          savingsAccount.deposit(amount);
          showMessageDialog(atmFrame, savingsAccount);
        }
        entryArea.setText(null);
      }
    });

    transferButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        double amount = getAmountEntered(entryArea, atmFrame);
        if (checkingButton.isSelected() && amount > 0) {
          try {
            checkingAccount.transfer(savingsAccount, amount);
            showMessageDialog(atmFrame, checkingAccount);
            showMessageDialog(atmFrame, savingsAccount);
          } catch (InsufficientFundsException ex) {
            showMessageDialog(atmFrame, ex.getMessage());
          } finally {
            entryArea.setText(null);
          }
        } else if (savingsButton.isSelected() && amount > 0) {
          try {
            savingsAccount.transfer(checkingAccount, amount);
            showMessageDialog(atmFrame, savingsAccount);
            showMessageDialog(atmFrame, checkingAccount);
          } catch (InsufficientFundsException ex) {
            showMessageDialog(atmFrame, ex.getMessage());
          } finally {
            entryArea.setText(null);
          }
        }
      }
    });

    balanceButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (checkingButton.isSelected()) {
          showMessageDialog(atmFrame, checkingAccount);
        } else {
          showMessageDialog(atmFrame, savingsAccount);
        }
        entryArea.setText(null);
      }
    });

    moneyActionPanel.add(withdrawButton);
    moneyActionPanel.add(depositButton);
    moneyActionPanel.add(transferButton);
    moneyActionPanel.add(balanceButton);
    atmButtonPanel.add(moneyActionPanel);
    atmButtonPanel.add(checkingButton);
    atmButtonPanel.add(savingsButton);
    atmButtonPanel.add(atmTextAreaPanel);

    atmFrame.setLayout(new BorderLayout(5, 5));
    atmFrame.add(new JPanel(), EAST);
    atmFrame.add(new JPanel(), WEST);
    atmFrame.add(new JPanel(), NORTH);
    atmFrame.add(atmButtonPanel, CENTER);

    atmFrame.display();
  }
  
  /**
   * Determines if the entry is a {@code double} and returns the value.
   * 
   * @param entry the text area reference
   * @param frame the main frame reference
   * @return the number that was input into the text area
   */
  private double getAmountEntered(AtmTextArea entry, AtmFrame frame) {
    double amount = 0;
    try {
      amount = Double.parseDouble(entry.getText());
    } catch (NumberFormatException ex) {
      showMessageDialog(frame, "Enter a valid dollar amount");
      entry.setText(null);
    }
    return amount;
  }
}
