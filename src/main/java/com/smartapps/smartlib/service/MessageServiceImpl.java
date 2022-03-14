package com.smartapps.smartlib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	public String getMessage(String code) {
		return this.messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
	}

	@Override
	public String getMessage(String code, Object[] params) {
		return this.messageSource.getMessage(code, params, LocaleContextHolder.getLocale());
	}

}
