package com.smartapps.smartlib.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.smartapps.smartlib.util.SmartLibraryUtil;
import com.smartapps.smartlib.validators.annotations.ValidAppId;

public class AppIdValidator implements ConstraintValidator<ValidAppId, String>{

	@Override
	public boolean isValid(String input, ConstraintValidatorContext ctx) {
		return SmartLibraryUtil.isValidAppid(input);
	}

}
