/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * FrontEndGameBoard.java
 */
import java.awt.Image;

public class FrontEndGameBoard extends FrontEndTileHolder {

  protected FrontEndGameBoard(Messenger msgr, int x, int y) {
    super(msgr, x, y, 4, 4);
  }
  
  @Override
  protected Image getTileImageAt(int x, int y) {
    return getMessenger().getTileInGrid(x, y);
  }

  @Override
  protected int getSlotIdAt(int x, int y) {
    return 16+x+y*4;
  }
}