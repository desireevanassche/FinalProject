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

  display: boolean = false;

newTodo : Todo = new Todo ();

todos : Todo[] = [];
editTodo : Todo | null = null;
selected : Todo | null = null;
currentUserId: number | null = 0;

  constructor(
    private todoService: TodoService,
     private router : Router,
      private currentRoute : ActivatedRoute,
       private authServ: AuthService) { }

  ngOnInit(): void {

    this.currentUserId = parseInt('' + this.authServ.getCurrentUserId());
    console.log(this.currentUserId);

    if (!this.selected && this.currentRoute.snapshot.paramMap.get('id')) {
      let id = this.currentRoute.snapshot.paramMap.get("id");
      if(id){
        // this.showTodo(parseInt(id))
      }
    } else if(!this.selected && this.currentRoute.snapshot.paramMap.get('')) {
      this.router.navigateByUrl("**")
    }


    this.displayTodo;
    this.reloadTodos();

  }

  reloadTodos(){
    this.todoService.index().subscribe({
      next: (data) => {
        this.todos = data;
      },
      error: (err) => {
        console.log(err + " error inside reload todos index in todo comp.ts");

      }
    })
  }

  displayTodo = (todo: Todo) => {
    this.selected = todo;
    console.log(todo);

  }


  // addTodo(todo : Todo){
  //   this.todoService.create(todo).subscribe(
  //   success=>{
  //     this.newTodo = new Todo();
  //     this.reloadTodos();
  //   },
  //   error => console.log("Adding Oberservable got an error")
  //   );
  //   }

  addTodo(todo : Todo, userPlantId : number){
    this.todoService.create(todo,userPlantId).subscribe(
    success=>{
      this.newTodo = new Todo();
      this.reloadTodos();
    },
    error => console.log("Adding Oberservable got an error")
    );
    }




  updateTodo( id : number, updatedTodo : Todo){
    this.todoService.update(id, updatedTodo).subscribe({
      next:(data)=>{
        this.reloadTodos();
        this.newTodo = updatedTodo;
      },
      error: (err)=>{
        console.log(err + " this is an error inside update todo in todo comp.ts");

      }
    })
  }

  deleteTodo(id:number) {
    this.todoService.deleteTodo(id).subscribe({
      next:(data) =>{
      this.reloadTodos();
      },
      error:(err)=>{
        console.log(err + " This error is inside the delete todo comp.ts");

      }
    })
  }




  setEditTodo = (todo : Todo) =>{
this.editTodo = Object.assign({}, todo)
  }

}
