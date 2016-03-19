 /**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * GameBoard.java
 */
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
  private GameTile[] sideArray = new GameTile[16];
  
  public GameBoard() {
    for (int i=0; i<16; i++) {
      tiles[i] = new GameTile(i);
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
  
  //Moves the tile to a specified location.
  public void moveTile(GameTile t, int x, int y)
  {
    t.setScreenLoc(x, y);
  }
  
  
  
  //Checks if each tile is in the correct position, returns true only if all
  //tiles are in the correct position.
  private boolean checkHasWon()
  {
    boolean victory = true;
    for (int i = 0; i<16; i++)
    {
      if (tiles[i].correctPosition() == false)
      {
        victory = false;
      }
    }
    return victory;
  }
  
  //Assuming the grid starts at 0,0 being 250 pixels, 300 pixels. Limited
  //functionality - does not check to see whether
  //or not a tile is already at that position in the grid.
  public void setTileInGrid(GameTile t, int gridX, int gridY)
  {
    int size = GameTile.SIZE;
    t.setScreenLoc(250+size*gridX, 300+size*gridY);
  }
  
  
  //Pretty janky method. Basically checks to see if there are tiles in each of
  //the starting positions and keeping track of the position as it goes.
  //Eventually sets the position to the first available position corresponding
  //to one that the tiles were set to at the start of the game. Overall unclear
  //on why this method is needed, could have missed the mark entirely on what it
  //needs to do.
  public void removeTileFromGrid(GameTile t)
  {
    int position = 0;
    for (int i=0; i<16; i++) 
    {
      if (position<8)  
      {
        if ((tiles[i].getScreenXLoc() == 20) &&
            (tiles[i].getScreenYLoc() == 60+position*110))
        {
          i = 0;
          position++;
        }
      }
      else
      {
        if ((tiles[i].getScreenXLoc() == 780) && 
            (tiles[i].getScreenYLoc() == 60+(position-8)*110))
        {
          i = 0;
          position++;
        }
      }
    }
    
    if (position<8)
    {
      t.setScreenLoc(20, 60+position*110);
    }
    else
    {
        t.setScreenLoc(780, 60+(position-8)*110);
    }
  }
  
  
}
