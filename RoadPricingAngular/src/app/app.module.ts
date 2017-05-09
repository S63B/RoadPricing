import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { TranslateModule } from 'ng2-translate';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';

import { routing } from './app.routing';
import { InvoiceComponent } from './invoice/invoice.component';
import { HomeComponent } from './home/home.component';
import { RidesComponent } from './rides/rides.component';
import { InvoicesComponent } from './invoices/invoices.component';
import { HttpService } from "app/http.service";
import { RideService } from "app/ride.service";
import { InvoiceService } from "app/invoice.service";
import { DistancePipe } from './distance.pipe';
import { AgmCoreModule } from 'angular2-google-maps/core';
import { DirectionsMapDirective } from './directions-map.directive';
import { MapComponent } from './map/map.component';
import { RideComponent } from './ride/ride.component';
import { ProfileComponent } from './profile/profile.component';
import { CarComponent } from './car/car.component';
import { CarService } from "app/car.service";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    InvoiceComponent,
    HomeComponent,
    RidesComponent,
    InvoicesComponent,
    DistancePipe,
    RideComponent,
    ProfileComponent,
    CarComponent
    DirectionsMapDirective,
    MapComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    TranslateModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDNtmOxKdE2VfxAHO6wTdiqRZMoGN_20cc'
    })
  ],
  providers: [
    HttpService,
    HttpModule,
    RideService,
    InvoiceService,
    CarService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
