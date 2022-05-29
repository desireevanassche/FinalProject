import { Pipe, PipeTransform } from '@angular/core';
import { filter } from 'rxjs';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(value: any, filterString: string, searchValue: string): any[] {
    const resultArray = [];

  if(value.length === 0 || filterString === '' || searchValue === '') {
    return value;
  }

  for(const item of value) {
    if(item[searchValue]=== filterString){
      resultArray.push(item);
    }
  }

  return resultArray;
  }

}
