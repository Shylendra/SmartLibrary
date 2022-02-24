package com.smartapps.smartlib.util;

import org.joda.time.LocalDateTime;

public class SmartDateUtil {

	public static final String SYS_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static String getCurrentSystemDateTimeStr() {
		return LocalDateTime.now().toString(SYS_DATE_TIME_FORMAT);
	}
}
