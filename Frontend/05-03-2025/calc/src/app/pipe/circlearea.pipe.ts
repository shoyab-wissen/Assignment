import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'circlearea',
})
export class CircleareaPipe implements PipeTransform {
  transform(radius: number, ...args: unknown[]): number {
    return Math.PI * Math.pow(radius, 2) || 0;
  }
}
