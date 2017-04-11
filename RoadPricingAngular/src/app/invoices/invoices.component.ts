import { Component, OnInit, Input } from '@angular/core';
import { Invoice } from "app/invoice";

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.css']
})
export class InvoicesComponent implements OnInit {

  @Input() invoices: Invoice[];

  constructor() { }

  ngOnInit() {
  }

}
