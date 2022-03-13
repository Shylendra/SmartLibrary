package com.smartapps.smartlib.validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.smartapps.smartlib.validators.AppIdValidator;

@Documented
@Constraint(validatedBy = AppIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAppId {
    String message() default "Invalid application id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
