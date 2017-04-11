import { Component, OnInit } from '@angular/core';
import { RideService } from "app/ride.service";
import { Ride } from "app/ride";

@Component({
  selector: 'app-rides',
  templateUrl: './rides.component.html',
  styleUrls: ['./rides.component.css']
})
export class RidesComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
