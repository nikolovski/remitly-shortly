package com.remitly.shortly.validation.validator;

import com.remitly.shortly.validation.constraint.Url;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RemitlyUrlValidator implements ConstraintValidator<Url, String> {
    @Override
    public boolean isValid(String url, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isEmpty(url)) return false;
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
    }
}
