import { PlantService } from './../../services/plant.service';

import { UserplantService } from './../../services/userplant.service';
import { Component, OnInit } from '@angular/core';
import { Userplant } from 'src/app/models/userplant';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Plant } from 'src/app/models/plant';

@Component({
  selector: 'app-userplant',
  templateUrl: './userplant.component.html',
  styleUrls: ['./userplant.component.css'],
})
export class UserplantComponent implements OnInit {
  title: string = 'My Garden <3';

  selected: Userplant | null = null;

  newUserPlant: Userplant = new Userplant();

  editUserPlant: Userplant | null = null;

  userPlants: Userplant[] = [];

  currentUserId: number | null = 0;

  plants: Plant[] = [];

  plant: Plant | null = null;

  constructor(
    private userPlantSvc: UserplantService,
    private route: ActivatedRoute,
    private router: Router,
    private authServ: AuthService,
    private plantSvc: PlantService
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
    this.userPlantSvc.indexUserPlants().subscribe({
      next: (data) => {
        this.userPlants = data;
        this.displayAllUserPlants();

        this.plantSvc.index().subscribe({
          next: (plantData) => {
            this.plants = plantData;
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

  show(id: number) {
    this.userPlantSvc.show(id).subscribe(
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

  addUserPlant(newUserPlant: Userplant) {
    this.userPlantSvc.create(newUserPlant).subscribe(
      (data) => {
        this.reload();
        this.newUserPlant = new Userplant();
      },
      (err) => console.error(err)
    );
  }

  updateUserPlant(userPlant: Userplant, id: number) {
    this.userPlantSvc.update(userPlant, id).subscribe(
      (data) => {
        this.reload();
        this.editUserPlant = null;
        if (this.selected) {
          this.selected = Object.assign({}, userPlant);
        }
      },
      (err) => console.error(err)
    );
  }
  deactivateUserPlant(userPlant: Userplant, id: number) {
    this.userPlantSvc.deactivate(userPlant, id).subscribe(
      (data) => {
        this.reload();
        this.editUserPlant = null;
        if (this.selected) {
          this.selected = Object.assign({}, userPlant);
        }
      },
      (err) => console.error(err)
    );
  }
  setPlant(userplant: Userplant) {
    this.selected = userplant;
  }
  setEditUserPlant() {
    this.editUserPlant = Object.assign({}, this.selected);
  }
  displayUserPlant(plant: Userplant) {
    this.selected = plant;
  }
  displayTable() {
    this.selected = null;
  }
  displayAllUserPlants() {
    this.userPlantSvc.indexUserPlants().subscribe(
      (data) => {
        this.userPlants = data;
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
