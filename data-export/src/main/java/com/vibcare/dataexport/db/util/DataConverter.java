package com.vibcare.dataexport.db.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataConverter
{
  private static final int FOUR_BYTES_STEP = 4;

  public static List<Double> convertToListOfFloat(byte[] allBytes)
  {
    List<Double> result = new ArrayList<>();
    for (int i = 0; i < allBytes.length; i = i + FOUR_BYTES_STEP)
    {
      byte[] subArray = Arrays.copyOfRange(allBytes, i, i + FOUR_BYTES_STEP);
      float f = ByteHelper.toFloat(subArray);
      result.add((double) f);
    }
    return result;
  }
}
