package com.vibcare.dataexport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryWriter
{

  public void write(DataFile df) throws IOException
  {

    FileOutputStream filOs = new FileOutputStream(df.getFilename());
    for (DataEntry entry : df.getDataFields().values())
    {
      if (entry.getFormat() == DataEntry.Format.INT16)
      {
        ByteBuffer b = ByteBuffer.allocate(2);
        b.order(ByteOrder.LITTLE_ENDIAN);
        b.putShort((Short) entry.getValue());
        filOs.write(b.array());
      }
      else if (entry.getFormat() == DataEntry.Format.INT32)
      {
        ByteBuffer b = ByteBuffer.allocate(4);
        b.order(ByteOrder.LITTLE_ENDIAN);
        b.putInt((Integer) entry.getValue());
        filOs.write(b.array());
      } else if (entry.getFormat() == DataEntry.Format.INT64)
      {
        ByteBuffer b = ByteBuffer.allocate(8);
        b.order(ByteOrder.LITTLE_ENDIAN);
        b.putLong((Long) entry.getValue());
        filOs.write(b.array());
      }
      else if (entry.getFormat() == DataEntry.Format.FLOAT)
      {
        ByteBuffer b = ByteBuffer.allocate(4);
        b.order(ByteOrder.LITTLE_ENDIAN);
        b.putFloat((Float) entry.getValue());
        filOs.write(b.array());
      }
      else if (entry.getFormat() == DataEntry.Format.NULL)
      {
        ByteBuffer b = ByteBuffer.allocate(entry.getLength());
        byte[] reserve = new byte[entry.getLength()];
        b.put(reserve);
        filOs.write(b.array());
      }
      else if (entry.getFormat() == DataEntry.Format.STRING)
      {
        ByteBuffer b = ByteBuffer.allocate(entry.getLength());
        b.put(entry.getValue().toString().getBytes());
        filOs.write(b.array());
      }
    }
    filOs.close();
  }

//  public static void main(String args[]) throws IOException
//  {
//    FileOutputStream filOs = new FileOutputStream("fileName");
//    ByteBuffer b = ByteBuffer.allocate(2);
//    b.order(ByteOrder.LITTLE_ENDIAN);
//    b.putShort(Short.valueOf("1"));
//    filOs.write(b.array());
//    filOs.close();
//  }
}
