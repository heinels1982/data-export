package com.vibcare.dataexport.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("")
public class SpringJDBCConfig
{
  @Value("${dbloader.jdbc.url}")
  private String jdbcUrl;

  @Bean
  @SuppressWarnings("unused")
  public DataSource fbDataSource()
  {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.firebirdsql.jdbc.FBDriver");
    dataSource.setUrl(jdbcUrl);
    dataSource.setUsername("SYSDBA");
    dataSource.setPassword("f4c68627d1bee34f6375");
    return dataSource;
  }
}
