import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public username: string = "";
  public password: string = "";
  public succes: boolean = true;

  constructor(public authService: AuthService, public router: Router) { }

  ngOnInit() {
    this.authService.logout();
  }

  login() {
    this.authService.login(this.username, this.password).subscribe(res => {
      console.log(res);
      this.succes = (res == 200);
      console.log(`Succes: ${this.succes}`);

      if (this.succes) {
        console.log('Log in succesfull');
        // localStorage.setItem("owner", res);
        this.router.navigateByUrl('');
      }
      else {
        console.log("Failed logging in")
        localStorage.removeItem("auth");
      }
    })

  }

}
