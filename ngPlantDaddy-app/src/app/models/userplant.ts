import { Plant } from "./plant";
import { Todo } from "./todo";
import { User } from "./user";

export class Userplant {


  id : number;
  nickname: string | null;
  description: string | null;
  height: number | null;
  spread: number | null;
  potDiameter:number | null;
  imageUrl : string | null;
  homeLocation:string |null;
  active: boolean | null;
  // plants: Plant[] | null;
  // user: User | null;
  // todos: Todo [] | null;
  // photos: Photo [] | null;




constructor(
  id: number = 0,
  nickname: string = "",
  description: string = "",
  height: number = 0,
  spread: number = 0,
  potDiameter:number = 0,
  imageUrl: string = "",
  homeLocation : string ="",
  active: boolean = true,
  // plants: Plant [],
  // user : User,
  // todos: Todo [],
  // photos : Photo []

){

this.id = id;
this.nickname = nickname;
this.description = description;
this.height = height;
this.spread = spread;
this.potDiameter = potDiameter,
this.imageUrl = imageUrl;
this.homeLocation = homeLocation;
this.active = active;
// this.plants = plants;
// this.user = user;
// this.todos = todos;
// this.photos = photos;


}

}
