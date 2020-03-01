import {Component, Inject, OnInit} from '@angular/core';
import {catchError, filter, map, mergeMap, tap} from "rxjs/operators";
import {HttpErrorResponse} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AppService} from "../app.service";
import {Observable, of} from "rxjs";
import {Url} from "../core/models/url.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  hostname: string;
  url: Url;
  toShorten: string;
  loadMain: boolean = true;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private _snackBar: MatSnackBar,
              @Inject(AppService) private mainService: AppService) {
    this.hostname = window.location.hostname;
  }

  ngOnInit() {
    this.redirectListener();
  }

  private redirectListener(): void {
    this.route.paramMap
      .pipe(
        filter(paramMap => !!paramMap.get('shortUrl')),
        map(paramMap => paramMap.get('shortUrl')),
        tap(() => {this.loadMain = false}),
        mergeMap(shortUrl => this.mainService.getOriginalUrl(shortUrl))
      )
      .subscribe( result => {
        window.open(result.original, "_self");
      }, (error: HttpErrorResponse) => {
        this.loadMain = true;
        this._snackBar.open(error.message, 'Close', {duration: 2000, panelClass: "error-snackbar"});
      })
  }

  onSubmit(event) {
    event.preventDefault();
    if(!this.toShorten) {
      this._snackBar.open("Please provide url to be shortened");
      return;
    }
    this.mainService.createShortUrl(this.toShorten).subscribe(url => {
      this.url = url;
    }, error => {
      this._snackBar.open(error.message, 'Close', {duration: 2000, panelClass: "error-snackbar"});
    });
  }
}
