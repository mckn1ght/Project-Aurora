import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JobService } from '../service/job/job-service.service';
import { ExpertiseAreaService } from '../service/expertiseArea/expertise-area.service';
import { AuthService } from '../service/auth/auth.service';
import { User } from 'src/app/models/user';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';

@Component({
  selector: 'app-new-job',
  templateUrl: './new-job.component.html',
  styleUrls: ['./new-job.component.css'],
})
export class NewJobComponent {
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;
  loggedInUser: User | null = null;
  newJob: any = {
    title: '',
    description: '',
    location: '',
    createdByUser: '',
    assignedToSpecialist: 1,
    creationDate: '',
    dueDate: '',
    lastUpdated: '',
    currentWorkflowStep: 'New',
  };

  expertiseAreasList: any[] = [];
  error = '';
  newJobForm: FormGroup;
  minDate: string;

  constructor(
    private formBuilder: FormBuilder,
    private jobService: JobService,
    private router: Router,
    private expertiseAreaService: ExpertiseAreaService,
    private authService: AuthService
  ) {
    this.newJobForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(10)]],
      description: ['', [Validators.required, Validators.minLength(25)]],
      expertiseArea: ['', [Validators.required]],
      location: ['', [Validators.required, Validators.minLength(5)]],
      // phoneNumber: ['', [Validators.required]],
      dueDate: ['', Validators.required],
    });
    const currentDate = new Date();
    this.minDate = currentDate.toISOString().split('T')[0];
  }

  ngOnInit() {
    this.loadExpertiseAreas();
    this.authService.getLoggedInUser().subscribe((user) => {
      this.loggedInUser = user;
      if (this.loggedInUser) {
        const userId = this.loggedInUser.id;
      }
    });
  }

  addJob() {
    this.error = '';
    this.newJob.creationDate = new Date();
    this.newJob.lastUpdated = new Date();
    this.newJob.createdByUser = this.loggedInUser?.id;
    if (this.newJobForm.valid) {
      this.jobService.addJob(this.newJob).subscribe(
        (response) => {
          console.log('Job added successfully:', response);
          this.router.navigate(['/jobs']);
        },
        (error) => {
          console.error('Error adding job:', error.error);
          this.error = error.error
            ? error.error
            : 'An error occurred while adding the job.';
        }
      );
    } else {
      console.error('Job posting failed. Problem during API call:', Error);
    }
  }

  loadExpertiseAreas() {
    this.expertiseAreaService.getAllExpertiseAreas().subscribe(
      (data) => {
        this.expertiseAreasList = data;
      },
      (error) => {
        console.error('Error fetching expertise areas:', error);
      }
    );
  }
}
