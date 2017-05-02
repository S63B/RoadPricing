import { Component, OnInit } from '@angular/core';
import { Ride } from "app/ride";
import { RideService } from "app/ride.service";
import { Pol } from "app/pol";
import { Invoice } from "app/invoice";
import { User } from "app/user";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  rides: Ride[] = [];
  invoices: Invoice[] = [];

  constructor(private rideService: RideService) { }

  ngOnInit() {
    let date = new Date();
    let currentMilis: number = Date.now();
    date.setMonth(date.getMonth() - 1);
    let prevMonthMilis: number = date.getMilliseconds();

    this.getRidesBetween(prevMonthMilis, currentMilis);

    this.getInvoices();
  }

  public getRidesBetween(startTime: number, endTime: number) {
    this.rideService.getRidesBetween('10DRLL', startTime, endTime).subscribe(rides => {
      this.rides = rides;
    });
  }


  public getInvoices() {
    // this.rideService.getRidesBetween('bramdb', startTime, endTime).subscribe(rides => {
    //   this.rides = rides;
    // });

    let user: User = new User();
    this.invoices.push(new Invoice(1, user, new Date(), 123, new Date(), new Date(), 1, 'nl'));
    this.invoices.push(new Invoice(1, user, new Date(), 123, new Date(), new Date(), 1, 'nl'));
    this.invoices.push(new Invoice(1, user, new Date(), 123, new Date(), new Date(), 1, 'nl'));
    this.invoices.push(new Invoice(1, user, new Date(), 123, new Date(), new Date(), 1, 'nl'));
  }
}
