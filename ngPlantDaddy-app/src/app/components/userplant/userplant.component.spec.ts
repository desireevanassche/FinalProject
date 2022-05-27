import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserplantComponent } from './userplant.component';

describe('UserplantComponent', () => {
  let component: UserplantComponent;
  let fixture: ComponentFixture<UserplantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserplantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserplantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
