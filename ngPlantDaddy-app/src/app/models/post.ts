import { Topic } from "./topic";
import { User } from "./user";


export class Post {

  id: number;
  title: string | null;
  content: string | null;
  imageUrl: string | null;
  createDate: string | null;
  updateDate: string | null;
  active: boolean | null;
  topic: Topic;
  user: User;


constructor(
  id: number = 0,
  title: string = "",
  content: string = "",
  imageUrl: string = "",
  createDate: string = "",
  updateDate: string = "",
  active: boolean = false,
  topic : Topic = new Topic(),
  user : User = new User()


){

this.id = id;
this.title = title;
this.content = content;
this.imageUrl = imageUrl
this.createDate = createDate;
this.updateDate =updateDate;
this.active = active;
this.topic = topic;
this.user = user;



}

}
