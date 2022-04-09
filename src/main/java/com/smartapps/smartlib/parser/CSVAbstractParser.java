package com.smartapps.smartlib.parser;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.smartapps.smartlib.util.CsvUtil;

public abstract class CSVAbstractParser<T> {
	
    private static final char COMMA_DELIMITER = ',';

	public List<T> parse(String path, Class<T> tClass) {

		Reader csvReader = CsvUtil.getReader(Paths.get(path));
		if(csvReader != null) {
			CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
					.withType(tClass)
					.withSeparator(COMMA_DELIMITER)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
			return csvToBean.parse();
		}
		
 		return new ArrayList<>();
	}

	public String write(String path, List<T> tClassList, Class<T> tClass) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		Writer csvWriter = CsvUtil.getWriter(Paths.get(path));
		if(csvWriter != null) {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(csvWriter)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(tClassList);
		}
		
 		return path;
	}

}
