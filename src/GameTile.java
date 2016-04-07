/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * Gametile.java
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class GameTile 
{
  
  
  
    
    // We should probably inherit from a some kind of abstract base class,
    // since this behavior is the same as the Board's.
    // If we do, it should probably replace the Drawable interface. -AC
    private int locX;
    private int locY;
    private int displayNum;
    private int correctArrayPos;
    private Image image;
    //private int currentArrayPos;
    
    //Initializing GameTile. currentArrayPos was only needed if we are going to
    //manipulate the array past initialization. -AG
    public GameTile(int arrayPos)
    {
      //currentArrayPos = arrayPos;
      displayNum = arrayPos;
      correctArrayPos = arrayPos;
    }
    
    
    public void setScreenLoc(int x, int y) 
    {
      locX = x;
      locY = y;
    }
    
    // This should eventually draw the parts of the image. -AC
    /*@Override
    public void draw(Graphics g) {
      g.setColor(Color.ORANGE);
      g.fillRect(locX, locY, SIZE, SIZE);
    }*/
  
  // Changed to getImage() -- should now return the image on the tile!
  public Image getImage()
  {
    return image;
  }
  
  // Sets the Tile's image -AL
  public void setImage(Image img)
  {
    image = img;
  }

  public int getScreenXLoc()
  {
    return locX;
  }
  
  public int getScreenYLoc()
  {
    return locY;
  }
  
}