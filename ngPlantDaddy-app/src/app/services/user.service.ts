import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = environment.baseUrl + "api/users";

  constructor(private http : HttpClient, private datePipe : DatePipe,private auth : AuthService) { }


  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  public userInfo() : Observable<User> {
    return this.http.get<User>(this.url,this.getHttpOptions() )
    .pipe(
      catchError((err:any) => {
        return throwError("Error Retireieving Used Data- KABOOM!")
      })
    );
    }


  update(user: User): Observable<User> {

    console.log(user);
    return this.http.put<User>(this.url, user, this.getHttpOptions()).pipe(

      catchError( (err: any) => {
        console.error('User update error');
        console.error(err);
        return throwError(
          () => new Error(
            'MEOW error updating user' + err )
        );
      })
    );
  }

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.url).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
            new Error('Error retrieving list of users ' + err)
          );
        })
      );
  }

  show(userId: number): Observable<User> {
    return this.http.get<User>(`${this.url}/${userId}`).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error(
            'error user: ' + err
            )
          );
        })
      );
  }






}
