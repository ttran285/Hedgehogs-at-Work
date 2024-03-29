import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.awt.event.*;

/**
 * This is the basic hedgehog class. It has methods for both
 * activities that use it. The methods load different size/coloured
 * hedgehogs into an image arraylist, which is the used for the 
 * mouse ware aspects.
 * Note that since javadoc is not picking up caught exceptions, we included the exceptions in the local
 * variables.
 * <p><b>Verison 1 (4 Hours):</b> Creating the Hedgehog class as a datbse class with get and set methods. Done by Aashmika.</p>
 * <p><b>Verison 2 (3 Hours):</b> Creating the methods and changing the class to create arrayList, and using on Color choice methods. Done by Tiffany.</p>
 * <p><b>Verison 3 (2 Hours):</b> Printing code and all methods created in this version.</p>
 * 
 * @author Aashmika Mali modified by Tiffany Tran
 * @version 1 05.10.16
 * @version 2 05.19.16
 * @version 3 05.25.16
 * Program name: Hedgehog.java
 */

public class Hedgehog extends JPanel
{
  /**
   * Private instance variables
   * 
   * 
   * */
  /**
   * Saves the hedgehog number as an int. 
   * */
  private int numHog;
  
  /**
   * A static Image arrayList to save the hedgehogs and use in. 
   * */
  static public ArrayList <Image> hedgehogs = new ArrayList <Image> ();
  
  /**
   * A staic string arraylist with the used colors.
   * */
  static public ArrayList <String> usedColours = new ArrayList <String> ();
  /**
   * A static string arraylist with the colorOrder for the hedgehogs. 
   * */
  static public ArrayList <String> colourOrder = new ArrayList <String> ();
  
  /**
   * A static arraylist with the sizes for the hedgehogs
   * */
  static public ArrayList <Integer> sizes = new ArrayList <Integer> ();
  
  /**
   * A static int array with the levelOneSizes of Hedgehog. 
   * */
  static int [] levelOneSizes = {90, 50, 140, 90}; 
  /**
   * A static int array with the levelTwoSizes of Hedgehog. 
   * */
  static int [] levelTwoSizes = {90, 50, 120, 70, 140, 90};
  /**
   * A static int array with the levelThreeSizes of Hedgehog. 
   * */
  static int [] levelThreeSizes = {60, 45, 90, 50, 120, 70, 140, 90}; 
  
  /**
   * The number of colors for the hedgehogs as an int.
   * */
  static int numColours;
  /**
   * An int for storing the random choice of size/color.
   * */
  static int random = 0;
  
  
  /**
   * The empty parameter Hedgehog class constructor. Here depending on the activity,
   * the hedgehog array will be filled with certain hedgehog images. It will also
   * clear all the arrayLists holding hedgehog values in this class.
   * <p>
   * <b>Execeptions: </b>
   * <p>
   * <b>e </b> Of type ArrayIndexOutOfBounds, it is caught when loading the difSizeHedgehogs.
   * 
   * */  
  public Hedgehog ()
  {
    hedgehogs.clear();
    sizes.clear();
    colourOrder.clear();
    usedColours.clear();    
    if (LevelSelect.activityNum == 1)
    {
      loadDiffColourHedgehogs();
    }
    else
    {
      if (LevelSelect.activityNum == 2)
      {
        try
        {
          loadDiffSizeHedgehogs(); 
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
        }
      }
    }
  }
  
  /**
   * This method loads the different coloured hedgehogs used in the first activity.
   * The for loops choose a certain number of colours and storing them into an array,
   * and get random coloured hedgehogs from the already chosen random colours.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>tracker </b> Of type MediaTracker, it is used when creating the different colored hogs.
   * <p>
   * <b>hedgehogsStrings </b> A string array that holds the colors of hogs.
   * <p>
   * <b>unusedColours </b> A string arrayList that holds that colors not used from random selection.
   * <p>
   * <b>x, y </b> Ints used in for loops that are used for incrementing values.
   * <p>
   * <b>e </b> Of type InterruptedException and is useed for the MediaTracker.
   */ 
  public static void loadDiffColourHedgehogs()
  {    
    MediaTracker tracker = new MediaTracker (new Frame ());        
    String [] hedgehogsStrings = {"Blue", "Red", "Yellow", "Orange", "Purple", "Green", "Pink"};
    ArrayList <String> unusedColours = new ArrayList <String> ();
    
    for (int x = 0; x < 7; x++)
    {
      unusedColours.add(hedgehogsStrings[x]);
    }
    
    if (LevelSelect.levelChosen == 1) 
    {
      numColours = 2;
    }
    else if (LevelSelect.levelChosen == 2) 
    {
      numColours = 3;
    }
    else 
    {
      numColours = 4;
    }
    
    for (int y = 0; y < numColours; y++) 
    {
      random = (int) (Math.random () * (7 - y));
      usedColours.add (unusedColours.get (random)); 
      unusedColours.remove(random);
    }
    
    for (int x = 0; x < 6; x++)
    {
      random = (int) (Math.random () * numColours);
      hedgehogs.add (Toolkit.getDefaultToolkit ().getImage ("resources/Images/Hedgehogs/" + usedColours.get(random) + ".png"));
      tracker.addImage (hedgehogs.get(x), x);
      colourOrder.add(usedColours.get(random));
    }
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
  }
  
  /**
   * This method loads the different sizesd hedgehogs used in the second activity.
   * The for loops are used to store all 6 sizes of hogs and all 6 pictures.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>tracker </b> Of type MediaTracker, it is used when creating the different sized hogs.
   * <p>
   * <b>x </b> Int used in for loops that are used for incrementing values the loop to store each hog
   * dependant on the level.
   * <p>
   * <b>Exceptions</b>:
   * <p>
   * <b>e </b> Of type InterruptedException and is useed for the MediaTracker.
   */ 
  public static void loadDiffSizeHedgehogs()
  {
    MediaTracker tracker = new MediaTracker (new Frame ());   
    for (int x = 0; x < 6; x++)
    {
      hedgehogs.add (Toolkit.getDefaultToolkit ().getImage ("resources/Images/Hedgehogs/Regular Hog.png"));
      tracker.addImage (hedgehogs.get(x), x);      
      random = (int) (Math.random () * (LevelSelect.levelChosen + 4)); 
      
      if (LevelSelect.levelChosen == 1)
      {
        random = (int) (Math.random () * (LevelSelect.levelChosen + 2));
        if (random % 2 == 0)
        {
          sizes.add (levelOneSizes[random]);
          sizes.add (levelOneSizes[random + 1]);
        }
        else
        {
          sizes.add (levelOneSizes[random - 1]);
          sizes.add (levelOneSizes[random]);
        }
      }
      else if (LevelSelect.levelChosen == 2)
      {
        if (random % 2 == 0)
        {
          sizes.add (levelTwoSizes[random]);
          sizes.add (levelTwoSizes[random + 1]);
        }
        else
        {
          sizes.add (levelTwoSizes[random - 1]);
          sizes.add (levelTwoSizes[random]);
        }
      }
      else
      {
        if (LevelSelect.levelChosen == 3)
        {
          if (random % 2 == 0)
          {
            sizes.add (levelThreeSizes[random]);          
            sizes.add (levelThreeSizes[random + 1]);
          }
          else
          {
            sizes.add (levelThreeSizes[random-1]);
            sizes.add (levelThreeSizes[random]);
          }
        }
      }
    }
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
  }
}