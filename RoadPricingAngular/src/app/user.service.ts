import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";

import { API_URL_LOCALHOST } from './constants';
import { Observable } from "rxjs/Rx";

@Injectable()
export class UserService {

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
      .get(`${API_URL_LOCALHOST}/user?id=${id}`)
      .map(this.httpService.extractData);
  }

  /**
   * Updates the info of a user.
   * 
   * @param {number} id The id of the user which should be updated.
   * @param {string} address The address of the user.
   * @param {string} residence The residence of the user.
   * @returns 
   */
  update(id: number, address: string, residence: string): Observable<any> {
    return this.httpService
      .post(`${API_URL_LOCALHOST}/user/info?id=${id}&address=${address}&residence=${residence}`)
      .map(this.httpService.extractData);
  }

}
