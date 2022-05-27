import { TestBed } from '@angular/core/testing';

import { UserplantService } from './userplant.service';

describe('UserplantService', () => {
  let service: UserplantService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserplantService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
