import { TestBed } from '@angular/core/testing';

import { GrowthDataService } from './growth-data.service';

describe('GrowthDataService', () => {
  let service: GrowthDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GrowthDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
