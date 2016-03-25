 /**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * FrontEndSideHolder.java
 */

public class FrontEndSideHolder extends FrontEndTileHolder {
  // We need to know what side we are so we can query the messenger for the
  // correct information. -AC
  private BoardSide side;
  
  protected FrontEndSideHolder(Messenger msgr, BoardSide side, int x, int y) {
    super(msgr, x, y, 1, 8);
    this.side = side;
  }
  
  @Override
  protected int getTileNumberAt(int x, int y) {
    if (side==BoardSide.LEFT)
      return getMessenger().getTileInLeft(y);
    else
      return getMessenger().getTileInRight(y);
  }
}