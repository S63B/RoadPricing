import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { InvoiceComponent } from "app/invoice/invoice.component";

const routes: Routes = [
//   { path: '', redirectTo: '/invoice', pathMatch: 'full' },
//   { path: 'invoice', component: InvoiceComponent }
//   { path: 'dashboard',  component: DashboardComponent },
//   { path: 'detail/:id', component: HeroDetailComponent },
//   { path: 'heroes',     component: HeroesComponent }
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}