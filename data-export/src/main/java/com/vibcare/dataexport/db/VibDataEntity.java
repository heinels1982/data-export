package com.vibcare.dataexport.db;

public class VibDataEntity
{

  private String vbProfileName;
  private String machineName;
  private String pointName;
  private String gmtEvent;
  private Double maxSecsOrRevs;
  private byte[] weaveFormData;
  private Double assocRpmInHz;
  private Integer samplingCounts;
  private Integer locId;
  private Float rmsOverall;
  private Float truePkpk;
  private Float crestFactor;
  private Integer channelId;


  public VibDataEntity(String vbProfileName, String machineName, String pointName, String gmtEvent, Double maxSecsOrRevs,
    byte[] weaveFormData, Double assocRpmInHz, Integer samplingCounts, Integer locId, Float rmsOverall, Float truePkpk,
    Float crestFactor, Integer channelId)
  {
    this.vbProfileName = vbProfileName;
    this.machineName = machineName;
    this.pointName = pointName;
    this.gmtEvent = gmtEvent;
    this.maxSecsOrRevs = maxSecsOrRevs;
    this.weaveFormData = weaveFormData;
    this.assocRpmInHz = assocRpmInHz;
    this.samplingCounts = samplingCounts;
    this.locId = locId;
    this.rmsOverall = rmsOverall;
    this.truePkpk = truePkpk;
    this.crestFactor = crestFactor;
    this.channelId = channelId;
  }

  public String getMachineName()
  {
    return machineName;
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

  public Integer getLocId()
  {
    return locId;
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
