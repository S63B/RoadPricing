import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { Ride } from "app/ride";
import { environment } from "environments/environment";
import {AuthService} from "./auth.service";


@Injectable()
export class RideService {
  private API_URL_ROADPRICING: string = environment.roadPricingUrl;
  private API_URL_ADINISTRATION: string = environment.administrationUrl;
  private API_URL_TRACKING: string = environment.trackingUrl;

  constructor(private httpService: HttpService) {
  }


  public getRidesBetween(id: String, startTime: number, endTime: number): Observable<Ride[]> {
    return this.httpService.get(`${this.API_URL_TRACKING}/rides?license_plate=${id}&start_date=${startTime}&end_date=${endTime}`)
      .map(this.httpService.extractData);
  }

  public get(id: String): Observable<Ride> {
    return this.httpService.get(`${this.API_URL_TRACKING}/ride?id=${id}`)
      .map(response => response.json());
  }

  public getCars(id: number) : Observable<any> {
    return this.httpService.get(`${this.API_URL_ADINISTRATION}/owner/${id}/cars`)
      .map(response => response.json());
  }
}
