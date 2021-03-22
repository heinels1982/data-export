package com.vibcare.dataexport;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("")
@Component
public class CustomProperties
{
  private final Map<String, String> siteNameMapping = new HashMap<>();
  private final Map<String, String> machineNameMapping = new HashMap<>();
  private final Map<String, String> channelMapping = new HashMap<>();

  public Map<String, String> getSiteNameMapping()
  {
    return siteNameMapping;
  }

  public Map<String, String> getMachineNameMapping()
  {
    return machineNameMapping;
  }

  public Map<String, String> getChannelMapping()
  {
    return channelMapping;
  }
}
