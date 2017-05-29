import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { CarService } from "app/car.service";

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {
  private userId: number;
  private isNewCar: boolean;
  private ownership = {
    car: {
      id: 0,
      licensePlate: {
        license: '',
        expirationDate: ''
      },
      energyLabel: ''
    },
    purchaseDate: '',
    tracker: {
      id: 0,
      serialNumber: ''
    }
  };
  private energyLabels: string[] = ["A", "B", "C", "D", "E", "F"];

  constructor(
    private activatedRoute: ActivatedRoute,
    private carService: CarService,
    private router: Router) {

  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(parameters => {
      const carId: number = Number(parameters['carId']);
      // If the car Id is equal to -1 it means a new car should be created.
      if (carId === -1) {
        this.isNewCar = true;
      } else {
        this.isNewCar = false;
        this.getCarInformation(parameters['carId']);
      }

      // Get the users / owners ID so new cars can be linked to the owner.
      const userId: number = Number(parameters['userId']);
      if (typeof userId !== 'undefined') {
        this.userId = userId;
      }
    });
  }

  /**
   * Gets all information of a car with the given id.
   * 
   * @param {number} carId The identifier of a car.
   */
  getCarInformation(carId: number) {
    this.carService.getById(carId).subscribe(result => {
      this.ownership = this.convertCarOwnershipDates(result.json());;
    });
  }

  /**
   * Saves all info of a new or already existing car using the submitted input from the form.
   */
  saveCarInformation() {
    if (this.isNewCar) {
      this.createNewCar(this.userId, this.ownership.car.licensePlate.license, this.ownership.car.licensePlate.expirationDate, this.ownership.purchaseDate, this.ownership.car.energyLabel, this.ownership.tracker.serialNumber);
    } else {
      this.updateCar(this.ownership.car.id, this.ownership.car.licensePlate.license, this.ownership.car.licensePlate.expirationDate, this.ownership.purchaseDate, this.ownership.car.energyLabel, this.ownership.tracker.serialNumber);
    }
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
  createNewCar(userId: number, licenseplate: string, licenseExpirationDate: string, carPurchaseDate: string, energyLabel: string, trackerSerialNumber: string) {
    this.carService.create(userId, licenseplate, licenseExpirationDate, carPurchaseDate, energyLabel, trackerSerialNumber).subscribe(result => {
      const newCarID = result.json().car.id;
      // Navigates to same page with the new car id.
      this.router.navigate([`/car/${this.userId}/${newCarID}`]);
    });
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
  updateCar(carId: number, licenseplate: string, licenseExpirationDate: string, carPurchaseDate: string, energyLabel: string, trackerSerialNumber: string) {
    this.carService.update(carId, licenseplate, licenseExpirationDate, carPurchaseDate, energyLabel, trackerSerialNumber).subscribe(result => {
      this.ownership = this.convertCarOwnershipDates(result.json());
    });
  }

  /**
   * Converts the car ownership dates to readable string which can be edited in the component.
   * @param {any} carOwnership The car ownership object of which the dates should be converted.
   * @returns {any} The car ownership object with updated dates.
   */
  convertCarOwnershipDates(carOwnership: any) {
      carOwnership.purchaseDate = this.carService.convertJodaTimeDateToString(carOwnership.purchaseDate);
      carOwnership.car.licensePlate.expirationDate = this.carService.convertJodaTimeDateToString(carOwnership.car.licensePlate.expirationDate);
      return carOwnership;
  }
}
