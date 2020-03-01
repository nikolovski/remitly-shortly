package com.remitly.shortly.service.impl;

import com.remitly.shortly.repository.ShortlyUrlRepository;
import com.remitly.shortly.service.IDbMaintenanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class DbMaintenanceService implements IDbMaintenanceService {

    ShortlyUrlRepository repository;

    public DbMaintenanceService(ShortlyUrlRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 60000)
    @Override
    public void cleanUpExpiredShortUrls() {
        log.info("Deleting all expired short urls");
        Long timestamp = (new Date()).toInstant().toEpochMilli();
        repository.deleteAllByExpiresBefore(timestamp);
    }
}
