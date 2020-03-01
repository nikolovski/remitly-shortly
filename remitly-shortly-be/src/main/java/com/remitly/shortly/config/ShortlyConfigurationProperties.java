package com.remitly.shortly.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "remitly.shortly")
public class ShortlyConfigurationProperties {
    Integer expirationDays;
    String dbCleanUpCron;
}
