import { User } from "./user";

export class Plant {

  id : number;
  commonName : string;
  description : string;
  imageUrl : string;
  botanicalName : string;
  careDifficulty : string;
  waterCycle : string | null;
  waterType : string | null;
  lightReq : string | null;
  active : boolean | null;
  user: User;

  constructor( id : number = 0,
    commonName : string = "",
    description : string = "",
    imageUrl : string = "",
    botanicalName : string = "",
    careDifficulty : string = "",
    waterCycle : string = "",
    waterType : string = "",
    lightReq : string = "",
    active : boolean = true,
    user : User = new User()

    ){

    this.id = id;
    this.commonName = commonName;
    this.description = description;
    this.imageUrl = imageUrl;
    this.botanicalName = botanicalName;
    this.careDifficulty = careDifficulty;
    this.waterCycle = waterCycle;
    this.waterType = waterType;
    this.lightReq = lightReq;
    this.active = active;
    this.user = user;
  }
}
