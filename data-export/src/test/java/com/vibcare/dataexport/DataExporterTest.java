package com.vibcare.dataexport;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vibcare.dataexport.db.DBLoaderDAO;
import com.vibcare.dataexport.db.VibDataEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {DataExportApplication.class})
class DataExporterTest
{
  @Autowired
  private DataExporter exporter;



  @Test
  public void test() {
    exporter.export();

  }

}