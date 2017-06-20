import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, Http } from '@angular/http';
import { TranslateModule, TranslateLoader, TranslateStaticLoader } from 'ng2-translate';

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
import { JodatimePipe } from './jodatime.pipe';
import { AgmCoreModule } from 'angular2-google-maps/core';
import { DirectionsMapDirective } from './directions-map.directive';
import { MapComponent } from './map/map.component';
import { ProfileComponent } from './profile/profile.component';
import { CarComponent } from './car/car.component';
import { CarService } from "app/car.service";
import { OwnerService } from "app/user.service";
import {RegistrationComponent} from "./registration/registration.component";
import {LoginComponent} from "./login/login.component";
import {AuthService} from "./auth.service";
import {CanActivateAuthGuard} from "./can-active.authguard";

export function createTranslateStaticLoader(http: Http) {
  return new TranslateStaticLoader(http, '/assets/i18n', '.json');
}

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
    JodatimePipe,
    ProfileComponent,
    CarComponent,
    DirectionsMapDirective,
    MapComponent,
    RegistrationComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    TranslateModule.forRoot({
      provide: TranslateLoader,
      useFactory: (createTranslateStaticLoader),
      deps: [Http]
    }),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDNtmOxKdE2VfxAHO6wTdiqRZMoGN_20cc'
    })
  ],
  providers: [
    HttpService,
    HttpModule,
    RideService,
    InvoiceService,
    CarService,
    OwnerService,
    AuthService,
    CanActivateAuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
