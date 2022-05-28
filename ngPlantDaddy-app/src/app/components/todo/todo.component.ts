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


  newTodo : Todo = new Todo();

  editTodo : Todo | null = null;

  selected: Todo | null = null;

  display : boolean = false;

  todos: Todo[] = [];

  showIncomplete : Todo | null = null;

  showComplete : boolean = false;

  currentUserId: number | null = 0;

  constructor(private todoService: TodoService, private router : Router, private currentRoute : ActivatedRoute, private authServ: AuthService) { }

  ngOnInit(): void {
    this.currentUserId = parseInt('' + this.authServ.getCurrentUserId());
    console.log(this.currentUserId);

    if (!this.selected && this.currentRoute.snapshot.paramMap.get('id')) {
      let id = this.currentRoute.snapshot.paramMap.get("id");
      if(id){
        this.showTodo(parseInt(id))
      }

    } else if(!this.selected && this.currentRoute.snapshot.paramMap.get('')) {

      this.router.navigateByUrl("**")

    }
    this.reloadTodo();
  }

  showTodo(id:number){
    this.todoService.show(id).subscribe(
      success => {
        this.selected = success;
        if(!this.selected){
          this.router.navigateByUrl('**')
        }
        },
        error => console.log("Obersable got an error" + error)
      );


  }
  reloadTodo(){
    this.todoService.index().subscribe(
      success => {
        this.todos= success;
      console.log(this.todos);
      },
      error => console.log("Obersable got an error" + error)
    );
  }

  displayTodo = (todo : Todo)=>{
    this.selected = todo;
    }

    displayTable =()=>{
      this.selected = null;
    }

  addTodo(todo : Todo, userPlantId : number){
  this.todoService.create(todo,userPlantId).subscribe(
  success=>{
    this.newTodo = new Todo();
    this.reloadTodo();
  },
  error => console.log("Adding Oberservable got an error")
  );
  }





  setEditTodo(){

    this.editTodo = Object.assign({}, this.selected);
  }










  // currentUserId: number | null = 0;

  // selectedTodo : Todo | null = null;

  // todos :Todo []= [];

  // newTodo : Todo = new Todo();

  // constructor(
  //   private todoServ: TodoService,
  //   private route: ActivatedRoute,
  //   private authServ: AuthService
  //   // private userPlant : Userplant
  // ) { }

  // ngOnInit(): void {

  //   this.currentUserId = parseInt('' + this.authServ.getCurrentUserId());


  //   console.log(this.currentUserId);
  //   if (!this.selectedTodo && this.route.snapshot.paramMap.get('id')) {
  //     let id = this.route.snapshot.paramMap.get('id');
  //     // if (id) {
  //     //  // this.show(parseInt(id));
  //     // }
  //   } else {
  //   }
  //   this.reloadTodo();
  // }
  // reloadTodo() {
  //   this.todoServ.index().subscribe({
  //     next: (data) => {
  //       this.todos = data;
  //       console.log(data);



  //     },
  //     error: (err) => {
  //       console.error(err);
  //     },
  //   });
  // }


  // addTodo(todo : Todo){
  //   this.todoServ.create(todo).subscribe(
  //   success=>{
  //     this.reloadTodo();
  //     this.newTodo = new Todo();
  //   },
  //   error => console.log("Adding Oberservable got an error")
  //   );
  //   }






}
