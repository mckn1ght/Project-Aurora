import { Component } from '@angular/core';
import { JobService } from '../service/job/job-service.service';
import { Router } from '@angular/router';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';

@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.css'],
})
export class JobListComponent {
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;
  activeJobs: any[] = [];
  constructor(private jobService: JobService, private router: Router) {}

  ngOnInit(): void {
    this.getAllNewJobs();
  }

  getAllNewJobs(): void {
    this.jobService.getAllNewJobs().subscribe(
      (jobs) => {
        this.activeJobs = jobs;
      },
      (error) => {
        console.error('Error fetching active jobs:', error);
      }
    );
  }

  navigateToJobDetails(jobId: number) {
    this.router.navigate(['/job-details', jobId]);
  }
}
