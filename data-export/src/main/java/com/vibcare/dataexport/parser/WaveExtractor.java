package com.vibcare.dataexport.parser;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.vibcare.dataexport.db.util.DataConverter;
import com.vibcare.dataexport.util.*;


public class WaveExtractor
{

  public static Wave parse(File wavFile) throws IOException
  {
    Wave w = new Wave();
    FileInputStream fis = null;

    // create file input stream
    fis = new FileInputStream(wavFile);
    byte[] allBytes = fis.readAllBytes();
    try
    {
      w.HEADER_VERSION = convertTo16Int(allBytes, 0, 2);
      w.DATA_SIZE = convertTo32Int(allBytes, 2, 6);
      w.DATA_ANALYSIS = convertTo16Int(allBytes, 6, 8);
      w.SCALE_COEFFICIENT = ByteHelper.toFloat(Decoder.getBytes(allBytes, 8, 8 + 4));
      w.SCALE_OFFSET = ByteHelper.toFloat(Decoder.getBytes(allBytes, 12, 12 + 4));
      w.WORKING_CONDITION = getFromRange(allBytes, 80, 80 + 16);
      w.TIMESTAMP = getFromRange(allBytes, 208, 208 + 32);
      w.SAMPLING_COUNTS = convertTo32Int(allBytes, 272, 272 + 4);

      //w.timestamp = Long.valueOf(convertToInt(allBytes, 300, 307)) * 1000;
      w.MEAN_GEN_SPEED = ByteHelper.toFloat(Decoder.getBytes(allBytes, 164, 168));
      w.MAX_GEN_POWER = ByteHelper.toFloat(Decoder.getBytes(allBytes, 144, 144 + 4));

      w.MIN_GEN_SPEED = ByteHelper.toFloat(Decoder.getBytes(allBytes, 160, 160 + 4));
      w.SAMPLING_RATE = Decoder.byteArrayTo32Int(Decoder.getBytes(allBytes, 272, 272 + 4));
      w.VIB_PP = ByteHelper.toFloat(Decoder.getBytes(allBytes, 302, 302 + 4));
      w.COMTYPE = Decoder.convertTo2UInt(Decoder.getBytes(allBytes, 284, 286));
      w.VALUE_TYPE = Decoder.convertTo2UInt(Decoder.getBytes(allBytes, 288, 290));
      w.WAVE_LEN = convertTo32Int(allBytes, 290, 294);
      w.DATA = DataConverter.convertToListOfFloat(Decoder.getBytes(allBytes, 513, 513 + w.WAVE_LEN));
    } catch (RuntimeException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return w;
  }

  private static void debug(byte[] allBytes) throws IOException
  {
    File fiche = new File("allBytes");
    FileWriter f = new FileWriter(fiche);
    for (byte d : allBytes)
    {
      f.write(d + "\n");
    }
  }

  private static String getFromRange(byte[] allBytes, int start, int end)
  {
    return new String(Decoder.getBytes(allBytes, start, end));
  }

  private static int convertTo32Int(byte[] allBytes, int start, int end)
  {
    byte[] buf = Decoder.getBytes(allBytes, start, end);
    return Decoder.byteArrayTo32Int(buf);
  }

  private static int convertTo16Int(byte[] allBytes, int start, int end)
  {
    byte[] buf = Decoder.getBytes(allBytes, start, end);
    return Decoder.byteArrayTo16Int(buf);
  }

}
