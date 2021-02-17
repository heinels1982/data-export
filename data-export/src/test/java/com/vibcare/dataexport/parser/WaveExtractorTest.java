package com.vibcare.dataexport.parser;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import static com.vibcare.dataexport.parser.WaveExtractor.parse;

class WaveExtractorTest
{

  @Test
  public void test1() throws IOException
  {
    File waveFile = new File(
      "/home/heinels/dev/work/data-export/src/main/resources/test/data/示例数据/仪征/AAAA_01_20190717132547_CH11_Cond1.dat");
    Wave wave = parse(waveFile);
    System.out.println(wave);
  }

  @Test
  public void test2() throws IOException
  {
    File waveFile = new File(
      "/home/heinels/dev/work/data-export/test");
    Wave wave = parse(waveFile);
    System.out.println(wave);
  }

}