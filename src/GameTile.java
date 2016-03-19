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
    
    // We should probably inherit from a some kind of base class, since
    // this behavior is the same as the Board's.
    // If we do, it should probably replace the Drawable interface.
    private int locX;
    private int locY;
    private int correctArrayPos;
    //private int currentArrayPos;
    
    
    public void setScreenLoc(int x, int y) {
      locX = x;
      locY = y;
    }
    
    // This should eventually be drawing the parts of the image.
    @Override
    public void draw(Graphics g) {
      g.setColor(Color.ORANGE);
      g.fillRect(locX, locY, SIZE, SIZE);
    }


  
  //Initializing GameTile. currentArrayPos was only needed if we are going to
  //manipulate the array past initialization.
  public GameTile(int arrayPos)
  {
    //currentArrayPos = arrayPos;
    correctArrayPos = arrayPos;
  }
  
  
  public int getScreenXLoc()
  {
    return locX;
  }
  
  public int getScreenYLoc()
  {
    return locY;
  }

  
  //Both of these are methods that would be used if we were manipulating the array
  //in some way (e.g. if it were used for the grid or the side array)
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
  //comparing the x and y positions to the position for the correct spot on the grid.
  public boolean correctPosition()
  {
    if ((locX == 250+SIZE*(correctArrayPos%4)) && 
        (locY == 300+SIZE*(correctArrayPos/4)))
    {
      return true;
    }
    return false;
    
    
    //Originally thought we were implementing the grid as an array in which case
    //this would be the way to do this method, corrected for pixels above.
    /*
    if (currentArrayPos == correctArrayPos)
    {
      return true;
    }
    return false;*/
  }
  
}
