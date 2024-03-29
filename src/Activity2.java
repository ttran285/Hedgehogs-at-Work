import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/** 
 * This class has all of the processing of the second activity of our game. It makes 
 * draging and dropping the hedgehogs possible and also checks to make sure the right 
 * sized hedgehog is being dropped into it's designated box. If it has, it will
 * update the user's score and display an encouraging message each time they have
 * successfully placed a group of hedgehogs into their designated boxes.
 * 
 * <p><b>Version 1 (5 Hours):</b> Methods were added with implementations of all three levels.</p>
 * 
 * @author Tiffany Tran 
 * 
 * @version 1 05.24.16
 * Program name: Activity2.java
 */

public class Activity2 extends JPanel 
{
  
  /**
   * The number of the hedgehog we're dealing with.
   * */
  int hogNum;
  
  /**
   * This stores a phrase of encouragement that is shown to the user.
   * */
  int phrase = 0;
  
  /**
   * This stores the temporary X coordinates for the hedgehogs.
   * */
  int [] tempX = new int [12]; 
  
  /**
   * This stores the temporary Y for the hedgehogs.
   * */
  int [] tempY = new int [12];
  
  /**
   * This stores whether or not a certain hedgehog has already been put into the correct box.
   * */
  boolean [] correctPos = new boolean [6];
  
  /**
   * This stores the starting coordinates for all of the hedgehogs.
   * */
  int [] coordinates = {40, 240, 200, 240, 360, 240, 520, 240, 670, 240, 820, 240}; 
  
  /**
   * A reference variable used to point to the SpringLayout class.
   * */
  SpringLayout layout = new SpringLayout();
  
  /**
   * This is the class constructor. It sets the layout of the panel to spring layout and placed the 
   * timer onto the background. It is also used to allow the user to drag and drop the hedgehogs.
   * The constructor also makes it possible to pause the game using keyboard shortcuts.
   * <p>
   * Within this constructor, a mousePressed method is there to implement MouseEvent. This method
   * checks to see where the user has pressed. If they pressed the area in which a hedgehog is located,
   * it will set the hogNum to that hedgehog to be used for processing later in the constructor. If they
   * pressed the area in which the pause button is located, the activity they are playing will be paused
   * and they will be taken to another screen where they can choose to resume the game or return to the 
   * main menu.
   * <p>
   * This constructor also contains a mouseReleased method which is used to implement MouseEvent. This 
   * method checks to see where the user has released the hedgehog. If they released it in the correct
   * box, the hedgehog will dissapear.
   * <p>
   * Along with that, this constructor contains a mouseDragged method which is used to implement MouseEvent.
   * This method makes sure the user does not drag the hedgehogs offscreen and it is also used to update
   * the current location of the hedgehogs.
   * <p>
   * <b>Parameters:</b>
   * <p>
   * e - This points to the MouseEvent class.
   * <p>
   * e - This points to the ActionEvent class.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>h </b> This is a reference varible that points to the Hedgehog class.
   * <p>
   * <b>c </b> This is a reference varible that points to the Countdown class.
   * <p>
   * <b>temp1 </b> This stores the current x coordinate.
   * <p>
   * <b>temp2 </b> This stores the current y coordinate.
   * <p>
   * <b>p </b> This is a reference varible that points to the Pause class.
   * <p>
   * <b>pause </b> This points to the Action class.
   */ 
  public Activity2 ()
  {
    setLayout(layout); 
    
    layout.putConstraint (SpringLayout.NORTH, Countdown.time, 10, SpringLayout.NORTH, Activity2.this); 
    layout.putConstraint (SpringLayout.WEST, Countdown.time, 910, SpringLayout.WEST, Activity2.this);
    
    add(Countdown.time);
    setVisible(true);    
    
    repaint();
    
    Hedgehog h = new Hedgehog ();  
    Countdown c = new Countdown();
    
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
        
        if (temp1 >= coordinates[0] && temp1 <= coordinates[0] + 120 && temp2 >= coordinates[1] && temp2 <= coordinates[1] + 70) //First hog
        {
          hogNum = 1;
        }
        else if (temp1 >= coordinates[2] && temp1 <= coordinates[2] + 120 && temp2 >= coordinates[3] && temp2 <= coordinates[3] + 70) //Second hog
        {
          hogNum = 2;            
        }
        else if (temp1 >= coordinates[4] && temp1 <= coordinates[4] + 120 && temp2 >= coordinates[5] && temp2 <= coordinates[5] + 70) //Third hog
        {
          hogNum = 3;            
        }
        else if (temp1 >= coordinates[6] && temp1 <= coordinates[6] + 120 && temp2 >= coordinates[7] && temp2 <= coordinates[7] + 70) //Fourth hog
        {
          hogNum = 4;             
        }
        else if (temp1 >= coordinates[8] && temp1 <= coordinates[8] + 120 && temp2 >= coordinates[9] && temp2 <= coordinates[9] + 70) //Fifth hog
        {
          hogNum = 5;            
        }
        else 
        {
          if (temp1 >= coordinates[10] && temp1 <= coordinates[10] + 120 && temp2 >= coordinates[11] && temp2 <= coordinates[11] + 70) //Sixth hog
          {
            hogNum = 6;          
          }
        }
        repaint();
      }
      
