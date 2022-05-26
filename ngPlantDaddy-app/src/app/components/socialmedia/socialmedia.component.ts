import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-socialmedia',
  templateUrl: './socialmedia.component.html',
  styleUrls: ['./socialmedia.component.css'],
})
export class SocialmediaComponent implements OnInit {
  title: string = 'Social Media';

  newPost: Post = new Post();

  editPost: Post | null = null;

  posts: Post[] = [];

  selected: Post | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postSvc: PostService
  ) {}

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
    this.postSvc.indexPosts().subscribe(
      (data) => (this.posts = data),
      (err) => console.error(err)
    );
  }

  addPost() {
    this.postSvc.createPost(this.newPost).subscribe(
      (data) => {
        this.reload();
        this.newPost = new Post();
      },
      (err) => console.error(err)
    );
  }

  updatePost(post: Post, id: number) {
    this.postSvc.updatePost(post, id).subscribe(
      (data) => {
        this.reload();
        this.editPost = null;
        if (this.selected) {
          this.selected = Object.assign({}, post);
        }
      },
      (err) => console.error(err)
    );
  }
  disablePost(post: Post, id: number) {
    this.postSvc.disablePost(post, id).subscribe(
      (data) => {
        this.reload();
        this.editPost = null;
        if (this.selected) {
          this.selected = Object.assign({}, post);
        }
      },
      (err) => console.error(err)
    );
  }
  displayPost(post: Post) {
    this.selected = post;
  }

}
