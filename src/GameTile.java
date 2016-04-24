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
  private int rotation;
  private int origRotation;
  
  //Initializing GameTile. currentArrayPos was only needed if we are going to
  //manipulate the array past initialization. -AG
  public GameTile(int arrayPos)
  {
    correctArrayPos = arrayPos;
    rotation = -999;
  }
  
  public void rotateTile()
  {
    if(rotation == 3)
      rotation = 0;
    else
      rotation++;
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
  
  // Sets the Tile's rotation (0-3) -AG
  public void setRotation(int rot)
  {
    if (rotation == -999)
    {
      setOrigRotation(rot);
    }
    rotation = rot;
    //System.out.println("Rotation set to " + rot);
  }
  
  //Sets tile's original rotation (0-3) -AG
  public void setOrigRotation(int rot)
  {
    origRotation = rot;
  }
  
  // Returns the Tile's rotation (0-3) -AG
  public int getRotation()
  {
    //System.out.println(rotation + " returned.");
    return rotation;
  }
  
  // Returns the Tile's original rotation (0-3) -AG
  public int getOrigRotation()
  {
    return origRotation;
  }
  
 
}