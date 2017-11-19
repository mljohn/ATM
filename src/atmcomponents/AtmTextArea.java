/**
 * File: AtmTextArea
 * Author: Michelle John
 * Date: 19 Nov 2017
 * Purpose: Week 4 ATM project
 */
package atmcomponents;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

import javax.swing.JTextArea;

/**
 * Class that extends {@link JTextArea} and is customized for this application.
 */
public class AtmTextArea extends JTextArea {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @param editable sets whether or not the text field can be edited
   */
  public AtmTextArea(boolean editable) {
    super();
    setEditable(editable);
    setBackground(WHITE);
    setForeground(BLACK);
  }
}
