package com.smartapps.smartlib.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.smartapps.smartlib.util.SmartHttpUtil;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ApiResponses(value = { 
		@ApiResponse(responseCode = SmartHttpUtil.RC_400, description = SmartHttpUtil.RCDESC_400),
		@ApiResponse(responseCode = SmartHttpUtil.RC_404, description = SmartHttpUtil.RCDESC_404)
    })
public @interface GlobalApiReponsesDelete {

}
