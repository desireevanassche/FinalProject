import { Pipe, PipeTransform } from '@angular/core';
import { Plant } from '../models/plant';

@Pipe({
  name: 'watertypefilter'
})
export class WatertypefilterPipe implements PipeTransform {
  transform(plants: Plant[], filterWaterType: string):  Plant[] {

    if(!plants || !filterWaterType) {
      return plants;
    }
    return plants.filter(plant =>
    plant.waterType?.toLocaleLowerCase().includes(filterWaterType.toLowerCase())





    );


}
}
