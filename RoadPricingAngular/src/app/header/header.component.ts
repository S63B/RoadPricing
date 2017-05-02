import { Component, OnInit } from '@angular/core';
import { TranslateService } from "ng2-translate";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private translate: TranslateService) { }

  ngOnInit() {
  }

}
