import { Topic } from './../models/topic';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  private baseUrl = environment.baseUrl + 'api/topics';

  constructor(private http: HttpClient, private auth: AuthService) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  // ----------------  SHOW ALL TOPICS WITH LOGIN NEEDED ---------------
  public indexTopics() {
    return this.http.get<Topic[]>(this.baseUrl).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => {
          new Error('index posts has an error- KABOOM!');
        });
      })
    );
  }
}
