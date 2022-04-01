package com.smartapps.smartlib.service;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.smartapps.smartlib.util.CsvUtil;

@Service
public class CsvServiceImpl<T> implements CsvService<T> {

	@Override
	public List<T> read(String csvFileNamePath, String[] columns, Class<T> tClass) throws IOException {
		List<T> entities = new ArrayList<T>();
		
		ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<T>();
		strategy.setType(tClass);
		strategy.setColumnMapping(columns);
		
		Reader csvReader = CsvUtil.getReader(csvFileNamePath);
		if(csvReader != null) {
			CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
					.withMappingStrategy(strategy)
					//.withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
			
			Iterator<T> entityIterator = csvToBean.iterator();
			while(entityIterator.hasNext()) {
				entities.add(entityIterator.next());
			}
		}
		
 		return entities;
	}

}
