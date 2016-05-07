/**
 * @author Group L
 * @author Matt Grant, Adam Coggeshall, Jared Frank 
 * @author Alex Germann, Auston Larson
 * COSC 3011
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
 * <p>
 * We use a separate class for this since FrontEndTileHolders need to draw
 * tiles, but we may also need to draw tiles directly from the window when
 * click / dragging. -AC
 * <p>
 * Includes flashingTiles and setFlash which are used to warn user 
 * when they try to put it in an occupied slot. -MG
 * @see flashingTiles
 * @see setFlash
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
   * image -- Basically a wrapper for drawImage now. -AC, MG
   * @param g Graphics2D object
   * @param x Integer x position of tile
   * @param y Integer y position of tile
   * @param img Image of tile
   * @param rot rotation of Tile represented in 90 degree clockwise rotations
   * @see GameTile
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
  
  /**
   * Sets the flash property of a GameTile's image according to enable. -MG
   * @param img Image of tile to set flash property
   * @param enable Boolean to toggle/detoggle
   */
  public static void setFlash(Image img, boolean enable) {
    if (enable)
      flashingTiles.add(img);
    else
      flashingTiles.remove(img);
  }
}