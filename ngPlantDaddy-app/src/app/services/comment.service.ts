import { User } from 'src/app/models/user';
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
  private url2 = environment.baseUrl + "comment"
  private url3 = environment.baseUrl + "api/posts"


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

  public createCommentOnPost(postId:number, comment: Comment){


    return this.http.post<Comment>(this.url3 + "/" + postId + "/comment", comment, this.getHttpOptions())
    .pipe(
      catchError((err:any) => {
        console.log(err);

      return throwError("commenting on a post has an error- KABOOM!")
    })

    )  ;
  }

  public createCommentOnComment(postId:number, commentId:number, comment : Comment){
    console.log(commentId);
    console.log(postId);

    return this.http.post<Comment>(this.url + "/" + postId + "/comments/" + commentId, comment, this.getHttpOptions())
    .pipe(
      catchError((err:any) => {
      return throwError("index posts has an error- KABOOM!")
    })

    )  ;

  }






  public getUserFromComment(commentId : number, userId:number){
    return this.http.get<User>(this.url2 + "/" + commentId + "/user/" + userId, this.getHttpOptions())
    .pipe(
      catchError((err:any) => {
      return throwError("index posts has an error- KABOOM!")
    })

    )  ;
  }



}
