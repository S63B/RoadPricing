import { Component, OnInit, Input } from '@angular/core';
import { Invoice } from "app/invoice";
import { environment } from "environments/environment";
import {InvoiceService} from "../invoice.service";

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.css']
})
export class InvoicesComponent implements OnInit {
  @Input() invoices: Invoice[];
  private API_URL_ADMINISTRATION: string = environment.administrationUrl;

  constructor(private invoiceService: InvoiceService ) { }

  ngOnInit() {
  }

  payInvoice(totalPrice: number, invoiceId: number) {
    this.invoiceService.pay(totalPrice, invoiceId).subscribe(res => {
      window.open(res.json().href);
    });
  }
}
