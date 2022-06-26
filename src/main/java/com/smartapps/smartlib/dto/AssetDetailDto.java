package com.smartapps.smartlib.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.util.AwsUtil;
import com.smartapps.smartlib.util.SmartDateUtil;
import com.smartapps.smartlib.util.SmartLibraryUtil;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(name = "AssetDetail")
public class AssetDetailDto extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String host;
	private String bucketName;
	private String type;//AssetTypeEnum
	private String name;
	private String category;
	private String journeyDate;
	private String filePath;
	private String url;
	private String lastModified;
	private String eTag;
	private long size;

	public AssetDetailDto() {}

	public AssetDetailDto(String appId, String userId, MultipartFile multipartFile, String category, String journeyDate) {
		this.host = AwsUtil.S3_HOST;
		this.bucketName = AwsUtil.S3_ASSETS_BUCKET;
		this.type = retrieveContentType(multipartFile);
		this.setProcApprId(appId);
		this.setProcUserId(userId);
		this.name = formatFileName(multipartFile.getOriginalFilename());
		this.category = category;
		this.journeyDate = journeyDate;
		this.filePath = AwsUtil.prepareS3Query(this.type, this.getProcApprId(), this.getProcUserId(), category, journeyDate, this.name);
		this.url = AwsUtil.prepareS3Url(this.filePath);
	}
	
	@JsonIgnore
	private String retrieveContentType(MultipartFile file) {
		
		List<String> documents = Arrays.asList(
				"text",
				"application",
				"font");
		
		String contentType = file.getContentType().split("/")[0];
		if(documents.contains(contentType)) {
			contentType = "document";
		}
		
		return contentType;
	}

	@JsonIgnore
	private String formatFileName(String fileName) {
		if(StringUtils.isNotEmpty(fileName) && fileName.contains(".")) {
			return fileName.replace(".", "_" + SmartDateUtil.getCurrentSystemDateTimeUniqueStr() + "."); 
		}
		return fileName;
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
