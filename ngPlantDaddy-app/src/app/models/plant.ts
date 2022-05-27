import { User } from "./user";

export class Plant {

  id : number;
  commonName : string;
  description : string | null;
  imageUrl : string | null;
  botanicalName : string | null;
  careDifficulty : string | null;
  waterCycle : string | null;
  waterType : string | null;
  lightRequirement : string | null;
  active : boolean;
  user: User;

  constructor( id : number = 0,
    commonName : string = "",
    description : string = "",
    imageUrl : string = "",
    botanicalName : string = "",
    careDifficulty : string = "",
    waterCycle : string = "",
    waterType : string = "",
    lightRequirement : string = "",
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
    this.lightRequirement = lightRequirement;
    this.active = active;
    this.user = user;
  }
}
