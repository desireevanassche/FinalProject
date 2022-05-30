import { TodoService } from './../../services/todo.service';
import { PlantService } from './../../services/plant.service';

import { UserplantService } from './../../services/userplant.service';
import { Component, OnInit } from '@angular/core';
import { Userplant } from 'src/app/models/userplant';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Plant } from 'src/app/models/plant';
import { Todo } from 'src/app/models/todo';

@Component({
  selector: 'app-userplant',
  templateUrl: './userplant.component.html',
  styleUrls: ['./userplant.component.css'],
})
export class UserplantComponent implements OnInit {
  title: string = 'My Garden Ɛ>';

  selected: Userplant | null = null;

  newUserPlant: Userplant = new Userplant();

  editUserPlant: Userplant | null = null;

  userPlants: Userplant[] = [];

  displayAddUserPlantForm: Boolean = false;

  editForm: Boolean = false;

  currentUserId: number | null = 0;

  plants: Plant[] = [];

  plant: Plant | null = null;

  todo: Todo = new Todo();

  todos : Todo [] = [];

  editTodo : Todo | null = null;

  selectedTodo : Todo | null = null;

  display: boolean = false;

  newTodo : Todo = new Todo ();

  displayTodos : boolean = false;

  displaySubmit : boolean = true;
  plantId : number = 0;

  constructor(
    private userPlantSvc: UserplantService,
    private route: ActivatedRoute,
    private router: Router,
    private authServ: AuthService,
    private plantSvc: PlantService,
    private todoService: TodoService,



  ) {}

  ngOnInit(): void {
    this.currentUserId = parseInt('' + this.authServ.getCurrentUserId());
    console.log(this.currentUserId);

    if (!this.selected && this.route.snapshot.paramMap.get('id')) {
      let id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.show(parseInt(id));
        this.reload();
      }
    } else {
    }
    this.reload();
    // this.displayTodo;
    // this.reloadTodos();
  }
  reload() {
    this.userPlantSvc.indexUserPlants().subscribe({
      next: (data) => {
        this.userPlants = data;
        this.displayAllUserPlants();

        this.plantSvc.index().subscribe({
          next: (plantData) => {

            this.plants = plantData;
          },
          error: (fail) => {
            console.log(fail);
          },
        });
      },
      error: (err) => {
        console.error(err);
      },
    });
  }




  show(id: number) {
    this.userPlantSvc.show(id).subscribe(
      (data) => {
        this.selected = data;
        if (!this.selected) {
          this.router.navigateByUrl('/notFound');
        }
      },
      (err) => {
        console.log(err);
        if (!this.selected) {
          this.router.navigateByUrl('/notFound');
        }
      }
    );
  }

  addUserPlant(newUserPlant: Userplant) {
    this.userPlantSvc.create(newUserPlant).subscribe(
      (data) => {
        this.newUserPlant = new Userplant();
        this.reload();
      },
      (err) => console.error(err)
    );
  }

  // getCurrentUserPlantId (selected : Userplant) {
  //   if(this.selected){
  //     this.todo.userPlants.id =  selected.id
  //     console.log("selected id is : " + selected.id);
  //   console.log("Inside getcurrentuserplantid, id is:" + this.todo.userPlants.id);
  //   }
  //  }

  updateUserPlant(userPlant: Userplant, id: number) {
    this.userPlantSvc.update(userPlant, id).subscribe(
      (data) => {
        this.reload();
        this.editUserPlant = null;
        if (this.selected) {
          this.selected = Object.assign({}, userPlant);
        }
      },
      (err) => console.error(err)
    );
  }
  deactivateUserPlant(userPlant: Userplant, id: number) {
    this.userPlantSvc.deactivate(userPlant, id).subscribe(
      (data) => {
        this.reload();
        this.editUserPlant = null;
        if (this.selected) {
          this.selected = Object.assign({}, userPlant);
        }
      },
      (err) => console.error(err)
    );
  }
  setPlant(userplant: Userplant) {
    this.selected = userplant;
  }
  setEditUserPlant() {
    this.editUserPlant = Object.assign({}, this.selected);
  }
  displayUserPlant(plant: Userplant) {
    this.selected = plant;
  }
  displayTable() {
    this.selected = null;
  }
  displayAllUserPlants() {
    this.userPlantSvc.indexUserPlants().subscribe(
      (data) => {

        this.userPlants = data;

      },
      (err) => {
        console.log(err);
      }
    );
  }

  // GO HERE TOMORROW AND CHECK THIS AGAIN
  selectedReset(id:number){
    this.selected = null;
    console.log("before rest to 0" +id);

    id = this.plantId;
    console.log("After reset to 0" + id );

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


  reloadSpecificTodoList(id : number){
    this.todoService.getAllUserPlantTodos(id).subscribe({
      next: (data) => {

        this.todos = data;

      },
      error: (err) => {
        console.log(err + " error inside reload todos index in todo comp.ts");

      }
    })
  }




  displayTodo = (todo: Todo) => {
    this.selectedTodo = todo;
    console.log(todo);

  }



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




  displayPlantsTodos(id : number) {
    this.todoService.getAllUserPlantTodos(id).subscribe({
      next:(data)=>{
        this.reloadSpecificTodoList(id);
        this.todos = data;
        console.log(id);
      },
      error :(err)=>{
        console.log(err + "this error is inside the get all user todos id method in user comp.ts");

      }
    })
  }















}
