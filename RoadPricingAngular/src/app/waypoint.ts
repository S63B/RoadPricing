import { LatLng } from "angular2-google-maps/core";
export class Waypoint {
    constructor(
        public location: LatLng,
        public stopover: boolean) {
    }
}
