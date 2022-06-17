package com.smartapps.smartlib.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SmartDateUtil {

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter DATE_TIME_FORMAT_UNIQUE = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	public static String getCurrentSystemDateTimeUniqueStr() {
		return LocalDateTime.now().format(DATE_TIME_FORMAT_UNIQUE);
	}

	public static String getCurrentSystemDateTimeStr() {
		return LocalDateTime.now().format(DATE_TIME_FORMAT);
	}

	public static java.sql.Date getCurrentSystemDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	public static java.sql.Timestamp getCurrentSystemTimestamp() {
		return Timestamp.from(Instant.now());
	}

	public static java.sql.Date parseDate(String date) {
	    try {
	        return new Date(DATE_FORMAT.parse(date).getTime());
	    } catch (ParseException e) {
	        throw new IllegalArgumentException(e);
	    }
	}
	 
	public static java.sql.Timestamp parseTimestamp(String timestamp) {
	    return Timestamp.valueOf(timestamp);
	}
}
