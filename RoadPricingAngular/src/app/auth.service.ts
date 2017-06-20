import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";
import { Observable } from "rxjs/Rx";
import 'rxjs/add/operator/map';
import {API_URL_LOCALHOST} from "./constants";
import {Router} from "@angular/router";

@Injectable()
export class AuthService {

  constructor(private httpService: HttpService, private router: Router) {
  }

  isAuthorized(): boolean {
    let token = localStorage.getItem("auth");
    console.log(token);
    return token && token.length > 0;
  }

  login(username: string, password: string): Observable<any> {
    localStorage.setItem("auth", `${btoa(`${username}:${password}`)}`);
    console.log("auth set");

    let url = `${API_URL_LOCALHOST}/owner/loggedin`;
    return this.httpService.get(url)
      .map(res => res.status)
      .catch(res => Observable.of(res.status));
  }

  logout() {
    localStorage.removeItem("auth");
    this.router.navigateByUrl('login');
  }
}
