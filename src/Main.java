/**
 * @author Group L
 * @author Matt Grant, Adam Coggeshall, Jared Frank 
 * @author Alex Germann, Auston Larson
 * COSC 3011
 * Main.java
 */
import javax.swing.*;


public class Main 
{
  /**
   * 
   * @param args Unused
   * @throws Exception
   */
  public static void main(String[] args) throws Exception
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
    
    //File file = new File("default.mze");
    //MazeReader fr = new MazeReader(file);
    
    // Create game window and board. -AC
    GameBoard board = new GameBoard();
    Messenger messenger = new Messenger(board);
    new GameWindow(messenger);
  }
  
};
