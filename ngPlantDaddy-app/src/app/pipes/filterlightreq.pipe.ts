import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterLightReq'
})
export class FilterLightReqPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
