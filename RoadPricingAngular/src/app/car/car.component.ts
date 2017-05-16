import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
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
    purchaseDate: '',
    licensePlate: {
      license: '',
      expirationDate: ''
    },
    energyLabel: '',
    tracker: {
      id: 0
    }
  };
  private energyLabels: string[] = [ "A", "B", "C", "D", "E", "F" ];

  constructor(
    private route: ActivatedRoute,
    private carService: CarService) {

  }

  ngOnInit() {
    this.route.params.subscribe(parameters => {
      const carId: number = Number(parameters['carId']);
      // If the car Id is a number and equal to -1, a new car should be created.
      if (!isNaN(carId) && carId === -1) {
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
      this.car = foundCar;
    });
  }

  /**
   * Saves all info of a new or already existing car using the submitted input from the form.
   */
  saveCarInformation() {
    if (this.isNewCar) {
      this.createNewCar(this.userId, this.car.licensePlate.license, this.car.energyLabel);
    } else {
      this.updateCar(this.car.id, this.car.licensePlate.license, this.car.energyLabel, this.car.tracker.id);
    }
  }

  createNewCar(userId: number, licenseplate: string, energyLabel: string) {
    this.carService.create(userId, licenseplate, energyLabel);
  }

  updateCar(carId: number, licenseplate: string, energyLabel: string, trackerId: number) {
    this.carService.update(carId, licenseplate, energyLabel, trackerId);
  }
}
