/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * GameBoard.java
 */

import java.awt.Image;
import java.io.File;
import java.io.IOException;


/**
 * This is the main component of the back-end
 * It is mainly responsible for handling the game state and tracking
 * where tiles are in the grid. -AC
 */
public class GameBoard {
  
  // This stores all our tiles.
  // Not sure if the ordering in the array is important yet. -AC
  private GameTile[] tiles = new GameTile[32];
  private GameTile[] sideArray = new GameTile[16];
  private GameTile[] gridArray = new GameTile[16];
  private FileReader filereader;
  
  
  public GameBoard(FileReader fr) 
  {
    filereader = fr;
   
  }
  
  //Sets the tile placements. If the game has not been played, randomizes the tile placements.
  //If it has been played, returns the tiles to original placements. -AG
  public void setTiles(FileReader fr)
  {
    if (!filereader.played())
    {
      randomizeTiles(fr);
    }
    else
    {
      for (int i = 0; i<32; i++)
      {
        Image tileImg = fr.getImageAtIndex(i);
        
        if (tileImg != null) {
          tiles[i] = new GameTile(i);
          tiles[i].setImage(tileImg);
          tiles[i].setRotation(fr.getRotation(i));
          if (i<16)
            sideArray[i] = tiles[i];
          else
            gridArray[i-16] = tiles[i];
        }
      }
    }
  }

  
  public void randomizeTiles(FileReader fr)
  {
    //Randomizing tile placements. -AG
    int i = 0;
    while ( i < 16)
    {
      int random = (int )(Math.random() * 16);
      if(tiles[random] == null)
      {
        tiles[random] = new GameTile(i);
        tiles[random].setImage(fr.getImageAtIndex(i)); 
        sideArray[random] = tiles[random];
        gridArray[i] = null;
        i++;
      }
    }
    //Setting 4 random tiles to 0 rotation. -AG
    int j = 0;
    while (j < 4)
    {
      int random = (int )(Math.random() * 16);
      tiles[random].setRotation(0);
      j++;
    }
    
    //Booleans checking whether every rotation has been used. -AG
    boolean rot1 = false;
    boolean rot2 = false;
    boolean rot3 = false;
    int k = 0;
    //Randomizing rotations. -AG
    while (k < 16)
    {
      int random = (int )(Math.random() * 3 + 1);

      if(tiles[k].getRotation() != 0)
      {
        tiles[k].setRotation(random);

        if (random == 1)
        {
          rot1 = true;
        }
        if (random == 2)
        {
          rot2 = true;
        }
        if (random == 1)
        {
          rot3 = true;
        }
      }
      k++;
    }
    
    //If one of the rotations is not used, find a random nonzero rotation and change it to specified rotation. -AG
    if(rot1 == false)
    {
      boolean zero = true;
      while (zero)
      {
        int random = (int )(Math.random() * 16);
        if(tiles[random].getRotation() != 0)
        {
          tiles[random].setRotation(1);
          zero = false;
        }
      }
    }
    if(rot2 == false)
    {
      boolean zero = true;
      while (zero)
      {
        int random = (int )(Math.random() * 16);
        if(tiles[random].getRotation() != 0)
        {
          tiles[random].setRotation(2);
          zero = false;
        }
      }
    }
    if(rot3 == false)
    {
      boolean zero = true;
      while (zero)
      {
        int random = (int )(Math.random() * 16);
        if(tiles[random].getRotation() != 0)
        {
          tiles[random].setRotation(3);
          zero = false;
        }
      }
    }
  }
  
  public FileReader getFileReader()
  {
    return filereader;
  }
  
  // The GameWindow needs to be able to get tiles, but it should not be able
  // to change the array. -AC
  public GameTile getTileByIndex(int i) 
  {
    return tiles[i];
  }
  
  
  public int getTileRotationInGrid(int x, int y) {
    if (gridArray[y*4+x] != null)
      return gridArray[y*4+x].getRotation();
    return -1;
  }
  
  public int getTileRotationInLeft(int y) {
    if (sideArray[y] != null)
      return sideArray[y].getRotation();
    return -1;
  }
  
  public int getTileRotationInRight(int y) {
    if (sideArray[y+8] != null)
      return sideArray[y+8].getRotation();
    return -1;
  }
  
  public void doRotateInGrid(int x, int y) {
    if(gridArray[y*4+x] != null)
      gridArray[y*4+x].rotateTile();
  }

  public void doRotateInLeft(int y) {
    if(sideArray[y] != null)
      sideArray[y].rotateTile();
  }

  public void doRotateInRight(int y) {
    if(sideArray[y+8] != null)
      sideArray[y+8].rotateTile();
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
  
  
  //Returns the number displayed on the Tile in the specified position
  //of the left side of the holding area. -AG
  public Image getTileInLeft(int pos)
  {
    if (sideArray[pos] != null)
      return sideArray[pos].getImage();
    return null;
  }
  
  
  //Returns the number displayed on the Tile in the
  //specified position of the right side of the holding area. -AG
  public Image getTileInRight(int pos)
  {
    if (sideArray[pos+8] != null)
      return sideArray[pos+8].getImage();
    return null;
  }
  
  //Returns the number displayed on the Tile in the
  //specified position of the grid. -AG
  public Image getTileInGrid(int x, int y)
  {
    if (gridArray[y*4+x] != null)
      return gridArray[y*4+x].getImage();
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
  
  //Resets the game by moving every tile to their
  //original position and making the grid empty. -AG
  public void resetGame()
  {
    for (int i=0; i<16; i++)
    {
      sideArray[i] = getTileByIndex(i);

      sideArray[i].setRotation(tiles[i].getOrigRotation());
    }
    for (int i=0; i<16; i++)
    {
      gridArray[i] = null;
    }
  }

  //Gets rid of all tiles in the arrays, used in preparation. -AG
  public void deleteTiles()
  {
    for (int i = 0; i< 16; i++)
    {
      sideArray[i] = null;
      gridArray[i] = null;
      tiles[i] = null;
    }
  }
  
  //Loads the maze specified in the file. -AG
  public void loadMaze(File file) throws Exception
  {
    FileReader fr = new FileReader(file);
    filereader = fr;
    filereader.loadMaze(file);

    fr.close();
  }
  
}