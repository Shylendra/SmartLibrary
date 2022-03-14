package com.smartapps.smartlib.service;

public interface MessageService {
	public String getMessage(String code);
	public String getMessage(String code, Object[] params);
}
