package com.remitly.shortly.service;

import com.remitly.shortly.model.Url;
import com.remitly.shortly.model.UrlRequest;

public interface IShortlyService {
    Url getShortUrl(String url);
    Url getUrl(String shortUrl);
    Url createUrl(UrlRequest originalUrl);
}
