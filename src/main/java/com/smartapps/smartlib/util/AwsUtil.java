package com.smartapps.smartlib.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class AwsUtil {

	public static final String S3_ASSETS_BUCKET = "smartapps-assets";

	public static final String S3_HOST = "https://s3.cloudLocation.amazonaws.com";
	
	public static final String SMARTAPPS_S3_ASSETS_UPLOAD_HOST = String.format("%s/%s", S3_HOST, S3_ASSETS_BUCKET);
	
	public static final String SMARTAPPS_S3_ASSETS_EXTERNAL_HOST = String.format("https://%s.s3.amazonaws.com", S3_ASSETS_BUCKET);

	public static void main(String[] args) {
	}

	public static String prepareS3Query(String type, String appId, String userId, String category, String journeyDate,
			String fileName) {
		if (StringUtils.isNotEmpty(journeyDate) && StringUtils.isNotEmpty(fileName)) {
			return String.format("%s/%s/%s/%s/%s/%s", type, appId, userId, category, journeyDate, fileName);
		}
		if (StringUtils.isNotEmpty(journeyDate)) {
			return String.format("%s/%s/%s/%s/%s", type, appId, userId, category, journeyDate);
		}
		if (StringUtils.isNotEmpty(fileName)) {
			return String.format("%s/%s/%s/%s/%s", type, appId, userId, category, fileName);
		}
		return String.format("%s/%s/%s/%s/", type, appId, userId, category);
	}
	
	public static String prepareS3Url(String path) {
		return String.format("%s/%s", SMARTAPPS_S3_ASSETS_UPLOAD_HOST, path);
	}
	
	public static String extractExternalUrl(String url) {
		return StringUtils.isNotEmpty(url) ? url.replace(SMARTAPPS_S3_ASSETS_UPLOAD_HOST, SMARTAPPS_S3_ASSETS_EXTERNAL_HOST) : "";
	}

	public static String retrieveContentType(MultipartFile file) {
		
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
	
	public static String formatFileName(String fileName) {
		if(StringUtils.isNotEmpty(fileName) && fileName.contains(".")) {
			return fileName.replace(".", "_" + SmartDateUtil.getCurrentSystemDateTimeUniqueStr() + "."); 
		}
		return fileName;
	}

}
