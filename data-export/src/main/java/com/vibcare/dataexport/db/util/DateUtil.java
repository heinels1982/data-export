package com.vibcare.dataexport.db.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
  public static String convertByFormat(String dateStr, String format) throws ParseException
  {
    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = parser.parse(dateStr);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.HOUR, 8);
    date = cal.getTime();
    SimpleDateFormat targetFormat = new SimpleDateFormat(format);
    String strDate = targetFormat.format(date);

    return strDate;
  }
}
