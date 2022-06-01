import { Post } from "./post";

export class Topic {

  id: number;
  name: string;
  description: string | null;
  imageUrl: string | null;
  posts: Post[];



constructor(
  id: number = 0,
  name: string ="",
  description: string = "",
  imageUrl: string = "",
  posts: Post[] = []



){

this.id = id;
this.name =name;
this.description = description;
this.imageUrl = imageUrl,
this.posts = posts;



}
}
