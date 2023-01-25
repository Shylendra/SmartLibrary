package com.smartapps.smartlib.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.util.AwsUtil;
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
		this.type = AwsUtil.retrieveContentType(multipartFile);
		this.setProcAppId(appId);
		this.setProcUserId(userId);
		this.name = AwsUtil.formatFileName(multipartFile.getOriginalFilename());
		this.category = category;
		this.journeyDate = journeyDate;
		this.filePath = AwsUtil.prepareS3Query(this.type, this.getProcAppId(), this.getProcUserId(), category, journeyDate, this.name);
		this.url = AwsUtil.prepareS3Url(this.filePath);
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
