import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrowthDataComponent } from './growth-data.component';

describe('GrowthDataComponent', () => {
  let component: GrowthDataComponent;
  let fixture: ComponentFixture<GrowthDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GrowthDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GrowthDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
