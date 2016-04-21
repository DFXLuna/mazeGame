/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * VisualTileHolder.java
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;


/**
 * This is a generic representation of a front-end tile container.
 * It is responsible for drawing itself and the tiles it contains.
 * It should also translate mouse coordinates to slot IDs... -AC
 */

public abstract class VisualTileHolder {
  
  // Screen position of the holder (Upper left corner!) -AC
  private int posX;
  private int posY;
  // Size of the container in tiles. -AC
  private int width;
  private int height;
  
  // Our sub-classes generally require a reference to a messenger. -AC
  private Messenger messenger;
  
  protected VisualTileHolder(Messenger msgr, int x, int y, int w, int h) {
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
  
  public void draw(Graphics2D g) {
    
    int size = TileDrawer.TILE_SIZE;
    
    // Currently we draw the empty holder with
    // a grey checkerboard pattern. -AC
    for (int x=0; x<width; x++) {
      for (int y=0;y<height; y++) {
        Point loc = new Point(x, y);
        Image tileImg = getTileImageAt(loc);
        int tileRot = getTileRotationAt(loc);
        
        // We only draw the tile if it exists and is not being dragged. -AC
        if (tileImg != null && messenger.getDraggedTileImage() != tileImg)
          TileDrawer.drawTile(g, posX+x*size, posY+y*size, tileImg, tileRot);
        else {
          if ((x+y)%2==0)
            g.setColor(new Color(100,100,100));
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
  
  /**
   * Get the slot ID from a mouse click. We need the slot ID to perform
   * swaps between slots when drags are successful. -AC
   */
  public int getSlotFromClick(MouseEvent e) {
    Point position = getLocationFromClick(e);
    if (position != null)
      return getSlotIdAt(position);
    return -1;
  }
  
  public Image getTileImageFromClick(MouseEvent e) {
    Point position = getLocationFromClick(e);
    if (position != null)
      return getTileImageAt(position);
    return null;
  }
  
  public int getRotationFromClick(MouseEvent e) {
    Point position = getLocationFromClick(e);
    if (position != null) {
      return getTileRotationAt(position);
    }
    return -1;
  }
  
  public void rotateTileFromClick(MouseEvent e) {
    Point position = getLocationFromClick(e);
    if (position != null)
      doRotateAt(position);
  }
  
  /**
   * Get the tile number at a tile position. Sub-classes must implement this.
   * A negative number indicates that there is no tile present. -AC
   */
  protected abstract Image getTileImageAt(Point loc);
  
  /**
   * Same, for slot IDs. -AC
   */
  protected abstract int getSlotIdAt(Point loc);
  
  /**
   * Same, for rotation. -AC
   */
  protected abstract int getTileRotationAt(Point loc);
  
  /**
   * Same, for PERFORMING rotations. -AC
   */ 
  protected abstract void doRotateAt(Point loc);
}