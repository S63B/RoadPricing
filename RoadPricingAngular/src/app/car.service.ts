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
    return this.httpService.get(`${API_URL_LOCALHOST}/car?id=${carId}`)
      .map((result) => {
        return result.json();
      });
  }

  create(userId: number, licenseplate: string, expirationDate: string, purchaseDate: string, energylabel: string) {
    return this.httpService.post(`${API_URL_LOCALHOST}/car/create?userId=${userId}&licensePlate=${licenseplate}&expirationDate=${expirationDate}&purchaseDate=${purchaseDate}&energylabel=${energylabel}`);
  }

  update(carId: number, licenseplate: string, energyLabel: string, trackerId: number) {
    return this.httpService.post(`${API_URL_LOCALHOST}/car/update?carId=${carId}&licensePlate=${licenseplate}&energyLabel=${energyLabel}&trackerid=${trackerId}`);
  }
}