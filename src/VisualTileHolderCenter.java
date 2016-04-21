/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * VisualTileHolderCenter.java
 */
import java.awt.Image;
import java.awt.Point;

public class VisualTileHolderCenter extends VisualTileHolder {

  protected VisualTileHolderCenter(Messenger msgr, int x, int y) {
    super(msgr, x, y, 4, 4);
  }
  
  @Override
  protected Image getTileImageAt(Point loc) {
    return getMessenger().getTileInGrid(loc.x, loc.y);
  }

  @Override
  protected int getSlotIdAt(Point loc) {
    return 16+loc.x+loc.y*4;
  }

  @Override
  protected int getTileRotationAt(Point loc) {
    return getMessenger().getTileRotationInGrid(loc.x, loc.y);
  }

  @Override
  protected void doRotateAt(Point loc) {
    getMessenger().doRotateInGrid(loc.x, loc.y);
  }
}