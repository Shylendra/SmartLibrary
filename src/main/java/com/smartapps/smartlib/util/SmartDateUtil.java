package com.smartapps.smartlib.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

import org.joda.time.LocalDateTime;

public class SmartDateUtil {

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String getCurrentSystemDateTimeStr() {
		return LocalDateTime.now().toString(DATE_TIME_FORMAT.toString());
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
	    try {
	        return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
	    } catch (ParseException e) {
	        throw new IllegalArgumentException(e);
	    }
	}
}
