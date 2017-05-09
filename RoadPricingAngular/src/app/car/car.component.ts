import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { CarService } from "app/car.service";

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {
  private isNewCar: boolean = true;
  private car: object = {
    id: -1,
    manufacturer: "Tesla",
    model: "Model X",
    licensePlate: {
      id: -1,
      license: "AA-123-B",
      expirationDate: new Date()
    },
    energyLabel: "A",
    isStolen: false,
    tracker: {
      id: undefined
    }
  }
  private energyLabels: string[] = [ "A", "B", "C", "D", "E", "F" ];

  constructor(
    private route: ActivatedRoute,
    private carService: CarService) {

  }

  ngOnInit() {
    // If no valid carId has been sent as a parameter, the page should display different controls to insert a new car.
    this.route.params.subscribe(parameters => {
      if (parameters['carId'] && Number(parameters['carId']) !== -1) {
        this.getCarInformation(parameters['carId']);
        this.isNewCar = false;
      }
    });
  }

  /**
   * Gets all information of a car with the given id.
   * 
   * @param {number} carId The identifier of a car.
   * 
   * @memberOf CarComponent
   */
  getCarInformation(carId: number) {
    // this.car = this.carService.getCarInformation(carId);
  }

  /**
   * Saves all info of a new or already existing car using the submitted input from the form.
   */
  saveCarInformation() {
    console.log("Test on click save car information");
  }
}
