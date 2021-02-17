package com.vibcare.dataexport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vibcare.dataexport.util.Encoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CustomPropertiesTest
{
  @Autowired
  private CustomProperties properties;

  @Test
  public void test()
  {
    System.out.println(Encoder.encodingPropertiesChars(properties.getSiteNameMapping().get("B")));
    System.out.println(properties.getMachineNameMapping());
    assertNotNull(properties.getSiteNameMapping());
    assertNotNull(properties.getMachineNameMapping());
  }

}
