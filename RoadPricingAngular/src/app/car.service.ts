import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";

import { environment } from "environments/environment";

@Injectable()
export class CarService {
  private API_URL_ROADPRICING: string = environment.roadPricingUrl;

  constructor(private httpService: HttpService) {
  }

  /**
   * Gets all information belonging to a car.
   * 
   * @param {number} carId The identifier of a car.
   * @returns {Car} A car object containing all information of a car.
   */
  getById(carId: number) {
    return this.httpService.get(`${this.API_URL_ROADPRICING}/car?id=${carId}`);
  }

  /**
   * Creates a new car.
   * @param {number} userId The id of the owner of the car.
   * @param {string} licensePlate The license plate string of the new car.
   * @param {string} licenseExpirationDate The expiration date of the license plate of the new car.
   * @param {string} purchaseDate The purchase date of the car.
   * @param {string} energyLabel The energy label of the car.
   * @param {number} trackerSerialNumber The serial number of the tracker that is being used by the car.
   */
  create(userId: number, licenseplate: string, licenseExpirationDate: string, carPurchaseDate: string, energylabel: string, trackerSerialNumber: string) {
    return this.httpService.post(`${this.API_URL_ROADPRICING}/car/create?userId=${userId}&licensePlate=${licenseplate}&expirationDate=${licenseExpirationDate}&purchaseDate=${carPurchaseDate}&energylabel=${energylabel}&trackerSerialNumber=${trackerSerialNumber}`);
  }

  /**
   * Updates an existing car.
   * @param {number} carId The id of the car which should be updated.
   * @param {string} licensePlate The license plate string of a car.
   * @param {string} licenseExpirationDate The expiration date of the license plate of a car.
   * @param {string} carPurchaseDate The purhase date of a car.
   * @param {string} energyLabel The energy label of a car.
   * @param {number} trackerSerialNumber The serial number of the tracker that is being used by the car.
   */
  update(carId: number, licenseplate: string, licenseExpirationDate: string, carPurchaseDate: string, energyLabel: string, trackerSerialNumber: string) {
    return this.httpService.put(`${this.API_URL_ROADPRICING}/car/update?carId=${carId}&licensePlate=${licenseplate}&expirationDate=${licenseExpirationDate}&purchaseDate=${carPurchaseDate}&energyLabel=${energyLabel}&trackerSerialNumber=${trackerSerialNumber}`);
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