      public void mouseReleased(MouseEvent e) {
        if (hogNum == 1)
        {
          tempX[0] = e.getX();
          tempY[0] = e.getY();
        }
        else if (hogNum == 2)
        {
          tempX[1] = e.getX();
          tempY[1] = e.getY();
        }
        else if (hogNum == 3)
        {
          tempX[2] = e.getX();
          tempY[2] = e.getY();
        }
        else if (hogNum == 4)
        {
          tempX[3] = e.getX();
          tempY[3] = e.getY();
        }
        else if (hogNum == 5)
        {
          tempX[4] = e.getX();
          tempY[4] = e.getY();
        }
        else 
        {
          if (hogNum == 6)
          {
            tempX[5] = e.getX();
            tempY[5] = e.getY();
          }
        }
        
        if (LevelSelect.levelChosen == 1 && hogNum != 0 && !correctPos[hogNum-1])  //If statement below - ArrayIndexOutOfBoundsException: -1
        {
          if ((e.getX() >= 4 && e.getX() <= 158 && e.getY() >= 445 && e.getY() <= 640 && Hedgehog.levelOneSizes[0] == Hedgehog.sizes.get(hogNum*2-2) && Hedgehog.levelOneSizes[1] == Hedgehog.sizes.get(hogNum*2-1))||(e.getX() >= 169 && e.getX() <= 324 && e.getY() >= 445 && e.getY() <= 640 && Hedgehog.levelOneSizes[2] == Hedgehog.sizes.get(hogNum*2-2) && Hedgehog.levelOneSizes[3] == Hedgehog.sizes.get(hogNum*2-1))) /////////
          {
            correctPos[hogNum-1] = true;      
            LevelSelect.lvlOneScore2++;
          }
          else
          {
            if(e.getY() >= 445 && e.getY() <= 640 && (e.getX() >= 4 && e.getX() <= 168 || e.getX() >= 169 && e.getX() <= 334))
            {
              if (hogNum == 1)
              {
                coordinates[0] = 40;
                coordinates[1] = 240;
              }
              else if (hogNum == 2)
              {
                coordinates[2] = 200;
                coordinates[3] = 240;
              }
              else if (hogNum == 3)
              {
                coordinates[4] = 360;
                coordinates[5] = 240;
              }
              else if (hogNum == 4)
              {
                coordinates[6] = 520;
                coordinates[7] = 240;
              }
              else if (hogNum == 5)
              {
                coordinates[8] = 670;
                coordinates[9] = 240;
              }
              else 
              {
                if (hogNum == 6)
                {
                  coordinates[10] = 820;
                  coordinates[11] = 240;
                }
              }             
            }
            
          }
        }
        
        
        else if (LevelSelect.levelChosen == 2 && hogNum != 0 && !correctPos[hogNum-1])
        {
          
          if ((e.getX() >= 4 && e.getX() <= 158 && e.getY() >= 445 && e.getY() <= 640 && Hedgehog.levelTwoSizes[0] == Hedgehog.sizes.get(hogNum*2-2) && Hedgehog.levelTwoSizes[1] == Hedgehog.sizes.get(hogNum*2-1))||(e.getX() >= 169 && e.getX() <= 324 && e.getY() >= 445 && e.getY() <= 640 && Hedgehog.levelTwoSizes[2] == Hedgehog.sizes.get(hogNum*2-2) && Hedgehog.levelTwoSizes[3] == Hedgehog.sizes.get(hogNum*2-1))|| (e.getX() >= 370 && e.getX() <= 530 && e.getY() >= 445 && e.getY() <= 640 && Hedgehog.levelTwoSizes[4] == Hedgehog.sizes.get(hogNum*2-2) && Hedgehog.levelTwoSizes[5] == Hedgehog.sizes.get(hogNum*2-1))) /////////
          {
            correctPos[hogNum-1] = true;  
            LevelSelect.lvlTwoScore2++;
          }
          else
          {
            if(e.getY() >= 445 && e.getY() <= 640 && (e.getX() >= 4 && e.getX() <= 168 || e.getX() >= 169 && e.getX() <= 334 || e.getX() >= 370 && e.getX() <= 540))
            {
              if (hogNum == 1)
              {
                coordinates[0] = 40;
                coordinates[1] = 240;
              }
              else if (hogNum == 2)
              {
                coordinates[2] = 200;
                coordinates[3] = 240;
              }
              else if (hogNum == 3)
              {
                coordinates[4] = 360;
                coordinates[5] = 240;
              }
              else if (hogNum == 4)
              {
                coordinates[6] = 520;
                coordinates[7] = 240;
              }
              else if (hogNum == 5)
              {
                coordinates[8] = 670;
                coordinates[9] = 240;
              }
              else 
              {
                if (hogNum == 6)
                {
                  coordinates[10] = 820;
                  coordinates[11] = 240;
                }
              }             
            }
          }             
          
        }
        else 
        {
          if (LevelSelect.levelChosen == 3 && hogNum != 0 && !correctPos[hogNum-1])
          {
            if ((e.getX() >= 4 && e.getX() <= 158 && e.getY() >= 445 && e.getY() <= 640 && Hedgehog.levelThreeSizes[0] == Hedgehog.sizes.get(hogNum*2-2) && Hedgehog.levelThreeSizes[1] == Hedgehog.sizes.get(hogNum*2-1))||(e.getX() >= 169 && e.getX() <= 324 && e.getY() >= 445 && e.getY() <= 640 && Hedgehog.levelThreeSizes[2] == Hedgehog.sizes.get(hogNum*2-2) && Hedgehog.levelThreeSizes[3] == Hedgehog.sizes.get(hogNum*2-1))|| (e.getX() >= 370 && e.getX() <= 530 && e.getY() >= 445 && e.getY() <= 640 && Hedgehog.levelThreeSizes[4] == Hedgehog.sizes.get(hogNum*2-2) && Hedgehog.levelThreeSizes[5] == Hedgehog.sizes.get(hogNum*2-1))|| (e.getX() >= 572 && e.getX() <= 734 && e.getY() >= 445 && e.getY() <= 640 && Hedgehog.levelThreeSizes[6] == Hedgehog.sizes.get(hogNum*2-2) && Hedgehog.levelThreeSizes[7] == Hedgehog.sizes.get(hogNum*2-1)))
            {
              correctPos[hogNum-1] = true;  
              LevelSelect.lvlThreeScore2++;
            }
            else
            {
              if(e.getY() >= 445 && e.getY() <= 640 && (e.getX() >= 4 && e.getX() <= 168 || e.getX() >= 169 && e.getX() <= 334 || e.getX() >= 370 && e.getX() <= 540 || e.getX() >= 572 && e.getX() <= 744))
              {
                if (hogNum == 1)
                {
                  coordinates[0] = 40;
                  coordinates[1] = 240;
                }
                else if (hogNum == 2)
                {
                  coordinates[2] = 200;
                  coordinates[3] = 240;
                }
                else if (hogNum == 3)
                {
                  coordinates[4] = 360;
                  coordinates[5] = 240;
                }
                else if (hogNum == 4)
                {
                  coordinates[6] = 520;
                  coordinates[7] = 240;
                }
                else if (hogNum == 5)
                {
                  coordinates[8] = 670;
                  coordinates[9] = 240;
                }
                else 
                {
                  if (hogNum == 6)
                  {
                    coordinates[10] = 820;
                    coordinates[11] = 270;
                  }
                }             
              }
            }
          }
        }          
        hogNum = 0;        
        repaint();
      }
      
    });
    
    addMouseMotionListener(new MouseMotionAdapter() {      
      public void mouseMoved(MouseEvent e) {
      }      
      
      public void mouseDragged(MouseEvent e) {
        if (e.getX() >= 0 && e.getX() <= 876 && e.getY() >= 0 && e.getY() <= 600)
        {
          if (hogNum == 1 && !correctPos[0])
          {
            coordinates[0] = e.getX();
            coordinates[1] = e.getY();
          }
          else if (hogNum == 2 && !correctPos[1])
          {
            coordinates[2] = e.getX();
            coordinates[3] = e.getY();
          }     
          else if (hogNum == 3 && !correctPos[2])
          {
            coordinates[4] = e.getX();
            coordinates[5] = e.getY();
          }  
          else if (hogNum == 4 && !correctPos[3])
          {
            coordinates[6] = e.getX();
            coordinates[7] = e.getY();
          }  
          else if (hogNum == 5 && !correctPos[4])
          {
            coordinates[8] = e.getX();
            coordinates[9] = e.getY();
          }  
          else 
          {
            if (hogNum == 6 && !correctPos[5])
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
   * This method draws the background, hedgehogs, timer, and messages of encouragement onto the screen.
   * It also reads in the images from the Images folder and resets the coordinates of the hedgehogs along
   * with the arrays when the user has successfully placed a whole group of hedgehogs in the boxes. The
   * try catch block is used to prevent the program from crashing in the event that the image the program
   * is trying to read in cannot be found.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>tracker </b> This is a reference variable that points to the MediaTracker 
   * <p>
   * <b>background </b> This stores the image that is displayed.
   * <p>
   * <b>x </b> This is used within a for loop.
   * <p>
   * <b>counter </b> This is used to keep track of the number of unplaced hedgehogs.
   * <p>   
   * <b>Exceptions</b>:
   * <p>
   * <b>InterruptedException e </b> This is an exception caught when using a MediaTracker.
   * 
   * @param g This is needed to draw the image onto the screen.
   */ 
  public void paintComponent(Graphics g) 
  {    
    MediaTracker tracker = new MediaTracker (new Frame ());    
    Image background = Toolkit.getDefaultToolkit ().getImage ("resources/Images/Activity Backgrounds/Activity Two - Level" + LevelSelect.levelChosen + ".png");
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
    
    int counter = 0;
    
    g.setFont(new Font("Arial", Font.BOLD, 30));
    g.setColor (Color.white);
    if (phrase == 1)
    {
      g.drawString ("GREAT JOB!", 775, 560);
    }
    else if (phrase == 2)
    {
      g.drawString ("KEEP GOING!", 775, 560);
    }
    else
    {
      if (phrase == 3)
      {
        g.drawString ("WOAH!!!", 775, 560);
      }
    }
    
    for (int x = 0; x < Hedgehog.hedgehogs.size(); x++) 
    {
      if (!correctPos[x])
      {
        g.drawImage(Hedgehog.hedgehogs.get(x), coordinates[x*2], coordinates[x*2+1], Hedgehog.sizes.get(x*2), Hedgehog.sizes.get(x*2+1), this);
        counter++;
      }
    }
    g.setFont(new Font("Arial", Font.BOLD, 70));
    g.setColor (Color.white);
    if (LevelSelect.levelChosen == 1)
    {
      g.drawString ("" + LevelSelect.lvlOneScore2, 515, 75);
    }
    else if (LevelSelect.levelChosen == 2)
    {
      g.drawString ("" + LevelSelect.lvlTwoScore2, 515, 75);
    }
    else
    {
      g.drawString ("" + LevelSelect.lvlThreeScore2, 515, 75);
    }
    
    if (counter == 0)
    {
      Hedgehog.hedgehogs.clear();
      Hedgehog.sizes.clear();
      Hedgehog.loadDiffSizeHedgehogs();
      
      for (int x = 0; x < 6; x++)
      {
        correctPos[x] = false;
      }
      phrase = (int)(Math.random() * 3 + 1);         
      coordinates[0] = 40;
      coordinates[1] = 240;
      coordinates[2] = 200;
      coordinates[3] = 240;
      coordinates[4] = 360;
      coordinates[5] = 240;
      coordinates[6] = 520;
      coordinates[7] = 240;
      coordinates[8] = 680;
      coordinates[9] = 240;
      coordinates[10] = 840;
      coordinates[11] = 240;
      
      repaint();
    }    
    this.revalidate();     
  }
}
