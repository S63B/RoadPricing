import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { InvoiceComponent } from "app/invoice/invoice.component";
import { ProfileComponent } from "app/profile/profile.component";
import { CarComponent } from "app/car/car.component";
import {RegistrationComponent} from "./registration/registration.component";
import {LoginComponent} from "./login/login.component";
import {CanActivateAuthGuard} from "./can-active.authguard";

const appRoutes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [CanActivateAuthGuard]
  },
  {
    path: 'invoice',
    component: InvoiceComponent,
    canActivate: [CanActivateAuthGuard]
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [CanActivateAuthGuard]
  },
  {
    path: 'car/:userId/:carId',
    component: CarComponent,
    canActivate: [CanActivateAuthGuard]
  },
  {
    path: 'car',
    component: CarComponent,
    canActivate: [CanActivateAuthGuard]
  },
  {
    path: 'register',
    component: RegistrationComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
