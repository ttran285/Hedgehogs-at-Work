import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * This class displays a screen after the user finishes each level.
 * Note that since javadoc is not picking up caught exceptions, we included the exceptions in the local
 * variables.
 * <p><b>Verison 1 (2 Hours):</b> The background and the setHighscores implemetation added, as well as the other methods.</p>
 * 
 * @author Aashmika Mali 
 * @version 1 05.26.16
 * Program name: LevelComplete.java
 */
public class LevelComplete extends JPanel
{
  /**
   * This is the constructor for level complete. Depending on the level(decided by the ifs), it will call the setHighscores
   * method in Highscores, to set the highscores before displaying to the user if their score
   * is high scores worthy. A mouse listener is added to see if the user pressed continue, if it was the last
   * level. 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>c </b> Of type Countdown, it creates a new countdown for the next level.
   * <p>
   * <b>e </b> Of type MouseEvent, used when the mouseListener for mousePressed is added.
   * <p>
   * <b>relativeX </b> It is an int that has the temporary mouse click x coordinate.
   * <p>
   * <b>relativeY </b> It is an int that has the temporary mouse click y coordinate.
   * <p>
   * <b>c </b> Of type certificate, it is created if the level completed was the third level.
   * <p>
   * <b>cont </b> This points to the Action class.
   * <p>
   * <b>Parameters</b>:
   * <p>
   * <b>e </b> Points to the ActionEvent class.
   */ 
  public LevelComplete ()
  {
    if (LevelSelect.levelChosen == 1)
    {
      Highscores.setHighscores (LevelSelect.lvlOneScore + LevelSelect.lvlOneScore2 + LevelSelect.lvlOneScore3, LevelSelect.userName);
    }
    else if (LevelSelect.levelChosen == 2)
    {
      Highscores.setHighscores (LevelSelect.lvlTwoScore + LevelSelect.lvlTwoScore2 + LevelSelect.lvlTwoScore3, LevelSelect.userName);
    }
    else
    {      
      Highscores.setHighscores (LevelSelect.lvlThreeScore + LevelSelect.lvlThreeScore2 + LevelSelect.lvlThreeScore3, LevelSelect.userName);
    }
    
    if (LevelSelect.levelChosen != 3)
    {
      Levels.atLevels = true;
      Countdown c = new Countdown();
    }
    else
    {
      Action cont = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
          Driver.frame.getContentPane().removeAll();
          Certificate c = new Certificate (Driver.frame);
        }
      };
      this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "continue");
      this.getActionMap().put("continue", cont);
      
      addMouseListener(new MouseAdapter() { 
        public void mousePressed(MouseEvent e) { 
          int relativeX = e.getX();
          int relativeY = e.getY();
          if (relativeX >= 740 && relativeX <= 975 && relativeY >= 570 && relativeY <= 655)
          {
            Driver.frame.getContentPane().removeAll();
            Certificate c = new Certificate (Driver.frame);
          }
        } 
      }); 
    }
  }
  /**
   * This is the paint method in the class. It draws the BufferedImage of the background on the frame,
   * and also draws the text with the user's score, name, and if they are on the high scores board.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>background </b> Of type BufferedImage, it is used when drawing the background.
   * <p>
   * <b>Parameters</b>:
   * <p>
   * <b>e </b> Of type MouseEvent, used when the mouseListener for mousePressed is added.
   * <p>
   * <b>Exceptions</b>:
   * <p>
   * <b>w </b> Of type IOException, it is caught when reading the image from a file.
   * 
   * @param g This is needed to draw the image onto the screen
   */ 
  public void paintComponent(Graphics g) 
  { 
    super.paintComponent(g); 
    BufferedImage background;
    try
    {      
      if (LevelSelect.levelChosen == 3)
      {
        background = ImageIO.read(new File("resources/Images/Backgrounds/Level3 Complete Background.png"));  
      }
      else
      {
        background = ImageIO.read(new File("resources/Images/Backgrounds/Level Complete Background.png"));  
      }
      g.setFont(new Font("Arial", Font.BOLD, 36));
      
      g.drawImage(background, 0, 0, null);
      
      if (LevelSelect.levelChosen == 4)
      {
        LevelSelect.levelChosen = 3;
      }
      g.drawString ("You have completed level " + LevelSelect.levelChosen + "!!!", 50, 150);
      g.drawString ("Good job " + Highscores.userName + ", your score is " + Highscores.userScore +  "!", 50, 250);
      if (Highscores.onHighscoresBoard)
      {
        g.drawString ("Awesome! Your score is on the ", 50, 350);
        g.drawString ("high scores board :D:D:D", 50, 450);
      }
    }
    catch (IOException w)
    {
    }
  }
}