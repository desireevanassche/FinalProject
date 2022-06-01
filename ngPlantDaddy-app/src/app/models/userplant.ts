import { Growthdata } from './growthdata';
import { Plant } from "./plant";
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
  growthDescription: string | null;
  growthImage: string | null;
  active: boolean | null;
  user: User;
  plant: Plant;
  growthData : Growthdata[] | null;

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
  growthDescription: string = "",
  growthImage: string = "",
  active: boolean = true,
  plant: Plant = new Plant(),
  user : User = new User (),
  growthData : Growthdata[] = [],
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
this.growthDescription = growthDescription;
this.growthImage = growthImage;
this.active = active;
this.plant = plant;
this.user = user;
this.growthData = growthData;

// this.photos = photos;



}

}
