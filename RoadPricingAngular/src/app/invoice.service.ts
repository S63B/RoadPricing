import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";

import { Observable } from "rxjs/Rx";
import { API_URL_LOCALHOST } from "app/constants";
import { Invoice } from "app/invoice";

@Injectable()
export class InvoiceService {

  constructor(private httpService: HttpService) { }

   public getInvoices(id: String): Observable<Invoice[]> {
    return this.httpService.get(`${API_URL_LOCALHOST}/invoices?user=${id}`)
      .map(this.httpService.extractData);
  }

}
