package com.remitly.shortly.controller;

import com.remitly.shortly.model.Url;
import com.remitly.shortly.model.UrlRequest;
import com.remitly.shortly.service.IShortlyService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
@RestController
public class ShortlyMainController {

    IShortlyService shortlyService;

    public ShortlyMainController(IShortlyService shortlyService) {
        this.shortlyService = shortlyService;
    }

    @PostMapping(value = "/create-short-url", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Url> createShortUrl(@RequestBody @Valid UrlRequest urlRequest) {
        Url url = shortlyService.createUrl(urlRequest);
        return ResponseEntity.ok(url);
    }


    @GetMapping(value = "/get-url/{shortUrl}")
    public ResponseEntity<Url> getOriginalUrl(@PathVariable String shortUrl) {
        Url url = shortlyService.getUrl(shortUrl);
        return ResponseEntity.ok(url);
    }
}
