import { Pipe, PipeTransform } from '@angular/core';
import { Plant } from '../models/plant';

@Pipe({
  name: 'watercyclefilter'
})
export class WatercyclefilterPipe implements PipeTransform {
  transform(plants: Plant[], filterWaterCycle: string):  Plant[] {

    if(!plants || !filterWaterCycle) {
      return plants;
    }
    return plants.filter(plant =>
     plant.waterCycle?.toLocaleLowerCase().includes(filterWaterCycle.toLowerCase())





    );

    }
}
