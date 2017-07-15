import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * This class displays information on how to play this game. This class contains several next and previous 
 * buttons to allow the user to switch between the different instruction screens.
 * Note that since javadoc is not picking up caught exceptions, we included the exceptions in the local
 * variables.
 * <p><b>Verison 1 (1 Hour):</b> All the methods were created.</p>
 * <p><b>Verison 2 (0.5 Hours):</b> The paint method was added and the constructor was modified.</p>
 * @author Tiffany Tran
 * @version 1 05.15.16
 * @version 2 06.03.16
 * Program name: Instructions.java
 */

public class Instructions extends JPanel
{
  /**
   * This stores the instruction png.
   * */
  Image instructions;
  
  /**
   * This is used to keep track of the instruction panel the user is currently on.
   * */
  int counter = 1;
  
  /**
   * This is the class constructor. It is used to check where the user has pressed and perform a 
   * function depending on what the user chose to do.
   * 
   * <p>
   * Within this constructor, a mousePressed method is there to implement MouseEvent. This method
   * checks to see where the user has pressed. If they pressed the area in which the previous button is
   * located, the previous instruction panel will be shown. If they pressed the area in which the next
   * button is located, the next instruction panel will be shown. If they pressed the main menu button
   * located on the last instruction panel, they will be brought back to the main menu. This constructor
   * makes it possible for the user to use keyboard shortcuts.
   * <p>
   * <b>Parameters:</b>
   * <p>
   * e - This points to the ActionEvent class.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>relativeX </b> This stores the x coordinate of where the user has pressed.
   * <p>
   * <b>relativeY </b> This stores the y coordinate of where the user has pressed.
   * <p>
   * <b>back </b> This points to the Action class.
   * <p>
   * <b>next </b> This points to the Action class.
   * <p>
   * <b>previous </b> This points to the Action class.
   * <p>
   * <b>Exceptions</b>:
   * <p>
   * <b>IOException e </b> This is the exception caught when reading an image from a file.
   */ 
  public Instructions()
  {    
    Action back = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        if (counter == 6)
        {
          Driver.changeScreens("MainMenu"); 
        }
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("BACK_SPACE"), "back");
    this.getActionMap().put("back", back);
    
    Action next = new AbstractAction() {
      @Override public void actionPerformed(ActionEvent e) {
        if (counter != 6)
        {
          counter++;
          repaint();
        }
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "next");
    this.getActionMap().put("next", next);
    
    
    Action previous = new AbstractAction() {
      @Override public void actionPerformed(ActionEvent e) {
        if (counter != 1)
        {
          counter--;
          repaint();
        }
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "previous");
    this.getActionMap().put("previous", previous);
    
    
    addMouseListener(new MouseAdapter() { 
      public void mousePressed(MouseEvent e) { 
        
        int relativeX = e.getX();
        int relativeY = e.getY();
        
        if (relativeX >= 749 && relativeX <= 981 && relativeY >= 584 && relativeY <= 659) //Next
        {
          if (counter != 6)
          {
            counter++;
            repaint();
          }
          else
          {
            Driver.changeScreens("MainMenu");
          }
        }
        else
        {
          if (relativeX >= 19 && relativeX <= 251 && relativeY >= 584 && relativeY <= 659) //Previous
          {
            if (counter != 1)
            {
              counter--;
              repaint();
            }
          }
        }
      } 
    }); 
  }
  
  /**
   * This method reads in the instruction backgrounds based on the information gathered from the
   * constructor and displays it onto the screen. 
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>tracker </b> This is a reference variable that points to the MediaTracker class.
   * <p>
   * <b>instructions </b> This stores the image that is displayed.
   * 
   * @param g This is needed to draw the image onto the screen
   */ 
  public void paintComponent (Graphics g)
  {
    MediaTracker tracker = new MediaTracker (new Frame ());    
    instructions = Toolkit.getDefaultToolkit ().getImage ("resources/Images/Backgrounds/Instructions" + counter + ".png");
    tracker.addImage (instructions, 0); 
    g.drawImage (instructions, 0, 0, this);
  }
}