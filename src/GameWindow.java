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
import javax.swing.*;
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

    /*
     * Here I declare some buttons and declare an array to hold the grid elements. 
     * But, you can do what you want.
     */
    private int startAt=1;
    
    /**
     * Constructor sets the window name using super(), changes the layout,
     * which you really need to read up on, and maybe you can see why I chose
     * this one.
     *
     * @param s
     */

    public GameWindow(String s)
    {
      super(s);
      GridBagLayout gbl=new GridBagLayout();
      setLayout(gbl);
    }

    /**
     * For the buttons
     * @param e is the ActionEvent
     * 
     * BTW can ask the event for the name of the object generating event.
     * The odd syntax for non-java people is that "exit" for instance is
     * converted to a String object, then that object's equals() method is
     * called.
     */

    public void actionPerformed(ActionEvent e) {
      if("exit".equals(e.getActionCommand()))
        System.exit(0);
      if("reset".equals(e.getActionCommand()))
        System.out.println("reset pressed\n");
      if("new".equals(e.getActionCommand()))
        System.out.println("new pressed\n");
      }

    /**
     *  Establishes the inital board
     */

    public void setUp()
    {
      //actually create the array for elements, make sure it is big enough
      
      // Need to play around with the dimensionts and the gridx/y values
      // These constraints are going to be added to the pieces/parts I 
      // stuff into the "GridBag".
      GridBagConstraints basic = new GridBagConstraints();
      basic.gridx=startAt;
      basic.gridy=0;
      basic.gridwidth=1;
      basic.gridheight=1;
      basic.fill=GridBagConstraints.BOTH;

      //Here I create 16 elements to put into my gameBoard

      // Now I add each one, modifying the default gridx/y and add
      // it along with the modified constraint

      this.addButtons();
      return;
      
      
    }
    /**
     * Used by setUp() to configure the buttons on a button bar and
     * add it to the gameBoard
     */

    public void addButtons(){

      return;
    }

  };