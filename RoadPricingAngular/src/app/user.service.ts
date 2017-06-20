import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";

import { Observable } from "rxjs/Rx";
import {Owner} from "./owner";
import {RequestOptions, Headers} from "@angular/http";
import { environment } from "environments/environment";

@Injectable()
export class OwnerService {
  private API_URL_ROADPRICING: string = environment.roadPricingUrl;
  private API_URL_ADMINISTRATION: string = environment.administrationUrl;

  constructor(private httpService: HttpService) {
  }

  /**
   * Gets all info of a user.
   *
   * @param {number} id The id of the user of which all information should be retrieved.
   * @returns
   */
  getUserInfo(id: number): Observable<any> {
    return this.httpService
      .get(`${this.API_URL_ROADPRICING}/owner?id=${id}`)
      .map(this.httpService.extractData);
  }

  /**
   * Updates the info of a user.
   *
   * @param {number} id The id of the user which should be updated.
   * @param {string} address The address of the user.
   * @param {string} residence The residence of the user.
   */
  update(id: number, address: string, residence: string): Observable<any> {
    return this.httpService
      .put(`${this.API_URL_ROADPRICING}/owner/info?id=${id}&address=${address}&residence=${residence}`)
      .map(this.httpService.extractData);
  }

  /**
   * Gets all cars from a given owner.
   * @param ownerId The id of the owner.
   */
  getCarsByOwnerId(ownerId: number) {
    return this.httpService.get(`${this.API_URL_ROADPRICING}/owner/${ownerId}/cars`);
  }

  /**
   * Register an Owner.
   * @param owner The newly created owner
   */
  register(owner: Owner) {
    let url = `${this.API_URL_ADMINISTRATION}/owner/create`;
    let body = JSON.stringify(owner);
    console.log(body);

    let head = new Headers();
    head.append("Content-Type", 'application/json');

    let options = new RequestOptions({headers: head});

    return this.httpService
      .nonAuthorizedPost(url, body, options)
      .map(res => res.status);
  }

}
