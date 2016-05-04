/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 02
 * Messenger.java
 */
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * This is the communication module between the front and back-end
 * It is responsible for all method calls to the back-end, and 
 *responses to the front-end. -AG
 */


public class Messenger 
{
  private GameBoard board;
  private boolean changed = false;
  
  public Messenger(GameBoard gameboard)
  {
    board = gameboard;
  }
  

  //Resets the game and the game's "changed" state. -AG
  public void resetGame()
  {
    board.resetGame();
    this.changed = false;
  }
  
  /**
   * Attempt to open a maze. Throw exception on failure. -AC
   */
  public void loadMaze(File file) throws Exception
  {
    board.loadMaze(file);
    this.changed = false;
  }
  
  /**
   * Attempt to save the maze. Throw exception on failure. -AC
   */
  public void saveMaze(File file) throws Exception 
  {
    board.saveMaze(file);
    this.changed = false;
  }
  
  /** Check if the game has changed since last load/save. -AC */
  public boolean gameHasChanged() {
    if (changed)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  // Calls back-end method which moves the tile from a given position to a
  // given position. -AG
  
  // No longer swaps tiles, now returns false on failure to move tiles. -AC
  public boolean moveTile(int from, int to)
  {
    changed = true;
    return board.moveTile(from, to);
  }
  
  // Calls back-end method which returns the number of a tile in a given
  // position in the grid. -AG
  public Image getTile(BoardSide side, int x, int y)
  {
    return board.getTile(side, x, y);
  }
  
  public void doRotate(BoardSide side, int x, int y) 
  {
    changed = true;
    board.doRotate(side, x, y);
  }
  
  public int getTileRotation(BoardSide side, int x, int y) 
  {
    return board.getTileRotation(side, x, y);
  }
  
  // The Messenger holds drag information. It includes the source slot, dragged
  // tile number, and possibly x/y offsets from the mouse. -AC
  
  // Now also includes dragged tile rotation! -AC
  private int dragSourceSlot = -1;
  private Image dragTileImage = null;
  private int dragRotation = -1;
  
  public void setDragInfo(int sourceSlot, Image tileImage, int rot) {
    dragSourceSlot = sourceSlot;
    dragTileImage = tileImage;
    dragRotation = rot;
  }
  
  public void clearDragInfo() {
    setDragInfo(-1,null,-1);
  }
  
  public Image getDraggedTileImage() {
    return dragTileImage;
  }
  
  public int getDragSourceSlot() {
    return dragSourceSlot;
  }
  
  public int getDragRotation() {
    return dragRotation;
  }
}
