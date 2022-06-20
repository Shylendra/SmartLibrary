package com.smartapps.smartlib.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Schema(name = "AssetDeleteRequest")
public class AssetDeleteRequestDto extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String bucketName;
	private List<String> keys;

	@JsonIgnore
	private List<KeyVersion> retrieveKeyVersion() {
		List<KeyVersion> keyVersions = new ArrayList<>();
		for(String key:keys) {
			keyVersions.add(new KeyVersion(key));
		}
		return keyVersions;
	}

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
