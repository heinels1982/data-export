package com.vibcare.dataexport.db;

public class TimeDataBuilder {
  private String vbProfileName;
  private String machineName;
  private String pointName;
  private String gmtEvent;
  private Double maxSecsOrRevs;
  private byte[] weaveFormData;
  private Double assocRpmInHz;

  public TimeDataBuilder setMachineName(String machineName)
  {
    this.machineName = machineName;
    return this;
  }

  public TimeDataBuilder setVbProfileName(String vbProfileName)
  {
    this.vbProfileName = vbProfileName;
    return this;
  }

  public TimeDataBuilder setPointName(String pointName)
  {
    this.pointName = pointName;
    return this;
  }

  public TimeDataBuilder setGmtEvent(String gmtEvent)
  {
    this.gmtEvent = gmtEvent;
    return this;
  }

  public TimeDataBuilder setMaxSecsOrRevs(Double maxSecsOrRevs)
  {
    this.maxSecsOrRevs = maxSecsOrRevs;
    return this;
  }

  public TimeDataBuilder setWeaveFormData(byte[] weaveFormData)
  {
    this.weaveFormData = weaveFormData;
    return this;
  }

  public TimeDataBuilder setAssocRpmInHz(Double assocRpmInHz)
  {
    this.assocRpmInHz = assocRpmInHz;
    return this;
  }

  public VibDataEntity createTimeData()
  {
    return new VibDataEntity(vbProfileName, machineName, pointName, gmtEvent, maxSecsOrRevs, weaveFormData, assocRpmInHz);
  }
}