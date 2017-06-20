import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";
import { Observable } from "rxjs/Rx";
import 'rxjs/add/operator/map';
import {Router} from "@angular/router";

import { environment } from "environments/environment";

@Injectable()
export class AuthService {
  private API_URL_ROADPRICING: string = environment.roadPricingUrl;

  constructor(private httpService: HttpService, private router: Router) {
  }

  isAuthorized(): boolean {
    let token = localStorage.getItem("auth");
    return token && token.length > 0;
  }

  login(username: string, password: string): Observable<any> {
    localStorage.setItem("auth", `${btoa(`${username}:${password}`)}`);

    let url = `${this.API_URL_ROADPRICING}/owner/loggedin`;
    return this.httpService.get(url)
      .map(res => res.status)
      .catch(res => Observable.of(res.status));
  }

  logout() {
    localStorage.removeItem("auth");
    this.router.navigateByUrl('login');
  }
}
