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
		@ApiResponse(responseCode = SmartHttpUtil.RC_200, description = SmartHttpUtil.RCDESC_200), 
		@ApiResponse(responseCode = SmartHttpUtil.RC_400, description = SmartHttpUtil.RCDESC_400),
		@ApiResponse(responseCode = SmartHttpUtil.RC_401, description = SmartHttpUtil.RCDESC_401),
		@ApiResponse(responseCode = SmartHttpUtil.RC_403, description = SmartHttpUtil.RCDESC_403),
		@ApiResponse(responseCode = SmartHttpUtil.RC_404, description = SmartHttpUtil.RCDESC_404)
    })
public @interface GlobalApiReponsesPut {

}
