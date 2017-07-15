import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.net.URL;
import javax.sound.sampled.*;
/**
 * This is the class that asks the user for their game options. 
 * They can choose either to play, get instructions, access highscores,
 * exit or turn on the sound.
 * Note that since javadoc is not picking up caught exceptions, we included the exceptions in the local
 * variables.
 * **************Code used from StackOverflow @JavaDude
 * @author Tiffany Tran Modified by Aashmika
 * @version 1 05.14.16
 * @version 2 may 30 added paint method
 * Program name: MainMenu.java
 */
public class MainMenu extends JPanel
{
  /**
   * This stores whether or not the music should be played.
   */
  static boolean shouldPlaySound = true;  
  
  /**
   * This is used to store the music.
   */
  static Clip music;
  
  /**
   * This is used to store whether or not the sound is on.
   */
  static boolean soundOn = true;
  
  /**
   * The constructor. It takes the parameter of the JFrame so the image can be outputted based on it.
   * The constructor makes it possible for the users to use keyboard controls. 
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>play </b> This points to the Action class.
   * <p>
   * <b>instructions </b> This points to the Action class.
   * <p>
   * <b>highscores </b> This points to the Action class.
   * <p>
   * <b>exit </b> This points to the Action class.
   * <p>
   * <b>music </b> This points to the Action class.
   * <p>
   * <b>Parameters:</b>
   * <p>
   * e - This points to the ActionEvent class.
   * */
  public MainMenu ()
  {
    LevelSelect.userName = "";
    
    Action play = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        Driver.changeScreens("LevelSelect");
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"), "1");
    this.getActionMap().put("1", play);
    
    Action instructions = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        Driver.changeScreens("Instructions");
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"), "2");
    this.getActionMap().put("2", instructions);
    
    Action highscores = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        Driver.changeScreens("Highscores");
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"), "3");
    this.getActionMap().put("3", highscores);
    
    Action exit = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
    this.getActionMap().put("escape", exit);
    
    Action music = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        doMusic();
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), "music");
    this.getActionMap().put("music", music);
    
    addMouseListener(new MouseAdapter() { 
      public void mousePressed(MouseEvent e) { 
        
        int relativeX = e.getX();
        int relativeY = e.getY();
        
        if (relativeX >= 335 && relativeX <= 664 && relativeY >= 171 && relativeY <= 270)
        {
          Driver.changeScreens("LevelSelect");
        }
        else if (relativeX >= 335 && relativeX <= 664 && relativeY >= 328 && relativeY <= 427)
        {
          Driver.changeScreens("Instructions");
        }
        else if (relativeX >= 335 && relativeX <= 664 && relativeY >= 488 && relativeY <= 587)
        {
          Driver.changeScreens("Highscores");
        }
        else if (relativeX >= 19 && relativeX <= 103 && relativeY >= 586 && relativeY <= 664)
        {
          System.exit(0);
        }
        else if (relativeX >= 462 && relativeX <= 514 && relativeY >= 612 && relativeY <= 661)
        {
          openChm();
        }      
        else
        {
          if (relativeX >= 889 && relativeX <= 974 && relativeY >= 587 && relativeY <= 665)
          {
            doMusic();
          }
        }
      } 
    }); 
  }
  
  /**
   * This method makes it possible for the sound to be played by calling
   * the music method.
   * */
  public void doMusic()
  {
    music ();
    shouldPlaySound = !shouldPlaySound;
    if (!soundOn)
    {
      soundOn = true;
    }
    else
    {
      soundOn = false;
    }
    repaint();
  }
  
  /**
   * This method opens the chm help file. It has a try catch method to prevent
   * the program from crashing when opening the chm file.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>file </b> This stores the chm file.
   * <p>
   * <b>Exceptions</b>:
   * <p>
   * <b>Exception ex </b> It prevents the program from crashing when getting the chm.
   * */
  public static void openChm()
  {
    File file = new File ("resources/Hedgehogs at Work Help File/File.chm");
    try
    {
      Runtime.getRuntime().exec ("hh mk:@MSITStore:" + file.getAbsolutePath () + "::/AboutFile.htm");
    }
    catch (Exception ex)
    {
    }
  }
  
  /**
   * This method opens opens and displays the background image onto the screen.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>tracker </b> This is a reference variable that points to the MediaTracker 
   * <p>
   * <b>background </b> This stores the image that is displayed.
   * <p>
   * <b>background2 </b> This stores the image that is displayed.
   * <p>
   * <b>Exceptions</b>:
   * <p>
   * <b>InterruptedException e </b> This is an exception caught when using a MediaTracker.
   * 
   * @param graphics This is needed to draw the image onto the screen
   * */
  public void paint(Graphics graphics)
  {    
    MediaTracker tracker = new MediaTracker (new Frame ());    
    Image background = Toolkit.getDefaultToolkit ().getImage ("resources/Images/Backgrounds/MainMenu.png");
    Image background2 = Toolkit.getDefaultToolkit ().getImage ("resources/Images/Backgrounds/Main Menu no sound.png");
    tracker.addImage (background, 0);        
    tracker.addImage (background2, 1);        
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
    
    if (!soundOn)
    {
      graphics.drawImage (background2, 0, 0, null); 
    }
    else
    {
      graphics.drawImage (background, 0, 0, null); 
    }
  }
  
  /**
   * This method gets and plays/stops the background music. The try catch block is used
   * to prevent the program from crashing if there are any issues during the getting of
   * the music.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>url </b> This stores the music.
   * <p>
   * <b>audioIn </b> This points to the AudioInputStream class.
   * <p>
   * <b>background2 </b> This stores the image that is displayed.
   * <p>
   * <b>Exceptions</b>:
   * <p>
   * <b>UnsupportedAudioFileException e </b> This is an exception caught if the music file is unsupported.
   * <p>
   * <b>LineUnavailableException r </b> This is an exception caught if the line is unavaliable.
   * <p>
   * <b>IOException error </b> This is an exception caught if the music file cannot be found.
   * */
  public void music() 
  {       
    if (shouldPlaySound)
    {     
      try
      {
        URL url = this.getClass().getClassLoader().getResource("resources/Music/Factory Background Music.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
        music = AudioSystem.getClip();
        music.open(audioIn);
        music.loop(music.LOOP_CONTINUOUSLY);
        music.start();
      }
      catch (UnsupportedAudioFileException e)
      {
      }
      catch (LineUnavailableException r)
      {
      }
      catch(IOException error)
      {
      }     
    }
    else
    {     
      music.stop();
    }
  }  
}