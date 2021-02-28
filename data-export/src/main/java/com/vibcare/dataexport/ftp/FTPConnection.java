package com.vibcare.dataexport.ftp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FTPConnection
{
  private final Logger logger = LoggerFactory.getLogger(getClass());

  private FTPClient f;

  public boolean connect(String server, String username, String password) throws IOException
  {
    f = new FTPClient();
    f.setConnectTimeout(2000);
    f.enterLocalPassiveMode();
    f.connect(server);
    f.enterLocalPassiveMode();
    f.setFileType(FTPClient.BINARY_FILE_TYPE);
    FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
    conf.setUnparseableEntries(true);
    f.configure(conf);
    boolean isLoginSuccess = f.login(username, password);
    if (!isLoginSuccess)
    {
      throw new RuntimeException();
    }
    return f.isConnected();
  }

  public FTPFile[] listFiles(String directory) throws IOException
  {
    f.type(FTP.BINARY_FILE_TYPE);
    f.enterLocalPassiveMode();
    return f.listFiles(directory);
  }

  public void upload(File src) throws IOException
  {
    if (src.isDirectory())
    {
      f.makeDirectory(src.getName());
      f.changeWorkingDirectory(src.getName());
      for (File file : src.listFiles())
      {
        upload(file);
      }
      f.changeToParentDirectory();
    }
    else
    {
      InputStream srcStream = null;
      try
      {
        srcStream = src.toURI().toURL().openStream();
        f.storeFile(src.getName(), srcStream);
      }
      finally
      {
        IOUtils.closeQuietly(srcStream);
      }
    }
    logger.info("Finish uploaded");
  }

  public void disconnect() throws IOException
  {
    f.disconnect();
  }

}