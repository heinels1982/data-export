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

  public VibDataEntity(String vbProfileName, String machineName, String pointName, String gmtEvent, Double maxSecsOrRevs,
    byte[] weaveFormData, Double assocRpmInHz)
  {
    this.vbProfileName = vbProfileName;
    this.machineName = machineName;
    this.pointName = pointName;
    this.gmtEvent = gmtEvent;
    this.maxSecsOrRevs = maxSecsOrRevs;
    this.weaveFormData = weaveFormData;
    this.assocRpmInHz = assocRpmInHz;
  }

  public String getMachineName()
  {
    return machineName;
  }

  public String getVbProfileName()
  {
    return vbProfileName;
  }

  public String getGmtEvent()
  {
    return gmtEvent;
  }

  public byte[] getWeaveFormData()
  {
    return weaveFormData;
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
