 /**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * Main.java
 */
import javax.swing.*;
import java.io.IOException;
import java.awt.*;

public class Main 
{
  
  public static void main(String[] args) throws IOException
  {
    // Testing FileReader
    FileReader fr = new FileReader("default.mze");
    // Create game window and board.
    // Most of the game logic is handled by these two classes. -AC
    GameBoard board = new GameBoard(fr);
    Messenger messenger = new Messenger(board);
    GameWindow window = new GameWindow(messenger);
    
    // I am still not sure how we should be handling these.
    // The current LookAndFeel seems to work on both Windows and Linux. -AC
    try {
      // The 4 that installed on Linux here
      // May have to test on Windows boxes to see what is there.
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      //This is the "Java" or CrossPlatform version and the default
      //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
      //Linux only
      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
      // really old style Motif 
      //UIManager.setLookAndFeel
      //("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
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
