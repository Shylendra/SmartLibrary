package com.smartapps.smartlib.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SmartHttpUtil {
	
	//https://simplesolution.dev/spring-boot-web-get-client-ip-address/
	
	//Alternate solution:
	//https://dirask.com/posts/Spring-Boot-get-client-IP-address-from-request-HttpServletRequest-pBv9Bp
	
	/* HTTP Response Codes */
	public static final String RC_200 = "200";
	public static final String RCDESC_200 = "Successfully retrieved.";

	public static final String RC_400 = "400";
	public static final String RCDESC_400 = "Bad request.";

	public static final String RC_401 = "401";
	public static final String RCDESC_401 = "You are not authorized to access the resource.";

	public static final String RC_403 = "403";
	public static final String RCDESC_403 = "Accessing the resource you were trying to reach is forbidden.";

	public static final String RC_404 = "404";
	public static final String RCDESC_404 = "The resource you were trying to reach is not found.";

	/* HTTP Request Header Elements */
	public static final String APP_ID_HEADER = "APP_ID";
	public static final String USER_ID_HEADER = "USER_ID";
	public static final String USER_GROUPS_HEADER = "USER_GROUPS";
	public static final String AUTH_HEADER = "Authorization";
	public static final String AUTH_HEADER_PREFIX = "Bearer ";
	
	private static final String LOCALHOST_IPV4 = "127.0.0.1";
	private static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

	public static String getHeaderAppId(HttpServletRequest request) {
		final String appId = request.getHeader(APP_ID_HEADER);
		if(StringUtils.isNotEmpty(appId)) {
			return appId;
		}
		return null;
	}
	
	public static String getHeaderUserId(HttpServletRequest request) {
		final String userId = request.getHeader(USER_ID_HEADER);
		if(StringUtils.isNotEmpty(userId)) {
			return userId;
		}
		return null;
	}
	
	public static String getHeaderUserGroups(HttpServletRequest request) {
		final String userGroups = request.getHeader(USER_GROUPS_HEADER);
		if(StringUtils.isNotEmpty(userGroups)) {
			return userGroups;
		}
		return null;
	}

	public static String getHeaderAuthorizationToken(HttpServletRequest request) {
		final String authorization = request.getHeader(AUTH_HEADER);
		if(StringUtils.isNotEmpty(authorization) && authorization.startsWith(AUTH_HEADER_PREFIX)) {
			return authorization.substring(7);
		}
		return null;
	}
	
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Forwarded-For");
		if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		
		if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		
		if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if(LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
				try {
					InetAddress inetAddress = InetAddress.getLocalHost();
					ipAddress = inetAddress.getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(!StringUtils.isEmpty(ipAddress) 
				&& ipAddress.length() > 15
				&& ipAddress.indexOf(",") > 0) {
			ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
		}
		
		return ipAddress;
	}
	
	public static String getServerHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			log.debug("UnknownHostException: ", e);
			return null;
		}
	}

}
