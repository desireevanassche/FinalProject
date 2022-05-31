import { Userplant } from 'src/app/models/userplant';
export class Todo {

  id: number;
  name: string | null;
  description: string | null;
  createdAt: string | null;
  completedDate: string | null;
  dueDate: string | null;
  active: boolean | null;
  userplant: Userplant;



constructor(
  id: number = 0,
  name: string = "",
  description: string = "",
  createdAt: string = "",
  completedDate: string = "",
  dueDate: string ="",
  active: boolean = true,
  userplant : Userplant = new Userplant()

){

this.id = id;
this.name = name;
this.description = description;
this.createdAt = createdAt;
this.completedDate =completedDate;
this.dueDate = dueDate;
this.active = active;
this.userplant = userplant;



}

}
