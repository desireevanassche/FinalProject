import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Blog } from 'src/app/models/blog';
import { BlogService } from './../../services/blog.service';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {
  title: string = 'Blog';

  newBlog: Blog = new Blog();

  editBlog: Blog | null = null;

  posts: Blog[] = [];

  allBlog : Blog [] =[];

  blogs: Blog[] = [];

  selected: Blog | null = null;

  searchValue: string = "";


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private blogSvc: BlogService
  ) { }

  ngOnInit(): void {
    if (!this.selected && this.route.snapshot.paramMap.get('id')) {
      let id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.show(parseInt(id));
      }
    } else {
    }
    this.reload();
  }

  reload() {
      this.blogSvc.indexAllBlogs().subscribe(
        data => this.blogs = data,
        err => console.log(err)
      );
  }

  addBlog(newBlog: Blog) {
    this.blogSvc.createBlog(newBlog).subscribe(
      (data) => {
        this.reload();
        this.newBlog = new Blog();
      },
      (err) => console.error(err)
    );
  }

  updateBlog(updatedBlog: Blog, id: number) {
    this.blogSvc.updateBlog(updatedBlog, id).subscribe(
      (data) => {
        this.reload();
        this.newBlog = updatedBlog;
        if (this.selected) {
          this.selected = Object.assign({}, updatedBlog);
        }
      },
      (err) => console.error(err)
    );
  }
  disableBlog(blog: Blog) {
    this.blogSvc.deleteBlog(blog).subscribe(
      (data) => {
        this.reload();
        this.editBlog = null;
        if (this.selected) {
          this.selected = Object.assign({}, blog);
        }
      },
      (err) => console.error(err)
    );
  }
  displayBlog(blog: Blog) {
    this.selected = blog;
  }


  displayTable(){
    this.selected = null;
  }


  show(id: number) {
    this.blogSvc.show(id).subscribe(
      (data) => {
        this.selected = data;
        if (!this.selected) {
          this.router.navigateByUrl('/notFound');
        }
      },
      (err) => {
        console.log(err);
        if (!this.selected) {
          this.router.navigateByUrl('/notFound');
        }
      }
    );
  }



displayAllBlogs(){
  this.blogSvc.indexAllBlogs().subscribe(
    (data) => {
       this.allBlog = data;

    },
    (err) => {
      console.log(err);

    }
  )
}


}



