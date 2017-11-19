/**
 * File: AtmLabel
 * Author: Michelle John
 * Date: 19 Nov 2017
 * Purpose: Week 4 ATM project
 */
package atmcomponents;

import javax.swing.JLabel;

/**
 * Class that extends {@link JLable} and is customized for this application. 
 */
public class AtmLabel extends JLabel {

  private static final long serialVersionUID = 1L;
  
  /**
   * Constructor.
   * 
   * @param text the text of the label
   */
  public AtmLabel(String text) {
    super(text, CENTER);
  }
}
