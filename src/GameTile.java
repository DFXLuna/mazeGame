import java.awt.Color;
import java.awt.Graphics;

public class GameTile implements Drawable {
  
  public static final int SIZE = 100;
  
  // We should probably inherit from a some kind of base class, since
  // this behavior is the same as the Board's.
  // If we do, it should probably replace the Drawable interface.
  private int locX;
  private int locY;
  
  public void setScreenLoc(int x, int y) {
    locX = x;
    locY = y;
  }
  
  // This should eventually be drawing the parts of the image.
  @Override
  public void draw(Graphics g) {
    g.setColor(Color.ORANGE);
    g.fillRect(locX, locY, SIZE, SIZE);
  }

}
