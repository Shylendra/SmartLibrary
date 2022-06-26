package com.smartapps.smartlib.util;

import org.apache.commons.lang3.StringUtils;

public class AwsUtil {

	public static final String S3_ASSETS_BUCKET = "smartapps-assets";

	public static final String S3_HOST = "https://s3.cloudLocation.amazonaws.com";

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
		return String.format("%s/%s/%s", S3_HOST, S3_ASSETS_BUCKET, path);
	}

}
