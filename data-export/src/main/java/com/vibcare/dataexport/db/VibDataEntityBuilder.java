package com.vibcare.dataexport.db;

public class VibDataEntityBuilder {
  private String vbProfileName;
  private String machineName;
  private String pointName;
  private String gmtEvent;
  private Double maxSecsOrRevs;
  private byte[] weaveFormData;
  private Double assocRpmInHz;
  private Integer locId;
  private Integer samplingCounts;

  public VibDataEntityBuilder setVbProfileName(String vbProfileName)
  {
    this.vbProfileName = vbProfileName;
    return this;
  }

  public VibDataEntityBuilder setMachineName(String machineName)
  {
    this.machineName = machineName;
    return this;
  }

  public VibDataEntityBuilder setPointName(String pointName)
  {
    this.pointName = pointName;
    return this;
  }

  public VibDataEntityBuilder setGmtEvent(String gmtEvent)
  {
    this.gmtEvent = gmtEvent;
    return this;
  }

  public VibDataEntityBuilder setMaxSecsOrRevs(Double maxSecsOrRevs)
  {
    this.maxSecsOrRevs = maxSecsOrRevs;
    return this;
  }

  public VibDataEntityBuilder setWeaveFormData(byte[] weaveFormData)
  {
    this.weaveFormData = weaveFormData;
    return this;
  }

  public VibDataEntityBuilder setAssocRpmInHz(Double assocRpmInHz)
  {
    this.assocRpmInHz = assocRpmInHz;
    return this;
  }

  public VibDataEntityBuilder setLocId(Integer locId)
  {
    this.locId = locId;
    return this;
  }

  public VibDataEntityBuilder setSamplingCounts(Integer samplingCounts)
  {
    this.samplingCounts = samplingCounts;
    return this;
  }

  public VibDataEntity createVibDataEntity()
  {
    return new VibDataEntity(vbProfileName, machineName, pointName, gmtEvent, maxSecsOrRevs, weaveFormData, assocRpmInHz, locId,
      samplingCounts);
  }
}