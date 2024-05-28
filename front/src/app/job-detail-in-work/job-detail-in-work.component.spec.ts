import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobDetailInWorkComponent } from './job-detail-in-work.component';

describe('JobDetailInWorkComponent', () => {
  let component: JobDetailInWorkComponent;
  let fixture: ComponentFixture<JobDetailInWorkComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobDetailInWorkComponent]
    });
    fixture = TestBed.createComponent(JobDetailInWorkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
