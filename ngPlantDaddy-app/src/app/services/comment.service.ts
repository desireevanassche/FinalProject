import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CommentService {


  private url = environment.baseUrl + "api/users/posts";


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

  public indexComments(postId:number){
    return this.http.get<Comment[]>(this.url + "/comments/" + postId ,this.getHttpOptions() )
    .pipe(
      catchError((err:any) => {
        return throwError("index posts has an error- KABOOM!")
      })
    );
    }

  public createComment(postId:number, commentId:number, comment: Comment){
    return this.http.post<Comment>(this.url + "/" + postId + "/comments/" + commentId, comment, this.getHttpOptions())
    .pipe(
      catchError((err:any) => {
      return throwError("index posts has an error- KABOOM!")
    })

    )  ;
  }


}
