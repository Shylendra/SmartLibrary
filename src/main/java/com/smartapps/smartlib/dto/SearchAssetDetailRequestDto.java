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
@Schema(name = "SearchAssetDetailRequest")
public class SearchAssetDetailRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String host;
	private String bucketName;
	private String type;
	private String name;
	private String category;
	private String journeyDate;
	private String appId;
	private String userId;
	private String filePath;
	private String url;

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
