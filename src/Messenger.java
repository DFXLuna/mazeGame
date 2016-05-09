/**
 * @author Group L
 * @author Matt Grant, Adam Coggeshall, Jared Frank 
 * @author Alex Germann, Auston Larson
 * COSC 3011
 * Messenger.java
 */
import java.awt.Image;
import java.io.File;


/**
 * This is the communication module between the front and back-end
 * It is responsible for all method calls to the back-end, and 
 * responses to the front-end. -AG
 * <p>
 * The Messenger holds drag information. It includes the source slot, dragged
 * tile number, rotation and possibly x/y offsets from the mouse. -AC, MG
 */



public class Messenger 
{
  private GameBoard board;
  private boolean changed = false;
  

  private int dragSourceSlot = -1;
  private Image dragTileImage = null;
  private int dragRotation = -1;
  
  public Messenger(GameBoard gameboard)
  {
    board = gameboard;
  }
  

  /**
   * Resets the game and the game's changed field -AG, MG
   */
  public void resetGame()
  {
    board.resetGame();
    this.changed = false;
    board.resetTimer();
  }
  
  /**
   * Checks if the game has been won. -AG
   * @return victory condition
   */
  public boolean determineIfWon()
  {
    return board.determineIfWon();
  }
  
  /**
   * Attempt to open a maze. Throw exception on failure. -AC, MG 
   * @param file Input File to read maze from
   * @throws Exception
   */
  public void loadMaze(File file) throws Exception
  {
    board.loadMaze(file);
    this.changed = false;
  }
  
  /**
   * Attempt to save the maze. Throw exception on failure. -AC, MG
   * @param file output File to write maze to
   * @throws Exception
   */
  public void saveMaze(File file) throws Exception 
  {
    board.saveMaze(file);
    this.changed = false;
  }
  
  /** 
   * Check if the game has changed since last load/save. -AC, MG
   * @return changed field
   */
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
  
 /**
 * Calls back-end method which moves the tile from a given position to a
 * given position. -AG
 * No longer swaps tiles, now returns false on failure to move tiles. -AC, MG
 * @param from Integer position of GameTile's original location
 * @param to Integer position of GameTile's new location
 * @return true if move was successful
 */
  public boolean moveTile(int from, int to)
  {
    changed = true;
    return board.moveTile(from, to);
  }
  
  /** Calls board.getTile which returns the ID of a tile in a given -MG
  * position in the grid. -AG, MG
  * @param side Position of tile among Left, center and right holders
  * @param x X coordinate of GameTile in holder
  * @param y Y coordinate of GameTile in holder
  * @return Image of tile at given position or null if no tile present
  */
  public Image getTile(BoardSide side, int x, int y)
  {
    return board.getTile(side, x, y);
  }
  
  /**
  * Rotates tile 90 degrees clockwise in holder side at position x,y -MG
  * @param side Position of tile among Left, center and right holders
  * @param x X coordinate of GameTile in holder
  * @param y Y coordinate of GameTile in holder
  */
  public void doRotate(BoardSide side, int x, int y) 
  {
    changed = true;
    board.doRotate(side, x, y);
  }
  /**
  * Returns GameTile's rotation in number of clockwise 90 degree rotations -MG
  * @param side Position of tile among Left, center and right holders
  * @param x X coordinate of GameTile in holder
  * @param y Y coordinate of GameTile in holder
  * @return GameTile's rotation in number of clockwise 90 degree rotations
  */
  public int getTileRotation(BoardSide side, int x, int y) 
  {
    return board.getTileRotation(side, x, y);
  }
  
  /**
   * Sets dragSourceSlot, DragTileImage and DragRotation from GameTile's fields -MG
   * @param sourceSlot GameTile's original position
   * @param tileImage GameTile's image
   * @param rot GameTile's rotation
   */
  public void setDragInfo(int sourceSlot, Image tileImage, int rot) {
    dragSourceSlot = sourceSlot;
    dragTileImage = tileImage;
    dragRotation = rot;
  }
  
  /**
   * Clears dragSourceSlot, DragTileImage and DragRotation -MG
   */
  public void clearDragInfo() {
    setDragInfo(-1,null,-1);
  }
  
  /**
   * 
   * @return Dragged GameTile's image -MG
   */
  public Image getDraggedTileImage() {
    return dragTileImage;
  }
 
  /**
   * 
   * @return Dragged GameTile's original slot -MG
   */
  public int getDragSourceSlot() {
    return dragSourceSlot;
  }
  
  /**
   * 
   * @return Dragged GameTile's rotation -MG
   */
  public int getDragRotation() {
    return dragRotation;
  }
}
