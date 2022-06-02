import { Pipe, PipeTransform } from "@angular/core";
import { orderBy } from 'lodash';

@Pipe({
 name: "orderBy"
})
export class OrderByPipe implements PipeTransform {
//  transform(array: any, sortBy: string, order?: string): any[] {
//  const sortOrder = order ? order : 'asc'; // setting default ascending order

//   return orderBy(array, [sortBy], [sortOrder]);
//   }
// }

transform(array: any, field: string): any[] {
//   console.log(array[0]);
// let testDate = new Date(array[0].createdAt);
// console.log(testDate);



  array.sort((a: any, b: any) => {
    if (a[field] > b[field]) {
      return -1;
    } else if (a[field] < b[field]) {
      return 1;
    } else {
      return 0;
    }
  });
  return array;
}


}
