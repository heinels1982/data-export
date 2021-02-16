package com.vibcare.dataexport.util;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EncoderTest
{
  private static final double COEFFICIENT = 0.000012619292647286784;

  @Test
  public void testEncodeDoubleDataToShortInByte()
  {
    List<Double> doubleList = List.of(1.594140625, -2.874, 1.5874, -2.047487);
    double coefficient = Encoder.findCoefficient(doubleList);
    byte[] bytes = Encoder.encodeDoubleDataToShortInByte(doubleList, coefficient);
    assertNotNull(bytes);
    List<Short> shortValList = Decoder.convertToListOfShort(bytes);
    System.out.println(shortValList.get(3) * coefficient);
    assertEquals(1.594093755722395, shortValList.get(0) * coefficient);
    assertEquals(-2.874, shortValList.get(1) * coefficient);
    assertEquals(1.5873398644936825, shortValList.get(2) * coefficient);
    assertEquals(-2.047481596777147, shortValList.get(3) * coefficient);
  }

}