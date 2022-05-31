import { Userplant } from 'src/app/models/userplant';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Todo } from '../models/todo';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  constructor(private http: HttpClient, private auth: AuthService) {}

  private url = environment.baseUrl + 'api/todos';
  private url2 = environment.baseUrl + 'api/todos/userplants';



  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  public index() {
    return this.http.get<Todo[]>(this.url, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          return throwError('Check this- KABOOM!');
        })
      );
  }

  // public create(todo: Todo) {
  //   return this.http.post<Todo>(this.url,todo, this.getHttpOptions())
  //     .pipe(
  //       catchError((err: any) => {
  //         return throwError('Check this- KABOOM!');
  //       })
  //     );
  // }
  public create(todo: Todo, userPlantId : number) {
    return this.http.post<Todo>(this.url2 + "/" + userPlantId,todo, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          return throwError('Check this- KABOOM!');
        })
      );
  }

  public update(todoId : number, todo:Todo){
    return this.http.put<Todo>(this.url + "/" + todoId ,todo, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        return throwError('Check this- KABOOM!');
      })
    );
  }


  public deleteTodo(todoId : number){
    return this.http.delete<Todo>(this.url + "/" + todoId, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        return throwError('Check this- KABOOM!');
      })
    );
  }


  public show(todoId: number) {
    return this.http.get<Todo>(this.url + '/' + todoId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError('Check this- KABOOM!');
      })
    );
  }

  public getAllUserPlantTodos(userPlantId : number){
    return this.http.get<Todo[]>(this.url2 + "/" + userPlantId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError('Check this- KABOOM!');
      })

    );
  }
}
