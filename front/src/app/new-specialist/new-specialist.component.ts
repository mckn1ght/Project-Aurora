import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ExpertiseAreaService } from '../service/expertiseArea/expertise-area.service';
import { AuthService } from '../service/auth/auth.service';
import { User } from '../models/user';
import { SpecialistService } from '../service/specialist/specialist-service';
import { EMPTY, Observable, catchError, tap, throwError } from 'rxjs';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-specialist',
  templateUrl: './new-specialist.component.html',
  styleUrls: ['./new-specialist.component.css'],
})
export class NewSpecialistComponent implements OnInit {
  newSpecialistForm: FormGroup;
  expertiseAreasList: any[] = [];
  loggedInUser: User | null = null;
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  constructor(
    private formBuilder: FormBuilder,
    private expertiseAreaService: ExpertiseAreaService,
    private authService: AuthService,
    private specialistService: SpecialistService,
    private router: Router
  ) {
    this.newSpecialistForm = this.formBuilder.group({
      expertiseAreas: ['', [Validators.required]],
    });
  }

  ngOnInit() {
    this.loadExpertiseAreas();
    this.authService.getLoggedInUser().subscribe((user) => {
      this.loggedInUser = user;
      console.log('Logged in user:', this.loggedInUser);
      if (!this.loggedInUser) {
        console.error('User is not logged in');
      }
    });
  }

  loadExpertiseAreas(): void {
    this.expertiseAreaService.getAllExpertiseAreas().subscribe(
      (data) => {
        this.expertiseAreasList = data;
      },
      (error) => {
        console.error('Error fetching expertise areas:', error);
      }
    );
  }

  addSpecialist() {
    console.log('Form Submitted');
    if (this.newSpecialistForm.invalid) {
      console.warn('Form is invalid');
      return;
    }

    if (!this.loggedInUser) {
      console.warn('User is not logged in');
      return;
    }

    const selectedExpertiseAreas =
      this.newSpecialistForm.get('expertiseAreas')?.value;
    console.log('Selected Expertise Areas:', selectedExpertiseAreas);

    // Transform the array of objects into an array of integers
    const expertiseAreaIds = selectedExpertiseAreas.map((area: any) => area.id);
    console.log('Expertise Area IDs:', expertiseAreaIds);

    this.specialistService
      .addSpecialist(this.loggedInUser.id!, expertiseAreaIds)
      .pipe(
        tap((response) => {
          console.log('Specialist added successfully:', response);
          // Refresh logged-in user details before redirecting to home
          this.authService.getLoggedInUser().subscribe((user) => {
            this.loggedInUser = user;
            this.router.navigate(['/login']);
          });
        }),
        catchError((error) => {
          console.error('Error adding specialist:', error);
          return throwError(() => new Error('Failed to add specialist'));
        })
      )
      .subscribe();
  }
}
