export class Topic {

  id: number | null;
  description: string | null;
  imageUrl: string | null;



constructor(
  id: number = 0,
  description: string = "",
  imageUrl: string = "",



){

this.id = id;
this.description = description;
this.imageUrl = imageUrl



}
}
