import { Component, OnInit, Input } from '@angular/core';
import { RideService } from "app/ride.service";

import { Ride } from "app/ride";

@Component({
  selector: 'app-rides',
  templateUrl: './rides.component.html',
  styleUrls: ['./rides.component.css']
})
export class RidesComponent implements OnInit {
  @Input() rides: Ride[];

  lat: number = 52.5;
  lng: number = 5.75;
  zoom: number = 7;

  constructor() { }

  ngOnInit() {
  }

}
