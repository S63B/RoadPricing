import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";
import { API_URL } from "app/constants";

@Injectable()
export class CarService {

  constructor(private httpService: HttpService) {
  }

  /**
   * Gets all information belonging to a car.
   * 
   * @param {number} carId The identifier of a car.
   * @returns {Car} A car object containing all information of a car.
   * 
   * @memberOf CarService
   */
  getCarInformation(carId: number) {
    return this.httpService.get(`${API_URL}/car?carId=${carId}`)
      .map(this.httpService.extractData);
  }

}
