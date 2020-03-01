import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Url} from "./core/models/url.model";
import {UrlRequest} from "./core/models/url-request.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  createShortUrl(url: string ): Observable<Url> {
    return this.http.post<Url>('/api/create-short-url', new UrlRequest(url));
  }

  getOriginalUrl(shortUrl: string): Observable<Url> {
    return this.http.get<Url>('/api/get-url/'+ shortUrl);
  }

  saveInCookie(url: Url) {
    document.cookie = "shortUrl "+url.shortened + "=" + url.original+" "+url.expires;
  }

  getShortUrlsFromCookies(): Array<Url> {
    const decodedCookie = decodeURIComponent(document.cookie);
    const allCookies= decodedCookie.split(';');
    const shortUrlCookies = allCookies.filter(cookie => cookie.includes("shortUrl"));
    const shortUrls = new Array<Url>();

    shortUrlCookies.forEach(shortUrlCookie  => {
      let keyValue = shortUrlCookie.split('=');
      let key = keyValue[0].trim();
      let value = keyValue[1].trim();
      let shortUrl = new Url();
      shortUrl.shortened = key.split(' ')[1];
      shortUrl.original = value.split(' ')[0];
      shortUrl.expires = +value.split(' ')[1];
      shortUrls.unshift(shortUrl);
    });

    return shortUrls;
  }
}
