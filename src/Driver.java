import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * This is the basic driver class, for the HedgeHogs at Work game. It uses a static method for changing classes through
 * gameplay.
 * Note that since javadoc is not picking up caught exceptions, we included the exceptions in the local
 * variables.
 * <p><b>Verison 1 (1.5 Hours):</b> Basic driver class used to call other classes. Done by Aashmika.</p>
 * <p><b>Verison 2 (3.5 Hours):</b> Implementing the permanent JFrame and changeScreen methods. Done by Tiffany.</p>
 * 
 * @author Aashmika Mali modified by Tiffany Tran
 * @version 1 05.10.16
 * @version 2 05.15.16
 * Program name: Driver.java
 */
public class Driver extends JFrame
{  
  /**
   * Creates and saves the main JFrame in a variable named frame.
   * 
   * */    
  static JFrame frame = new JFrame("Hedgehogs At Work"); 
  
  /**
   * The Driver Construtor with no parameters. This creates
   * the JFrame and calls the SplashScreen. It then goes to the
   * main menu.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>rootPane </b> Points to the JRootPane class.
   * <p>
   * <b>Exceptions</b>:
   * <p>
   * <b>e </b> Of type Exception used when using the Thread.sleep delay.
   * */  
  public Driver()
  {
    Highscores.getInformation ();
    frame.setSize(1005, 708);
    frame.setResizable (false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    frame.getContentPane().add(new SplashScreen());
    frame.setVisible(true);
    try
    {
      Thread.sleep (5000); 
    }
    catch (Exception e)
    {
    }
    frame.getContentPane().removeAll();    
    frame.getContentPane().add(new MainMenu());
    frame.setVisible(true);
    
    JRootPane rootPane = frame.getRootPane();
    Action chm = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        MainMenu.openChm();
      }
    };
    rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), "F1");
    rootPane.getActionMap().put("F1", chm);
  }
  
  /**
   * The static changescreens method.
   * Allows a new class to be called and implemented to the frame during gamelplay.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>panel </b> This creates of a class with the name className passed in by a string.
   * <p>
   * <b>newScreen </b> This creates a JPanel for the new class to be displayed on. 
   * <p>
   * <b>e </b> Of type Exception, it is used when creating the new classes.
   * 
   * @param className String is a string that passes the class name that the program wants to change to.
   * */  
  public static void changeScreens(String className)
  {    
    try
    {
      Class<?> panel = Class.forName(className);
      JPanel newScreen = (JPanel) panel.newInstance();
      
      frame.getContentPane().removeAll();
      frame.getContentPane().add(newScreen);
      frame.revalidate();
      frame.setResizable (false);
    }
    catch (Exception e)
    {
    }
  }
  
  /**
   * This method is the main method that runs the program.
   * It sets the output window to a double buffer.
   * 
   * @param args is a String [] for the main method parameters.
   */  
  public static void main(String [] args)
  {
    RepaintManager.currentManager(null).setDoubleBufferingEnabled(true);
    new Driver();
  }  
}