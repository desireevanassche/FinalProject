import { AuthService } from 'src/app/services/auth.service';
import { TopicService } from './../../services/topic.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { PostService } from 'src/app/services/post.service';
import { Topic } from 'src/app/models/topic';

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

  allPosts : Post [] =[];

  topics: Topic[] = [];

  selected: Post | null = null;

  display:boolean = false;

  currentUserId:number | null = 0;



  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postSvc: PostService,
    private topicSvc: TopicService,
    private authServ: AuthService
  ) {}

  ngOnInit(): void {
    this.currentUserId = parseInt(""+this.authServ.getCurrentUserId());
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
        this.displayAllPosts();
        console.log(data);

        this.topicSvc.indexTopics().subscribe({
          next: (topicData) => {
            this.topics = topicData;
          },
          error: (fail) => {
            console.log(fail);
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

  displayPosts(){
    this.selected = null;
  }

  displayCreateForm(){
    this.display = true;
  }

  displayEditForm(){
this.display = true;

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
  disablePost(id: number, disablePost:Post) {
    this.postSvc.disablePost(id, disablePost).subscribe(
      (data) => {
        console.log(id),disablePost;

        this.reload();
        this.editPost = null;
        if (this.selected) {
          this.selected.id = Object.assign({}, id, disablePost);
        }
      },
      (err) => console.error(err)
    );
  }

  displayPost(post: Post) {
    this.selected = post;
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

displayAllPosts(){
  this.postSvc.indexAllPosts().subscribe(
    (data) => {
       this.allPosts = data;
      // if (!this.allPosts) {
      //   this.router.navigateByUrl('/notFound');
      // }
    },
    (err) => {
      console.log(err);
      // if (!this.allPosts) {
      //   this.router.navigateByUrl('/notFound');
      // }
    }
  )
}


editPostCheck(){

}

}
