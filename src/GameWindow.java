 /**
 * @author Kim Buckner
 * Date: Feb 19, 2016
 *
 * This is the actual "game". May/will have to make some major changes.
 * This is just a "hollow" shell.
 *
 * When you get done, I should see the buttons at the top in the "play" area
 * (not a pull-down menu). The only one that should do anything is Quit.
 *
 * Should also see something that shows where the 4x4 board and the "spare"
 * tiles will be when we get them stuffed in.
 */

/* ousndounsdfn */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameWindow extends JFrame
  {
    /**
     * because it is a serializable object, need this or javac
     * complains a lot
     */
    public static final long serialVersionUID=1;
    
    private GameBoard board;
    
    /**
     * The constructor sets up the UI.
     * We pass it a reference to the backend GameBoard.
     */

    public GameWindow(GameBoard board)
    {
      super("Group L aMaze");
      
      this.board = board;
      
      setupUI();
    }

    /**
     *  Sets up the UI.
     *  Also sets the initial position of the board and tiles.
     */

    public void setupUI()
    {
      setSize(new Dimension(900, 1000));
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane().setBackground(Color.cyan);
      
      // Set the location of the board.
      board.setScreenLoc(250, 300);
      
      // Set the initial position of all the tiles.
      for (int i=0; i<16; i++) {
        GameTile t = board.getTileByIndex(i);
        if (i<8)
          t.setScreenLoc(20, 60+i*110);
        else
          t.setScreenLoc(780, 60+(i-8)*110);
      }
      
      this.addButtons();
      
      setVisible(true);
    }
    /**
     * Used by setupUI() to create and configure the buttons.
     */

    public void addButtons(){
      
      // We only use the layout for the buttons.
      // If we need to add more elements somewhere else,
      // we can make another container
      FlowLayout button_layout = new FlowLayout(FlowLayout.LEFT, 5, 5);
      this.setLayout(button_layout);
      
      Button btn_new = new Button("New Game");
      btn_new.addActionListener((ActionEvent e) -> System.out.println("New Game"));
      this.add(btn_new);
      
      Button btn_reset = new Button("Reset");
      btn_reset.addActionListener((ActionEvent e) -> System.out.println("Reset"));
      this.add(btn_reset);
      
      Button btn_quit = new Button("Quit");
      btn_quit.addActionListener((ActionEvent e) -> System.exit(0));
      this.add(btn_quit);
    }
    
    
    /**
     * We decided to draw the game board and tiles ourselves, rather than
     * extending UI components.
     */
    @Override
    public void paint(Graphics g) {
      super.paint(g);
      
      board.draw(g);
      
      for (int i=0; i<16; i++) {
        GameTile t = board.getTileByIndex(i);
        t.draw(g);
      }
    }
  };