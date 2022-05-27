import { catchError, throwError } from 'rxjs';
import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Post } from '../models/post';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private url = environment.baseUrl + "api/users/posts";
  private url2 = environment.baseUrl + "api/posts";
  private url3 = environment.baseUrl + "api/users/posts/disable";

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

   //--------------  SHOW ALL POST  WITH LOGIN NOT NEEDED----------------

   public indexAllPosts(){
    return this.http.get<Post[]>(this.url2)
    .pipe(
      catchError((err:any) => {
        return throwError("index posts has an error- KABOOM!")
      })
    );
    }


// ----------------  SHOW ALL POST WITH LOGIN NEEDED ---------------
  public indexPosts(){
  return this.http.get<Post[]>(this.url,this.getHttpOptions() )
  .pipe(
    catchError((err:any) => {
      return throwError("index posts has an error- KABOOM!")
    })
  );
  }


  //--------------   CREATE POST ----------------


  public createPost(newPost : Post){
  return this.http.post<Post>(this.url, newPost,this.getHttpOptions())
  .pipe(
    catchError((err:any)=>{
      console.log(err);
      return throwError("Create post has an error- kaboom")

    })
  )
  }



  //--------------   UPDATE POST ----------------


  public updatePost(updatePost : Post, id:number){
    return this.http.put<Post> (this.url + "/" + id, updatePost,this.getHttpOptions())
    .pipe(
      catchError((err : any)=>{

        console.log(err);
        return throwError("Error updating post- KABOOM");
      })
    );
  }



  //--------------   DISABLE POST ----------------

  public disablePost(id : number, disablePost : Post ){
    return this.http.put<Post> (this.url3 + "/" + id, disablePost, this.getHttpOptions())
    .pipe(
      catchError((err:any)=>{
        console.log(err);
        return throwError("Error in disabling post- KABOOM");

      })
    );
  }

  /// --------------------- SHOW POST ------------------

  public show(id:number){
    return this.http.get<Post>(this.url + "/" + id,this.getHttpOptions())
    .pipe(
      catchError((err:any)=>{
        return throwError('Check this- KABOOM!')
      })
    )
  }


}
