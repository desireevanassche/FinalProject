import { Topic } from "./topic";
import { User } from "./user";
import { Comment } from "./comment";


export class Post {

  id: number;
  title: string ;
  content: string;
  imageUrl: string | null;
  createDate: string | null;
  updateDate: string | null;
  active: boolean | null;
  topic: Topic;
  user: User;
  comments : Comment[] |null;


constructor(
  id: number = 0,
  title: string = "",
  content: string = "",
  imageUrl: string = "",
  createDate: string = "",
  updateDate: string = "",
  active: boolean = true,
  topic : Topic = new Topic(),
  user : User = new User(),
  comments: Comment [] = []

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
this.comments = comments;



}

}
