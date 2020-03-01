package com.remitly.shortly.validation.constraint;


import com.remitly.shortly.validation.validator.RemitlyUrlValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = RemitlyUrlValidator.class)
@Documented
public @interface Url {
    String message() default "The url provided is invalid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
