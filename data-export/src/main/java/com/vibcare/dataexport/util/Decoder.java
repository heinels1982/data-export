package com.vibcare.dataexport.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Decoder
{
  public static final int TWO_BYTES_STEP = 2;

  public static short convertTo2UInt(byte[] bytes)
  {
    ByteBuffer bb = ByteBuffer.allocate(2);
    bb.order(ByteOrder.LITTLE_ENDIAN);
    bb.put(bytes[0]);
    bb.put(bytes[1]);
    short shortVal = bb.getShort(0);
    return shortVal;
  }

  public static int byteArrayTo32Int(byte[] encodedValue)
  {
    int value = (encodedValue[3] << (Byte.SIZE * 3));
    value |= (encodedValue[2] & 0xFF) << (Byte.SIZE * 2);
    value |= (encodedValue[1] & 0xFF) << (Byte.SIZE * 1);
    value |= (encodedValue[0] & 0xFF);
    return value;
  }

  public static long byteArrayTo64Int(byte[] encodedValue)
  {
    ByteBuffer bb = ByteBuffer.allocate(8);
    bb.order(ByteOrder.LITTLE_ENDIAN);
    bb.put(encodedValue[0]);
    bb.put(encodedValue[1]);
    bb.put(encodedValue[2]);
    bb.put(encodedValue[3]);
    bb.put(encodedValue[4]);
    bb.put(encodedValue[5]);
    bb.put(encodedValue[6]);
    bb.put(encodedValue[7]);
    long longVal = bb.getLong(0);

    return longVal;
  }

  public static int byteArrayTo16Int(byte[] encodedValue)
  {
    int r = encodedValue[1] & 0xFF;
    return (r << 8) | (encodedValue[0] & 0xFF);

  }

  public static byte[] getBytes(byte[] allBytes, int start, int i)
  {
    return Arrays.copyOfRange(allBytes, start, i);
  }

  public static float toFloat(byte[] bytes)
  {
    int asInt = (bytes[0] & 0xFF)
                | ((bytes[1] & 0xFF) << 8)
                | ((bytes[2] & 0xFF) << 16)
                | ((bytes[3] & 0xFF) << 24);
    return Float.intBitsToFloat(asInt);
  }

  public static String convertToString(byte[] allBytes, int start, int end)
  {
    return new String(getBytes(allBytes, start, end));
  }

  public static List<Short> convertToListOfShort(byte[] allBytes)
  {
    List<Short> result = new ArrayList<>();
    for (int i = 0; i < allBytes.length; i = i + TWO_BYTES_STEP)
    {
      byte[] subArray = Arrays.copyOfRange(allBytes, i, i + TWO_BYTES_STEP);
      short f = Decoder.convertTo2UInt(subArray);
      result.add(f);
    }
    return result;
  }
}
