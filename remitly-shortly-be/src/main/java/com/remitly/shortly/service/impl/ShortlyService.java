package com.remitly.shortly.service.impl;

import com.remitly.shortly.config.ShortlyConfigurationProperties;
import com.remitly.shortly.exception.UrlNotFoundException;
import com.remitly.shortly.model.Url;
import com.remitly.shortly.model.UrlRequest;
import com.remitly.shortly.repository.ShortlyUrlRepository;
import com.remitly.shortly.service.IShortlyService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShortlyService implements IShortlyService {

    ShortlyUrlRepository repository;
    ShortlyConfigurationProperties properties;

    public ShortlyService(ShortlyUrlRepository repository, ShortlyConfigurationProperties properties) {
        this.repository = repository;
        this.properties = properties;
    }

    @Override
    public Url getShortUrl(String url) {
        Long timestamp = (new Date()).toInstant().toEpochMilli();
        Url urlByOriginal = repository.findByOriginalAndExpiresAfter(url, timestamp);
        if(urlByOriginal == null) throw new UrlNotFoundException();
        return urlByOriginal;
    }

    @Override
    public Url getUrl(String shortUrl) {
        Long timestamp = (new Date()).toInstant().toEpochMilli();
        Url urlByShortened = repository.findByShortenedAndExpiresAfter(shortUrl, timestamp);
        if(urlByShortened == null) throw new UrlNotFoundException();
        return urlByShortened;
    }

    @Cacheable(value = "urls", unless = "#urlRequest.url == null")
    @Override
    public Url createUrl(UrlRequest urlRequest) {
        boolean exists = repository.existsByOriginalAndExpiresAfter(urlRequest.getUrl(), (new Date()).toInstant().toEpochMilli());
        if(exists) return getShortUrl(urlRequest.getUrl());
        Url url = new Url();
        url.setOriginal(urlRequest.getUrl());
        url.setShortened(generateShortUrl());
        url.setExpires(getExpirationDate());
        return repository.save(url);
    }

    private Long getExpirationDate() {
        long limit = (long) properties.getExpirationDays() * 24 * 60 * 60 * 1000;
        return (new Date()).toInstant().toEpochMilli() + limit;
    }

    private String generateShortUrl() {
        return RandomStringUtils.randomAlphanumeric(7);
    }
}
