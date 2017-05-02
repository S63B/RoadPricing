import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'distance'
})
export class DistancePipe implements PipeTransform {

  transform(value: number): string {
    let numb = value / 1000;
    return numb.toFixed(2) + ' km';
  }

}
