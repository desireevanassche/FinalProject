import { Pipe, PipeTransform } from '@angular/core';
import { Plant } from '../models/plant';

@Pipe({
  name: 'searchPlants'
})
export class SearchPlantsPipe implements PipeTransform {
  transform(plants: Plant[], searchValue: string):  Plant[] {

    if(!plants || !searchValue) {
      return plants;
    }
    return plants.filter(plant =>
    plant.commonName.toLowerCase().includes(searchValue.toLowerCase())
    ||plant.description?.toLowerCase().includes(searchValue.toLowerCase())
    ||plant.botanicalName?.toLowerCase().includes(searchValue.toLowerCase())
    ||plant.careDifficulty?.toLowerCase().includes(searchValue.toLowerCase())
    ||plant.lightRequirement?.toLowerCase().includes(searchValue.toLowerCase())




    );

    }
}


