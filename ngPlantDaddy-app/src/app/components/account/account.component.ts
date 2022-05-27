import { SocialmediaComponent } from './../socialmedia/socialmedia.component';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { PostService } from 'src/app/services/post.service';
import { TopicService } from 'src/app/services/topic.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  title: string = 'Account';

  posts: Post[] = [];
  editPost: Post | null = null;
  selected: Post | null = null;
  allPosts : Post [] =[];
  newPost: Post = new Post();




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
    this.postSvc.indexPosts().subscribe({
      next: (data) => {
        this.posts = data;
        this.postSvc.indexAllPosts();

      },
      error: (err) => {
        console.error(err);
      },
    });
  }

  show(id: number) {
    this.postSvc.show(id).subscribe(
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

  displayTable(){
    this.selected = null;
  }

  addPost(newPost: Post) {
    this.postSvc.createPost(newPost).subscribe(
      (data) => {
        this.reload();
        this.newPost = new Post();
      },
      (err) => console.error(err)
    );
  }

  updatePost(updatedPost: Post, id: number) {
    this.postSvc.updatePost(updatedPost, id).subscribe(
      (data) => {
        this.reload();
        this.newPost = updatedPost;
        if (this.selected) {
          this.selected = Object.assign({}, updatedPost);
        }
      },
      (err) => console.error(err)
    );
  }
  // disablePost(post: Post) {
  //   this.postSvc.disablePost(post).subscribe(
  //     (data) => {
  //       this.reload();
  //       this.editPost = null;
  //       if (this.selected) {
  //         this.selected = Object.assign({}, post);
  //       }
  //     },
  //     (err) => console.error(err)
  //   );
  // }
  displayPost(post: Post) {
    this.selected = post;
  }





}

