import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'circlediameter',
})
export class CirclediameterPipe implements PipeTransform {
  transform(radius: number, ...args: unknown[]): number {
    return 2 * radius;
  }
}
