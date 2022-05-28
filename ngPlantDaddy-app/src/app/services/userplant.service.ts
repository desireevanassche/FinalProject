import { Userplant } from './../models/userplant';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserplantService {

  private baseUrl = environment.baseUrl + 'api/userPlants';
  private url2 = this.baseUrl + 'api/search';

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

  public indexTopics() {
    return this.http.get<Userplant[]>(this.baseUrl).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => {
          new Error('index posts has an error- KABOOM!');
        });
      })
    );
  }



}
