package com.smartapps.smartlib.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.smartapps.smartlib.util.SmartHttpUtil;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Override
	public String getIpAddress(HttpServletRequest request) {
		return SmartHttpUtil.getIpAddress(request);
	}

}
