import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  currentUser: User = new User();

  constructor(
    private auth : AuthService,
    private router : Router,
    private userSvc: UserService,) { }

  ngOnInit(): void {
    this.loadUserData();
  }


  logout(): void {
    console.log('logging out.');

    this.auth.logout();
    this.router.navigateByUrl("/home")
  }


  loggedIn(): boolean {
    return this.auth.checkLogin();
  }


  getUser() {
    const username = this.auth.getLoggedInUser();
    if(username !== null) {
    this.auth.getLoggedInUser().subscribe ({
        next: (user: User) => {
          this.currentUser = user;

        },
        error: (err: any) => {
          console.error('Error retreiving userinfo' + err);

        }
      });
    }
  }

  loadUserData() {
      this.userSvc.show(this.currentUser.id).subscribe(
        (data) => {
          this.getUser();


        },
        (err) => {
          console.log(err);

        }
      );
    }



}

