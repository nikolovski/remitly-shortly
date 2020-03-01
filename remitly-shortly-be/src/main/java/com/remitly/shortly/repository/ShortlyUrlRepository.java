package com.remitly.shortly.repository;

import com.remitly.shortly.model.Url;

public interface ShortlyUrlRepository {
    Url findByShortenedAndExpiresAfter(String shortUrl, Long timestamp);
    Url findByOriginalAndExpiresAfter(String originalUrl, Long timestamp);
    boolean existsByOriginalAndExpiresAfter(String originalUrl, Long timestamp);
    void deleteAllByExpiresBefore(Long timestamp);
    Url save(Url s);
}
