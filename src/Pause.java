import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/** 
 * This is the class for the pause button. It both displays 
 * a seperate screen for the pause, and pauses the timer.
 * Note that since javadoc is not picking up caught exceptions, we included the exceptions in the local
 * variables.
 * 
 * <p><b>Verison 1 (4 Hours):</b> Completing the tasks to incorporate with the timer.</p>
 * 
 * @author Tiffany Tran
 * @version 1 05.29.16
 * Program name: Pause.java
 */
public class Pause extends JPanel
{
  /**
   * Private instance variables
   * 
   * */
  
  /**
   * A new JFrame for the pause button.
   * */      
  JFrame pauseFrame = new JFrame("Hedgehogs At Work");
  
  /**
   * A static boolean for if the game is paused.
   * */      
  static boolean pausePressed;
  
  /**
   * This is the empty Pause class contructor. It sets the size and default operations
   * of the pause JFrame. The constructor makes it possible for the user to use
   * keyboard shortcuts.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>rootPane </b> This points to the JRootPane class.
   * <p>
   * <b>b </b> This is a reference varible that points to the Box class.
   * <p>
   * <b>resume </b> This points to the Action class.
   * <p>
   * <b>main </b> This points to the Action class.
   * <p>
   * <b>Parameters:</b>
   * <p>
   * e - This points to the ActionEvent class.
   * */      
  public Pause ()
  {    
    JRootPane rootPane = pauseFrame.getRootPane();
    pauseFrame.setSize(1010, 715);
    pauseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Action resume = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        resume();
      }
    };
    rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "resume");
    rootPane.getActionMap().put("resume", resume);
    
    Action main = new AbstractAction() {
      @Override public void actionPerformed(ActionEvent e) {
        backToMain();
      }
    };
    rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("BACK_SPACE"), "main");
    rootPane.getActionMap().put("main", main);
    
    displayPauseMenu();
  }
  
  /**
   * This is the method that displays the background of the main menu.
   * It has a mouse litener for what the user selects from the menu.
   * If they press resume, it returns to the game. If they press return to
   * main menu, they are back at the main menu.'
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>image </b> Of type BufferedImage, it holds the background BufferedImage.
   * <p>
   * <b>e </b> Of type MouseEvent, used when the mouseListener for mousePressed is added.
   * <p>
   * <b>relativeX </b> It is an int that has the temporary mouse click x coordinate.
   * <p>
   * <b>relativeY </b> It is an int that has the temporary mouse click y coordinate.
   * <p>
   * <b>picLabel </b> This stores the image.
   * <p>
   * <b>Parameters:</b>
   * <p>
   * e - This points to the MouseEvent class.
   * <b>Exceptions</b>:
   * <p>
   * <b>IOException e </b> This is an exception caught if the image cannot be found.
   * */      
  public void displayPauseMenu()
  {    
    try
    {
      BufferedImage image = ImageIO.read(new File("resources/Images/Backgrounds/Pause Menu.png"));
      JLabel picLabel = new JLabel(new ImageIcon(image));
      pauseFrame.getContentPane().add(picLabel);         
      pauseFrame.setVisible(true);
    }
    catch (IOException e)
    {
    }        
    
    pauseFrame.addMouseListener(new MouseAdapter() { 
      public void mousePressed(MouseEvent e) { 
        int relativeX = e.getX();
        int relativeY = e.getY();
        
        if (relativeX >= 314 && relativeX <= 690 && relativeY >= 242 && relativeY <= 388) 
        {
          resume();
        }
        else
        {
          if (relativeX >= 314 && relativeX <= 690 && relativeY >= 481 && relativeY <= 627) 
          {
            backToMain();
          }
        }
      } 
    }); 
  }
  
  /**
   * This method resumes the program.
   * */      
  public void resume()
  {
    pausePressed = false;          
    pauseFrame.setVisible(false);
    Driver.frame.setVisible(true);
    Driver.frame.revalidate();
    Driver.frame.repaint(); 
  }
  
  /**
   * This method allows the user to return to the main menu.
   * */   
  public void backToMain()
  {
    Driver.frame.getContentPane().removeAll();
    pauseFrame.setVisible(false);
    Driver.frame.setVisible(true);
    Countdown.timer.cancel();
    Countdown.timer.purge();
    pausePressed = false;
    Driver.changeScreens ("MainMenu");
    Driver.frame.revalidate();
    Driver.frame.repaint();  
  }
  
  /**
   * This method is the main method that runs the pause program.
   * It sets the output window to a double buffer.
   * 
   * @param args is a String [] for the main method parameters.
   */  
  public static void main(String [] args)
  {
    RepaintManager.currentManager(null).setDoubleBufferingEnabled(true);
    new Pause();        
  }  
}
