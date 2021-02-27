package com.vibcare.dataexport;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataFilePrefixGenerator
{

  private static final String MACHINE_CODE_PREFIX = "FJ";
  private static final String SITE_CODE_PREFIX = "ZD";
  private static final String CHANNEL_CODE_PREFIX = "CH";
  private final Map<String, String> unmappedMachine = new HashMap();
  private final Map<String, String> unmappedSite = new HashMap();
  private final Map<Integer, String> unmappedChannel = new HashMap();
  @Autowired
  private CustomPropertiesProxy properties;
  private int unmappedMachineCount = 1;
  private int unmappedSiteCount = 1;
  private int unmappedChannelCount = 1;

  public String getMachineCode(String machineName)
  {

    String machineCode = properties.getMachineNameMapping().get(machineName);
    if (machineCode == null)
    {
      if (unmappedMachine.get(machineName) == null)
      {
        String generatedSiteCode = MACHINE_CODE_PREFIX + unmappedMachineCount;
        unmappedMachineCount++;
        unmappedMachine.put(machineName, generatedSiteCode);
      }
      machineCode = unmappedMachine.get(machineName);

    }
    return machineCode;
  }

  public String getFarmCode(String vbProfileName)
  {
    String siteCode = properties.getSiteNameMapping().get(vbProfileName);

    if (siteCode == null)
    {
      if (unmappedSite.get(vbProfileName) == null)
      {
        String generatedSiteCode = SITE_CODE_PREFIX + unmappedMachineCount;
        unmappedSiteCount++;
        unmappedSite.put(vbProfileName, generatedSiteCode);
      }
      siteCode = unmappedSite.get(vbProfileName);
    }
    return siteCode;
  }

  public String getChannelCode(Integer locId)
  {
    if (unmappedChannel.get(locId) == null)
    {
      String generatedSiteCode = CHANNEL_CODE_PREFIX + unmappedChannelCount;
      unmappedChannelCount++;
      unmappedChannel.put(locId, generatedSiteCode);
    }
    return unmappedChannel.get(locId);
  }
}
