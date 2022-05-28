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

  currentUserId: number = 0;
  users: User[] = [];
  currentUser: User = new User();
  editedUser: User | null = new User();
  editUser: boolean = false;



  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postSvc: PostService,
    private userSvc: UserService,
    private authSvc: AuthService


  ) {}



  ngOnInit(): void {
    this.currentUserId = parseInt(""+this.authSvc.getCurrentUserId());
    if (!this.selected && this.route.snapshot.paramMap.get('id')) {
      let id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.showPost(parseInt(id));
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
        this.userSvc.show(this.currentUser.id).subscribe(
          { // OBJECT
            next: (user) => {
              this.getUser();
              this.editUser = false;
              this.currentUser = user;
          },
        });
      },
      error: (err) => {
        console.error(err);
      },
    });
  }


  displayTable(){
    this.selected = null;
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

  disablePost(id: number, disablePost: Post) {
    this.postSvc.disablePost(id, disablePost).subscribe(
      (data) => {
        console.log(id), disablePost;

        this.reload();
        this.editPost = null;
        if (this.selected) {
          this.selected.id = Object.assign({}, id, disablePost);

        }
      },
      (err) => console.error(err)
    );
  }



  showPost(id: number) {
    this.postSvc.show(id).subscribe(
      (data) => {
        this.selected = data;
        if (!this.selected) {
          this.router.navigateByUrl('/notFound');
        }
      });
    }


  getUser() {
    const username = this.authSvc.getLoggedInUser();
    if(username !== null) {
    this.authSvc.getLoggedInUser().subscribe ({
        next: (user: User) => {
          this.currentUser = user;

        },
        error: (err: any) => {
          console.error('Error retreiving userinfo' + err);

        }
      });
    }
  }



  updateUser(user: User) {
    this.userSvc.update(user).subscribe({
      next: (userUpdated) => {
        this.editedUser = null;
        if(user.username != null) {
          this.currentUser = userUpdated;
        }

      },
      error: (err) => {
        console.error('Error updating user ' + err);

      }
    });
  }


}

