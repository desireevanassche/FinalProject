import { Growthdata } from './../models/growthdata';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Userplant } from '../models/userplant';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GrowthDataService {

  private url = environment.baseUrl + 'api/userPlants';
  private url2 = this.url + '/search';

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }


  public indexGrowthData(userPlantId : number) {
    return this.http.get<Growthdata[]>(this.url + "/" + userPlantId + "/growth", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);

        return throwError(() => {
          new Error('index all growth details based on user plant id has an error- KABOOM!');
        });
      })

    );
  }

//   public create(newUserPlant: Userplant) {
//     return this.http.post<Userplant>(this.url, newUserPlant, this.getHttpOptions()).pipe(
//       catchError((err: any) => {
//         console.log(err);
//         return throwError('UserPlantService: error creating Plant');
//       })
//     );
//   }
//   public update(updateUserPlant: Userplant, id : number) {
//     const httpOptions = {
//       headers: {
//         'Content-type': 'application/json',
//         Authorization: 'Basic ' + this.auth.getCredentials(),
//       },
//     };
//     return this.http
//       .put<Userplant>(this.url + "/" + id, updateUserPlant, this.getHttpOptions())
//       .pipe(
//         catchError((err: any) => {
//           console.log(err);
//           return throwError('PlantService: error creating Plant');
//         })
//       );
//   }
// public deactivate(deactivate: Userplant, id : number) {
//   return this.http
//       .put<Userplant>(this.url + "/deactivate/" + id, deactivate, this.getHttpOptions())
//       .pipe(
//         catchError((err: any) => {
//           console.log(err);
//           return throwError('PlantService: error creating Plant');
//         })
//       );
// }

show(userPlantId: number, growthId : number) {
  return this.http.get<Growthdata>(this.url + "/" + userPlantId + "/growth/" + growthId, this.getHttpOptions()).pipe(

    catchError((err: any) => {
      console.log(err);
      return throwError('Growth Serv: error retrieving a single growth detail ');
    })
  );
}

}
