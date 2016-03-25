import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * TileDrawer.java
 */

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
  public static final int TILE_SIZE = 80;
  
  // This is the font that we draw numbers with.
  private static final Font numberFont =
      new Font(Font.DIALOG,Font.BOLD,30);
  
  /**
   * Draws a tile to a graphics object at a specified position with a specified
   * number. -AC
   */
  public static void drawTile(Graphics g, int x, int y, int number) {
    g.setColor(Color.BLACK);
    g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
    g.setColor(Color.YELLOW);
    g.fillRect(x+2, y+2, TILE_SIZE-4, TILE_SIZE-4);
    g.setColor(Color.BLACK);
    g.setFont(numberFont);
    g.drawString(String.valueOf(number), x+5, y+30);
  }
}