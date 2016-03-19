 /**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * Main.java
 */
import javax.swing.*;
import java.awt.*;

public class Main 
{
  
  // Probably should declare any buttons here
  public JButton lbutton,rbutton, mbutton;
  
  
  public static void main(String[] args)
  {
    // This is the play area
    GameBoard board = new GameBoard();
    GameWindow game = new GameWindow(board);
    
    try {
      // The 4 that installed on Linux here
      // May have to test on Windows boxes to see what is there.
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      //This is the "Java" or CrossPlatform version and the default
      //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
      //Linux only
      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
      // really old style Motif 
      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    } 
    catch (UnsupportedLookAndFeelException e) {
     // handle possible exception
    }
    catch (ClassNotFoundException e) {
     // handle possible exception
    }
    catch (InstantiationException e) {
     // handle possible exception
    }
    catch (IllegalAccessException e) {
     // handle possible exception
    }
  
  }
  
};
