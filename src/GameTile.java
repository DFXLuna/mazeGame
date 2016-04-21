/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * Gametile.java
 */

import java.awt.Image;

public class GameTile 
{
  
  
  
    
    private int correctArrayPos;
    private Image image;
    //private int currentArrayPos;
    
    //Initializing GameTile. currentArrayPos was only needed if we are going to
    //manipulate the array past initialization. -AG
    public GameTile(int arrayPos)
    {
      correctArrayPos = arrayPos;
    }
    
  
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
}