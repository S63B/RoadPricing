import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { InvoiceComponent } from "app/invoice/invoice.component";
import { ProfileComponent } from "app/profile/profile.component";
import { CarComponent } from "app/car/car.component";

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
    path: 'profile/:userId',
    component: ProfileComponent
  },
  {
    path: 'car/:userId/:carId',
    component: CarComponent
  },
  {
    path: 'car',
    component: CarComponent
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
