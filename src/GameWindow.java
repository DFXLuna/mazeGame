 /**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * GameWindow.java
 */

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener
  {
    /**
     * because it is a serializable object, need this or javac
     * complains a lot
     */
    public static final long serialVersionUID=1;
    
    private ArrayList<FrontEndTileHolder> tileHolders = new ArrayList<FrontEndTileHolder>();
    
    private Messenger messenger;
    
    /** This determines the size that tiles are rendered at. It also controls
     * the size of the containers.
     */
    public static final int TILE_SIZE = 80;
    
    /**
     * The constructor sets up the UI.
     * We pass it a reference to the backend GameBoard. -AC
     */
    public GameWindow(Messenger messenger)
    {
      super("Group L aMaze");
      
      this.messenger = messenger;
      
      setupUI();
      setupGame();
    }

    /**
     *  Sets up the UI.
     *  Also sets the initial position of the board and tiles. -AC
     */

    public void setupUI()
    {
      setSize(new Dimension(900, 1000));
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane().setBackground(Color.cyan);
      
      // Set the location of the board. -AC
      /*board.setScreenLoc(250, 300);
      
      // Set the initial position of all the tiles. -AC
      for (int i=0; i<16; i++) {
        GameTile t = board.getTileByIndex(i);
        if (i<8)
          t.setScreenLoc(20, 60+i*110);
        else
          t.setScreenLoc(780, 60+(i-8)*110);
      }
      */
      this.addButtons();
      
      setVisible(true);
    }
    
    /**
     * Sets up the visual representation of tile containers.
     */
    public void setupGame() {
      tileHolders.add( new FrontEndGameBoard(290, 300) );
      tileHolders.add( new FrontEndSideHolder(50, 80) );
      tileHolders.add( new FrontEndSideHolder(770, 80) );
    }
    
    
    /**
     * Used by setupUI() to create and configure the buttons. -AC
     */
    public void addButtons(){
      
      // We decided to create the layout here, and use a FlowLayout.
      // It handles buttons much more naturally than a GridLayout.
      // If we really need to add more elements using more advanced
      // layout, we can add another container later. -AC
      FlowLayout button_layout = new FlowLayout(FlowLayout.LEFT, 5, 5);
      this.setLayout(button_layout);
      
      Button btn_new = new Button("New Game");
      btn_new.setActionCommand("new");
      btn_new.addActionListener(this);
      this.add(btn_new);
      
      Button btn_reset = new Button("Reset");
      btn_reset.setActionCommand("reset");
      btn_reset.addActionListener(this);
      this.add(btn_reset);
      
      Button btn_quit = new Button("Quit");
      btn_quit.setActionCommand("quit");
      btn_quit.addActionListener(this);
      this.add(btn_quit);
    }
    
    // Here we handle events generated by the buttons. -AC
    @Override
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
      case "new":
        System.out.println("New Game");
        break;
      case "reset":
        System.out.println("Reset");
        break;
      case "quit":
        System.exit(0);
        break;
      }
    }
    
    
    /**
     * We decided to draw the game board and tiles ourselves, rather than
     * extending UI components. This calls the draw method of board and
     * each tile. -AC
     */
    @Override
    public void paint(Graphics g) {
      super.paint(g);
      
      for (FrontEndTileHolder holder : tileHolders) {
        holder.draw(g);
      }
      
      /*
      board.draw(g);
      
      for (int i=0; i<16; i++) {
        GameTile t = board.getTileByIndex(i);
        t.draw(g);
      }*/
    }
    
  };
  
  class Messenger {
    
  }
