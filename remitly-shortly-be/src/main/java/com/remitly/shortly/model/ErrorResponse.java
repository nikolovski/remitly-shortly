package com.remitly.shortly.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    Long timestamp;
    Integer status;
    String message;
}
