import { Userplant } from './../../models/userplant';
import { UserplantService } from './../../services/userplant.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-userplant',
  templateUrl: './userplant.component.html',
  styleUrls: ['./userplant.component.css']
})
export class UserplantComponent implements OnInit {



  constructor(userPlant: UserplantService) { }

  ngOnInit(): void {
  }

}
