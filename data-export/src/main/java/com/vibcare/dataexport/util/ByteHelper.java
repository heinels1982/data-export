package com.vibcare.dataexport.util;

public class ByteHelper
{

  public static float toFloat(byte[] bytes)
  {
    int asInt = (bytes[0] & 0xFF)
                | ((bytes[1] & 0xFF) << 8)
                | ((bytes[2] & 0xFF) << 16)
                | ((bytes[3] & 0xFF) << 24);
    return Float.intBitsToFloat(asInt);
  }
}
