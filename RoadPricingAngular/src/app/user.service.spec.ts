import { TestBed, inject } from '@angular/core/testing';

import { OwnerService } from './user.service';

describe('UserService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OwnerService]
    });
  });

  it('should ...', inject([OwnerService], (service: OwnerService) => {
    expect(service).toBeTruthy();
  }));
});
