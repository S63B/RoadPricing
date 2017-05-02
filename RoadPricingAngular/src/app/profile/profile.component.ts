import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  @Input() userID: number;
  private user: object = {
    name: "John Doe",
    address: "Rachelsmolen 1",
    residence: "Eindhoven",
    // cars: []
    cars: [{ id: 1, name: "Tesla" }]
  }

  constructor() { }

  ngOnInit() {
  }

  saveUserInfo() {
    console.log(this.user['name'], this.user['address'], this.user['residence']);
  }

}
