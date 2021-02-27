package com.vibcare.dataexport.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Encoder
{
  private static final Logger LOGGER = LoggerFactory.getLogger(Encoder.class);
  public static byte[] encodeDoubleDataToShortInByte(List<Double> data, Double coefficient)
  {
    ByteBuffer b = ByteBuffer.allocate(data.size() * 2);
    for (Double datum : data)
    {
      Short shortVal = (short) (datum / coefficient);
      b.put(shortConvertToBytes(shortVal));
    }
    return b.array();
  }

  public static byte[] shortConvertToBytes(Short val)
  {
    ByteBuffer b = ByteBuffer.allocate(2);
    b.order(ByteOrder.LITTLE_ENDIAN);
    b.putShort(val);
    return b.array();
  }

  public static ByteBuffer intConvertToBytes(Integer intVal)
  {
    ByteBuffer b = ByteBuffer.allocate(4);
    b.order(ByteOrder.LITTLE_ENDIAN);
    b.putInt(intVal);
    return b;
  }

  public static Float getMax(Float a, Float b)
  {
    return (a > b ? a : b);
  }

  public static Float findCoefficient(List<Double> doubleList)
  {
    Float maxVal = (float)Math.abs(Collections.max(doubleList));
    Float minVal = (float)Math.abs(Collections.min(doubleList));
    maxVal = getMax(maxVal, minVal);
    return maxVal / (Short.MAX_VALUE - 1);
  }

  public static String encodingPropertiesChars(String str)
  {
    try
    {
      String properDisplay = new String(str.getBytes("ISO8859_1"));
      return new String(properDisplay.getBytes(Charset.forName("GB2312")), Charset
        .forName("ISO8859_1"));
    }
    catch (UnsupportedEncodingException e)
    {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }
}
