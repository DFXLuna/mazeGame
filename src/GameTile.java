/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * Gametile.java
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class GameTile 
{ 
  private int startPosition;
  private int rotation;
  private int origRotation;
  
  private float[] linePoints;
  
  private Image image;
  
  //Initializing GameTile. currentArrayPos was only needed if we are going to
  //manipulate the array past initialization. -AG
  public GameTile(int startPosition, int startRotation, float[] points)
  {
    this.startPosition = startPosition;
    rotation = startRotation;
    origRotation = startRotation;
    linePoints = points;
    image = makeImage(points);
  }
  
  public int getStartPosition() {
    return startPosition;
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
  
  // Returns the Tile's rotation (0-3) -AG
  public int getRotation()
  {
    //System.out.println(rotation + " returned.");
    return rotation;
  }
  
  // Returns the point array. (Actually returns a copy so the caller can't
  // edit it!) -AC
  public float[] getPoints() {
    return linePoints.clone();
  }
  
  public void resetRotation() {
    rotation = origRotation;
  }
  
  /** 
   * Reads in data for lines and makes them into an Image. -AL
   * Originally in the MazeReader, slightly modified for GameTile. -AC
   */
  private static Image makeImage(float[] points)
  {
    // pointsArray stores points for drawing lines. -AL
    // The format: x1 in index 0, y1 in index 1,
    //             x2 in index 2, y2 in index 3.
    
    // Setting up the tile image. -AL
    BufferedImage image = new BufferedImage(
        TileDrawer.TILE_SIZE,
        TileDrawer.TILE_SIZE,
        BufferedImage.TYPE_INT_RGB );
    
    // Makes the background of each tile image white. -AL
    Graphics2D g2d = image.createGraphics();
    g2d.setBackground(Color.WHITE);
    g2d.fillRect(0, 0, TileDrawer.TILE_SIZE, TileDrawer.TILE_SIZE);
    
    // Reads in the information for drawing the lines, and draws them. -AL
    for(int i = 0; i < points.length; i+=4)
    {
      g2d.setColor(Color.BLACK);
      g2d.drawLine(
          (int)points[i],
          (int)points[i+1],
          (int)points[i+2],
          (int)points[i+3]
      );
    }
    
    return image;
  }
  
}