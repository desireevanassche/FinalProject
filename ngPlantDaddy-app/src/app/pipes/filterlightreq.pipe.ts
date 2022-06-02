import { Pipe, PipeTransform } from '@angular/core';
import { Plant } from '../models/plant';

@Pipe({
  name: 'filterLightReq'
})
export class FilterLightReqPipe implements PipeTransform {

  transform(plants: Plant[], filterLight: string):  Plant[] {

    if(!plants || !filterLight) {
      return plants;
    }
    return plants.filter(plant =>
    plant.lightReq?.toLowerCase().includes(filterLight.toLowerCase())

    );

    }
}
