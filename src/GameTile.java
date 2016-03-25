 /**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * Gametile.java
 */
import java.awt.Color;
import java.awt.Graphics;

public class GameTile implements Drawable 
{
  
  
  
  public static final int SIZE = 100;
    
    // We should probably inherit from a some kind of abstract base class,
    // since this behavior is the same as the Board's.
    // If we do, it should probably replace the Drawable interface. -AC
    private int locX;
    private int locY;
    private int displayNum;
    private int correctArrayPos;
    //private int currentArrayPos;
    
    
    public void setScreenLoc(int x, int y) 
    {
      locX = x;
      locY = y;
    }
    
    // This should eventually draw the parts of the image. -AC
    @Override
    public void draw(Graphics g) {
      g.setColor(Color.ORANGE);
      g.fillRect(locX, locY, SIZE, SIZE);
    }


  
  //Initializing GameTile. currentArrayPos was only needed if we are going to
  //manipulate the array past initialization. -AG
  public GameTile(int arrayPos)
  {
    //currentArrayPos = arrayPos;
    displayNum = arrayPos;
    correctArrayPos = arrayPos;
  }
  
  //Returns the number on the tile. -AG
  public int getNum()
  {
    return displayNum;
  }
  
  public int getScreenXLoc()
  {
    return locX;
  }
  
  public int getScreenYLoc()
  {
    return locY;
  }

  
  //Not sure if we will need these in the future -AL
  /*
  public int getArrayPos()
  {
    return currentArrayPos;
  }*/
  
  /*
  public void setArrayPos(int pos)
  {
    currentArrayPos = pos;
  }*/
  
  
  
  //Checks to see if the tile is in the correct position on the grid by
  //comparing the x and y positions to the position for the correct spot on the grid. -AG
  //May be needed in the future -AL
  /*
  public boolean correctPosition()
  {
    if ((locX == 250+SIZE*(correctArrayPos%4)) && 
        (locY == 300+SIZE*(correctArrayPos/4)))
    {
      return true;
    }
    return false;
  }
  */
  
}