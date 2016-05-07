/**
 * @author Group L
 * @author Matt Grant, Adam Coggeshall, Jared Frank 
 * @author Alex Germann, Auston Larson
 * COSC 3011
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
 
  //value if maze has been played -MG
  private int played = 0Xcafedeed;
  
  // Positions, rotations, and point arrays, stored in solved order.
  // These are initialized when the maze is loaded. -AC
  private int[] tilePositions;
  private int[] tileRotations;
  private float[][] tilePoints;
  
  // Similar to above, stores tiles in solved order. This is the only of the
  // arrays that external classes will have access to. -AC
  private GameTile[] tiles;

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
  
  /**
   * Gets a tile. Index is based on the SOLVED ORDER! -AC
   * @return GameTile
   */
  public GameTile getTile(int index) {
    return tiles[index];
  }
  
  /**
   * Reads file into GameTileObjects -MG
   * @throws Exception
   */
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
    
    tilePositions = new int[totalTileNum];
    tileRotations = new int[totalTileNum];
    tilePoints = new float[totalTileNum][];
    
    tiles = new GameTile[totalTileNum];
    
    for(int i = 0; i < totalTileNum; i++)
    {
      tilePositions[i] = readInt();
      tileRotations[i] = readInt();
      tilePoints[i] = readPoints();
    }
    
    if (!beenPlayed) {
      randomize();
    }
    
    for(int i = 0; i < totalTileNum; i++) {
      tiles[i] = 
          new GameTile(tilePositions[i], tileRotations[i], tilePoints[i]);
    }
  }
  
  /**
   * Randomizes GameTile positions and rotations -MG
   */
  private void randomize() {
    // Set an even distribution of rotations. -AC
    tileRotations = new int[] {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3};
    
    for (int i=0;i<16;i++) {
      
      // Randomize positions. -AC
      int j2 = (int)(Math.random() * 16);
      int pos = tilePositions[i];
      tilePositions[i] = tilePositions[j2];
      tilePositions[j2] = pos;
      
      // Randomize rotations. -AC
      int j = (int)(Math.random() * 16);
      int rot = tileRotations[i];
      tileRotations[i] = tileRotations[j];
      tileRotations[j] = rot;
    }
  }
  
  /** 
   * Reads in 4 bytes from file and converts to int. -AL, MG
   * @return int
   * @throws IOException
   */
  private int readInt() throws IOException
  {
    int Int;
    read(byteArray);
    Int = ConvertData.convertToInt(byteArray);
    return Int;
  }
  
  /**
   * Reads in 4 bytes from file and converts to float. -AL, MG
   * @return float
   * @throws IOException
   */
  private float readFloat() throws IOException
  {
    float Float;
    read(byteArray);
    Float = ConvertData.convertToFloat(byteArray);
    return Float;
  }
  
  /**
   * Read the point count and all the points, returning an array of floats.
   * This way we can both create the image and keep this array for saving. 
   * The returned array is guaranteed to be valid for tile creation. -AC, MG
   * @return float[]
   * @throws IOException
   */
  private float[] readPoints() throws IOException {
    int lineNum = readInt();
    
    float[] points = new float[lineNum*4];
    
    for (int i=0;i<points.length;i++) {
      points[i] = readFloat();
    }
    
    return points;
  }
}