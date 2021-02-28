package com.vibcare.dataexport;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vibcare.dataexport.ftp.FTPConnection;
import com.vibcare.dataexport.ftp.FtpConfig;

@SpringBootApplication
public class DataExportApplication implements CommandLineRunner
{

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private DataExporter dataExporter;

  @Autowired
  private FtpConfig conf;

  public static void main(String[] args)
  {
    SpringApplication.run(DataExportApplication.class, args);
  }

  @Override
  public void run(String... args) throws IOException, ParseException
  {
    dataExporter.export();
    uploadFtp();
    //    if ("prod".equalsIgnoreCase(args[0]))
    //    {
    //      System.exit(0);
    //    }
  }

  private void uploadFtp() throws IOException
  {
    FTPConnection ftpConn = new FTPConnection();
    File outputDir = new File("out");
    logger.info("Uploading file from " + outputDir.getAbsolutePath());

    ftpConn.connect(conf.getFtpServer(), conf.getFtpUser(), conf.getFtpPassword());
    ftpConn.upload(new File("out"));
    ftpConn.disconnect();
    logger.info("Uploading files complete");
  }

}
