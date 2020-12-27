package com.vibcare.dataexport.parser;

import java.util.ArrayList;
import java.util.List;

public class DataParser
{

  public static List<Double> extract(int[] dataBytes, int totalNumberOfChannel, int channelNumber, double amplification)
  {
    List<Double> resultList = new ArrayList();
    int channelOffset = (channelNumber - 1);
    for (int i = 0 + channelOffset; i < dataBytes.length; i = i + (totalNumberOfChannel))
    {
      Double value = dataBytes[i] * amplification;
      resultList.add(value);
    }
    return resultList;
  }
}
