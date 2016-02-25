import java.awt.Color;
import java.awt.Graphics;

/**
 * This is the main component of the back-end
 * It is mainly responsible for handling the game state and tracking
 * where tiles are in the grid.
 */
public class GameBoard implements Drawable {
  // These represent the position of the upper left corner of the GameBoard
  // on the screen. 
  private int locX;
  private int locY;
  
  // This stores all our tiles. Not sure about the ordering in the array yet.
  private GameTile[] tiles = new GameTile[16];
  
  public GameBoard() {
    for (int i=0; i<16; i++) {
      tiles[i] = new GameTile();
    }
  }
  
  public void setScreenLoc(int x, int y) {
    locX = x;
    locY = y;
  }
  
  // The GameWindow needs to be able to get tiles, but it should not be able
  // to change the array.
  public GameTile getTileByIndex(int i) {
    return tiles[i];
  }
  
  @Override
  public void draw(Graphics g) {
    
    int size = GameTile.SIZE;
    
    for (int x=0; x<4; x++) {
      for (int y=0;y<4; y++) {
        if ((x+y)%2==0)
          g.setColor(Color.WHITE);
        else
          g.setColor(new Color(200,200,200));
        g.fillRect(locX+x*size, locY+y*size, size, size);
      }
    }
  }
}