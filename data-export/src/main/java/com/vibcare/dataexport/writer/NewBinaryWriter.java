package com.vibcare.dataexport.writer;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.vibcare.dataexport.parser.Wave;
import com.vibcare.dataexport.util.ByteConverter;
import com.vibcare.dataexport.util.Decoder;



public class NewBinaryWriter
{

  public static void main(String args[]) throws IOException
  {
    String fileName = "out.bin";
    FileOutputStream filOs = new FileOutputStream(fileName);
    ObjectOutputStream os = new MyObjectOutputStream(filOs);
    short header = 12;
    os.writeShort(header);
    int dataSize = 64;
    os.writeInt(dataSize);
    os.close();
    Wave w = new Wave();
    FileInputStream fis = new FileInputStream("out.bin");
    byte[] allBytes = fis.readAllBytes();
    w.HEADER_VERSION = Decoder.convertTo2UInt(Decoder.getBytes(allBytes, 0, 2));
    System.out.println((w));

  }

  public static void writeShort(OutputStream os, short s) throws IOException {
    os.write((byte) (s >> 8));
    os.write((byte) s);
  }

  private static int convertTo16Int(byte[] allBytes, int start, int end)
  {
    byte[] buf = Decoder.getBytes(allBytes, start, end);
    return Decoder.byteArrayTo16Int(buf);
  }
  private static int convertTo32Int(byte[] allBytes, int start, int end)
  {
    byte[] buf = Decoder.getBytes(allBytes, start, end);
    return Decoder.byteArrayTo32Int(buf);
  }

}
