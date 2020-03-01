import {AfterContentInit, Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Route, Router, RouterModule} from "@angular/router";
import {AppService} from "./app.service";
import {catchError, filter, map, mergeMap, switchMap} from "rxjs/operators";
import {Observable, of} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, AfterContentInit{
  title = 'remitly-shortly-ui';

  constructor(private route: ActivatedRoute,
              private router: Router,
              @Inject(AppService) private mainService: AppService) {

  }

  ngOnInit(): void {
    this.redirectListener();
  }

  private redirectListener(): void {
    this.route.paramMap
      .pipe(
        filter(paramMap => !!paramMap.get('shortUrl')),
        map(paramMap => paramMap.get('shortUrl')),
        mergeMap(shortUrl => this.mainService.getOriginalUrl(shortUrl))
      )
      .subscribe( result => {
        window.open(result.original, "_self");
      }, error => {
        console.log(error)
      })
  }

  ngAfterContentInit(): void {


  }


}
