package com.vibcare.dataexport.db;

import java.nio.charset.Charset;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vibcare.dataexport.DataExportApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = {DataExportApplication.class})
public class DBLoaderDAOTest
{
  private static final int SPECTRUM_TOTAL_ENTRIES = 15;
  private static final int TIME_DATA_TOTAL_ENTRIES = 4335;
  private static final double EXPECTED_RPM_NUMBER = 44.0;
  private static final double DATA_COLLECTION_DURATION = 1.6;

  @Autowired
  private DBLoaderDAO loader;

  @Test
  //TODO
  public void testTimeDataIsLoadedCorrectly()
  {
    List<VibDataEntity> timeDataList = loader.readTimeData();
    assertEquals(TIME_DATA_TOTAL_ENTRIES, timeDataList.size());
    assertTrue(timeDataList.get(0).getMachineName().contains("2150"));
//    assertTrue(timeDataList.get(0).getPointName().contains("M1"));
//    assertEquals(DATA_COLLECTION_DURATION, timeDataList.get(0).getMaxSecsOrRevs());
//    assertEquals(EXPECTED_RPM_NUMBER, timeDataList.get(0).getAssocRpmInHz());
  }

  @Test
  //TODO
  public void testSpectrumDataIsLoadedCorrectly()
  {
    List<VibDataEntity> vibrationRawDataList = loader.readSpectrumData();
    assertNotNull(vibrationRawDataList);
    //assertEquals(SPECTRUM_TOTAL_ENTRIES, vibrationRawDataList.size());
    //assertNotNull(vibrationRawDataList.get(0).getMachineName());
    //assertEquals(EXPECTED_RPM_NUMBER, vibrationRawDataList.get(0).getAssocRpmInHz());
  }

  @Test
  public void testByteIsDecodedCorrectly()
  {
    byte[] bytes = {-75, -25, -69, -6, 77, 49};
    String a = new String(bytes, Charset.forName("GB2312"));
    assertEquals("电机M1", a);
  }

}
