
export class Post {

  id: number | null;
  title: string | null;
  content: string | null;
  imageUrl: string | null;
  createDate: string | null;
  updateDate: string | null;
  active: boolean | null;


constructor(
  id: number = 0,
  title: string = "",
  content: string = "",
  imageUrl: string = "",
  createDate: string = "",
  updateDate: string = "",
  active: boolean = false


){

this.id = id;
this.title = title;
this.content = content;
this.imageUrl = imageUrl
this.createDate = createDate;
this.updateDate =updateDate;
this.active = active;


}

}
