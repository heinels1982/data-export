package com.vibcare.dataexport;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vibcare.dataexport.db.DBLoaderDAO;
import com.vibcare.dataexport.db.VibDataEntity;
import com.vibcare.dataexport.db.util.DateUtil;

@Component
public class DataExporter
{

  @Autowired
  private DBLoaderDAO loader;

  @Autowired
  private DataFilePrefixGenerator dataFilePrefixGenerator;

  private static final String OUTPUT_DIR = "out/";
  private static final String UNDER_LINE = "_";

  public void export() throws IOException, ParseException
  {
    List<VibDataEntity> timeDataList = loader.readTimeData();
    BinaryWriter bw = new BinaryWriter();
    for (VibDataEntity dataEntity : timeDataList)
    {
      prepareDirectory();
      DataFile df = new DataFile(concatenateFileName(dataEntity));
      bw.write(df);
    }
  }

  private String concatenateFileName(VibDataEntity dataEntity) throws ParseException
  {

    String machineCode = dataFilePrefixGenerator.getMachineCode(dataEntity.getMachineName());
    String siteCode = dataFilePrefixGenerator.getSiteCode(dataEntity.getVbProfileName());
    return OUTPUT_DIR + siteCode + UNDER_LINE + machineCode + UNDER_LINE + DateUtil.convertByFormat(dataEntity.getGmtEvent(), "yyyyMMddHHmmss");
  }

  private void prepareDirectory()
  {
    File outDir = new File(OUTPUT_DIR);
    if (!outDir.exists())
    {
      outDir.mkdir();
    }
  }

}
