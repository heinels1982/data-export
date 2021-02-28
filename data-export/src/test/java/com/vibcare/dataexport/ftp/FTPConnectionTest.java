package com.vibcare.dataexport.ftp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPFile;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FTPConnectionTest
{
  static FTPConnection ftp = null;
  @Autowired
  private FtpConfig conf;

  @BeforeAll
  public static void beforeAll() throws IOException
  {
    ftp = new FTPConnection();

  }

  @AfterAll
  public static void afterAll() throws IOException
  {
    ftp.disconnect();
  }

  @Test
  public void testFileCanBeUploaded() throws IOException
  {
    ftp.connect(conf.getFtpServer(), conf.getFtpUser(), conf.getFtpPassword());
    ftp.upload(new File("mock"));
    assertTrue(true);
  }

  @Test
  public void testFileCanBeListed() throws IOException
  {
    ftp.connect(conf.getFtpServer(), conf.getFtpUser(), conf.getFtpPassword());
    FTPFile[] ftpFile = ftp.listFiles("mock");
    ftp.disconnect();
    assertEquals(ftpFile.length, 1);
  }
}