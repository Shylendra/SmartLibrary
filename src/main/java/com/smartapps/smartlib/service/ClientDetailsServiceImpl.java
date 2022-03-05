package com.smartapps.smartlib.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.smartapps.smartlib.dto.GeoLocationDto;
import com.smartapps.smartlib.util.SmartHttpUtil;
import com.smartapps.smartlib.util.SmartLibraryUtil;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Override
	public String getIpAddress(HttpServletRequest request) {
		return SmartHttpUtil.getIpAddress(request);
	}

	@Override
	/* (src/main/resources/databases/GeoLite2-City.mmdb) */
	public GeoLocationDto retrieveGeoLocationDetails(String ipAddress, String dbLocation) throws IOException, GeoIp2Exception {
		return SmartLibraryUtil.retrievepGeoLocation(ipAddress, dbLocation);
	}

}
