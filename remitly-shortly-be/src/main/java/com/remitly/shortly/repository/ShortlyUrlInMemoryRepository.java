package com.remitly.shortly.repository;

import com.remitly.shortly.model.Url;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;


@Profile("in-memory-db")
public interface ShortlyUrlInMemoryRepository extends CrudRepository<Url, String>, ShortlyUrlRepository {
    @Override
    Url findByShortenedAndExpiresAfter(String shortUrl, Long timestamp);

    @Override
    void deleteAllByExpiresBefore(Long expires);
}
