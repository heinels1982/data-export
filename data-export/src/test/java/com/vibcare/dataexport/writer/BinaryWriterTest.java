package com.vibcare.dataexport.writer;

import java.io.File;
import java.io.IOException;


import org.junit.jupiter.api.Test;

import com.vibcare.dataexport.parser.Wave;
import com.vibcare.dataexport.parser.WaveExtractor;

import static org.junit.jupiter.api.Assertions.*;

class BinaryWriterTest
{
  int DATA_SIZE_VALUE = 64;
  @Test
  public void test() throws IOException
  {
    BinaryWriter writer = new BinaryWriter(513);
    writer.storeINT32(2,4, DATA_SIZE_VALUE);
    writer.write("aaa");
    File file = new File("aaa");
    Wave w = WaveExtractor.parse(file);
    assertEquals(DATA_SIZE_VALUE, w.DATA_SIZE);
    System.out.println(w);
  }

  @Test
  void storeINT16()
  {
  }

  @Test
  void write()
  {
  }

  @Test
  void storeINT32()
  {

  }
}