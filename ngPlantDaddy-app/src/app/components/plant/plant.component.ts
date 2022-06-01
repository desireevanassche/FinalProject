import { AuthService } from 'src/app/services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PlantService } from './../../services/plant.service';
import { Component, OnInit } from '@angular/core';
import { Plant } from 'src/app/models/plant';

@Component({
  selector: 'app-plant',
  templateUrl: './plant.component.html',
  styleUrls: ['./plant.component.css', './plant.component.sideNav.css'],
})
export class PlantComponent implements OnInit {
  title: string = 'Plant Library';

  selected: Plant | null = null;

  newPlant: Plant = new Plant();

  editPlant: Plant | null = null;

  deactivatePlant: Plant | null = null;

  plants: Plant[] = [];

  plantCount: Plant[] = [];

  currentUserId: number = 0;
  displayAddForm: boolean = false;
  displayCareCard: boolean = false;

  searchValue: string = "";
  filterLight: string = "";
  filterWaterType: string = "";
  filterWaterCycle: string = "";



  constructor(
    private plantSvc: PlantService,
    private route: ActivatedRoute,
    private router: Router,
    private authServ: AuthService
  ) {}

  ngOnInit(): void {
    this.currentUserId = parseInt(""+this.authServ.getCurrentUserId());
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
    this.plantSvc.index().subscribe(
      (data) => (this.plants = data),
      (err) => console.error(err)
    );
  }
  show(id: number) {
    this.plantSvc.show(id).subscribe(
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


  displayPlant(plant: Plant) {
    this.selected = plant;
  }




  displayTable() {
    this.selected = null;
  }

  addPlant() {
    this.plantSvc.create(this.newPlant).subscribe(
      (data) => {
        this.reload();
        this.newPlant = new Plant();
      },
      (err) => console.error(err)
    );
  }

  updatePlant(plant: Plant, id : number) {
    this.plantSvc.update(plant, id).subscribe(
      (data) => {
        this.reload();
        this.editPlant = null;
        if (this.selected) {
          this.selected = Object.assign({}, plant);
        }
      },
      (err) => console.error(err)
    );
  }
  deactivate(plant: Plant, id : number) {
    this.plantSvc.deactivate(plant, id).subscribe(
      (data) => {
        this.reload();
        this.editPlant = null;
        if (this.selected) {
          this.selected = Object.assign({}, id, plant);
        }
      },
      (err) => console.error(err)
    );
  }
  setPlant(plant: Plant) {
    this.selected = plant;
  }
  setEditPlant(){
    this.editPlant = Object.assign({}, this.selected);
  }

  isNumber(id: number){
    return Number.isNaN(id);
  }

  getNumOfPlants() {
    return this.plants.length;
  }

  tagSearch(searchTag: string){
  this.searchValue = searchTag;
  }







}
