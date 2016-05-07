/**
 * @author Group L
 * @author Matt Grant, Adam Coggeshall, Jared Frank 
 * @author Alex Germann, Auston Larson
 * COSC 3011
 * ConvertData.java
 */

/**
 * Utility class for converting to and from byte arrays
 */
import java.nio.ByteBuffer;

public class ConvertData {
  
  //Converting from data types to Byte Array
  //Auxiliary methods for convertToByteArray
  public static byte[] convertToByteArray(char value) {
    byte[] bytes = new byte[2];
    ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
    buffer.putChar(value);
    return buffer.array();
}
public static byte[] convertToByteArray(int value) {
    byte[] bytes = new byte[4];
    ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
    buffer.putInt(value);
    return buffer.array();
}

public static byte[] convertToByteArray(long value) {

    byte[] bytes = new byte[8];
    ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
    buffer.putLong(value);
    return buffer.array();
}

public static byte[] convertToByteArray(short value) {

    byte[] bytes = new byte[2];
    ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
    buffer.putShort(value);
    return buffer.array();
}

public static byte[] convertToByteArray(float value) {
    byte[] bytes = new byte[4];
    ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
    buffer.putFloat(value);
    return buffer.array();
}

public static byte[] convertToByteArray(double value) {
    byte[] bytes = new byte[8];
    ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
    buffer.putDouble(value);
    return buffer.array();
}

public static byte[] convertToByteArray(String value) {

    return value.getBytes();

}

public static byte[] convertToByteArray(boolean value) {
    byte[] array = new byte[1];
    array[0] = (byte)(value == true ? 1 : 0);
    return array;
}

public static byte convertToByte(byte[] array) {

    return array[0];

}

//Converting from byte[] to data types
//Auxiliary methods for convertToValue
public static int convertToInt(byte[] array) {
    ByteBuffer buffer = ByteBuffer.wrap(array);
    return buffer.getInt();
}

public static long convertToLong(byte[] array) {
    ByteBuffer buffer = ByteBuffer.wrap(array);
    return buffer.getLong();
}

public static short convertToShort(byte[] array) {
    ByteBuffer buffer = ByteBuffer.wrap(array);
    return buffer.getShort();
}

public static String convertToString(byte[] array) {
    String value = new String(array);
    return value;
}

public static boolean convertToBoolean(byte[] array) {
    return (array[0] > 0 ? true : false );
}

public static char convertToCharacter(byte[] array) {
    ByteBuffer buffer = ByteBuffer.wrap(array);
    return buffer.getChar();
}

public static double convertToDouble(byte[] array) {
    ByteBuffer buffer = ByteBuffer.wrap(array);
    return buffer.getDouble();
}

public static float convertToFloat(byte[] array) {
    ByteBuffer buffer = ByteBuffer.wrap(array);
    return buffer.getFloat();
}

}