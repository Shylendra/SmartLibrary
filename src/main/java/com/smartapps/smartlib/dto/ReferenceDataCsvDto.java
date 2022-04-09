package com.smartapps.smartlib.dto;

import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.bean.CsvBindByName;
import com.smartapps.smartlib.util.SmartLibraryUtil;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(name = "ReferenceDataCsv")
public class ReferenceDataCsvDto extends CsvBean {

	@CsvBindByName(column = "REF_DATA_CODE")
	private String refDataCode;
	
	@CsvBindByName(column = "REF_DATA_TYPE")
	private String refDataType;
	
	@CsvBindByName(column = "REF_DATA_DESC")
	private String refDataDescription;
	
	@CsvBindByName(column = "REF_DATA_DESCDETAIL")
	private String refDataDescriptionDetail;

	@Override
	public String toString() {
		try {
			return SmartLibraryUtil.mapToString(this, true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@JsonIgnore
	public boolean isEmpty() {
		return Stream.of(
				refDataCode,
				refDataType,
				refDataDescription).allMatch(Objects::isNull);
	}
	
}
