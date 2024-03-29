import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;

/** 
 * This is the third activity of our game, in which the user is able
 * to drag boxes into the bin equal to the answer of a math equation.
 * They can remove boxes by pressing remove, and check their number by 
 * pressing done. Every equation they answer correctly (when they press 
 * done) awards them with one point, which can be awarded to them for 
 * the duration of the time limit. The user can also pause while doing
 * this activity, and can return to the main screen if they wish, or resume.
 * <p><b>Version 1 (4 Hours):</b> All Methods were created in this for the mouse ware and equations. Done by Aashmika.</p>
 * <p><b>Version 2 (2 Hour):</b> Methods were modified with implementations of all three levels.Done by Aashmika.</p>
 * <p><b>Version 3 (1.5 Hours):</b> Implementing the pause option. Done by Tiffany.</p>
 * 
 * @author Aashmika Mali Modified by Tiffany
 * @version 1 05.28.16
 * @version 2 05.29.16
 * @version 3 05.09.31
 * Program name: Activity3.java
 */
public  class Activity3 extends JPanel 
{
  /**
   * These are the ints that hold the x and y locaton of the image.
   * */
  private int imageX, imageY;
  
  /**
   * The number of the box we're dealing with.
   * */
  int boxNum;
  
  /**
   * The int array for the temporary X coordinates.
   * */
  int [] tempX = new int [12]; 
  /**
   * The int array for the temporary Y coordinates.
   * */  
  int [] tempY = new int [12];
  
  /**
   * The counter for the number of boxes they place in the truck.
   * */  
  int counter = 0;
  /**
   * The int varaibles for the different ints in the math equations
   * */
  int num1, num2, answer;
  /**
   * The boolean for if the counter is the correct answer.
   * */
  boolean correct = false;
  /**
   * A boolean array that turns true for a certain box number if that box has been placed into the truck.
   * */
  boolean [] correctPos = new boolean [6];
  
  
  /**
   * An int array for the coordinates of the boxes (each box has 2 coordinates).
   * */
  int [] coordinates = {40, 270, 200, 270, 360, 270, 40, 370, 200, 370, 360, 370};
  
  /**
   * This method sets the numbers used in the equation for the current truckload.
   * It uses if statements to check which level the game is at.
   **/ 
  public void setNums ()
  {
    if (LevelSelect.levelChosen == 1)
    {
      num1 = (int)(Math.random () * 3 + 1);
      num2 = (int)(Math.random () * 3 + 1);
      answer = num1 + num2;
    }
    else if (LevelSelect.levelChosen == 2)
    {
      num1 = (int)(Math.random () * 5 + 1);
      num2 = (int)(Math.random () * 5 + 1);
      answer = num1 + num2;
    }
    else
    {      
      num1 = (int)(Math.random () * 10 + 2);
      num2 = (int)(Math.random () * (num1 - 1) + 1);
      answer = num1 - num2;
    }
    
  }
  /**
   * This no paramater constructor of the Activity3 class deals mainly
   * with the mouse ware for the class. This means that it has abstract 
   * methods for MouseListener, such as mouseMoved, released, dragged 
   * and clicked. Using these methods, we are able to draw the boxes
   * in different places based on the user's mouse commands. The constructor 
   * also makes it possible to pause the game using keyboard shortcuts.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>layout </b> Of type SpringLayout, it is used when putting constraints on graphical objects.
   * <p>
   * <b>c </b> Of type Countdown, it creates a new countdown for the activity.
   * <p>
   * <b>h</b> Of type Hedgehog, it creates a new Hedgehog for the activity.
   * <p>
   * <b>b</b> Of type Box, it creates a new Box for the activity.
   * <p>
   * <b>temp1 </b> It saves the temporary mouse click x coordinate.
   * <p>
   * <b>temp2 </b> It saves the temporary mouse click y coordinate.
   * <p>
   * <b>p</b> Of type pause, it is used to activate the pause class.
   * <p>
   * <b>pause </b> This points to the Action class.
   * <p>
   * <b>Parameters:</b>
   * <p>
   * e - This points to the ActionEvent class.
   * <p>
   * e - This points to the MouseEvent class.
   * <p>
   * <b>Exceptions</b>:
   * <p>
   * <b>PrinterException ex </b> It is the caught exception when calling the print method.
   * 
   **/
  public Activity3 ()
  {    
    SpringLayout layout = new SpringLayout();
    setLayout(layout); 
    
    layout.putConstraint (SpringLayout.NORTH, Countdown.time, 10, SpringLayout.NORTH, Activity3.this); 
    layout.putConstraint (SpringLayout.WEST, Countdown.time, 910, SpringLayout.WEST, Activity3.this);
    
    add(Countdown.time);
    setVisible(true);
    
    Countdown c = new Countdown();
    Hedgehog h = new Hedgehog();
    Box b = new Box();
    
    setNums();
    repaint ();
    
    Action pause = new AbstractAction() { 
      @Override public void actionPerformed(ActionEvent e) {
        Pause.pausePressed = true;
        Driver.frame.setVisible(false);
        Pause p = new Pause();
      }
    };
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("P"), "pause");
    this.getActionMap().put("pause", pause);
    
    addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent e){
        
        int temp1, temp2;
        temp1 = e.getX();
        temp2 = e.getY();
        
        if (temp1 >= 18 && temp1 <= 93 && temp2 >= 22 && temp2 <= 92)
        {
          Pause.pausePressed = true;
          Driver.frame.setVisible(false);
          Pause p = new Pause();
        }
        if (temp1 >= coordinates[0] && temp1 <= coordinates[0] + 120 && temp2 >= coordinates[1] && temp2 <= coordinates[1] + 70) //First box
        {
          boxNum = 1;
        }
        else if (temp1 >= coordinates[2] && temp1 <= coordinates[2] + 120 && temp2 >= coordinates[3] && temp2 <= coordinates[3] + 70) //Second box
        {
          boxNum = 2;            
        }
        else if (temp1 >= coordinates[4] && temp1 <= coordinates[4] + 120 && temp2 >= coordinates[5] && temp2 <= coordinates[5] + 70) //Third box
        {
          boxNum = 3;            
        }
        else if (temp1 >= coordinates[6] && temp1 <= coordinates[6] + 120 && temp2 >= coordinates[7] && temp2 <= coordinates[7] + 70) //Fourth box
        {
          boxNum = 4;             
        }
        else if (temp1 >= coordinates[8] && temp1 <= coordinates[8] + 120 && temp2 >= coordinates[9] && temp2 <= coordinates[9] + 70) //Fifth box
        {
          boxNum = 5;            
        }
        else if (temp1 >= 580 && temp1 <= 780 && temp2 >= 115 && temp2 <= 195) //Done button
        {
          if (counter == answer)
          {
            counter = 0;
            correct = true;
            if (LevelSelect.levelChosen == 1)
            {
              LevelSelect.lvlOneScore3++;
            }
            else if (LevelSelect.levelChosen == 2)
            {
              LevelSelect.lvlTwoScore3++;
            }
            else
            {
              LevelSelect.lvlThreeScore3++;
            } 
            correct = true;
            setNums();
          }
          else
          {
            JOptionPane.showMessageDialog (null,
                                           "That was not the correct answer :( Keep trying!", "OOPS!", JOptionPane.ERROR_MESSAGE);
          }
        }
        else if (temp1 >= 795 && temp1 <= 995 && temp2 >= 115 && temp2 <= 195) 
        {
          if (counter > 0)
          {
            counter--;
          }
        }
        else if (temp1 >= coordinates[10] && temp1 <= coordinates[10] + 120 && temp2 >= coordinates[11] && temp2 <= coordinates[11] + 70) 
        {
          boxNum = 6;          
        }
        else
        {
          boxNum = 0;
        }
        repaint();
      }
      
      public void mouseReleased(MouseEvent e) {
        if (boxNum == 1)
        {
          tempX[0] = e.getX();
          tempY[0] = e.getY();
        }
        else if (boxNum == 2)
        {
          tempX[1] = e.getX();
          tempY[1] = e.getY();
        }
        else if (boxNum == 3)
        {
          tempX[2] = e.getX();
          tempY[2] = e.getY();
        }
        else if (boxNum == 4)
        {
          tempX[3] = e.getX();
          tempY[3] = e.getY();
        }
        else if (boxNum == 5)
        {
          tempX[4] = e.getX();
          tempY[4] = e.getY();
        }
        else 
        {
          if (boxNum == 6)
          {
            tempX[5] = e.getX();
            tempY[5] = e.getY();
          }
        }
        
        if ((e.getX() >= 520 && e.getX() <= 1005 && e.getY() >= 210 && e.getY() <= 510) && (boxNum != 0) && !correctPos[boxNum-1]) 
        {
          correctPos[boxNum-1] = true;    
          boxNum = 0;  
          counter++;
        }                 
        
        repaint();
      }
    });
    
    addMouseMotionListener(new MouseMotionAdapter() {      
      public void mouseMoved(MouseEvent e) {
      }      
      
      public void mouseDragged(MouseEvent e) {
        if (e.getX() >= 0 && e.getX() <= 876 && e.getY() >= 0 && e.getY() <= 608) 
        {
          if (boxNum == 1 && !correctPos[0])
          {
            coordinates[0] = e.getX();
            coordinates[1] = e.getY();
          }
          else if (boxNum == 2 && !correctPos[1])
          {
            coordinates[2] = e.getX();
            coordinates[3] = e.getY();
          }     
          else if (boxNum == 3 && !correctPos[2])
          {
            coordinates[4] = e.getX();
            coordinates[5] = e.getY();
          }  
          else if (boxNum == 4 && !correctPos[3])
          {
            coordinates[6] = e.getX();
            coordinates[7] = e.getY();
          }  
          else if (boxNum == 5 && !correctPos[4])
          {
            coordinates[8] = e.getX();
            coordinates[9] = e.getY();
          }  
          else 
          {
            if (boxNum == 6 && !correctPos[5])
            {
              coordinates[10] = e.getX();
              coordinates[11] = e.getY();
            }  
          }
          repaint();
        }
      }
    });    
  }
  
  /**
   * This  is the paint method for the class. It deals with drawing the boxes,
   * background, equation and other screen graohics. It uses a MediaTracker for
   * the background, and autoReplenishes the background when all the boxes have 
   * been dragged into the truck.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>tracker </b> Of type MediaTracker, it is used when drawing the background.
   * <p>
   * <b>background </b> Of type Image, it is used to draw on the frame.
   * <p>
   * <b>x </b> An int used in for loops as an incrementing variable.
   * 
   **/
  public void paintComponent(Graphics g) 
  {  
    g.setFont(new Font("Arial", Font.BOLD, 35));
    MediaTracker tracker = new MediaTracker (new Frame ());    
    Image background = Toolkit.getDefaultToolkit ().getImage ("resources/Images/Activity Backgrounds/Activity Three - Level" + LevelSelect.levelChosen + ".png");
    tracker.addImage (background, 0);        
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
    
    g.drawImage (background, 0, 0, null); 
    g.drawString ("The truck has " + counter + " boxes.", 595, 275);
    if (correct)
    {
      g.setFont(new Font("Arial", Font.BOLD, 30));
      g.setColor (Color.white);
      g.drawString ("CORRECT!", 475, 640);
    }
    g.setColor (Color.magenta);
    g.setFont(new Font("Arial", Font.BOLD, 40));
    g.drawString ("Fill the truck! What is...", 50, 550);
    g.setFont(new Font("Arial", Font.BOLD, 70));
    if (LevelSelect.levelChosen == 1)
    {
      g.setColor (Color.black);
      g.drawString ("" + LevelSelect.lvlOneScore3, 515, 75);
      g.setColor (Color.red);
      g.drawString (num1 + " + " + num2 + " = " + "?", 50, 620);
    }
    else if (LevelSelect.levelChosen == 2)
    {
      g.setColor (Color.black);
      g.drawString ("" + LevelSelect.lvlTwoScore3, 515, 75);
      g.setColor (Color.red);
      g.drawString (num1 + " + " + num2 + " = " + "?", 50, 620);
    }
    else
    {
      g.setColor (Color.black);
      g.drawString ("" + LevelSelect.lvlThreeScore3, 515, 75);
      g.setColor (Color.red);
      g.drawString (num1 + " - " + num2 + " = " + "?", 50, 620);
    } 
    
    if (boxNum == 1 && !correctPos[0])
    {
      g.drawImage(Box.boxes.get(0), coordinates[0], coordinates[1], 120, 70, this);
    }
    else if (boxNum == 2 && !correctPos[1])
    {      
      g.drawImage(Box.boxes.get(1), coordinates[2], coordinates[3], 120, 70, this);
    }
    else if (boxNum == 3 && !correctPos[2])
    {      
      g.drawImage(Box.boxes.get(2), coordinates[4], coordinates[5], 120, 70, this);
    }
    else if (boxNum == 4 && !correctPos[3])
    {      
      g.drawImage(Box.boxes.get(3), coordinates[6], coordinates[7], 120, 70, this);
    }
    else if (boxNum == 5 && !correctPos[4])
    {      
      g.drawImage(Box.boxes.get(4), coordinates[8], coordinates[9], 120, 70, this);
    }
    else
    {
      if (boxNum == 6 && !correctPos[5])
      {      
        g.drawImage(Box.boxes.get(5), coordinates[10], coordinates[11], 120, 70, this);
      }
    }
    
    int counter = 0;
    
    for (int x = 0; x < Box.boxes.size(); x++) 
    {
      if (!correctPos[x])
      {
        g.drawImage(Box.boxes.get(x), coordinates[x*2], coordinates[x*2+1], 120, 70, this);
        counter++;
      }
    }
    
    if (counter == 0)
    {
      for (int x = 0; x < 6; x++)
      {
        correctPos[x] = false;
      }
      
      coordinates[0] = 40;
      coordinates[1] = 270;
      coordinates[2] = 200;
      coordinates[3] = 270;
      coordinates[4] = 360;
      coordinates[5] = 270;
      coordinates[6] = 40;
      coordinates[7] = 370;
      coordinates[8] = 200;
      coordinates[9] = 370;
      coordinates[10] = 360;
      coordinates[11] = 370;
      
      repaint();    
    }
    
    this.revalidate ();        
  }  
}