package com.vibcare.dataexport.parser;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.vibcare.dataexport.db.util.DataConverter;
import com.vibcare.dataexport.util.Decoder;

import static com.vibcare.dataexport.util.Decoder.byteArrayTo64Int;


public class WaveExtractor
{

  public static Wave parse(File wavFile) throws IOException
  {
    Wave w = new Wave();
    FileInputStream fis;

    // create file input stream
    fis = new FileInputStream(wavFile);
    byte[] allBytes = fis.readAllBytes();
    try
    {
      w.HEADER_VERSION = Decoder.convertTo2UInt(Decoder.getBytes(allBytes, 0, 2));
      w.DATA_SIZE = convertTo32Int(allBytes, 2, 6);
      w.DATA_ANALYSIS = convertTo16Int(allBytes, 6, 8);
      w.SCALE_COEFFICIENT = Decoder.toFloat(Decoder.getBytes(allBytes, 8, 8 + 4));
      w.SCALE_OFFSET = Decoder.toFloat(Decoder.getBytes(allBytes, 12, 12 + 4));
      w.WORKING_CONDITION = Decoder.convertToString(allBytes, 80, 80 + 16);
      w.CONDITION_DESCRIPTION_SIZE = Decoder.convertTo2UInt(Decoder.getBytes(allBytes, 64, 66));
      w.RESERVED_1 = Decoder.convertToString(allBytes, 16, 16 + 48);
      w.TIMESTAMP = Decoder.convertToString(allBytes, 208, 208 + 32);
      w.SAMPLING_COUNTS = convertTo32Int(allBytes, 272, 272 + 4);

      //w.timestamp = Long.valueOf(convertToInt(allBytes, 300, 307)) * 1000;
      w.MEAN_GEN_SPEED = Decoder.toFloat(Decoder.getBytes(allBytes, 164, 168));
      w.MAX_GEN_POWER = Decoder.toFloat(Decoder.getBytes(allBytes, 144, 144 + 4));

      w.MIN_GEN_SPEED = Decoder.toFloat(Decoder.getBytes(allBytes, 160, 160 + 4));
      w.SAMPLING_RATE = Decoder.byteArrayTo32Int(Decoder.getBytes(allBytes, 272, 272 + 4));

      w.DATA = DataConverter.convertToListOfFloat(Decoder.getBytes(allBytes, 513, 513 + w.WAVE_LEN));
      w.SAVE_TIME_COM = byteArrayTo64Int(Decoder.getBytes(allBytes, 276, 276 + 8));
      w.COMTYPE = Decoder.convertTo2UInt(Decoder.getBytes(allBytes, 284, 286));
      w.ENDIANNESS = Decoder.convertTo2UInt(Decoder.getBytes(allBytes, 286, 288));
      w.VALUE_TYPE = Decoder.convertTo2UInt(Decoder.getBytes(allBytes, 288, 290));
      w.WAVE_LEN = convertTo32Int(allBytes, 290, 294);
      w.VIB_RMS = Decoder.toFloat(Decoder.getBytes(allBytes, 294, 294 + 4));
      w.VIB_P = Decoder.toFloat(Decoder.getBytes(allBytes, 298, 298 + 4));
      w.VIB_PP = Decoder.toFloat(Decoder.getBytes(allBytes, 302, 302 + 4));
      w.RESERVED_5 = Decoder.convertToString(allBytes, 306, 306 + 206);

      byte[] waveBytes = Decoder.getBytes(allBytes, 512, allBytes.length);
      List<Short> shortList = Decoder.convertToListOfShort(waveBytes);

//      FileWriter fw = new FileWriter("dataRGY");
//      for(Short s : shortList) {
//        fw.write(s * w.SCALE_COEFFICIENT + "\n");
//      }
//      fw.close();
      //w.WAVE_DATA = ByteConverter.convertToString(allBytes, 512, allBytes.length);
    }
    catch (RuntimeException e)
    {
      e.printStackTrace();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return w;
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
