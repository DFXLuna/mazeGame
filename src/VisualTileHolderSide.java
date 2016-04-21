/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * VisualTileHolderSide.java
 */
import java.awt.Image;
import java.awt.Point;

public class VisualTileHolderSide extends VisualTileHolder {
  // We need to know what side we are so we can query the messenger for the
  // correct information. -AC
  private BoardSide side;
  
  protected VisualTileHolderSide(Messenger msgr, BoardSide side, int x, int y) {
    super(msgr, x, y, 1, 8);
    this.side = side;
  }
  
  @Override
  protected Image getTileImageAt(Point loc) {
    if (side==BoardSide.LEFT)
      return getMessenger().getTileInLeft(loc.y);
    else
      return getMessenger().getTileInRight(loc.y);
  }

  @Override
  protected int getSlotIdAt(Point loc) {
    if (side==BoardSide.LEFT)
      return loc.y;
    else
      return loc.y+8;
  }

  @Override
  protected int getTileRotationAt(Point loc) {
    if (side==BoardSide.LEFT)
      return getMessenger().getTileRotationInLeft(loc.y);
    else
      return getMessenger().getTileRotationInRight(loc.y);
  }

  @Override
  protected void doRotateAt(Point loc) {
    if (side==BoardSide.LEFT)
      getMessenger().doRotateInLeft(loc.y);
    else
      getMessenger().doRotateInRight(loc.y);
  }
}