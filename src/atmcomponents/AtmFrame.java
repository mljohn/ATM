/**
 * File: AtmFrame
 * Author: Michelle John
 * Date: 19 Nov 2017
 * Purpose: Week 4 ATM project
 */
package atmcomponents;

import javax.swing.JFrame;

/**
 * Class that extends {@link JFrame} and is customized for this application.
 */
public class AtmFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @param title the title of the {@link JFrame}
   * @param width the width of the frame
   * @param height the height of the frame
   */
  public AtmFrame(String title, int width, int height) {
    super(title);
    setSize(width, height);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  
  /**
   * Shows the gui.
   */
  public void display() {
    setVisible(true);
  }
}
