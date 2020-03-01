package com.remitly.shortly.model;

import com.remitly.shortly.validation.constraint.Url;
import lombok.Data;

@Data
public class UrlRequest {
    @Url
    private String url;
}
