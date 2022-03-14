package com.smartapps.smartlib.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully retrieved NEW2."), 
		@ApiResponse(responseCode = "400", description = "Bad request."),
		@ApiResponse(responseCode = "401", description = "You are not authorized to access the resource."),
		@ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden."),
		@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found.")
    })
public @interface GlobalApiReponses {

}
