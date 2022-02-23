package com.smartapps.smartlib.service;

import java.io.IOException;
import java.util.List;

public interface CsvService<T> {
	public List<T> read(String csvFileNamePath, String[] columns, Class<T> tClass) throws IOException;
}
