 /**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * FrontEndGameBoard.java
 */

public class FrontEndGameBoard extends FrontEndTileHolder {

  protected FrontEndGameBoard(Messenger msgr, int x, int y) {
    super(msgr, x, y, 4, 4);
  }
  
  @Override
  protected int getTileNumberAt(int x, int y) {
    return getMessenger().getTileInGrid(x, y);
  }
}