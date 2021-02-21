package com.vibcare.dataexport;

import java.io.IOException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DataExportApplication.class})
class DataExporterTest
{
  @Autowired
  private DataExporter exporter;

  @Test
  public void testExportBinaryFiles() throws IOException, ParseException
  {
    exporter.export();
  }
}