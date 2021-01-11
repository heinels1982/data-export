package com.vibcare.dataexport.writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.vibcare.dataexport.util.ByteConverter;

public class BinaryWriter
{
  List<Byte> outputBytesArrayList;

  BinaryWriter(int len)
  {
    outputBytesArrayList = new ArrayList(512);
  }

  void storeINT16(int length, int value)
  {

  }

  public void write(String filePath)
  {
    try
    {
      OutputStream outputStream = new FileOutputStream(filePath);
      Byte[] bytes = outputBytesArrayList.toArray(new Byte[outputBytesArrayList.size()]);
      outputStream.write(ArrayUtils.toPrimitive(bytes));
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
  }

  public void storeINT32(int start, int len, Integer value)
  {
    byte[] bytes = ByteConverter.convertToByteArray(value);
    outputBytesArrayList.set(start, bytes[0]);
    outputBytesArrayList.set(start + 1, bytes[1]);
    outputBytesArrayList.set(start + 2, bytes[2]);
    outputBytesArrayList.set(start + 3, bytes[3]);

  }


}
