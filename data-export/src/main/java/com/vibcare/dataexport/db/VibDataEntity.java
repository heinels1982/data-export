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

  public VibDataEntity(String vbProfileName, String machineName, String pointName, String gmtEvent, Double maxSecsOrRevs,
    byte[] weaveFormData, Double assocRpmInHz, Integer locId, Integer samplingCounts)
  {
    this.vbProfileName = vbProfileName;
    this.machineName = machineName;
    this.pointName = pointName;
    this.gmtEvent = gmtEvent;
    this.maxSecsOrRevs = maxSecsOrRevs;
    this.weaveFormData = weaveFormData;
    this.assocRpmInHz = assocRpmInHz;
    this.locId = locId;
    this.samplingCounts = samplingCounts;
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

  public String getPointName()
  {
    return pointName;
  }

  public Double getMaxSecsOrRevs()
  {
    return maxSecsOrRevs;
  }

  public Double getAssocRpmInHz()
  {
    return assocRpmInHz;
  }
}
