import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {



  constructor(private http : HttpClient, private datePipe : DatePipe,private auth : AuthService) { }





}
