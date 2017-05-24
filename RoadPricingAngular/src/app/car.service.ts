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
      .map((result) => result.json());
  }

  create(userId: number, licenseplate: string, licenseExpirationDate: string, carPurchaseDate: string, energylabel: string) {
    return this.httpService.post(`${API_URL_LOCALHOST}/car/create?userId=${userId}&licensePlate=${licenseplate}&expirationDate=${licenseExpirationDate}&purchaseDate=${carPurchaseDate}&energylabel=${energylabel}`);
  }

  update(carId: number, licenseplate: string, licenseExpirationDate: string, carPurchaseDate: string, energyLabel: string, trackerSerialNumber: string) {
    return this.httpService.post(`${API_URL_LOCALHOST}/car/update?carId=${carId}&licensePlate=${licenseplate}&expirationDate=${licenseExpirationDate}&purchaseDate=${carPurchaseDate}&energyLabel=${energyLabel}&trackerSerialNumber=${trackerSerialNumber}`);
  }

  /**
   * Converts a JodaTime Date-object to a human readable string in the 'DD-MM-YYYY' format.
   * @param jodaTimeDate The JodaTime Date-object which should be converted.
   * @returns {String} A human readable date string in the 'DD-MM-YYYY' format.
   */
  convertJodaTimeDateToString(jodaTimeDate: any): String {
    return `${jodaTimeDate.dayOfMonth}-${jodaTimeDate.monthOfYear}-${jodaTimeDate.year}`;
  }
}