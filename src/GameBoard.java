/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * GameBoard.java
 */

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * This is the main component of the back-end
 * It is mainly responsible for handling the game state and tracking
 * where tiles are in the grid. -AC
 */
public class GameBoard {
  
  // This stores all our tiles.
  // Not sure if the ordering in the array is important yet. -AC
  //private GameTile[] tiles = new GameTile[32];
  
  private GameTile[] sideArray = new GameTile[16];
  private GameTile[] gridArray = new GameTile[16];
  
  private MazeReader filereader;
  
  public void saveMaze(File file) throws Exception 
  {
    FileOutputStream out = new FileOutputStream(file);
    int played = 0Xcafedeed;
    
    out.write(ConvertData.convertToByteArray(played));
    out.write(ConvertData.convertToByteArray(filereader.getTotalTileNum()));
    
    // Save sides. -AC
    for (int i=0;i<16;i++) {
      GameTile tile = sideArray[i];
      if (tile != null) {
        out.write(ConvertData.convertToByteArray(i));
        out.write(ConvertData.convertToByteArray(tile.getRotation()));
        out.write(filereader.getLineChunk(tile.getId()));
      }
    }
    
    // Save grid. -AC
    for (int i=0;i<16;i++) {
      GameTile tile = gridArray[i];
      if (tile != null) {
        out.write(ConvertData.convertToByteArray(i+16));
        out.write(ConvertData.convertToByteArray(tile.getRotation()));
        out.write(filereader.getLineChunk(tile.getId()));
      }
    }
    
    out.close();
  }
  
  //Loads the maze specified in the file. -AG
  public void loadMaze(File file) throws Exception
  {
    filereader = new MazeReader(file);
    setTilesFromReader();
  }
  
  public int getTileRotation(BoardSide side, int x, int y) {
    switch (side) {
    case CENTER:
      if (gridArray[y*4+x] != null)
        return gridArray[y*4+x].getRotation();
      break;
    case LEFT:
      if (sideArray[y] != null)
        return sideArray[y].getRotation();
      break;
    case RIGHT:
      if (sideArray[y+8] != null)
        return sideArray[y+8].getRotation();
      break;
    }
    return -1;
  }
  
  public Object doRotate(BoardSide side, int x, int y) {
    switch (side) {
    case CENTER:
      if (gridArray[y*4+x] != null)
        gridArray[y*4+x].rotateTile();
      break;
    case LEFT:
      if (sideArray[y] != null)
        sideArray[y].rotateTile();
      break;
    case RIGHT:
      if (sideArray[y+8] != null)
        sideArray[y+8].rotateTile();
      break;
    }
    return -1;
  }
  
  //Moves the tile to a specified location. sideArray is 0-15,
  //gridArray 16-31. -AG
  public boolean moveTile(int from, int to)
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
          return true;
        }
        //Return false if the space is occupied. -AC
        else
        {
          return false;
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
            return true;
          }
          //Return false if the space is occupied. -AC
          else
          {
            return false;
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
          return true;
        }
        //Return false if the space is occupied. -AC
        else
        {
          return false;
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
            return true;
          }
          //Return false if the space is occupied. -AC
          else
          {
            return false;
          }
      }
    }
  }
  
  /**
   * Resets the game by moving every tile to their
   * original position and making the grid empty. -AG
   */
  public void resetGame()
  {
    // Note that this was originally more complex. It now serves as a wrapper
    // for setTilesFromReader(), but we're keeping it this way since
    // resetGame() is a more descriptive name for anything external of the
    // board that needs to use it, while setTilesFromReader() is a better
    // description of what's going on internally. -AC
    setTilesFromReader();
  }
  
  //Returns the number displayed on the Tile in the specified position
  //of the left side of the holding area. -AG
  public Image getTile(BoardSide side, int x, int y)
  {
    switch (side) {
    case CENTER:
      if (gridArray[y*4+x] != null)
        return gridArray[y*4+x].getImage();
      break;
    case LEFT:
      if (sideArray[y] != null)
        return sideArray[y].getImage();
      break;
    case RIGHT:
      if (sideArray[y+8] != null)
        return sideArray[y+8].getImage();
      break;
    }
    return null;
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

  /**
   * Loads tiles from the file reader. This is used on the initial load, and
   * on resets.
   */
  private void setTilesFromReader()
  {
    for (int i = 0; i<32; i++)
    {
      Image tileImg = filereader.getImageAtIndex(i);
      
      GameTile tile = null;
      if (tileImg != null) {
        tile = new GameTile(i,filereader.getRotation(i),tileImg);
      }
      
      if (i<16)
        sideArray[i] = tile;
      else
        gridArray[i-16] = tile;
    }
  }
}