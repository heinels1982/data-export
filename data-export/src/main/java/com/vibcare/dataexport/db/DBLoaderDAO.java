package com.vibcare.dataexport.db;

import java.io.IOException;
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
import com.vibcare.dataexport.util.Encoder;

import static com.vibcare.dataexport.db.util.EncodingHelper.reverseEncoding;


@Component
public class DBLoaderDAO
{
  private static final Logger LOGGER = LoggerFactory.getLogger(DBLoaderDAO.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Value("${dbloader.sql.time.data:}")
  private String timeDataSql;

  @Value("${time.dataType:}")
  private String dataType;

  public List<VibDataEntity> readTimeData()
  {
    List<VibDataEntity> timeDataList = new ArrayList();
    LOGGER.info("Start to execute sql {}", reverseEncoding(timeDataSql));

    jdbcTemplate.query(
      EncodingHelper.reverseEncoding(timeDataSql),
      preparedStatement -> {
        preparedStatement.setString(1, Encoder.encodingPropertiesChars("%" + dataType + "%"));
      },
      (rs, rowNum) -> timeDataList.add(new VibDataEntityBuilder()
        .setMachineName(rs.getString("machineName"))
        .setVbProfileName(rs.getString("vbProfileName"))
        .setLocId(rs.getInt("locationId"))
        .setPointName(EncodingHelper.encoding(rs.getString("pointName")))
        .setMaxSecsOrRevs(rs.getDouble("maxSecsOrRevs"))
        .setGmtEvent(rs.getString("gmtEvent"))
        .setWeaveFormData(getAllBytes(rs.getBlob("WAVEFORM_DATA")))
        .setAssocRpmInHz(rs.getDouble("ASSOC_RPM_IN_HZ"))
        .setSamplingCounts(rs.getInt("samplingCounts"))
        .setRmsOverall(rs.getFloat("RMS_OVERALL"))
        .setTruePkpk(rs.getFloat("TRUE_PKPK"))
        .setCrestFactor(rs.getFloat("CREST_FACTOR"))
        .setChannelId(rs.getInt("CHANNEL_ID"))
        .createVibDataEntity())
    );
    LOGGER.info("Start to execute sql with result {}", timeDataList.size());
    return timeDataList;
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
}
