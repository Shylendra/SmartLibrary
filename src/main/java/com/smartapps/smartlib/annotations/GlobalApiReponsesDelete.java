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
@Target(ElementType.METHOD)
@ApiResponses(value = { 
		@ApiResponse(responseCode = "400", description = "Bad request."),
		@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found.")
    })
public @interface GlobalApiReponsesDelete {

}
