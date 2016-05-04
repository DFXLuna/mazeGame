/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * MazeReader.java
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MazeReader extends FileInputStream
{
  private int totalTileNum;
  
  //Either played or orig will be present at top of .mze file to determine
  //if the file has been played or not. If no value is present, file is not 
  //valid. -MG
  
  //Value if maze has not been played -MG
  private int orig = 0Xcafebeef;
 
  //value if maze has been played
  private int played = 0Xcafedeed;
  
  // Values that correspond to file format. -AL
  private int tileNum;
  
  private int[] rotations = new int[32];
  // Contains images generated for tiles. -AL
  private Image[] tileImages = new Image[32];
  private byte[][] lineChunks = new byte[32][];

  private byte[] byteArray = new byte[4];
  
  private boolean beenPlayed = false;
  
  /**
   * MazeReader constructor. Reads a maze file, storing all the relevant
   * information in a hopefully accessible way. -AC
   */
  public MazeReader (File file) throws Exception
  {
    super(file);
    loadMaze();
    close();
  }
  
  public int getTotalTileNum()
  {
    return totalTileNum;
  }
  
  public int getRotation(int index)
  {
    return rotations[index];
  }
  
  /** 
   * Returns tile image from array. -AL
   */
  public Image getImageAtIndex(int index)
  {
    return tileImages[index];
  }
  
  // Return the chunk that needs to be written back out to a save file, for
  // a specific tile. -AC
  public byte[] getLineChunk(int id) {
    return lineChunks[id];
  }
  
  //Returns whether the maze has been played or not, specified in the file. -AG
  /*public boolean played()
  {
    return beenPlayed;
  }*/
  
  
  private void loadMaze() throws Exception
  {
    int compare = readInt();
    if (compare == played)
    {
      this.beenPlayed = true;
    }
    if(compare != played && compare != orig)
    {
       throw new Exception("Not a valid maze file.");
    }
    totalTileNum = readInt();
    for(int i = 0; i < totalTileNum; i++)
    {
      tileNum = readInt();
      rotations[i] = readInt();
      tileImages[tileNum] = makeImage(i);
    }
    
    if (!beenPlayed) {
      randomize();
    }
  }
  
  private void randomize() {
    // Set an even distribution of rotations. -AC
    rotations = new int[] {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3};
    
    for (int i=0;i<16;i++) {
      
      // Randomize rotations. -AC
      int j = (int)(Math.random() * 16);
      int rot = rotations[i];
      rotations[i] = rotations[j];
      rotations[j] = rot;
      
      // Randomize tiles. -AC
      int j2 = (int)(Math.random() * 16);
      Image img = tileImages[i];
      tileImages[i] = tileImages[j2];
      tileImages[j2] = img;
      
      byte[] chunk = lineChunks[i];
      lineChunks[i] = lineChunks[j2];
      lineChunks[j2] = chunk;
    }
  }
  
  /** 
   * Reads in 4 bytes from file and converts to int. -AL
   */
  private int readInt() throws IOException
  {
    int Int;
    read(byteArray);
    Int = ConvertData.convertToInt(byteArray);
    return Int;
  }
  
  /**
   * Reads in 4 bytes from file and converts to float. -AL
   */
  private float readFloat() throws IOException
  {
    float Float;
    read(byteArray);
    Float = ConvertData.convertToFloat(byteArray);
    return Float;
  }
  
  
  /** 
   * Reads in data for lines and makes them into an Image. -AL
   * Id is the number to save the line chunk under. -AC
   */
  private Image makeImage(int id) throws IOException
  {
    // pointsArray stores points for drawing lines. -AL
    // The format: x1 in index 0, y1 in index 1,
    //             x2 in index 2, y2 in index 3.
    
    int lineNum = readInt();
    
    // I'm sorry. -AC
    skip(-4);
    byte[] lineChunk = new byte[4+lineNum*16];
    read(lineChunk);
    skip(-lineNum*16);
    
    lineChunks[id] = lineChunk;
    
    //byte[] lineChunk = 
    
    Float[] pointsArray = new Float[4];
    
    // Setting up the tile image. -AL
    BufferedImage image = new BufferedImage(
        TileDrawer.TILE_SIZE,
        TileDrawer.TILE_SIZE,
        BufferedImage.TYPE_INT_RGB );
    
    // Makes the background of each tile image white. -AL
    Graphics2D g2d = image.createGraphics();
    g2d.setBackground(Color.WHITE);
    g2d.fillRect(0, 0, TileDrawer.TILE_SIZE, TileDrawer.TILE_SIZE);
    
    float temp;
    
    // Reads in the information for drawing the lines, and draws them. -AL
    for(int lines = 0; lines < lineNum; lines++)
    {
      for(int point = 0; point < 4; point++)
      {
        temp = readFloat();
        pointsArray[point] = temp;
      }
      g2d.setColor(Color.BLACK);
      g2d.drawLine(pointsArray[0].intValue(), pointsArray[1].intValue(),
          pointsArray[2].intValue(), pointsArray[3].intValue());
    }
    
    return image;
  }
}