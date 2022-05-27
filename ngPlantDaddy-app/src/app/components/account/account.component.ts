import { AuthService } from 'src/app/services/auth.service';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { PostService } from 'src/app/services/post.service';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

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

  user: User | null = null;
  edituser: User | null = null;
  newUser: Post | null = null;




  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postSvc: PostService,
    private userSvc: UserService,
    private authSvc: AuthService


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

        this.authSvc.getLoggedInUser().subscribe({
          next: (userData) => {
            this.user = userData;
            this.userSvc.userInfo();
          },
        });
      },
      error: (err) => {
        console.error(err);
      },
    });
  }

  // updateUser(updatedUser: User) {
  //   this.userSvc.updateUser(updatedUser).subscribe(
  //     (data) => {
  //       this.reload();
  //       this.newUser = updatedUser;
  //       if (this.selected) {
  //         this.selected = Object.assign({}, updatedUser);
  //       }
  //     },
  //     (err) => console.error(err)
  //   );
  // }




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



  //--------------------POST
  disablePost(post: Post) {
    this.postSvc.disablePost(post).subscribe(
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







}

