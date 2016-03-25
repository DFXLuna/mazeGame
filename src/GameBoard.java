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
 * where tiles are in the grid. -AC
 */
public class GameBoard {
  // These represent the position of the upper left corner of the GameBoard
  // on the screen. -AC
  private int locX;
  private int locY;
  
  // This stores all our tiles.
  // Not sure if the ordering in the array is important yet. -AC
  private GameTile[] tiles = new GameTile[16];
  private GameTile[] sideArray = new GameTile[16];
  private GameTile[] gridArray = new GameTile[16];
  
  public GameBoard() 
  {
    for (int i=0; i<16; i++) 
    {
      tiles[i] = new GameTile(i);
      sideArray[i] = tiles[i];
      gridArray[i] = null;
    }
  }
  
  public void setScreenLoc(int x, int y) {
    locX = x;
    locY = y;
  }
  
  // The GameWindow needs to be able to get tiles, but it should not be able
  // to change the array. -AC
  public GameTile getTileByIndex(int i) 
  {
    return tiles[i];
  }
  
  /*@Override
  public void draw(Graphics g) {
    
    int size = GameTile.SIZE;
    
    // Currently we draw the empty GameBoard with
    // a grey checkerboard pattern. -AC
    for (int x=0; x<4; x++) {
      for (int y=0;y<4; y++) {
        if ((x+y)%2==0)
          g.setColor(Color.WHITE);
        else
          g.setColor(new Color(200,200,200));
        g.fillRect(locX+x*size, locY+y*size, size, size);
      }
    }
  }*/
  
  //Moves the tile to a specified location. sideArray is 0-15, gridArray 16-31. -AG
  public void moveTile(int from, int to)
  {
    //If "to" is in the gridArray. -AG
    if (to > 15)
    {
      //If "from" is in the gridArray. -AG
      if (from > 15)
      {
        //If no tile in "to" position, moves the tile. -AG
        if (gridArray[to-16] == null)
        {
          gridArray[to-16]=gridArray[from-16];
          gridArray[from-16] = null;
        }
        //Swaps tiles if there is already a tile in "to" position. -AG
        else
        {
          GameTile placeholder = gridArray[to-16];
          gridArray[to-16]=gridArray[from-16];
          gridArray[from-16] = placeholder;
        }
      }
      //If "from" is in the sideArray. -AG
      else
      {
         //If no tile in "to" position, moves the tile. -AG
          if (gridArray[to-16] == null)
          {
            gridArray[to-16] = sideArray[from];
            sideArray[from] = null;
          }
          //Swaps tiles if there is already a tile in "to" position. -AG
          else
          {
            GameTile placeholder = gridArray[to-16];
            gridArray[to-16] = sideArray[from];
            sideArray[from] = placeholder;
          }
      }
    }
    //If "to" is in the sideArray. -AG
    else
    {
      //If "from" is in the gridArray. -AG
      if (from > 15)
      {
        //If no tile in "to" position, moves the tile. -AG
        if (sideArray[to] == null)
        {
          sideArray[to]=gridArray[from-16];
          gridArray[from-16] = null;
        }
        //Swaps tiles if there is already a tile in "to" position. -AG
        else
        {
          GameTile placeholder = sideArray[to];
          sideArray[to]=gridArray[from-16];
          gridArray[from-16] = placeholder;
        }
      }
      //If "from" is in the sideArray. -AG
      else
      {
         //If no tile in "to" position, moves the tile. -AG
          if (sideArray[to] == null)
          {
            sideArray[to] = sideArray[from];
            sideArray[from] = null;
          }
          //Swaps tiles if there is already a tile in "to" position. -AG
          else
          {
            GameTile placeholder = sideArray[to];
            sideArray[to] = sideArray[from];
            sideArray[from] = placeholder;
          }
      }
    }
  }
  
  //Returns the number displayed on the Tile in the specified position of the left side of the holding area. -AG
  public int getTileInLeft(int pos)
  {
    return sideArray[pos].getNum();
  }
  
  
  //Returns the number displayed on the Tile in the specified position of the right side of the holding area. -AG
  public int getTileInRight(int pos)
  {
    return sideArray[pos+8].getNum();
  }
  
  //Returns the number displayed on the Tile in the specified position of the grid. -AG
  public int getTileInGrid(int x, int y)
  {
    return gridArray[y*4+x].getNum();
  }
  
  
  
  //Checks if each tile is in the correct position, returns true only if all
  //tiles are in the correct position. -AG
  //May be needed in future. -AL
  /*
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
  */
  
  //Assuming the grid starts at 0,0 being 250 pixels, 300 pixels. Limited
  //functionality - does not check to see whether
  //or not a tile is already at that position in the grid. -AG
  public void setTileInGrid(GameTile t, int gridX, int gridY)
  {
    //int size = GameTile.SIZE;
    //t.setScreenLoc(250+size*gridX, 300+size*gridY);
  }
  
  
  //Testing our moveTile method. -AG
  public void testBoard()
  {
    moveTile(3, 6);
    System.out.println("sideArray[3] is now " + sideArray[3].getNum() + "  and sideArray[6] is now " + sideArray[6].getNum());
    
    moveTile(1, 18);
    System.out.println("sideArray[1] is now " + sideArray[1] + "  and gridArray[2] is now " + gridArray[2].getNum());
  }
  
  
}