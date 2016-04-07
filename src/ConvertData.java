import java.nio.ByteBuffer;

public class ConvertData {
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

public static Object convertToValue(Class aClass, byte[] inputArray) throws Exception {

    Object returnValue = null;
    String className = aClass.getName();
    if (className.equals(Integer.class.getName())) {
        returnValue = new Integer(convertToInt(inputArray));
    } else if (className.equals(String.class.getName()))    {
        returnValue = convertToString(inputArray);
    } else if (className.equals(Byte.class.getName())) {
        returnValue = new Byte(convertToByte(inputArray));
    } else if (className.equals(Long.class.getName())) {
        returnValue = new Long(convertToLong(inputArray));
    } else if (className.equals(Short.class.getName())) {
        returnValue = new Short(convertToShort(inputArray));
    } else if (className.equals(Boolean.class.getName())) {
        returnValue = new Boolean(convertToBoolean(inputArray));
    }else {
        throw new Exception("Cannot convert object of type " + className);
    }
    return returnValue;
}

public static byte[] convertToByteArray(Object object) throws Exception {

    byte[] returnArray = null;
    Class clazz = object.getClass();
    String clazzName = clazz.getName();

    if (clazz.equals(Integer.class)) {
        Integer aValue = (Integer)object;
        int intValue = aValue.intValue();
        returnArray = convertToByteArray(intValue);
    } else if (clazz.equals(String.class)) {
        String aValue = (String)object;
        returnArray = convertToByteArray(aValue);
    } else if (clazz.equals(Byte.class)) {
        Byte aValue = (Byte)object;
        byte byteValue = aValue.byteValue();
        returnArray = convertToByteArray(byteValue);
    } else if (clazz.equals(Long.class)) {
        Long aValue = (Long)object;
        long longValue = aValue.longValue();
        returnArray = convertToByteArray(longValue);
    } else if (clazz.equals(Short.class)) {
        Short aValue = (Short)object;
        short shortValue = aValue.shortValue();
        returnArray = convertToByteArray(shortValue);
    } else if (clazz.equals(Boolean.class)) {
        Boolean aValue = (Boolean)object;
        boolean booleanValue = aValue.booleanValue();
        returnArray = convertToByteArray(booleanValue);
    } else if (clazz.equals(Character.class)) {
        Character aValue = (Character)object;
        char charValue = aValue.charValue();
        returnArray = convertToByteArray(charValue);
    } else if (clazz.equals(Float.class)) {
        Float aValue = (Float)object;
        float floatValue = aValue.floatValue();
        returnArray = convertToByteArray(floatValue);
    } else if (clazz.equals(Double.class)) {
        Double aValue = (Double)object;
        double doubleValue = aValue.doubleValue();
        returnArray = convertToByteArray(doubleValue);
    } else {

        throw new Exception("Cannot convert object of type " + clazzName);
    }

    return returnArray;
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