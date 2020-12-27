package com.vibcare.dataexport.db;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.vibcare.dataexport.db.util.EncodingHelper;

import static com.vibcare.dataexport.db.util.EncodingHelper.reverseEncoding;


@Component
public class DBLoaderDAO
{
  private static final Logger LOGGER = LoggerFactory.getLogger(DBLoaderDAO.class);
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Value("${dbloader.sql.time.data:}")
  private String timeDataSql;

  @Value("${dbloader.sql.spectrum.data:}")
  private String spectrumDataSql;

  @Value("${time.machine.name:}")
  private String timeMachineName;

  @Value("${time.point.name:}")
  private String timePointName;

  @Value("${time.schedule.name:}")
  private String timeScheduleName;

  @Value("${time.axis.name:}")
  private String timeAxisName;

  public List<VibDataEntity> readTimeData()
  {
    List<VibDataEntity> timeDataList = new ArrayList();
    LOGGER.info("Start to execute sql {}", reverseEncoding(timeDataSql));

    jdbcTemplate.query(
      EncodingHelper.reverseEncoding(timeDataSql),
      preparedStatement -> {
        encoding(timeMachineName);
        preparedStatement.setString(1, encoding(timeMachineName));
        preparedStatement.setString(2, encoding(timePointName));
        preparedStatement.setString(3, encoding(timeScheduleName));
        preparedStatement.setString(4, encoding(timeAxisName));
      },
      (rs, rowNum) -> timeDataList.add(new TimeDataBuilder()
        .setMachineName(EncodingHelper.encoding(rs.getString("machineName")))
        .setPointName(EncodingHelper.encoding(rs.getString("pointName")))
        .setMaxSecsOrRevs(rs.getDouble("maxSecsOrRevs"))
        .setGmtEvent(rs.getString("gmtEvent"))
        .setWeaveFormData(getAllBytes(rs.getBlob("WAVEFORM_DATA")))
        .setAssocRpmInHz(rs.getDouble("ASSOC_RPM_IN_HZ"))
        .createTimeData())
    );
    LOGGER.info("Start to execute sql with result {}", timeDataList.size());
    return timeDataList;
  }

  private String encoding(String str)
  {
    try
    {
      String properDisplay = new String(str.getBytes("ISO8859_1"));
      return new String(properDisplay.getBytes(Charset.forName("GB2312")), Charset
        .forName("ISO8859_1"));
    }
    catch (UnsupportedEncodingException e)
    {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  private byte[] getAllBytes(Blob blob)
  {
    try
    {
      return blob.getBinaryStream().readAllBytes();
    }
    catch (IOException e)
    {
      LOGGER.error(e.getMessage(), e);
    }
    catch (SQLException e)
    {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  public List<VibDataEntity> readSpectrumData()
  {
    List<VibDataEntity> vibrationRawDataList = new ArrayList();

    LOGGER.info("Start to execute sql {}", reverseEncoding(spectrumDataSql));

    jdbcTemplate.query(
      EncodingHelper.reverseEncoding(spectrumDataSql),
      preparedStatement -> preparedStatement.setString(1, encoding("电机M1")),
      (rs, rowNum) -> vibrationRawDataList.add(new TimeDataBuilder()
        .setMachineName(EncodingHelper.encoding(rs.getString("machineName")))
        .setPointName(EncodingHelper.encoding(rs.getString("pointName")))
        .setMaxSecsOrRevs(rs.getDouble("maxSecsOrRevs"))
        .setGmtEvent(rs.getString("gmtEvent"))
        .setWeaveFormData(getAllBytes(rs.getBlob("SPECTRUM_DATA")))
        .setAssocRpmInHz(rs.getDouble("ASSOC_RPM_IN_HZ"))
        .createTimeData())
    );
    return vibrationRawDataList;
  }
}
