package com.smartapps.smartlib.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(name = "AssetDeleteResponse")
public class AssetDeleteResponseDto extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String bucketName;
	private Integer count;

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
