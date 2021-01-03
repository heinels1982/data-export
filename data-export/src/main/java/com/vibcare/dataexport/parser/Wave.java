package com.vibcare.dataexport.parser;


import java.util.List;

public class Wave
{
  public double MAX_GEN_POWER;
  public int HEADER_VERSION;
  public int DATA_SIZE;
  public int DATA_ANALYSIS;
  public double SCALE_COEFFICIENT;
  public double SCALE_OFFSET;
  public double MEAN_GEN_SPEED;
  public String TIMESTAMP;
  public String WORKING_CONDITION;
  public int SAMPLING_COUNTS;
  public String RESERVED_1;

  public long timestamp;
  public int SAMPLING_RATE;

  public double MIN_GEN_SPEED;
  public int COMTYPE;
  public int VALUE_TYPE;
  public int WAVE_LEN;


  public double VIB_PP;
  public double offset3;
  public int sizeOfDataChunkInBytes;

  public String channelName3;
  public String channelName4;
  public String channelName5;

  public String unitName1;
  public String unitName2;
  public String unitName3;
  public String unitName4;
  public String unitName5;

  public List<Double> DATA;
  public List<Double> channel2Data;
  public List<Double> channel3Data;
  public int CONDITION_DESCRIPTION_SIZE;

  @Override
  public String toString()
  {
    return "Wave{" +
           "MAX_GEN_POWER=" + MAX_GEN_POWER +
           ", HEADER_VERSION=" + HEADER_VERSION +
           ", DATA_SIZE=" + DATA_SIZE +
           ", DATA_ANALYSIS=" + DATA_ANALYSIS +
           ", SCALE_COEFFICIENT=" + SCALE_COEFFICIENT +
           ", SCALE_OFFSET=" + SCALE_OFFSET +
           ", MEAN_GEN_SPEED=" + MEAN_GEN_SPEED +
           ", TIMESTAMP='" + TIMESTAMP + '\'' +
           ", WORKING_CONDITION='" + WORKING_CONDITION + '\'' +
           ", SAMPLING_COUNTS=" + SAMPLING_COUNTS +
           ", timestamp=" + timestamp +
           ", SAMPLING_RATE=" + SAMPLING_RATE +
           ", MIN_GEN_SPEED=" + MIN_GEN_SPEED +
           ", COMTYPE=" + COMTYPE +
           ", VALUE_TYPE=" + VALUE_TYPE +
           ", WAVE_LEN=" + WAVE_LEN +
           ", VIB_PP=" + VIB_PP +
           ", offset3=" + offset3 +
           ", sizeOfDataChunkInBytes=" + sizeOfDataChunkInBytes +
           ", channelName3='" + channelName3 + '\'' +
           ", channelName4='" + channelName4 + '\'' +
           ", channelName5='" + channelName5 + '\'' +
           ", unitName1='" + unitName1 + '\'' +
           ", unitName2='" + unitName2 + '\'' +
           ", unitName3='" + unitName3 + '\'' +
           ", unitName4='" + unitName4 + '\'' +
           ", unitName5='" + unitName5 + '\'' +
          // ", DATA=" + DATA +
           ", channel2Data=" + channel2Data +
           ", channel3Data=" + channel3Data +
           ", CONDITION_DESCRIPTION_SIZE=" + CONDITION_DESCRIPTION_SIZE +
           '}';
  }
}