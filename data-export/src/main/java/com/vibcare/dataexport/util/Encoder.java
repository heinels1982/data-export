package com.vibcare.dataexport.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.List;

public class Encoder
{
  private static final double COEFFICIENT = 1.2619292647286784E-5;
  public static byte[] encodeDoubleDataToShortInByte(List<Double> data, Double coefficient) {
    ByteBuffer b = ByteBuffer.allocate(data.size() * 2);
    for(Double datum: data) {
      Short shortVal = (short)(datum/coefficient);
      b.put(shortConvertToBytes(shortVal));
    }
    return b.array();
}

  public static byte[] encodeDoubleDataToIntInByte(List<Double> data, Double coefficient) {
    ByteBuffer b = ByteBuffer.allocate(data.size() * 2);
    for(Double datum: data) {
      Short shortVal = (short)(datum/coefficient);
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

  public static double getMax(double a, double b)
  {
    return (a > b ? a : b);
  }

  public static double findCoefficient(List<Double> doubleList)
  {
    Double maxVal = Math.abs(Collections.max(doubleList));
    Double minVal = Math.abs(Collections.min(doubleList));
    maxVal = getMax(maxVal, minVal);
    return maxVal/(Short.MAX_VALUE - 1);
  }
}
