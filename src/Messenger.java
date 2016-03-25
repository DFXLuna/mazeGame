 /**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 02
 * Messenger.java
 */

/**
 * This is the communication module between the front and back-end
 * It is responsible for all method calls to the back-end, and responses to the front-end. -AG
 */


public class Messenger 
{
  private GameBoard board;
  
  public Messenger(GameBoard gameboard)
  {
    board = gameboard;
  }
  
  //Calls back-end method which returns the number of a tile in a given position in the left side array. -AG
  public int getTileInLeft(int pos)
  {
    return board.getTileInLeft(pos);
  }
  
  // Calls back-end method which returns the number of a tile in a given
  // position in the right side array. -AG
  public int getTileInRight(int pos)
  {
    return board.getTileInRight(pos);
  }
  
  // Calls back-end method which returns the number of a tile in a given
  // position in the grid. -AG
  public int getTileInGrid(int x, int y)
  {
    return board.getTileInGrid(x, y);
  }
  
  // Calls back-end method which moves the tile from a given position to a
  // given position, swapping tiles if need be. -AG
  public void movetile(int from, int to)
  {
    board.moveTile(from, to);
  }
  
  // The Messenger holds drag information. It includes the source slot, dragged
  // tile number, and possibly x/y offsets from the mouse.
  private int dragSourceSlot = -1;
  private int dragTileNumber = -1;
  
  public void setDragInfo(int sourceSlot, int tileNumber) {
    dragSourceSlot = sourceSlot;
    dragTileNumber = tileNumber;
  }
  
  public void clearDragInfo() {
    setDragInfo(-1,-1);
  }
  
  public int getDraggedTileNumber() {
    return dragTileNumber;
  }
  
  public int getDragSourceSlot() {
    return dragSourceSlot;
  }
  
  
}
