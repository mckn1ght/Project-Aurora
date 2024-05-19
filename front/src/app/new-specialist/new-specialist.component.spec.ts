import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewSpecialistComponent } from './new-specialist.component';

describe('NewSpecialistComponent', () => {
  let component: NewSpecialistComponent;
  let fixture: ComponentFixture<NewSpecialistComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewSpecialistComponent]
    });
    fixture = TestBed.createComponent(NewSpecialistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
