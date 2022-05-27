export class Topic {

  id: number;
  name: string;
  description: string | null;
  imageUrl: string | null;



constructor(
  id: number = 0,
  name: string ="",
  description: string = "",
  imageUrl: string = "",



){

this.id = id;
this.name =name;
this.description = description;
this.imageUrl = imageUrl



}
}
