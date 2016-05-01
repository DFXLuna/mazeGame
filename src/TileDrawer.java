/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * TileDrawer.java
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.HashSet;



/**
 * This static class is used to draw tiles.
 * It also controls the size of the tiles.
 * 
 * We use a separate class for this since FrontEndTileHolders need to draw
 * tiles, but we may also need to draw tiles directly from the window when
 * click / dragging. -AC
 */
public class TileDrawer {
  /** 
   * This determines the size that tiles are rendered at. It also controls
   * the size of the containers. -AC
   */
  public static final int TILE_SIZE = 100;
  
  private static final Font warningFont =
      new Font(Font.MONOSPACED, Font.BOLD, 20);
  
  /**
   * Draws a tile to a graphics object at a specified position with a specified
   * image -- Basically a wrapper for drawImage now. -AC
   */
  public static void drawTile(Graphics2D g, int x, int y, Image img, int rot) {
    
    // We save the old transform, so we can reset it after doing ours. -AC
    AffineTransform oldMatrix = g.getTransform();
    
    g.translate(x + TILE_SIZE/2, y + TILE_SIZE/2);
    g.rotate(rot*Math.PI/2);
    
    g.drawImage(img, -TILE_SIZE/2, -TILE_SIZE/2, null );
    
    g.setTransform(oldMatrix);
    
    if (flashingTiles.contains(img)) {
      g.setColor(Color.RED);
      g.setFont(warningFont);
      g.drawString("SLOT", x+24, y+40);
      g.drawString("OCCUPIED", x+2, y+60);
    }
  }
  
  /**
   * This is a system for controlling which tiles are flashing, for
   * notification that a space is occupied. I'm not super proud of it. -AC
   */
  private static HashSet<Image> flashingTiles = new HashSet<Image>();
  
  public static void setFlash(Image img, boolean enable) {
    if (enable)
      flashingTiles.add(img);
    else
      flashingTiles.remove(img);
  }
}