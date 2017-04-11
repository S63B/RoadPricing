import { Pol } from './pol';

export class Ride {
    constructor(
        public pols: Pol[],
        public distance: number,
        public startTime: number,
        public endTime: number) {
    }
    
    getDistanceString(): string {
        return this.distance / 1000 + " km";
    }
}
