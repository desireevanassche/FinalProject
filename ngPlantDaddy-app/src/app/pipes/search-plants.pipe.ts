import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchPlants'
})
export class SearchPlantsPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
