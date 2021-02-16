package com.vibcare.dataexport.db.util;

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

  public static short byte2short(byte[] b){
    short l = 0;
    for (int i = 0; i < 2; i++) {
      l<<=8; //<<=和我们的 +=是一样的，意思就是 l = l << 8
      l |= (b[i] & 0xff); //和上面也是一样的  l = l | (b[i]&0xff)
    }
    return l;
  }
}
