import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.*;
/** 
 * This class is the timer class for our Hedgehogs at Work Game. In this, we have
 * the countdown for the games, and for the "next level" screens. 
 * Note that since javadoc is not picking up caught exceptions, we included the exceptions in the local
 * variables.
 * <p><b>Verison 1 (5 Hours):</b> Countdown code activated and implemented for the levels/activities.</p>
 * @author Tiffany Tran
 * @version 1 05.23.16
 * Program name: Countdown.java
 */
public class Countdown extends JPanel 
{  
  /**
   * Private instance variables
   * 
   * */
  /**
   * An int for the interval of time left.
   * 
   * */    
  static int interval;
  /**
   * Of type Timer, it is used to keep time for the game.
   * 
   * */    
  static Timer timer;
  /**
   * The label that displays the time remaining.
   * 
   * */
  static JLabel time = new JLabel();
  /**
   * The counter as an int for the activity number.
   * 
   * */
  static int activityCounter = 0;
  
  /**
   * This is the no parameter constructor for the Countdown class.
   * In this constructor, the Timer is created, a d the intervals set to rin the time
   * A new TimerTask is created within this, when setting the timer scheduleAtFixedRate.
   * 
   * */
  public Countdown()
  {        
    time.setFont(new Font("Serif", Font.BOLD, 70));
    time.setForeground (Color.white);
    
    timer = new Timer();    
    
    if (!Pause.pausePressed)
    {
      if (Levels.atLevels) 
      {
        interval = 3;
      }
      else
      {
        interval = 31;
      }
    }
    
    timer.scheduleAtFixedRate(new TimerTask() {      
      public void run() { 
        time.setText(setInterval() - 1 + ""); 
      }
    }, 1000, 1000);
  }
  
  /**
   * This is the static final class to set the interval of the time.
   * It is final because once the time is set, for the countdown, it is
   * not changed but just used. This class returns this as an int. Depending
   * on the activity and level, it calls the next class when the interval is 0.
   * @return int for the interval number.
   * */
  static final int setInterval() 
  {
    if (interval == 1)
    {
      timer.cancel();      
      LevelSelect.activityNum += 1;
      
      if (LevelSelect.activityNum == 4)
      {
        Driver.changeScreens ("LevelComplete");
      }
      else if (LevelSelect.activityNum == 5)
      {
        LevelSelect.levelChosen += 1;
        LevelSelect.activityNum = 0;     
      }
      
      if (LevelSelect.levelChosen == 4)
      {
        Driver.changeScreens ("LevelComplete");
      }
      
      if (LevelSelect.activityNum == 0 && LevelSelect.levelChosen < 4)
      {
        Driver.changeScreens ("Levels");
      }
      else
      {
        Driver.changeScreens ("Activity" +  LevelSelect.activityNum);  
      }
    }
    
    if (!Pause.pausePressed)
    {
      return --interval;
    }
    return interval;
  }
}