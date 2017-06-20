import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";

import { Observable } from "rxjs/Rx";

import { Invoice } from "app/invoice";
import { environment } from "environments/environment";

@Injectable()
export class InvoiceService {
  private API_URL_ROADPRICING: string = environment.roadPricingUrl;

  constructor(private httpService: HttpService) { }

   public getInvoices(id: String): Observable<Invoice[]> {
    return this.httpService.get(`${this.API_URL_ROADPRICING}/invoices?user=${id}`)
      .map(this.httpService.extractData);
  }

}
