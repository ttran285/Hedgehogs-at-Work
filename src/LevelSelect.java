import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/** 
 * This is the class that asks the user which level they would like to 
 * start out with. They can choose levels 1-3, or they can choose to exit.
 * Note that since javadoc is not picking up caught exceptions, we included the exceptions in the local
 * variables.
 * <p><b>Verison 1 (2 Hours):</b> The background and mouse listener is added. Done by Tiffany</p>
 * <p><b>Verison 2 (2 Hours):</b> The enter userName method was added. Done by Aashmika</p>
 * 
 * @author Tiffany Tran modified by Aashmika Mali
 * @version 1 05.14.16
 * Program name: LevelSelect.java
 */
public class LevelSelect extends JPanel
{
  /**
   * Saves the name of the user to be put on the certificate.
   * 
   * */  
  static String userName;
  /**
   * Saves the level the user chose.
   * 
   * */  
  static int levelChosen;
  
  /**
   * Saves the activity the user is on.
   * 
   * */  
  static int activityNum;
  
  /**
   * This is used to store the level three activity three score.
   */
  static int lvlThreeScore3;
  /**
   * This is used to store the level three activity two score.
   */
  static int lvlThreeScore2;
  /**
   * This is used to store the level three activity one score.
   */
  static int lvlThreeScore;
  /**
   * This is used to store the level two activity three score.
   */
  static int lvlTwoScore3;
  /**
   * This is used to store the level two activity two score.
   */
  static int lvlTwoScore2;
  /**
   * This is used to store the level two activity one score.
   */
  static int lvlTwoScore;
  /**
   * This is used to store the level one activity three score.
   */
  static int lvlOneScore3;
  /**
   * This is used to store the level one activity two score.
   */
  static int lvlOneScore2;
  /**
   * This is used to store the level one activity one score.
   */
  static int lvlOneScore;
  
  /**
   * The constructor. It takes the parameter of the JFrame so the image can be outputted based on it.
   * It also resets all the scores, dispays the background, and uses mouse listener. The try catch block
   * is used to prevent the program from crashing when getting the image. This constructor makes it 
   * possible for users to use keyboard shortcuts.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>lvl1 </b> This points to the Action class.
   * <p>
   * <b>lvl2 </b> This points to the Action class.
   * <p>
   * <b>lvl3 </b> This points to the Action class.
   * <p>
   * <b>back </b> This points to the Action class.
   * <p>
   * <b>Parameters:</b>
   * <p>
   * e - This points to the ActionEvent class.
   * <p>
   * <b>Exceptions</b>:
   * <p>
   * <b>IOException e </b> It is the caught exception when getting the image.
   * */
  public LevelSelect ()
  {   
    Action lvl1 = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        levelChosen = 1;
        getUserName ("Enter Name", "Hello! Please enter your name.", new JFrame ());  
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"), "1");
    this.getActionMap().put("1", lvl1);
    
    Action lvl2 = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        levelChosen = 2;
        getUserName ("Enter Name", "Hello! Please enter your name.", new JFrame ());  
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"), "2");
    this.getActionMap().put("2", lvl2);
    
    Action lvl3 = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        levelChosen = 3;
        getUserName ("Enter Name", "Hello! Please enter your name.", new JFrame ());  
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"), "3");
    this.getActionMap().put("3", lvl3);
    
    Action back = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        Driver.changeScreens("MainMenu"); 
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("BACK_SPACE"), "back");
    this.getActionMap().put("back", back);
    
    lvlOneScore = 0;
    lvlOneScore2 = 0;
    lvlOneScore3 = 0;
    lvlTwoScore = 0;
    lvlTwoScore2 = 0;
    lvlTwoScore3 = 0;
    lvlThreeScore = 0;
    lvlThreeScore2 = 0;
    lvlThreeScore3 = 0;
    
    try
    {
      BufferedImage image = ImageIO.read(new File("resources/Images/Backgrounds/LevelSelect.png"));
      JLabel picLabel = new JLabel(new ImageIcon(image));
      add(picLabel);
    }
    catch (IOException e)
    {
    }    
    
    addMouseListener(new MouseAdapter() { 
      public void mousePressed(MouseEvent e) { 
        int relativeX = e.getX();
        int relativeY = e.getY();
        
        if (relativeX >= 341 && relativeX <= 677 && relativeY >= 139 && relativeY <= 227)
        {
          levelChosen = 1;
          getUserName ("Enter Name", "Hello! Please enter your name.", new JFrame ());   
          
        }
        else if (relativeX >= 289 && relativeX <= 634 && relativeY >= 243 && relativeY <= 330)
        {
          levelChosen = 2;
          getUserName ("Enter Name", "Hello! Please enter your name.", new JFrame ());   
        }
        else if (relativeX >= 361 && relativeX <= 685 && relativeY >= 354 && relativeY <= 433) 
        {
          levelChosen = 3;
          getUserName ("Enter Name", "Hello! Please enter your name.", new JFrame ());   
        }
        else
        {
          if (relativeX >= 335 && relativeX <= 627 && relativeY >= 443 && relativeY <= 513)
          {
            Driver.changeScreens("MainMenu");
          }
        }
      } 
    }); 
  }
  
  /**
   * This method creates a dialog window and asks the user to enter their username. 
   * This username is displayed on the ceritificate and on the highscores board.
   * The user can only print the certificate when they reach level 3
   * The ActionListener class inside the method is used in case of a button being pressed. It has the abstract
   * class actionPerformed.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>myDialog</b> This creates an instance of the JDialog class, and is used to ask the user for their name.
   * <p>
   * <b>inputField</b> This creates an instance of the JTextfield class and is used for the user to enter their name in.
   * <p>
   * <b>labelMessage</b> This creates an instance of the JLabel, and is used to tell the user to enter their name.
   * <p>
   * <b>enterName</b> This creates an instance of the JButton class, and is pressed by the user when they have entered
   * their name.
   * 
   * @param title is a String for the JDailog box
   * @param message is a String for the JLabel in the box.
   * @param frame is a JFrame passed in from the driver.
   * 
   * <p>
   * <b>Parameters:</b>
   * <p>
   * e - This points to the ActionEvent class.
   * */  
  public void getUserName(String title, String message, JFrame frame)
  {
    JDialog myDialog = new JDialog (frame, title);
    
    JTextField inputField = new JTextField (20);
    
    JLabel labelMessage = new JLabel (message);
    
    labelMessage.setFont (new Font ("Arial", Font.PLAIN, 16));
    
    myDialog.add (labelMessage);
    
    myDialog.add (inputField);
    
    myDialog.setResizable(false);
    
    myDialog.setSize (500, 100);
    
    myDialog.setLayout (new FlowLayout ());
    
    JButton enterName = new JButton ("Enter");
    
    enterName.addActionListener (new ActionListener ( )
                                   {      
      
      public void actionPerformed (ActionEvent e)
      {
        userName = inputField.getText ();
        if (userName.length() > 0 && userName.length() < 15)
        {          
          myDialog.dispose ();
          frame.dispose();
          activityNum = 0;
          Driver.changeScreens("Levels");
        }
        else
        {
          inputField.setText ("The name must be 1-14 characters.");
        }
      }      
    }                                 
    ) ;   
    
    myDialog.add (enterName);
    
    myDialog.setLocationRelativeTo (frame);
    
    myDialog.setVisible (true);
    
  }
}