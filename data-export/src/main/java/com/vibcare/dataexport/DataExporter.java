package com.vibcare.dataexport;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vibcare.dataexport.db.DBLoaderDAO;
import com.vibcare.dataexport.db.VibDataEntity;
import com.vibcare.dataexport.db.util.DataConverter;
import com.vibcare.dataexport.db.util.DateUtil;
import com.vibcare.dataexport.util.Encoder;

@Component
public class DataExporter
{

  private static final String OUTPUT_DIR = "out/";
  private static final String UNDER_LINE = "_";
  private static final String CONDITION_NAME = "Cond1";
  @Autowired
  private DBLoaderDAO loader;
  @Autowired
  private DataFilePrefixGenerator dataFilePrefixGenerator;

  public void export() throws IOException, ParseException
  {
    List<VibDataEntity> timeDataList = loader.readTimeData();
    BinaryWriter bw = new BinaryWriter();
    for (VibDataEntity dataEntity : timeDataList)
    {
      prepareDirectory();
      DataFile df = generateDataFile(dataEntity);

      bw.write(df);
    }
  }

  private DataFile generateDataFile(VibDataEntity dataEntity) throws ParseException
  {
    //update data fields
    DataFile df = new DataFile(concatenateFileName(dataEntity));
    List<Double> waveElementList = DataConverter.convertToListOfFloat(dataEntity.getWeaveFormData());

    df.updateDataFields(DataEntry.DataEntryType.WIND_FARM_NAME, dataFilePrefixGenerator.getFarmCode(dataEntity.getVbProfileName()));
    df.updateDataFields(DataEntry.DataEntryType.TURBINE_NAME, dataFilePrefixGenerator.getMachineCode(dataEntity.getMachineName()));
    df.updateDataFields(DataEntry.DataEntryType.SAMPLING_CHANNEL, dataFilePrefixGenerator.getChannelCode(dataEntity.getPointName(), dataEntity.getAxisName()));
    df.updateDataFields(DataEntry.DataEntryType.SCALE_COEFFICIENT, Encoder.findCoefficient(waveElementList));
    float rpm = (float) (dataEntity.getAssocRpmInHz() * 60);
    df.updateDataFields(DataEntry.DataEntryType.MAX_GEN_SPEED, rpm);
    df.updateDataFields(DataEntry.DataEntryType.MIN_GEN_SPEED, rpm);
    df.updateDataFields(DataEntry.DataEntryType.MEAN_GEN_SPEED, rpm);
    df.updateDataFields(DataEntry.DataEntryType.SAMPLING_COUNTS, dataEntity.getSamplingCounts());
    df.updateDataFields(DataEntry.DataEntryType.SAMPLING_RATE, (int) (dataEntity.getSamplingCounts() / dataEntity.getMaxSecsOrRevs()));
    df.updateDataFields(DataEntry.DataEntryType.WAVE_LEN, dataEntity.getWeaveFormData().length);
    df.updateDataFields(DataEntry.DataEntryType.TIMESTAMP, DateUtil.convertByFormat(dataEntity.getGmtEvent(), "yyyyMMddHHmmss"));
    df.updateDataFields(DataEntry.DataEntryType.VIB_RMS, dataEntity.getRmsOverall());
    df.updateDataFields(DataEntry.DataEntryType.VIB_P, dataEntity.getCrestFactor());
    df.updateDataFields(DataEntry.DataEntryType.VIB_PP, dataEntity.getTruePkpk());
    df.updateDataFields(DataEntry.DataEntryType.WAVE_DATA, waveElementList);
    return df;
  }

  private String concatenateFileName(VibDataEntity dataEntity) throws ParseException
  {
    String siteCode = dataFilePrefixGenerator.getFarmCode(dataEntity.getVbProfileName());
    String machineCode = dataFilePrefixGenerator.getMachineCode(dataEntity.getMachineName());
    String channelCode = dataFilePrefixGenerator.getChannelCode(dataEntity.getPointName(), dataEntity.getAxisName());
    return OUTPUT_DIR + siteCode + UNDER_LINE + machineCode + UNDER_LINE + DateUtil.convertByFormat(dataEntity.getGmtEvent(),
      "yyyyMMddHHmmss") + UNDER_LINE + channelCode + UNDER_LINE + CONDITION_NAME;
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
