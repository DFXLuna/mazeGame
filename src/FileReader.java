/**
 * @author Group L
 * Matt Grant, Adam Coggeshall, Jared Frank, Alex Germann, Auston Larson
 * COSC 3011 Program 01
 * FileReader.java
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader extends FileInputStream
{
  // Contains images generated for tiles. -AL
  private Image[] tileImages = new Image[16];
  
  private String fileName;
  private byte[] byteArray = new byte[4];
  private int totalTileNum;
  
  private int orig = 0Xcafebeef;
  private int played = 0Xcafedeed;
  private boolean beenPlayed = false;
  
  // Values that correspond to file format. -AL
  private int tileNum;
  private int lineNum;
  // No clue if this is correct, just trying to get it in a working state. -AC
  private int[] rotations = new int[32];
  
      
  public FileReader(String fileName) throws IOException 
  {
    super(fileName);
    //loadMaze(fileName);
  }
  
  /*
  public FileReader (File file) throws Exception
  {
    super(file);
    loadMaze(file);

  }*/
  
  /*
  public void loadMaze(String fileName) throws IOException
  {

    this.fileName = fileName;
    int compare = readInt();
    totalTileNum = readInt();
    if (compare == played)
    {
      beenPlayed = true;
    }
    
    for(int i = 0; i < totalTileNum; i++)
    {
      tileNum = readInt();
      rotations[i] = readInt();
      lineNum = readInt();
      tileImages[tileNum] = makeImage(lineNum);
    }
  }*/
  
  public void loadMaze(File file) throws Exception
  {
    //this.fileName = file;
    int compare = readInt();
    if (compare == played)
    {
      beenPlayed = true;
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
      lineNum = readInt();
      tileImages[tileNum] = makeImage(lineNum);
    }
  }
  
  public int getRotation(int index)
  {
    return rotations[index];
  }
  
  public boolean played()
  {
    return beenPlayed;
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
   */
  private Image makeImage(int lineNum) throws IOException
  {
    // pointsArray stores points for drawing lines. -AL
    // The format: x1 in index 0, y1 in index 1,
    //             x2 in index 2, y2 in index 3.
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
    
    // Reads in the information for drawing the lines, and draws them. -AL
    for(int lines = 0; lines < lineNum; lines++)
    {
      for(int point = 0; point < 4; point++)
      {
        pointsArray[point] = readFloat();
      }
      g2d.setColor(Color.BLACK);
      g2d.drawLine(pointsArray[0].intValue(), pointsArray[1].intValue(),
          pointsArray[2].intValue(), pointsArray[3].intValue());
    }
    return image;
  }
  
  
  /** 
   * Returns tile image from array. -AL
   */
  public Image getImageAtIndex(int index)
  {
    return tileImages[index];
  }
  
}