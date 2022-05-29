import { Pipe, PipeTransform } from '@angular/core';
import { Post } from '../models/post';

@Pipe({
  name: 'searchPosts'
})
export class SearchPostsPipe implements PipeTransform {
  transform(posts: Post[], searchValue: string): Post[] {

    if(!posts || !searchValue) {
      return posts;
    }
    return posts.filter(post =>
      post.title.toLowerCase().includes(searchValue.toLowerCase())
    || post.content.toLowerCase().includes(searchValue.toLowerCase())
    || post.topic.description?.toLowerCase().includes(searchValue.toLowerCase()));

    }
}
