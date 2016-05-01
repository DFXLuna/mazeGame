 /**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * Main.java
 */
import javax.swing.*;
import java.io.IOException;

public class Main 
{
  
  public static void main(String[] args) throws IOException
  {
    try {   
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      
      // This is the default.
      // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    } 
    catch (Exception e) {
      // We seem to have no intention of actually dealing with these, so
      // we may as well just do a catch-all. If the look and feel fails to
      // get selected, the default seems to work fine as well. -AC
    }
    
    // Moved after the look and feel setup, otherwise the look and feel will
    // change mid-startup. -AC
    
    // Testing FileReader
    FileReader fr = new FileReader("default.mze");
    
    // Create game window and board. -AC
    GameBoard board = new GameBoard(fr);
    Messenger messenger = new Messenger(board);
    new GameWindow(messenger);
  }
  
};
