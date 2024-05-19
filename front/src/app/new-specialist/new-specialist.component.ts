import { Component } from '@angular/core';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ExpertiseAreaService } from '../service/expertiseArea/expertise-area.service';
import { AuthService } from '../service/auth/auth.service';
import { User } from '../models/user';
import { EMPTY, Observable, catchError, tap, throwError } from 'rxjs';

@Component({
  selector: 'app-new-specialist',
  templateUrl: './new-specialist.component.html',
  styleUrls: ['./new-specialist.component.css'],
})
export class NewSpecialistComponent {
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;
  newSpecialistForm: FormGroup;
  expertiseAreasList: any[] = [];
  selectedExpertiseAreas: any[] = [];
  loggedInUser: User | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private expertiseAreaService: ExpertiseAreaService,
    private authService: AuthService
  ) {
    this.newSpecialistForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(10)]],
      description: ['', [Validators.required, Validators.minLength(25)]],
      expertiseAreas: ['', [Validators.required]],
      location: ['', [Validators.required, Validators.minLength(5)]],
      dueDate: ['', Validators.required],
    });
  }

  ngOnInit() {
    this.loadExpertiseAreas();
    this.authService.getLoggedInUser().subscribe((user) => {
      this.loggedInUser = user;
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

  insertExpertiseAreas(
    specialistId: number,
    expertiseAreas: any[]
  ): Observable<any> {
    if (expertiseAreas.length === 0) {
      console.log('No expertise areas to insert.');
      return EMPTY;
    }

    return this.expertiseAreaService
      .addSpecialistExpertiseArea(specialistId, expertiseAreas)
      .pipe(
        tap((response) =>
          console.log(
            'Expertise areas added to the specialist successfully:',
            response
          )
        ),
        catchError((error) => {
          console.error(
            'Error adding expertise areas to the specialist:',
            error
          );
          return throwError(() => new Error('Failed to add expertise areas'));
        })
      );
  }
}
