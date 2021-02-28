package com.vibcare.dataexport;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataExportApplication
{
	@Autowired
	private DataExporter dataExporter;

	public static void main(String[] args) throws IOException, ParseException
	{
		SpringApplication.run(DataExportApplication.class, args);
	}



}
