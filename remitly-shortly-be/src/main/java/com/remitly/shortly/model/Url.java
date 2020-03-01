package com.remitly.shortly.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "urls")
@Data
public class Url {
    private String shortened;
    private String original;
    private Long expires;
}
