import { Component, OnInit, Input } from '@angular/core';
import { UserService } from "app/user.service";
import { ActivatedRoute } from "@angular/router";
import { User } from "app/user";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  private userID: number;
  private user: User = new User();

  constructor(private userService: UserService, private route: ActivatedRoute) {
    this.route.params.subscribe(parameters => {
      this.userID = parameters['userId'];
    });
   }

  ngOnInit() {
    this.getUserInfo(this.userID);
  }

  /**
   * Gets all user info from the current user.
   * 
   * @param {number} userID The id of the user of which all info should be retrieved.
   */
  getUserInfo(userID: number) {
    this.userService.getUserInfo(userID).subscribe(response => {
      if (typeof response !== "string") {
        this.user = response as User;
        this.user["cars"] = [ { name: "Tesla", model: "Model X" }];
        console.log(this.user);
      }
    });
  }

  /**
   * Saves the current users info.
   */
  saveUserInfo() {
    this.userService.update(this.user.id, this.user.address, this.user.residence).subscribe(response => {
      location.reload();
      // Show success message
    });
  }

}
