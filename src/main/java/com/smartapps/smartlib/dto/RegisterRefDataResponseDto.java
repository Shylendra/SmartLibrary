package com.smartapps.smartlib.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.util.SmartLibraryUtil;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RegisterRefDataResponse")
public class RegisterRefDataResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String refDataType;
	private List<String> refDataCodes;
	private int createCount;
	private int updateCount;

	@Override
	public String toString() {
		try {
			return SmartLibraryUtil.mapToString(this, true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
