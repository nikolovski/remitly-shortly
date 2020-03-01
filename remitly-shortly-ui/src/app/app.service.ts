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
}
