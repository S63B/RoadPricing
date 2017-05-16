import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";
import { API_URL, API_URL_LOCALHOST } from "app/constants";

@Injectable()
export class CarService {

  constructor(private httpService: HttpService) {
  }

  /**
   * Gets all information belonging to a car.
   * 
   * @param {number} carId The identifier of a car.
   * @returns {Car} A car object containing all information of a car.
   */
  getById(carId: number) {
    return this.httpService.get(`${API_URL}/car?carId=${carId}`)
      .map(this.httpService.extractData);
  }

  create(userId: number, licenseplate: string, energyLabel: string) {
    return this.httpService.post(`${API_URL_LOCALHOST}/car?userId=${userId}&licensePlate=${licenseplate}&energyLabel=${energyLabel}`)
      .map(this.httpService.extractData);
  }

  update(carId: number, licenseplate: string, energyLabel: string, trackerId: number) {
    return this.httpService.post(`${API_URL_LOCALHOST}/car?carId=${carId}&licensePlate=${licenseplate}&energyLabel=${energyLabel}&trackerid=${trackerId}`)
      .map(this.httpService.extractData);
  }
}