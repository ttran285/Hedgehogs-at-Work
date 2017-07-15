import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/** 
 * This class displays the images for the levels, before the user plays it.
 * Note that since javadoc is not picking up caught exceptions, we included the exceptions in the local
 * variables.
 * <p><b>Verison 1 (1 Hour):</b> Implementing and creating the levels class.</p>
 * 
 * @author Tiffany Tran
 * @version 1 05.29.16
 * Program name: Levels.java
 */
public class Levels extends JPanel
{
  /**
   * Says if the user is at the levels.
   * 
   * */  
  static boolean atLevels = false;
  
  /**
   * Empty levels contructor that creates new countdown.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>c </b> Countdown c for the delay for this display.
   * */  
  public Levels()
  {    
    atLevels = true;
    Countdown c = new Countdown();
  }
  
  /**
   * This method reads in images and outputs them onto the screen.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>tracker </b> This is a reference variable that points to the MediaTracker 
   * <p>
   * <b>image1 </b> This stores the first level sign.
   * <p>
   * <b>image2 </b> This stores the second level sign.
   * <p>
   * <b>image3 </b> This stores the third level sign.
   * <p>
   * <b>Exceptions</b>:
   * <p>
   * <b>InterruptedException e </b> This is the exception caught when reading an image from a file.
   * 
   * @param graphics This is needed to draw the image onto the screen
   * */  
  public void paintComponent(Graphics graphics)
  {    
    MediaTracker tracker = new MediaTracker (new Frame ());    
    Image image1 = Toolkit.getDefaultToolkit ().getImage ("resources/Images/Backgrounds/Level One Sign.png");
    Image image2 = Toolkit.getDefaultToolkit ().getImage ("resources/Images/Backgrounds/Level Two Sign.png");
    Image image3 = Toolkit.getDefaultToolkit ().getImage ("resources/Images/Backgrounds/Level Three Sign.png");
    
    tracker.addImage (image1, 0);        
    tracker.addImage (image2, 1);   
    tracker.addImage (image3, 2);   
    try
    {
      tracker.waitForAll ();
    }
    catch (InterruptedException e)
    {
    }
    if (tracker.isErrorAny ())
    {
      return;
    }
    
    if (LevelSelect.levelChosen == 1)
    {
      graphics.drawImage (image1, 0, 0, 1000, 675, null);
    }
    else if (LevelSelect.levelChosen == 2)
    {
      graphics.drawImage (image2, 0, 0, 1000, 675, null);
    }
    else
    {
      graphics.drawImage (image3, 0, 0, 1000, 675, null);
    }
    
    atLevels = false;
  }
}