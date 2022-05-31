import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Blog } from '../models/blog';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class BlogService {
  private url = environment.baseUrl + "api/blogs";
  // private url2 = environment.baseUrl + "api/blogs/search";

  blogs : Blog[] = [];

  constructor(private http : HttpClient, private datePipe : DatePipe, private auth : AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

//--------------   CREATE BLOG ----------------


public createBlog(newBlog : Blog){
  return this.http.post<Blog>(this.url, newBlog,this.getHttpOptions())
  .pipe(
    catchError((err:any)=>{
      console.log(err);
      return throwError("Create blog has an error- kaboom")

    })
  )
  }



  //--------------   UPDATE BLOG ----------------


  public updateBlog(updateBlog : Blog, id:number){
    return this.http.put<Blog> (this.url + "/" + id, updateBlog,this.getHttpOptions())
    .pipe(
      catchError((err : any)=>{

        console.log(err);
        return throwError("Error updating Blog- KABOOM");
      })
    );
  }



  //--------------   DELETE BLOG ----------------

  public deleteBlog(deleteBlog : Blog){
    return this.http.put<Blog> (this.url, deleteBlog, this.getHttpOptions())
    .pipe(
      catchError((err:any)=>{
        console.log(err);
        return throwError("Error in disabling Blog- KABOOM");

      })
    );
  }

  /// --------------------- SHOW BLOG ------------------

  public show(id:number){
    return this.http.get<Blog>(this.url + "/" + id,this.getHttpOptions())
    .pipe(
      catchError((err:any)=>{
        return throwError('Check this- KABOOM!')
      })
    )
  }

  // SHOW ALL BLOG POSTS WITHOUT LOGIN --------------------
  public indexAllBlogs(){
    return this.http.get<Blog[]>(this.url)
    .pipe(
      catchError((err:any) => {
        return throwError("index Blog has an error- KABOOM!")
      })
    );
    }

 // SHOW ALL BLOG POST WITH LOGIN REQUIRED -----------------

 public indexBlog(){
  return this.http.get<Blog[]>(this.url,this.getHttpOptions() )
  .pipe(
    catchError((err:any) => {
      return throwError("index Blog has an error- KABOOM!")
    })
  );
  }


}



