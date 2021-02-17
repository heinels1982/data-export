package com.vibcare.dataexport.util;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EncoderTest
{

  @Test
  public void testEncodeDoubleDataToShortInByte_1()
  {
    List<Double> doubleList = List.of(1.594140625, -2.874, 1.5874, -2.047487);
    double coefficient = Encoder.findCoefficient(doubleList);
    byte[] bytes = Encoder.encodeDoubleDataToShortInByte(doubleList, coefficient);
    List<Short> shortValList = Decoder.convertToListOfShort(bytes);

    for (int i = 0; i < shortValList.size(); i++)
    {
      double rational = (shortValList.get(i) * coefficient) / (doubleList.get(i));
      System.out.println(rational);
      assertTrue(rational > 0.99 && rational <= 1);
    }
  }

  @Test
  public void testEncodeDoubleDataToShortInByte_2()
  {
    List<Double> defaultWaveData = List.of(0.125, -0.5478, 0.872);
    double coefficient = Encoder.findCoefficient(defaultWaveData);
    byte[] bytes = Encoder.encodeDoubleDataToShortInByte(defaultWaveData, coefficient);
    List<Short> shortValList = Decoder.convertToListOfShort(bytes);

    for (int i = 0; i < shortValList.size(); i++)
    {
      double rational = (shortValList.get(i) * coefficient) / (defaultWaveData.get(i));
      System.out.println(rational);
      assertTrue(rational > 0.99 && rational <= 1);
    }

  }

}