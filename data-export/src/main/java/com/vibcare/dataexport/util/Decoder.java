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

  public static int byteArrayTo16Int(byte[] encodedValue)
  {
    int r = encodedValue[1] & 0xFF;
    return (r << 8) | (encodedValue[0] & 0xFF);

  }

  public static byte[] getBytes(byte[] allBytes, int start, int i)
  {
    return Arrays.copyOfRange(allBytes, start, i + 1);
  }

  public static String convertToUTF8(byte[] bytes)
  {
    return new String(bytes, StandardCharsets.UTF_8);
  }


}
