import { Blog } from 'src/app/models/blog';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchfilter'
})
export class SearchfilterPipe implements PipeTransform {

  transform(blogs: Blog[], searchValue: string): Blog[] {

    if(!blogs || !searchValue) {
      return blogs;
    }
    return blogs.filter(blog =>
      blog.title.toLowerCase().includes(searchValue.toLowerCase())
    || blog.content.toLowerCase().includes(searchValue.toLowerCase())) ;

    }
}
