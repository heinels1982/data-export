package com.vibcare.dataexport.db.util;

import java.nio.charset.Charset;

public class EncodingHelper
{
  public static String encoding(String incomingStr)
  {
    return new String(incomingStr.getBytes(Charset.forName("ISO8859_1")), Charset.forName("GB2312"));
  }

  public static String reverseEncoding(String incomingStr)
  {
    return new String(incomingStr.getBytes(Charset.forName("GB2312")), Charset.forName("ISO8859_1"));
  }
}
