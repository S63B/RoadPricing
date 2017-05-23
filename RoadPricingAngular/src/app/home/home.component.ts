import { Component, OnInit } from '@angular/core';
import { Ride } from "app/ride";
import { RideService } from "app/ride.service";
import { Pol } from "app/pol";
import { Invoice } from "app/invoice";
import { User } from "app/user";
import { InvoiceService } from "app/invoice.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  rides: Ride[] = [];
  invoices: Invoice[] = [];

  constructor(private rideService: RideService, private invoiceService: InvoiceService) { }

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
    
    //   let pol: Pol = new Pol(1, 52.5, 5.7, 858);
    //   let pol2: Pol = new Pol(1, 52.5, 5.8, 858);
    //   let pol3: Pol = new Pol(1, 52.5, 5.9, 858);
    //   this.rides.push(new Ride([pol, pol2, pol3], 123123, 123123, 123123));
    //   this.rides.push(new Ride([pol2, pol, pol3], 34534, 3453462334, 345345345));
    //   this.rides.push(new Ride([pol3, pol2, pol], 123123, 24234234, 234345345));
  }


  public getInvoices() {
    this.invoiceService.getInvoices('1').subscribe(invoices => {
       this.invoices = invoices;
    });
  }
}
