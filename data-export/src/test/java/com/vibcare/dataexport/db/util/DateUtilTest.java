package com.vibcare.dataexport.db.util;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest
{

  @Test
  void convertByFormat() throws ParseException
  {
    assertEquals("20180603140648", DateUtil.convertByFormat("2018-06-03 06:06:48-000020000", "yyyyMMddHHmmss"));
    assertEquals("20180831040959", DateUtil.convertByFormat("2018-08-30 20:09:59-000044000", "yyyyMMddHHmmss"));

  }
}