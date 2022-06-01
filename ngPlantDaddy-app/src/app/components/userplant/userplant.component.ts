import { GrowthDataService } from './../../services/growth-data.service';
import { Growthdata } from './../../models/growthdata';
import { DatePipe } from '@angular/common';
import { TodoService } from './../../services/todo.service';
import { PlantService } from './../../services/plant.service';

import { UserplantService } from './../../services/userplant.service';
import { Component, OnInit } from '@angular/core';
import { Userplant } from 'src/app/models/userplant';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Plant } from 'src/app/models/plant';
import { Todo } from 'src/app/models/todo';
import { single } from 'rxjs';

@Component({
  selector: 'app-userplant',
  templateUrl: './userplant.component.html',
  styleUrls: ['./userplant.component.css'],
})
export class UserplantComponent implements OnInit {
  title: string = 'My Garden Ɛ>';

//--------------USERPLANT---------------------

  selected: Userplant | null = null;

  newUserPlant: Userplant = new Userplant();

  editUserPlant: Userplant | null = null;

  userPlants: Userplant[] = [];

  displayAddUserPlantForm: Boolean = false;

  editForm: Boolean = false;

  currentUserId: number | null = 0;


  plants: Plant[] = [];

  plant: Plant | null = null;


  selectedGrowth: Growthdata | null = null;

  growthData: Growthdata[]= [];

  singleGrowthData: Growthdata = new Growthdata();

  showGrowth: boolean = false;

  leftSide : boolean = false;




//------------------TODO----------------------

  todo: Todo = new Todo();

  todos : Todo [] = [];

  editTodo : Todo | null = null;

  selectedTodo : Todo | null = null;

  newTodo : Todo = new Todo ();

  displayTodos : boolean = false;

  display: boolean = false;

  displaySubmit : boolean = true;

  plantId : number = 0;

  todoCount: Todo[] = [];

  editTodoForm: Boolean = false;

  displayAddTodoForm: Boolean = false;

  displaySelectedTodo: Boolean = false;

  //--------------WATER SCHEDULE-----------------

  newWater: Todo = new Todo();

  displayAddWater: Boolean = false;

  selectedWater : Todo | null;


  constructor(
    private userPlantSvc: UserplantService,
    private route: ActivatedRoute,
    private router: Router,
    private authServ: AuthService,
    private plantSvc: PlantService,
    private todoService: TodoService,
    private growthServ : GrowthDataService


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
    console.log(this.selected.growthData);

  }
  setEditUserPlant() {
    this.editUserPlant = Object.assign({}, this.selected);
  }
  displayUserPlant(plant: Userplant) {
    this.selected = plant;
    console.log(this.selected);

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


  reloadGrowthData(userPlantId: number){
  console.log(userPlantId);


     this.growthServ.indexGrowthData(userPlantId).subscribe({



      next:(growthDataArray)=>{
        console.log(userPlantId);

        this.growthData = growthDataArray;
        console.log(growthDataArray);
        console.log(this.growthData);

      },
      error:(growErr)=>{
        console.log(growErr);

      }


     })

   }











  reloadTodos(){
    this.todoService.index().subscribe({
      next: (data) => {

        this.todos = data;
      },
      error: (err) => {
        console.log(err + " error inside reload todos index in userplant comp.ts");

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

  left: boolean = true;
  determineLeftOrRight(){
  if(this.left){
    this.left = false;
    return "timeline-2 left-2";
  }
  else{
   this.left= true;
   return "timeline-2 right-2";
  }
  }




  // displayLeftSideTimeLine(selected: Userplant){
  //   console.log(selected);

  //   if(this.selected.id % 2 === 0){

  //     console.log("object from left side"+this.selected.id);
  //   this.leftSide = true;
  //     return this.leftSide;
  //   }



  // }
  // displayRightSideTimeLine(selected: Userplant){
  //   if(selected.id % 2  > 0){
  //     console.log("object from left side"+this.selected);

  //     return this.selected;
  //   }


  // }






  displayGrowthData(userPlant: Userplant){
   this.selected = userPlant;


  }



  displayTodo = (todo: Todo) => {
    this.selectedTodo = todo;
    console.log(todo);

  }



  // addTodo(todo : Todo, userPlantId : number){
  //   this.todoService.create(todo).subscribe(
  //   success=>{
  //     this.newTodo = new Todo();
  //   },
  //   error => console.log("Adding Oberservable got an error")
  //   );
  //   }

  addTodo(todo : Todo, userPlantId : number){
    this.todoService.create(todo, userPlantId).subscribe(
    (data)=>{
      this.newTodo = new Todo();
      this.displayPlantsTodos(userPlantId);
    },
    error => console.log("Adding Oberservable got an error")
    );
    }

//addUserPlant(newUserPlant: Userplant) {
//   this.userPlantSvc.create(newUserPlant).subscribe(
//     (data) => {
//       this.newUserPlant = new Userplant();
//       this.reload();
//     },
//     (err) => console.error(err)
//   );
// }




  updateTodo( id : number, updatedTodo : Todo){
    this.todoService.update(id, updatedTodo).subscribe({
      next:(data)=>{
        this.updateTodo = null;

        if (this.selectedTodo) {
          this.selectedTodo = Object.assign({}, updatedTodo);
        }
        this.displayPlantsTodos(id);
      },
      error: (err)=>{
        console.log(err + " this is an error inside update todo in todo comp.ts");

      }
    })
  }

  deleteTodo(id:number) {
    this.todoService.deleteTodo(id).subscribe({
      next:(data) =>{
      this.displayPlantsTodos(id);
      },
      error:(err)=>{
        console.log(err + " This error is inside the delete todo comp.ts");

      }
    })
  }



  setEditTodo = () =>{
    this.editTodo = Object.assign({}, this.selectedTodo);
  }

  // displayTodo = (todo: Todo) => {
  //   this.selectedTodo = todo;
  //   console.log(todo);

  // }


  // setEditUserPlant() {
  //   this.editUserPlant = Object.assign({}, this.selected);
  // }

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


  checkWarningForIndividualTodo() {
    let today = new Date();


    let due = this.selectedTodo.dueDate;
    let dueDate = new Date(due);


    if(today > dueDate){
      return 'badge bg-danger';
    } else {
      return 'badge bg-success';
    }

  }

  checkWarning(todo: Todo) {
    let today = new Date();

    let due = todo.dueDate;
    let dueDate = new Date(due);

    if(today > dueDate){
      return 'badge bg-danger';
    } else {
      return 'badge bg-success';
    }


  }



  weekly(){
  let today = new Date();
  let weekly = new Date(today);
  weekly.setDate(weekly.getDate() + 7);
  return weekly.toISOString();

  }

  biWeekly(){
    let today = new Date();
    let twoWeeks = new Date(today);
    twoWeeks.setDate(twoWeeks.getDate() + 14);
    return twoWeeks.toISOString();
}

  monthly() {
    let today = new Date();
    let monthly = new Date(today);
    monthly.setDate(monthly.getDate() + 30);
    return monthly.toISOString();
  }




  addWater(todo : Todo, userPlantId : number){
    this.todoService.addWater(todo, userPlantId).subscribe(
    (data)=>{
      this.newWater = new Todo();
    },
    error => console.log("Adding Oberservable got an error")
    );
    }


    displayWater = (todo: Todo) => {
      this.selectedWater = todo;
    }



















}
