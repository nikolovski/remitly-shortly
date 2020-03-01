package com.remitly.shortly.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableCaching
@EnableMongoRepositories(basePackages = "com.remitly.shortly.repository.mongo")
@EnableScheduling
@EnableConfigurationProperties(ShortlyConfigurationProperties.class)
@Configuration
public class ShortlyGlobalConfiguration {

}
