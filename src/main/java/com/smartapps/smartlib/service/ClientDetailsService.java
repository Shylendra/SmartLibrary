package com.smartapps.smartlib.service;

import javax.servlet.http.HttpServletRequest;

public interface ClientDetailsService {

	String getIpAddress(HttpServletRequest request);

}
