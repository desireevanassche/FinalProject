import { Pipe, PipeTransform } from '@angular/core';
import { Post } from '../models/post';


@Pipe({
  name: 'filterTopic'
})
export class FilterTopicPipe implements PipeTransform {

  transform(posts: Post[], searchValue: string): Post[] {
    if  (searchValue.length === 0) {
      return posts;
    } else {
      return posts.filter(post => post.topic.name.toLowerCase() === searchValue.toLowerCase());
    }
  }

}

