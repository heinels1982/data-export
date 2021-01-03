package com.vibcare.dataexport;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataFile
{
  private final String filename;
  private final Map<DataEntry.DataEntryType, DataEntry> dataFields = new LinkedHashMap<>();

  {
    dataFields.put(DataEntry.DataEntryType.HEADER_VERSION, new DataEntry(DataEntry.Format.INT16, 0, 2, (short) 0));
    dataFields.put(DataEntry.DataEntryType.DATA_SIZE, new DataEntry(DataEntry.Format.INT32, 2, 4, 64));
    dataFields.put(DataEntry.DataEntryType.DATA_ANALYSIS, new DataEntry(DataEntry.Format.INT16, 6, 2, (short) 2));
    dataFields.put(DataEntry.DataEntryType.SCALE_COEFFICIENT, new DataEntry(DataEntry.Format.FLOAT, 8, 4, (float) 1));
    dataFields.put(DataEntry.DataEntryType.SCALE_OFFSET, new DataEntry(DataEntry.Format.FLOAT, 12, 4, (float) 0.0));
    dataFields.put(DataEntry.DataEntryType.RESERVED_1, new DataEntry(DataEntry.Format.NULL, 16, 48, 0));
    dataFields.put(DataEntry.DataEntryType.CONDITION_DESCRIPTION_SIZE, new DataEntry(DataEntry.Format.INT16, 64, 2, (short)12));
    dataFields.put(DataEntry.DataEntryType.RESERVED_2, new DataEntry(DataEntry.Format.NULL, 66, 14, 0));
    dataFields.put(DataEntry.DataEntryType.WORKING_CONDITION, new DataEntry(DataEntry.Format.STRING, 80, 16, "Cond1"));
    dataFields.put(DataEntry.DataEntryType.WIND_FARM_NAME, new DataEntry(DataEntry.Format.STRING, 96, 16, "FARM1"));
    dataFields.put(DataEntry.DataEntryType.TURBINE_NUM, new DataEntry(DataEntry.Format.STRING, 112, 16, "TURBINE1"));
    dataFields.put(DataEntry.DataEntryType.SAMPLING_CHANNEL, new DataEntry(DataEntry.Format.STRING, 128, 16, "SAMPLING_CH1"));
    dataFields.put(DataEntry.DataEntryType.MAX_GEN_POWER, new DataEntry(DataEntry.Format.FLOAT, 144, 4, (float)0));
    dataFields.put(DataEntry.DataEntryType.MIN_GEN_POWER, new DataEntry(DataEntry.Format.FLOAT, 148, 4, (float)0));
    dataFields.put(DataEntry.DataEntryType.MEAN_GEN_POWER, new DataEntry(DataEntry.Format.FLOAT, 152, 4, (float)0));
    dataFields.put(DataEntry.DataEntryType.MAX_GEN_SPEED, new DataEntry(DataEntry.Format.FLOAT, 156, 4, (float)0));
    dataFields.put(DataEntry.DataEntryType.MIN_GEN_SPEED, new DataEntry(DataEntry.Format.FLOAT, 160, 4, (float)0));
    dataFields.put(DataEntry.DataEntryType.MEAN_GEN_SPEED, new DataEntry(DataEntry.Format.FLOAT, 164, 4, (float)0));
    dataFields.put(DataEntry.DataEntryType.MAX_PITCH_ANGLE, new DataEntry(DataEntry.Format.FLOAT, 168, 4, (float)0));
    dataFields.put(DataEntry.DataEntryType.MIN_PITCH_ANGLE, new DataEntry(DataEntry.Format.FLOAT, 172, 4, (float)0));
    dataFields.put(DataEntry.DataEntryType.MEAN_PITCH_ANGLE, new DataEntry(DataEntry.Format.FLOAT, 176, 4, (float)0));
    dataFields.put(DataEntry.DataEntryType.MAX_WIND_SPEED, new DataEntry(DataEntry.Format.FLOAT, 180, 4, (float)0));
    dataFields.put(DataEntry.DataEntryType.MIN_WIND_SPEED, new DataEntry(DataEntry.Format.FLOAT, 184, 4, (float)0));
    dataFields.put(DataEntry.DataEntryType.MEAN_WIND_SPEED, new DataEntry(DataEntry.Format.FLOAT, 188, 4, (float)0));
    dataFields.put(DataEntry.DataEntryType.RESERVED_3, new DataEntry(DataEntry.Format.NULL, 192, 16, 0));
    dataFields.put(DataEntry.DataEntryType.TIMESTAMP, new DataEntry(DataEntry.Format.STRING, 208, 32, "20131231142831"));
    dataFields.put(DataEntry.DataEntryType.SAMPLING_COUNTS, new DataEntry(DataEntry.Format.INT32, 240, 4, 4092));
    dataFields.put(DataEntry.DataEntryType.RESERVED_4, new DataEntry(DataEntry.Format.NULL, 244, 28, 0));
    dataFields.put(DataEntry.DataEntryType.SAMPLING_RATE, new DataEntry(DataEntry.Format.INT32, 272, 4, 4092));
    dataFields.put(DataEntry.DataEntryType.SAVE_TIME_COM, new DataEntry(DataEntry.Format.INT64, 276, 8, 1569215954000l));

  }

  public DataFile(String filename)
  {
    this.filename = filename;
  }

  public String getFilename()
  {
    return filename;
  }

  public Map<DataEntry.DataEntryType, DataEntry> getDataFields()
  {
    return dataFields;
  }
}

class DataEntry
{
  private final int startPosition;
  private final int length;
  private final Object value;
  private final Format format;

  public DataEntry(Format format, int startPosition, int length, Object value)
  {
    this.format = format;
    this.startPosition = startPosition;
    this.length = length;
    this.value = value;
  }

  public Format getFormat()
  {
    return format;
  }

  public int getStartPosition()
  {
    return startPosition;
  }

  public int getEndPosition()
  {
    return startPosition + length;
  }

  public int getLength()
  {
    return length;
  }

  public Object getValue()
  {
    return value;
  }

  enum Format
  {INT16, INT32, INT64, FLOAT, NULL, STRING}

  enum DataEntryType
  {
    HEADER_VERSION, DATA_SIZE, DATA_ANALYSIS, SCALE_COEFFICIENT, SCALE_OFFSET, RESERVED_1, RESERVED_2,RESERVED_3, RESERVED_4,
    WORKING_CONDITION, CONDITION_DESCRIPTION_SIZE, WIND_FARM_NAME, TURBINE_NUM, SAMPLING_CHANNEL, MAX_GEN_POWER, MIN_GEN_POWER,
    MEAN_GEN_POWER, MAX_GEN_SPEED, MIN_GEN_SPEED, MEAN_GEN_SPEED, MAX_PITCH_ANGLE, MIN_PITCH_ANGLE, MEAN_PITCH_ANGLE, MAX_WIND_SPEED,
    MIN_WIND_SPEED, MEAN_WIND_SPEED, TIMESTAMP, SAMPLING_COUNTS, SAMPLING_RATE, SAVE_TIME_COM, RESERVED_5
  }
}