package com.vibcare.dataexport;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.HashBiMap;
import com.vibcare.dataexport.db.DBLoaderDAO;
import com.vibcare.dataexport.db.VibDataEntity;

@Component
public class DataExporter
{

  @Autowired
  private DBLoaderDAO loader;

  @Autowired
  private CustomProperties properties;

  public void export()
  {
    Map<String, String> inverted = HashBiMap.create(properties.getMachineNameMapping()).inverse();
    List<VibDataEntity> timeDataList = loader.readTimeData();
    for (VibDataEntity e : timeDataList)
    {
      System.out.println(inverted.get(e.getMachineName()));
    }
  }
}
