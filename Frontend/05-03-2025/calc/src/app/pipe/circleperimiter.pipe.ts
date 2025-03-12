import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'circleperimiter',
})
export class CircleperimiterPipe implements PipeTransform {
  transform(number: unknown, ...args: unknown[]): number {
    return Math.PI * 2 * Number(number);
  }
}
