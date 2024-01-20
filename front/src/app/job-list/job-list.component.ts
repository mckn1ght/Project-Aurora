import { Component } from '@angular/core';
import { JobService } from '../service/job/job-service.service';
import { Router, ActivatedRoute } from '@angular/router';
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

  searchInput: string = '';
  constructor(
    private jobService: JobService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.searchInput = params['searchInput'] || '';
      console.log('this.searchInput: ' + this.searchInput);
      if (this.searchInput != '') this.getAllJobsByKeyword(this.searchInput);
      else {
        this.getAllNewJobs();
      }
    });
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

  getAllJobsByLocation(searchInput: string): void {
    this.jobService.getAllJobsByLocation(searchInput).subscribe(
      (jobs) => {
        if (Array.isArray(jobs)) {
          this.activeJobs = jobs;
        } else {
          console.error('Unexpected response format:', jobs);
        }
      },
      (error) => {
        console.error('Error fetching jobs by location:', error.error);
      }
    );
  }

  getAllJobsByKeyword(searchInput: string): void {
    this.jobService.getAllJobsByKeyword(searchInput).subscribe(
      (jobs) => {
        if (Array.isArray(jobs)) {
          this.activeJobs = jobs;
        } else {
          console.error('Unexpected response format:', jobs);
        }
      },
      (error) => {
        console.error('Error fetching jobs: ', error.error);
      }
    );
  }

  getAllJobsByTitle(searchInput: string): void {
    this.jobService.getAllJobsByTitle(searchInput).subscribe(
      (jobs) => {
        if (Array.isArray(jobs)) {
          this.activeJobs = jobs;
        } else {
          console.error('Unexpected response format:', jobs);
        }
      },
      (error) => {
        console.error('Error fetching jobs by title:', error.error);
      }
    );
  }

  getAllJobsByDescription(searchInput: string): void {
    this.jobService.getAllJobsByDescription(searchInput).subscribe(
      (jobs) => {
        if (Array.isArray(jobs)) {
          this.activeJobs = jobs;
        } else {
          console.error('Unexpected response format:', jobs);
        }
      },
      (error) => {
        console.error('Error fetching jobs by Description:', error.error);
      }
    );
  }

  navigateToJobDetails(jobId: number) {
    this.router.navigate(['/job-details', jobId]);
  }
}
