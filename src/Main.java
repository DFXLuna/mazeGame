/**
 * @author Kim Buckner
 * Date: Feb 19, 2016
 *
 * A starting point for the COSC 3011 programming assignment
 * Probably need to fix a bunch of stuff, but this compiles and runs.
 *
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
    GameWindow game = new GameWindow("Group X aMaze");
    
    // have to override the default layout to reposition things!!!!!!!

    game.setSize(new Dimension(900, 1000));
    
    // So the debate here was, do I make the GameWindow object the game
    // or do I make main() the game, manipulating a window?
    // Should GameWindow methods know what they store?
    // Answer is, have the "game" do it.
    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    game.getContentPane().setBackground(Color.cyan);
    game.setUp();
    
    game.setVisible(true);

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