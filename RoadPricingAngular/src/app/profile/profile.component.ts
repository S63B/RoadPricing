import { Component, OnInit, Input } from '@angular/core';
import { OwnerService } from "app/user.service";
import { ActivatedRoute } from "@angular/router";
import { User } from "app/user";
import { CarService } from "app/car.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  private userId: number;
  private user: User = new User();
  private ownedCars: object[] = [];

  constructor(private userService: OwnerService,
              private route: ActivatedRoute) {
    this.route.params.subscribe(parameters => {
      this.userId = parameters['userId'];
    });
   }

  ngOnInit() {
    this.getUserInfo(this.userId);
    this.getUserCars(this.userId);
  }

  /**
   * Gets all user info from the current user.
   *
   * @param {number} userID The id of the user of which all info should be retrieved.
   */
  getUserInfo(userID: number) {
    this.userService.getUserInfo(userID).subscribe(foundUser => {
      if (typeof foundUser === "object") {
        this.user = foundUser as User;
      }
    });
  }

  /**
   * Gets all cars from the current user.
   * @param userID The id of the user of which all cars should be retrieved.
   */
  getUserCars(userId: number) {
    this.userService.getCarsByOwnerId(userId).subscribe(result => {
      this.ownedCars = result.json();
      console.log(this.ownedCars);
    });
  }

  /**
   * Saves the current users info.
   */
  saveUserInfo() {
    this.userService.update(this.user.id, this.user.address, this.user.residence).subscribe(updateSucceeded => {
      if (updateSucceeded) {
        this.getUserInfo(this.userId);
      }
    });
  }

}
