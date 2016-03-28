/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * FrontEndTileHolder.java
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;


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
  
  // Our sub-classes generally require a reference to a messenger. -AC
  private Messenger messenger;
  
  protected FrontEndTileHolder(Messenger msgr, int x, int y, int w, int h) {
    messenger = msgr;
    posX = x;
    posY = y;
    width = w;
    height = h;
  }
  
  // Use a protected getter rather than a field. -AC
  protected Messenger getMessenger() {
    return messenger;
  }
  
  public void draw(Graphics g) {
    
    int size = TileDrawer.TILE_SIZE;
    
    // Currently we draw the empty holder with
    // a grey checkerboard pattern. -AC
    for (int x=0; x<width; x++) {
      for (int y=0;y<height; y++) {
        int tileNumber = getTileNumberAt(x, y);
        
        // We only draw the tile if it exists and is not being dragged. -AC
        if (tileNumber>=0 && messenger.getDraggedTileNumber() != tileNumber)
          TileDrawer.drawTile(g, posX+x*size, posY+y*size, tileNumber);
        else {
          if ((x+y)%2==0)
            g.setColor(Color.WHITE);
          else
            g.setColor(new Color(200,200,200));
          g.fillRect(posX+x*size, posY+y*size, size, size);
        }
      }
    }
  }
  
  /**
   * Gets the x/y location of a slot in the container from a mouse click.
   * Returns null if there is no slot at that position.
   */
  private Point getLocationFromClick(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    
    if (x < posX || 
        y < posY ||
        x>posX+TileDrawer.TILE_SIZE*width ||
        y>posY+TileDrawer.TILE_SIZE*height ) {
      // We can bail out if the coordinates are outside our bounding box -AC
      return null;
    }
    
    int tileX = (x-posX)/TileDrawer.TILE_SIZE;
    int tileY = (y-posY)/TileDrawer.TILE_SIZE;
    
    return new Point(tileX,tileY);
  }
  
  public int getSlotFromClick(MouseEvent e) {
    Point position = getLocationFromClick(e);
    if (position != null)
      return getSlotIdAt(position.x, position.y);
    return -1;
  }
  
  public int getTileNumberFromClick(MouseEvent e) {
    Point position = getLocationFromClick(e);
    if (position != null)
      return getTileNumberAt(position.x, position.y);
    return -1;
  }
  
  /**
   * Get the tile number at a tile position. Sub-classes must implement this.
   * A negative number indicates that there is no tile present. -AC
   */
  protected abstract int getTileNumberAt(int x, int y);
  
  /**
   * Get the slot ID from a mouse click. We need the slot ID to perform
   * swaps between slots when drags are successful. -AC
   */
  protected abstract int getSlotIdAt(int x, int y);
}