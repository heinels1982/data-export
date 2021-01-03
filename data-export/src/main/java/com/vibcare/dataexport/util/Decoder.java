package com.vibcare.dataexport.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Decoder
{
  public static int convertTo2UInt(byte[] bytes)
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

  public static long byteArrayTo64Int(byte[] encodedValue) {
    long res = 0;
    for (int i = 0; i < encodedValue.length; i++) {
      res += (long)(encodedValue[i] & 0xFF) << ((encodedValue.length - 1 - i) * 8);
    }
    return res;
  }

  public static int byteArrayTo16Int(byte[] encodedValue)
  {
    int r = encodedValue[1] & 0xFF;
    return (r << 8) | (encodedValue[0] & 0xFF);

  }

  public static byte[] getBytes(byte[] allBytes, int start, int i)
  {
    return Arrays.copyOfRange(allBytes, start, i );
  }

  public static String convertToUTF8(byte[] bytes)
  {
    return new String(bytes, StandardCharsets.UTF_8);
  }


  public static float toFloat(byte[] bytes)
  {
    int asInt = (bytes[0] & 0xFF)
                | ((bytes[1] & 0xFF) << 8)
                | ((bytes[2] & 0xFF) << 16)
                | ((bytes[3] & 0xFF) << 24);
    return Float.intBitsToFloat(asInt);
  }
}
