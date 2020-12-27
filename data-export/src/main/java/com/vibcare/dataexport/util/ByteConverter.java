package com.vibcare.dataexport.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class ByteConverter
{

  public static byte[] convertToByteArray(short value)
  {

    byte[] bytes = new byte[2];
    ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
    buffer.putShort(value);
    return buffer.array();
  }

  public static byte[] convertToByteArray(int i)
  {

    return ByteBuffer.allocate(4).putInt(i).array();
  }
}
