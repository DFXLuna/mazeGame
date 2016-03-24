

/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * GameWindow.java
 */

import java.awt.Color;
import java.awt.Graphics;

/**
 * This is a generic representation of a front-end tile container.
 * It is responsible for drawing itself and the tiles it contains.
 * It should also translate mouse coordinates to slot IDs... -AC
 */

public abstract class FrontEndTileHolder {
  
  // Screen position of the holder (Upper left corner!) -AC
  private int posX;
  private int posY;
  // Size of the container in tiles. -AC
  private int width;
  private int height;
  
  protected FrontEndTileHolder(int x, int y, int w, int h) {
    posX = x;
    posY = y;
    width = w;
    height = h;
  }
  
  public void draw(Graphics g) {
    
    int size = GameWindow.TILE_SIZE;
    
    // Currently we draw the empty holder with
    // a grey checkerboard pattern. -AC
    for (int x=0; x<width; x++) {
      for (int y=0;y<height; y++) {
        if ((x+y)%2==0)
          g.setColor(Color.WHITE);
        else
          g.setColor(new Color(200,200,200));
        g.fillRect(posX+x*size, posY+y*size, size, size);
      }
    }
  }
}