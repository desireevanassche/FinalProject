import { AuthService } from 'src/app/services/auth.service';
import { TopicService } from './../../services/topic.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { PostService } from 'src/app/services/post.service';
import { Topic } from 'src/app/models/topic';
import { CommentService } from 'src/app/services/comment.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-socialmedia',
  templateUrl: './socialmedia.component.html',
  styleUrls: ['./socialmedia.component.css'],
})
export class SocialmediaComponent implements OnInit {
  title: string = 'Social Media';

  newPost: Post = new Post();

  editPost: Post | null = null;

  postId: number | null = null;

  posts: Post[] = [];

  allPosts: Post[] = [];

  comments: Comment[] = [];

  allComments: Comment[] = [];

  newComment: Comment = new Comment();

  topics: Topic[] = [];

  topic: Topic | null = null;

  selected: Post | null = null;

  display: boolean = false;

  currentUserId: number | null = 0;

  searchValue: string = "";
  displayPostModal = false;
  displayCommentModal = false;

  user: User = new User ();


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postSvc: PostService,
    private topicSvc: TopicService,
    private authServ: AuthService,
    private commentSvc: CommentService
  ) {}



  ngOnInit(): void {
    this.currentUserId = parseInt('' + this.authServ.getCurrentUserId());
    console.log(this.currentUserId);



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


  reloadComments(postId : number){
    this.commentSvc.indexComments(postId).subscribe({
      next : (data)=>{
        this.comments = data;
      },
      error: (err) => {
        console.error(err);
   } });
  }


  displayTable() {
    this.selected = null;
  }

  displayPosts() {
    this.selected = null;
  }

  displayCreateForm() {
    this.display = true;
  }

  displayEditForm() {
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

  displayPost(post: Post) {
    this.selected = post;
    console.log(post);

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

  displayAllPosts() {
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
    );
  }

  getUserFromComment(commentId:number,userId: number) {
    this.commentSvc.getUserFromComment(commentId, userId).subscribe({
      next: (data)=>{
        this.user = data;
      },
      error : (err)=>{
        console.log(err + "error existing inside social component getting user info");
      }
    })
  }

  createCommentOnPost(postId: number, comment: Comment ){
    this.commentSvc.createCommentOnPost(postId,comment).subscribe({

      next : (data)=>{



        this.newComment = new Comment();
        console.log(this.newComment);


      },
      error : (err) =>{
        console.log(err + "this error is inside creating a new comment in social component.ts");

      }
    })
  }

  createCommentOnComment(postId : number, commentId : number,comment : Comment ){
    this.commentSvc.createCommentOnComment(postId, commentId, comment ).subscribe({
      next : (data)=>{
        this.newComment = data;
      },
      error : (err) =>{
        console.log(err + "this error is inside creating a new comment on a comment in social component.ts");

      }
    })
  }



  // displayAllComments(postId:number){

  //   this.commentSvc.indexComments(postId).subscribe(
  //     (data)=> {
  //       this.allComments = data;
  //     },
  //     (err) => {
  //       console.log(err);
  //     }
  //   )
  // }
}
