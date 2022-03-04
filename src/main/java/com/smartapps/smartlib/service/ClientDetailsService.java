package com.smartapps.smartlib.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.smartapps.smartlib.dto.GeoLocationDto;

public interface ClientDetailsService {

	String getIpAddress(HttpServletRequest request);
	
	GeoLocationDto retrieveGeoLocationDetails(final String ipAddress, final String dbLocation) throws IOException, GeoIp2Exception;

}
