package com.remitly.shortly.repository.mongo;

import com.remitly.shortly.model.Url;
import com.remitly.shortly.repository.ShortlyUrlRepository;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ShortlyUrlMongoRepository extends MongoRepository<Url, String>, ShortlyUrlRepository {

    @Override
    Url findByShortenedAndExpiresAfter(String shortUrl, Long timestamp);

    @Override
    boolean existsByOriginalAndExpiresAfter(String originalUrl, Long timestamp);

    @Override
    Url findByOriginalAndExpiresAfter(String originalUrl, Long timestamp);

    @Override
    Url save(Url s);

    @Override
    void deleteAllByExpiresBefore(Long expires);
}
