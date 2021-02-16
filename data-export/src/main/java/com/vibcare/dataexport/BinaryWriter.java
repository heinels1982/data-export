package com.vibcare.dataexport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.vibcare.dataexport.util.Encoder;

public class BinaryWriter
{

  public void write(DataFile df) throws IOException
  {
    FileOutputStream filOs = new FileOutputStream(df.getFilename());
    for (DataEntry entry : df.getDataFields().values())
    {
      if (entry.getFormat() == DataEntry.Format.INT16)
      {
        byte[] shortByte = Encoder.shortConvertToBytes((Short) entry.getValue());
        filOs.write(shortByte);
      }
      else if (entry.getFormat() == DataEntry.Format.INT32)
      {
        ByteBuffer b = Encoder.intConvertToBytes((Integer)entry.getValue());
        filOs.write(b.array());
      }
      else if (entry.getFormat() == DataEntry.Format.INT64)
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
      else if (entry.getFormat() == DataEntry.Format.WAVE_DATA)
      {
//        ByteBuffer b = ByteBuffer.allocate(entry.getLength());
//        b.put(entry.getValue().toString().getBytes());
//        filOs.write(b.array());
      }
    }
    filOs.close();
  }

}
