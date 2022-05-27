import { ActivatedRoute, Router } from '@angular/router';
import { PlantService } from './../../services/plant.service';
import { Component, OnInit } from '@angular/core';
import { Plant } from 'src/app/models/plant';

@Component({
  selector: 'app-plant',
  templateUrl: './plant.component.html',
  styleUrls: ['./plant.component.css'],
})
export class PlantComponent implements OnInit {
  title: string = 'Plants';

  selected: Plant | null = null;

  newPlant: Plant = new Plant();

  editPlant: Plant | null = null;

  deactivatePlant: Plant | null = null;

  plants: Plant[] = [];

  plantCount: Plant[] = [];

  constructor(
    private plantSvc: PlantService,
    private route: ActivatedRoute,
    private router: Router
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

  updatePlant(plant: Plant) {
    this.plantSvc.update(plant).subscribe(
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
  deactivate(plant: Plant) {
    this.plantSvc.deactivate(plant).subscribe(
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
  displayAddForm(){
    this.selected = null;
  }

}
