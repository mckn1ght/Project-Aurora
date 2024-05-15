import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JobService } from '../service/job/job-service.service';
import { ExpertiseAreaService } from '../service/expertiseArea/expertise-area.service';
import { AuthService } from '../service/auth/auth.service';
import { User } from 'src/app/models/user';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { Observable, EMPTY, tap, catchError, throwError } from 'rxjs';

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
  selectedExpertiseAreas: any[] = [];
  selectedFiles: File[] = [];

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
      expertiseAreas: ['', [Validators.required]],
      location: ['', [Validators.required, Validators.minLength(5)]],
      dueDate: ['', Validators.required],
    });
    const currentDate = new Date();
    this.minDate = currentDate.toISOString().split('T')[0];
  }

  ngOnInit() {
    this.loadExpertiseAreas();
    this.authService.getLoggedInUser().subscribe((user) => {
      this.loggedInUser = user;
    });
  }

  onFileSelected(event: any) {
    console.log('File selection event:', event); // Log the event structure
    this.selectedFiles = event.currentFiles || event.files; // Adjust based on the event structure
    console.log('Files selected:', this.selectedFiles); // Log selected files
  }

  addJob() {
    this.error = '';
    this.newJob.creationDate = new Date();
    this.newJob.lastUpdated = new Date();
    this.newJob.createdByUser = this.loggedInUser?.id;

    console.log('Selected files before job creation:', this.selectedFiles); // Check selected files

    if (this.newJobForm.valid) {
      this.jobService.addJob(this.newJob).subscribe(
        (response) => {
          console.log('Job added successfully:', response);
          const jobId = response.jobId;
          this.insertExpertiseAreas(jobId, this.newJobForm.value.expertiseAreas)
            .pipe(
              catchError((err) => {
                console.error('Error while adding expertise areas:', err);
                this.error = 'Failed to add expertise areas.';
                return EMPTY;
              })
            )
            .subscribe(() => {
              if (this.selectedFiles && this.selectedFiles.length > 0) {
                console.log('Preparing to upload pictures for job ID:', jobId); // Log before uploading
                this.uploadPictures(jobId, this.selectedFiles);
              } else {
                console.log('No files to upload, navigating to my jobs'); // Log if no files to upload
                this.router.navigate(['/my-jobs']);
              }
            });
        },
        (error) => {
          console.error('Error adding job:', error);
          this.error = 'An error occurred while adding the job.';
        }
      );
    } else {
      console.error('Job posting failed. Problem during API call:', Error);
    }
  }

  uploadPictures(jobId: number, pictures: File[]) {
    console.log('Uploading pictures:', pictures); // Log before uploading
    this.jobService.uploadJobPictures(jobId, pictures).subscribe(
      (response) => {
        console.log('Pictures uploaded successfully:', response);
        this.router.navigate(['/my-jobs']);
      },
      (error) => {
        console.error('Error uploading pictures:', error);
        this.error = 'Failed to upload pictures.';
        this.router.navigate(['/my-jobs']);
      }
    );
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

  insertExpertiseAreas(jobId: number, expertiseAreas: any[]): Observable<any> {
    if (expertiseAreas.length === 0) {
      console.log('No expertise areas to insert.');
      return EMPTY;
    }

    return this.expertiseAreaService
      .addJobExpertiseArea(jobId, expertiseAreas)
      .pipe(
        tap((response) =>
          console.log(
            'Expertise areas added to the job successfully:',
            response
          )
        ),
        catchError((error) => {
          console.error('Error adding expertise areas to the job:', error);
          return throwError(() => new Error('Failed to add expertise areas'));
        })
      );
  }
}
