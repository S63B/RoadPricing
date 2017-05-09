import { GoogleMapsAPIWrapper } from 'angular2-google-maps/core/services/google-maps-api-wrapper';
import { Directive, Input } from '@angular/core';
import { LatLng } from "angular2-google-maps/core";
import { Waypoint } from "app/waypoint";

declare var google: any;

@Directive({
  selector: 'sebm-google-map-directions'
})
export class DirectionsMapDirective {

  @Input() pols;


  constructor(private gmapsApi: GoogleMapsAPIWrapper) { }

  ngOnInit() {
    this.gmapsApi.getNativeMap().then(map => {
      let directionsService = new google.maps.DirectionsService;
      let directionsDisplay = new google.maps.DirectionsRenderer;
      let waypoints: Waypoint[] = [];

      for (var i = 1; i < this.pols.length - 1; i++) {
        waypoints.push(new Waypoint(new google.maps.LatLng(this.pols[i].lat, this.pols[i].lng), true));
      }

      directionsDisplay.setMap(map);
      directionsService.route({
        origin: { lat: this.pols[0].lat, lng: this.pols[0].lng },
        destination: { lat: this.pols.slice(-1)[0].lat, lng: this.pols.slice(-1)[0].lng },
        waypoints: waypoints,
        optimizeWaypoints: true,
        travelMode: 'DRIVING'
      }, function (response, status) {
        if (status === 'OK') {
          directionsDisplay.setDirections(response);
        } else {
          window.alert('Directions request failed due to ' + status);
        }
      });

    });
  }
}