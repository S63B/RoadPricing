import { Component } from '@angular/core';
import { TranslateService } from "ng2-translate";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app works!';

  constructor(private translate: TranslateService) {
    translate.addLangs(['en', 'nl']);
    translate.setDefaultLang('nl');
    translate.use('en');
  }
}
