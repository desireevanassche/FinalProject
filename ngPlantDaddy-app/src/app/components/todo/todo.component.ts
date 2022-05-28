import { TodoService } from './../../services/todo.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Todo } from 'src/app/models/todo';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  currentUserId: number | null = 0;

  selectedTodo : Todo | null = null;

  todos :Todo []= [];

  newTodo : Todo = new Todo();

  constructor(
    private todoServ: TodoService,
    private route: ActivatedRoute,
    private authServ: AuthService
  ) { }

  ngOnInit(): void {

    this.currentUserId = parseInt('' + this.authServ.getCurrentUserId());
    console.log(this.currentUserId);
    if (!this.selectedTodo && this.route.snapshot.paramMap.get('id')) {
      let id = this.route.snapshot.paramMap.get('id');
      // if (id) {
      //  // this.show(parseInt(id));
      // }
    } else {
    }
    this.reloadTodo();
  }
  reloadTodo() {
    this.todoServ.index().subscribe({
      next: (data) => {
        this.todos = data;
        console.log(data);



      },
      error: (err) => {
        console.error(err);
      },
    });
  }


  addTodo(todo : Todo){
    this.todoServ.create(todo).subscribe(
    success=>{
      this.reloadTodo();
      this.newTodo = new Todo();
    },
    error => console.log("Adding Oberservable got an error")
    );
    }




}
