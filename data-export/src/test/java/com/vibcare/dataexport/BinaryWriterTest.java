package com.vibcare.dataexport;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.vibcare.dataexport.util.ByteConverter;
import com.vibcare.dataexport.util.Decoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinaryWriterTest
{

  @Test
  public void testWriteCorrectness() throws IOException
  {
    DataFile df = new DataFile("test");
    BinaryWriter bw = new BinaryWriter();
    bw.write(df);

    FileInputStream fis = new FileInputStream("test");
    byte[] allBytes = fis.readAllBytes();
    for (Map.Entry<DataEntry.DataEntryType, DataEntry> entry : df.getDataFields().entrySet())
    {
      System.out.println(entry);
      DataEntry entryElement = entry.getValue();
      if (entryElement.getFormat() == DataEntry.Format.INT32)
      {
        assertEquals(entryElement.getValue(),
          Decoder.byteArrayTo32Int(Decoder.getBytes(allBytes, entryElement.getStartPosition(), entryElement.getEndPosition())));
      }
      else if (entryElement.getFormat() == DataEntry.Format.INT16)
      {
        assertEquals(Integer.valueOf(entryElement.getValue().toString()),
          Decoder.convertTo2UInt(Decoder.getBytes(allBytes, entryElement.getStartPosition(), entryElement.getEndPosition())));
      }
      else if (entryElement.getFormat() == DataEntry.Format.FLOAT)
      {
        assertEquals(entryElement.getValue(),
          Decoder.toFloat(Decoder.getBytes(allBytes, entryElement.getStartPosition(), entryElement.getEndPosition())));
      }
      else if (entryElement.getFormat() == DataEntry.Format.STRING)
      {
        assertTrue(
          ByteConverter.convertToString(allBytes, entryElement.getStartPosition(), entryElement.getEndPosition())
            .contains(entryElement.getValue().toString()));
      } else if (entryElement.getFormat() == DataEntry.Format.INT64)
      {
        assertEquals(entryElement.getValue(),
          Decoder.byteArrayTo64Int(Decoder.getBytes(allBytes, entryElement.getStartPosition(), entryElement.getEndPosition())));
      }
    }
  }

}