import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { InvoiceComponent } from "app/invoice/invoice.component";
import { RideComponent } from "app/ride/ride.component";

const appRoutes: Routes = [
  {
    component: HomeComponent,
    path: '',
  },
  {
    path: 'invoice',
    component: InvoiceComponent
  },
  {
    path: 'ride:ride',
    component: RideComponent,
    data: {
      type: 'ride'
    }
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
