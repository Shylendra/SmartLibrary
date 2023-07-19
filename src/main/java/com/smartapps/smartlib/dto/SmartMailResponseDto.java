package com.smartapps.smartlib.dto;

import java.io.Serializable;

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
@Schema(name = "SmartMailResponse")
public class SmartMailResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String from;
	private String to;
	private String subject;
	private String text;
	private String pathToAttachment;

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
