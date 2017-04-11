import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { HomeComponent } from './home/home.component';

const appRoutes: Routes = [
  {
    component: HomeComponent,
    path: '',
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
