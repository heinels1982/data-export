package com.vibcare.dataexport.util;

import org.junit.jupiter.api.Test;

class DecoderTest
{
  @Test
  public void testConvertTo2UInt()
  {
    byte[] bytes1 = new byte[] {88, 18};
    System.out.println(Decoder.byteArrayTo16Int(bytes1) * 2.6612952450711103E-5);

    byte[] bytes2 = new byte[] {-103, -81};
    System.out.println(Decoder.byteArrayTo16Int(bytes2) * 2.6612952450711103E-5);

    byte[] bytes3 = new byte[] {-2, 127};
    System.out.println(Decoder.byteArrayTo16Int(bytes3) * 2.6612952450711103E-5);


  }

}