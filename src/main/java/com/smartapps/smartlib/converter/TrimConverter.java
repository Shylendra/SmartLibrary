package com.smartapps.smartlib.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TrimConverter implements AttributeConverter<String, String>{

	@Override
	public String convertToDatabaseColumn(String value) {
		return performNullCheck(value);
	}

	@Override
	public String convertToEntityAttribute(String value) {
		return performNullCheck(value);
	}
	
	private String performNullCheck(final String value) {
		if(value == null) {
			return "";
		}
		return value.trim();
	}

}
