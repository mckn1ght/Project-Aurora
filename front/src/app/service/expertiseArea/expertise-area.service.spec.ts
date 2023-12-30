import { TestBed } from '@angular/core/testing';

import { ExpertiseAreaService } from './expertise-area.service';

describe('ExpertiseAreaService', () => {
  let service: ExpertiseAreaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExpertiseAreaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
