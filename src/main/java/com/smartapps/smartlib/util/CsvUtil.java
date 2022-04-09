package com.smartapps.smartlib.util;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class CsvUtil {

	public static Reader getReader(Path path) {
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reader;
	}

	public static Writer getWriter(Path path) {
		Writer writer = null;
		try {
			writer = Files.newBufferedWriter(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer;
	}
	
}
