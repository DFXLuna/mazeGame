/**
 * @author Group L
 * @author Matt Grant, Adam Coggeshall, Jared Frank 
 * @author Alex Germann, Auston Larson
 * COSC 3011
 * GameBoard.java
 */

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.ByteBuffer;
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
  
  /**
   * Takes current maze orientation and writes it to file. Tiles are saved
   * in order of correct tile position. -MG
   * @param file output File
   * @throws Exception
   */
  public void saveMaze(File file) throws Exception 
  {
    // Calculate the byte length of the save file. -AC
    // Header(4), tile count(4)
    int fileSize = 8;
    
    for (int i = 0; i < filereader.getTotalTileNum(); i++) {
      // Position(4), rotation(4), line count(4), lines.
      fileSize += 12 + filereader.getTile(i).getPoints().length*4;
    }
    
    // Make a bytebuffer, backed by an array we know about. -AC
    byte[] saveArray = new byte[fileSize];
    
    ByteBuffer saveWriter = ByteBuffer.wrap(saveArray);
    
    // Write data. -AC
    saveWriter.putInt(0Xcafedeed);
    saveWriter.putInt(filereader.getTotalTileNum());
    
    for (int i=0;i<filereader.getTotalTileNum();i++) {
      GameTile tile = filereader.getTile(i);
      float[] points = tile.getPoints();
      
      saveWriter.putInt(findCurrentTilePosition(tile));
      saveWriter.putInt(tile.getRotation());
      saveWriter.putInt(points.length/4);
      for (int j=0;j< points.length; j++)
        saveWriter.putFloat(points[j]);
    }
    
    // Actually save our data. -AC
    FileOutputStream saveStream = new FileOutputStream(file);
    saveStream.write(saveArray);
    saveStream.close();
  }
  
  /**
   * Loads the maze specified in the file. -AG, MG
   * @param file output File
   * @throws Exception
   */
  public void loadMaze(File file) throws Exception
  {
    filereader = new MazeReader(file);
    setTilesFromReader();
  }
  
  /**
   * Returns GameTile's rotation in clockwise 90 degree rotations -MG
   * @param side Enum for which holder GameTile is contained in
   * @param x X coordinate of GameTile
   * @param y Y coordinate of GameTile
   * @return  GameTile's rotation in clockwise 90 degree rotations
   */
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
  /**
   * Rotates tile 90 degree clockwise. Returns -1 -MG
   * @param side Enum for which holder GameTile is contained in
   * @param x X coordinate of GameTile
   * @param y Y coordinate of GameTile
   * @return -1
   */
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
  
  /**
   * Moves the tile to a specified location. sideArray is 0-15,
   * gridArray 16-31. -AG, MG
   * @param from
   * @param to
   * @return true if move was successful
   */
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
  
  /**
   * Returns the number displayed on the Tile in the specified position
   * of the left side of the holding area. -AG, MG
   * @param side Enum for which holder GameTile is contained in
   * @param x X coordinate of GameTile
   * @param y Y coordinate of GameTile
   * @return GameTile's Image
   */
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

  /**
   * Loads tiles from the file reader. This is used on the initial load, and
   * on resets. -AC
   */
  private void setTilesFromReader()
  {
    // Clear the board. -AC
    for (int i = 0; i<16; i++) {
      gridArray[i] = null;
      sideArray[i] = null;
    }
    
    // Fill from the reader. -AC
    for (int i = 0; i<16; i++)
    {
      GameTile tile = filereader.getTile(i);
      tile.resetRotation();
      
      int tileStartPos = tile.getStartPosition();
      
      if (tileStartPos<16)
        sideArray[tileStartPos] = tile;
      else
        gridArray[tileStartPos-16] = tile;
    }
  }

/**
 * Given a tile, return the current position of the tile.
 * Really only used for saving. Given a chance to use it you should
 * probably not, as it has to check all the tiles. -AC, MG
 * @param tile GameTile to find
 * @return Integer position of GameTile
 */
private int findCurrentTilePosition(GameTile tile) {
  for (int i = 0; i<16; i++) {
    if (sideArray[i] == tile)
      return i;
    else if (gridArray[i] == tile)
      return i + 16;
  }
  return -1;
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
}