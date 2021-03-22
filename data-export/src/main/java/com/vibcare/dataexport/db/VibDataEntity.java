package com.vibcare.dataexport.db;

public class VibDataEntity
{

  private final String vbProfileName;
  private final String machineName;
  private final String pointName;
  private final String gmtEvent;
  private final Double maxSecsOrRevs;
  private final byte[] weaveFormData;
  private final Double assocRpmInHz;
  private final Integer samplingCounts;
  private final Float rmsOverall;
  private final Float truePkpk;
  private final Float crestFactor;
  private final Integer channelId;
  private final String axisName;

  public VibDataEntity(String vbProfileName, String machineName, String pointName, String gmtEvent, Double maxSecsOrRevs,
    byte[] weaveFormData, Double assocRpmInHz, Integer samplingCounts, Float rmsOverall, Float truePkpk,
    Float crestFactor, Integer channelId, String axisName)
  {
    this.vbProfileName = vbProfileName;
    this.machineName = machineName;
    this.pointName = pointName;
    this.gmtEvent = gmtEvent;
    this.maxSecsOrRevs = maxSecsOrRevs;
    this.weaveFormData = weaveFormData;
    this.assocRpmInHz = assocRpmInHz;
    this.samplingCounts = samplingCounts;
    this.rmsOverall = rmsOverall;
    this.truePkpk = truePkpk;
    this.crestFactor = crestFactor;
    this.channelId = channelId;
    this.axisName = axisName;
  }

  public String getMachineName()
  {
    return machineName;
  }

  public String getPointName()
  {
    return pointName;
  }

  public String getAxisName()
  {
    return axisName;
  }

  public String getVbProfileName()
  {
    return vbProfileName;
  }

  public Integer getSamplingCounts()
  {
    return samplingCounts;
  }

  public String getGmtEvent()
  {
    return gmtEvent;
  }

  public byte[] getWeaveFormData()
  {
    return weaveFormData;
  }

  public Double getMaxSecsOrRevs()
  {
    return maxSecsOrRevs;
  }

  public Double getAssocRpmInHz()
  {
    return assocRpmInHz;
  }

  public Float getRmsOverall()
  {
    return rmsOverall;
  }

  public Float getTruePkpk()
  {
    return truePkpk;
  }

  public Float getCrestFactor()
  {
    return crestFactor;
  }

  public Integer getChannelId()
  {
    return channelId;
  }

}
