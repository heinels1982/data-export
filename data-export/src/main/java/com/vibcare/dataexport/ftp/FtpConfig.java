package com.vibcare.dataexport.ftp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("")
@Component
public class FtpConfig
{
  private String ftpServer;
  private String ftpUser;
  private String ftpPassword;

  @SuppressWarnings("unused")
  public void setFtpServer(String ftpServer)
  {
    this.ftpServer = ftpServer;
  }

  @SuppressWarnings("unused")
  public void setFtpUser(String ftpUser)
  {
    this.ftpUser = ftpUser;
  }

  @SuppressWarnings("unused")
  public void setFtpPassword(String ftpPassword)
  {
    this.ftpPassword = ftpPassword;
  }

  public String getFtpServer()
  {
    return ftpServer;
  }

  public String getFtpUser()
  {
    return ftpUser;
  }

  public String getFtpPassword()
  {
    return ftpPassword;
  }
}
