package com.smartapps.smartlib.dto;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.util.AwsUtil;
import com.smartapps.smartlib.util.SmartLibraryUtil;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "AssetDetail")
public class AssetDetailDto extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String host;
	private String bucketName;
	private String type;//AssetTypeEnum
	private String name;
	private String filePath;
	private String url;

	public AssetDetailDto() {}

	public AssetDetailDto(String assetType, String appId, String userId, String fileName) {
		this.host = AwsUtil.S3_HOST;
		this.bucketName = AwsUtil.S3_ASSETS_BUCKET;
		this.type = assetType;
		this.setProcApprId(appId);
		this.setProcUserId(userId);
		this.name = fileName;
		this.filePath = String.format("%s/%s/%s/%s", this.type, this.getProcApprId(), this.getProcUserId(), this.name);
		this.url = String.format("%s/%s/%s", this.host, this.bucketName, this.filePath);
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
