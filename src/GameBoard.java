/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * GameBoard.java
 */

import java.awt.Image;
import java.io.IOException;


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
  private FileReader filereader;
  
  
  public GameBoard(FileReader fr) 
  {
    filereader = fr;
    randomizeTiles(fr);
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
      if(tiles[j].getRotation() != 0)
      {
        tiles[j].setRotation(random);
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
  
  //Moves the tile to a specified location. sideArray is 0-15,
  //gridArray 16-31. -AG
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
        //Tile goes back to its place if there is already a tile in "to" position. -AG
        else
        {
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
          //Tile goes back to its place if there is already a tile in "to" position. -AG
          else
          {
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
        //Tile goes back to its place if there is already a tile in "to" position. -AG
        else
        {
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
          //Tile goes back to its place if there is already a tile in "to" position. -AG
          else
          {
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
  
  //Assuming the grid starts at 0,0 being 250 pixels, 300 pixels. Limited
  //functionality - does not check to see whether
  //or not a tile is already at that position in the grid. -AG
  public void setTileInGrid(GameTile t, int gridX, int gridY)
  {
    //int size = GameTile.SIZE;
    //t.setScreenLoc(250+size*gridX, 300+size*gridY);
  }
  
  //Resets the game by moving every tile to their
  //original position and making the grid empty. -AG
  public void resetGame()
  {
    for (int i=0; i<16; i++)
    {
      sideArray[i] = getTileByIndex(i);
    }
    for (int i=0; i<16; i++)
    {
      gridArray[i] = null;
    }
  }
  
  //Makes a new game by creating a new set of Tiles and setting the old Tiles equal to them.
  public void newGame()
  {
    GameTile[] newTiles = new GameTile[16];
    
    int i = 0;
    while ( i < 16)
    {
      int random = (int )(Math.random() * 16);
      if(newTiles[random] == null)
      {
        newTiles[random] = new GameTile(i);
        newTiles[random].setImage(filereader.getImageAtIndex(i)); 
        sideArray[random] = newTiles[random];
        gridArray[i] = null;
        i++;
      }
    }
    
    //Setting 4 random tiles to 0 rotation. -AG
    int j = 0;
    while (j < 4)
    {
      int random = (int )(Math.random() * 16);
      newTiles[random].setRotation(0);
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
      if(newTiles[j].getRotation() != 0)
      {
        newTiles[j].setRotation(random);
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
        if(newTiles[random].getRotation() != 0)
        {
          newTiles[random].setRotation(1);
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
        if(newTiles[random].getRotation() != 0)
        {
          newTiles[random].setRotation(2);
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
        if(newTiles[random].getRotation() != 0)
        {
          newTiles[random].setRotation(3);
          zero = false;
        }
      }
    }
    
    //Setting the tiles equal to the new tileset we created. -AG
    for(int l = 0; l<16; l++)
    {
      tiles[l] = newTiles[l];
    }

  }
  
  
  //Testing our moveTile method. -AG
  public void testBoard()
  {
    moveTile(3, 6);
    System.out.println("sideArray[3] is now " + sideArray[3].getImage() +
        "  and sideArray[6] is now " + sideArray[6].getImage());
    
    moveTile(1, 18);
    System.out.println("sideArray[1] is now " + sideArray[1]
        + "  and gridArray[2] is now " + gridArray[2].getImage());
  }
  
  
}