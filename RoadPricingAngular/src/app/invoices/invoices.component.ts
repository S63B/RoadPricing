import { Component, OnInit, Input } from '@angular/core';
import { Invoice } from "app/invoice";
import { environment } from "environments/environment";

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.css']
})
export class InvoicesComponent implements OnInit {
  @Input() invoices: Invoice[];
  public API_URL_ROADPRICING: string = environment.roadPricingUrl;

  constructor() { }

  ngOnInit() {
  }

}
