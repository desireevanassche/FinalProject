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
        return throwError("User has an error- KABOOM!")
      })
    );
    }

    public updateUser(updateUser : User){
      return this.http.put (this.url, updateUser,this.getHttpOptions())
      .pipe(
        catchError((err : any)=>{

          console.log(err);
          return throwError("Error updating user- KABOOM");
        })
      );
    }


}
