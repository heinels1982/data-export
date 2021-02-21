package com.vibcare.dataexport;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.HashBiMap;
import com.vibcare.dataexport.util.Encoder;

@Component
public class CustomPropertiesProxy
{
  private static Map<String, String> machineNameReverseMapping;
  private static Map<String, String> siteNameReverseMapping;
  @Autowired
  private CustomProperties customProperties;

  public Map<String, String> getSiteNameMapping()
  {
    if (siteNameReverseMapping == null)
    {
      siteNameReverseMapping = encodeAndReverseMap(customProperties.getSiteNameMapping());
    }
    return siteNameReverseMapping;
  }

  public Map<String, String> getMachineNameMapping()
  {
    if (machineNameReverseMapping == null)
    {
      machineNameReverseMapping = encodeAndReverseMap(customProperties.getMachineNameMapping());
    }
    return machineNameReverseMapping;
  }

  private Map<String, String> encodeAndReverseMap(Map<String, String> originalPropertiesMap)
  {
    Map<String, String> cloneMap = new HashMap();
    for (String key : originalPropertiesMap.keySet())
    {
      cloneMap.put(key, Encoder.encodingPropertiesChars(originalPropertiesMap.get(key)));
    }

    return HashBiMap.create(cloneMap).inverse();
  }
}
