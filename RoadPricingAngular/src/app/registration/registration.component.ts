import { Component, OnInit } from '@angular/core';
import {Owner} from "../owner";
import {OwnerService} from "../user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  private owner: Owner = new Owner();

  constructor(private ownerService: OwnerService, private router: Router) { }

  ngOnInit() {
    this.owner.canEditPrice = false;
    this.owner.usesWebsite = true;
  }


  register() {
    this.ownerService
      .register(this.owner)
      .subscribe(res => {
        console.log(res);
        if (res == 200) {
          this.router.navigateByUrl('');
        }  else {
          this.owner = new Owner();
        }
      });
  }

}
