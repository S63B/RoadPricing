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
  private car = {
    id: 0,
    licensePlate: {
      license: '',
      expirationDate: ''
    },
    purchaseDate: '',
    energyLabel: '',
    tracker: {
      id: 0
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
      // If the car Id is a number and equal to -1, a new car should be created.
      if (carId === -1) {
        this.isNewCar = true;
      } else {
        this.isNewCar = false;
        this.getCarInformation(parameters['carId']);
      }

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
    this.carService.getById(carId).subscribe(foundCar => {
      foundCar.purchaseDate = this.carService.convertJodaTimeDateToString(foundCar.purchaseDate);
      foundCar.licensePlate.expirationDate = this.carService.convertJodaTimeDateToString(foundCar.licensePlate.expirationDate);
      this.car = foundCar;
    });
  }

  /**
   * Saves all info of a new or already existing car using the submitted input from the form.
   */
  saveCarInformation() {
    if (this.isNewCar) {
      this.createNewCar(this.userId, this.car.licensePlate.license, this.car.licensePlate.expirationDate, this.car.purchaseDate, this.car.energyLabel);
    } else {
      this.updateCar(this.car.id, this.car.licensePlate.license, this.car.energyLabel, this.car.tracker.id);
    }
  }

  /**
   * Creates a new car.
   * @param userId The id of the owner of the car.
   * @param licenseExpirationDate The license plate string of the new car.
   * @param carExpirationDate The expiration date of the license plate. 
   * @param purchaseDate The purchase date of the car.
   * @param energyLabel The energy label of the car.
   */
  createNewCar(userId: number, licenseplate: string, licenseExpirationDate: string, carPurchaseDate: string, energyLabel: string) {
    this.carService.create(userId, licenseplate, licenseExpirationDate, carPurchaseDate, energyLabel).subscribe(result => {
      const newCarID = result.json().id;
      // Navigates to same page with the new car id.
      this.router.navigate([`/car/${this.userId}/${newCarID}`]);
    });
  }

  updateCar(carId: number, licenseplate: string, energyLabel: string, trackerId: number) {
    this.carService.update(carId, licenseplate, energyLabel, trackerId);
  }
}
