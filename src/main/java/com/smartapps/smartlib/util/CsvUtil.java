package com.smartapps.smartlib.util;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvUtil {

	public static Reader getReader(String csvFileNamePath) {
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(csvFileNamePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reader;
	}
	
}
