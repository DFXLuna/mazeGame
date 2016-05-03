 /**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * GameWindow.java
 */

// CONTAINS ANONYMOUS CLASSES IN mouseReleased, openFileMenu, and!
// These anonymous classes generally encapsulates single action and are used
// for event handlers. -AC

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameWindow extends JFrame
  implements MouseListener, MouseMotionListener
  {
    /**
     * because it is a serializable object, need this or javac
     * complains a lot
     */
    public static final long serialVersionUID=1;
    
    private ArrayList<VisualTileHolder> tileHolders = 
        new ArrayList<VisualTileHolder>();
    
    private Messenger messenger;
    
    // We store the most recent mouse event so we can access the mouse's
    // position in our paint method. -AC
    private MouseEvent lastMouseEvent;
    
    // We use this to implement double buffering. Everything is drawn to this
    // buffer, then the buffer is drawn to the screen in one go.
    // This prevents screen flickering. It seems that some components will do
    // this automatically, but JFrame is not one of them. -AC
    private BufferedImage backBuffer;
    
    /**
     * The constructor sets up the UI.
     * We pass it a reference to the backend GameBoard. -AC
     */
    public GameWindow(Messenger messenger)
    {
      super("Group L aMaze");
      
      this.messenger = messenger;
      
      setupGame();
      setupUI();
      File file = new File("default.mze");
      tryLoadMaze(file,true);
    }

    /**
     *  Sets up the UI.
     *  Registers event listeners. -AC
     */

    private void setupUI()
    {
      Dimension windowSize = new Dimension(900, 1000);
      
      // Configure the window. -AC
      setSize(windowSize);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane().setBackground(new Color(150,150,150));
      
      // Set up out backbuffer. -AC
      backBuffer = new BufferedImage(
          windowSize.width,
          windowSize.height,
          BufferedImage.TYPE_INT_RGB );
      
      // Add event listeners. -AC
      addMouseListener(this);
      addMouseMotionListener(this);
      
      this.addButtons();
      
      setVisible(true);
    }
    
    /**
     * Sets up the visual representation of tile containers.
     */
    private void setupGame() {
      VisualTileHolderCenter board =
          new VisualTileHolderCenter(messenger, 250, 250);
      
      VisualTileHolderSide leftSide =
          new VisualTileHolderSide(messenger, BoardSide.LEFT, 50, 80);
      
      VisualTileHolderSide rightSide = 
          new VisualTileHolderSide(messenger, BoardSide.RIGHT, 750, 80);
      
      tileHolders.add( board );
      tileHolders.add( leftSide );
      tileHolders.add( rightSide );
    }
    
    
    /**
     * Used by setupUI() to create and configure the buttons. -AC
     */
    private void addButtons(){
      
      // We decided to create the layout here, and use a FlowLayout.
      // It handles buttons much more naturally than a GridLayout.
      // If we really need to add more elements using more advanced
      // layout, we can add another container later. -AC
      FlowLayout button_layout = new FlowLayout(FlowLayout.LEFT, 5, 5);
      this.setLayout(button_layout);
      
      Button btn_file = new Button("File");
      btn_file.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
          openFileMenu();
        }
      });
      this.add(btn_file);
      
      Button btn_reset = new Button("Reset");
      btn_reset.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
          messenger.resetGame();
          repaint();
        }
      });
      this.add(btn_reset);
      
      Button btn_quit = new Button("Quit");
      btn_quit.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
          if (promptSaveIfChanged())
            System.exit(0);
        }
      });
      this.add(btn_quit);
    }

    private void openFileMenu() {
      JPopupMenu j = new JPopupMenu();
      
      JMenuItem loadItem = new JMenuItem("Load");
      loadItem.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
          openLoadMenu();
        }
      });
      
      j.add(loadItem);
      
      
      JMenuItem saveItem = new JMenuItem("Save");
      saveItem.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
          openSaveMenu();
        }
      });
      
      j.add(saveItem);
      
      // We need to repaint the window if the popup menu dissapears -AC
      j.addPopupMenuListener(new PopupMenuListener() {
        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
          repaint();
        }
        
        public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {}
        public void popupMenuCanceled(PopupMenuEvent arg0) {}
      });
      
      j.show(this, 6, 54);
    }
    
    private void openLoadMenu() {
      // Bail out of the function if they hit the cancel button. -AC
      if (!promptSaveIfChanged())
        return;
      
      JFileChooser filePicker = 
          new JFileChooser(System.getProperty("user.dir"));
      int result = filePicker.showOpenDialog(this);
      
      File loadFile = filePicker.getSelectedFile();
      if (result == JFileChooser.APPROVE_OPTION && loadFile != null) {
        System.out.println("Load menu file: " + loadFile.toString() + " and attempting to load maze");
        tryLoadMaze(loadFile,false);
        this.repaint();
      }
    }
    
    /**
     * Opens the save menu, returns true if the file was saved.
     */
    private boolean openSaveMenu() {
      JFileChooser filePicker = 
          new JFileChooser(System.getProperty("user.dir"));
      int result = filePicker.showSaveDialog(this);
      
      File saveFile = filePicker.getSelectedFile();
      if (result == JFileChooser.APPROVE_OPTION && saveFile != null) {
        if (saveFile.exists()) {
          int res = JOptionPane.showConfirmDialog(
              this,
              "The file \""+saveFile+"\" already exists. Overwrite? \n" +
              "(Selecting \"No\" will return you to the save dialog.)",
              "Prompt",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE);
          
          if (res==1) { //NO
            return openSaveMenu();
          } else if (res==2) { //CANCEL
            return false;
          }
        }
        
        try {
          messenger.saveMaze(saveFile);
          
        } catch(Exception e) {
          JOptionPane.showMessageDialog(
              this,
              "Failed to save the file \"" + saveFile + "\". \n"+
              e.getMessage(),
              "Error",
              JOptionPane.ERROR_MESSAGE);
          // I wasn't sure if we should retry the save here, but I figured
          // it would be best to stay consistent between failing on a load
          // and a save. Returning false here ensures the caller won't go
          // through with any actions if a save was expected. -AC
          return false;
        }
        
        return true;
      }
      return false;
    }
    
    /**
     * Attempts to open a file. Displays a dialog if unsuccessful.
     * If retry is true, open the load menu instead. -AC
     */
    private void tryLoadMaze(File file,boolean retry) {
      String errorMsg = null;
      
      if (!file.exists())
        errorMsg = "The file does not seem to exist.";
      else if (!file.canRead())
        errorMsg = "Could not read the maze file. \n"+
        "Do you have permission to open it?";
      
      try {
        System.out.println("Maze attempt load");
        messenger.loadMaze(file);

      } catch (Exception e) {
        errorMsg = e.getMessage();
      }
      
      if (errorMsg != null) //&& !errorMsg.equalsIgnoreCase("16")) makes the game load "played.mze" and not display the weird error
      {
        System.out.println(errorMsg);
        if (retry) {
          JOptionPane.showMessageDialog(
              this,
              "Couldn't load the default maze file. \n" +
              "( "+errorMsg+" ) \n" +
              "Please select a file.",
              "Notification",
              JOptionPane.INFORMATION_MESSAGE);
          openLoadMenu();
        } else {
          JOptionPane.showMessageDialog(
              this,
              "Failed to load the file \"" + file + "\". \n"+
              errorMsg,
              "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    }
    
    /**
     * Ask the user if they would like to save, if and only if the maze has
     * been modified. Return false if the Cancel button is pressed, or if the
     * user agreed to save the file and never followed through. -AC
     */
    private boolean promptSaveIfChanged() {
      if (messenger.gameHasChanged()) {
        int res = JOptionPane.showConfirmDialog(
            this,
            "The maze has been modified. \nWould you like to save?",
            "Prompt",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        switch (res) {
        case 0: // Yes
          return openSaveMenu();
        case 2: // Cancel
          return false;
        }
      }
      return true;
    }
    
    /**
     * We decided to draw the game board and tiles ourselves, rather than
     * extending UI components. This now calls the draw method of each
     * tile holder. The tile holders are responsible for drawing their
     * tiles. We draw the currently dragged tile ourselves. -AC
     */
    @Override
    public void paint(Graphics windowGraphics) {
      // Use our backbuffer for all the drawing. -AC
      Graphics2D g = backBuffer.createGraphics();
      
      // Call the super paint method. Seems to clear the window to the
      // background color. -AC
      super.paint(g);
      
      // Draw tileholders and their contained tiles. -AC
      for (VisualTileHolder holder : tileHolders) {
        holder.draw(g);
      }
      
      // We draw the tile currently being dragged. -AC
      Image draggedTileImage = messenger.getDraggedTileImage();
      if (draggedTileImage != null) {
        // We position the dragged tile so that its center is on the cursor.
        // I had considered keeping the offset consistent with the offset
        // when the drag starts, but this is a good deal less complicated
        // and the positions that will allow for a valid drop will make a bit
        // more sense. -AC
        int draggedX = lastMouseEvent.getX() - TileDrawer.TILE_SIZE/2;
        int draggedY = lastMouseEvent.getY() - TileDrawer.TILE_SIZE/2;
        int dragRot = messenger.getDragRotation();
        TileDrawer.drawTile(g, draggedX, draggedY, draggedTileImage, dragRot);
      }
      
      windowGraphics.drawImage(backBuffer, 0, 0, null);
    }
    
    // Here we handle mouse input. We end up with some empty methods since
    // we have to implement everything in the MouseListener interface. -AC

    @Override
    public void mousePressed(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1) {
        for (VisualTileHolder holder : tileHolders) {
          Image tileImage = holder.getTileImageFromClick(e);
          // If there is a tile present, then we can start the drag! -AC
          if (tileImage != null) {
            int slot = holder.getSlotFromClick(e);
            int rot = holder.getRotationFromClick(e);
            
            messenger.setDragInfo(slot, tileImage, rot);
            lastMouseEvent = e;
            this.repaint();
            
            break;
          }
        }
      } else if (e.getButton() == MouseEvent.BUTTON3) {
        for (VisualTileHolder holder : tileHolders) {
          holder.rotateTileFromClick(e);
        }
        this.repaint();
      }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      // We only have to handle a drop if we are currently dragging. -AC
      if (messenger.getDraggedTileImage() != null) {
        for (VisualTileHolder holder : tileHolders) {
          int destinationSlot = holder.getSlotFromClick(e);
          // If we have a destination slot, do a swap. -AC
          if (destinationSlot >= 0) {
            boolean success = messenger.moveTile(
                messenger.getDragSourceSlot(), destinationSlot);
            // If we couldn't move the tile, make the destination tile flash.
            if (!success) {
              // Make this image final because I'm paranoid about the
              // anonymous class. Java 8 should be fine with an
              // "effectively" final variable, but I'll explicitly make it
              // final just in case. -AC
              final Image blockingTileImg = holder.getTileImageFromClick(e);
              
              // We don't flash if we're placing the tile back where we
              // got it from. -AC
              if (blockingTileImg != messenger.getDraggedTileImage()) {
                TileDrawer.setFlash(blockingTileImg, true);
                
                // Anonymous class here. It contains the task to disable the
                // flash after a specified amount of time. It also enjoys
                // pretending to be a closure. -AC
                TimerTask unflashTask = new TimerTask() {
                  @Override
                  public void run() {
                    TileDrawer.setFlash(blockingTileImg, false);
                    repaint();
                  }
                };
                
                Timer unflashTimer = new Timer();
                unflashTimer.schedule(unflashTask, 500);
              }
            }
            break;
          }
        }
        messenger.clearDragInfo();
        this.repaint();
      }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
      // Store the mouse's position by saving the event, and repaint the window
      // so dragged tiles display correctly. -AC
      lastMouseEvent = e;
      repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
      // Do nothing. -AC
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      // Do nothing. -AC
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
      // Do nothing for now. I was worried that not handling this event could
      // cause problems with the drag and drop, but it seems to work okay
      // so far. -AC
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
      // Do nothing. We only listen for the drag event.
    }
  };
