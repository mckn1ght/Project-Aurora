import { Component, OnInit } from '@angular/core';
import { JobService } from '../service/job/job-service.service';
import { Router, ActivatedRoute } from '@angular/router';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { Job } from '../models/job';

@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.css'],
})
export class JobListComponent implements OnInit {
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
    this.route.params.subscribe((params) => {
      const expertiseAreaId = params['expertiseAreaId'];
      if (expertiseAreaId) {
        this.getJobsByExpertiseArea(expertiseAreaId);
      } else {
        this.route.queryParams.subscribe((queryParams) => {
          this.searchInput = queryParams['searchInput'] || '';
          if (this.searchInput !== '') {
            this.getAllJobsByKeyword(this.searchInput);
          } else {
            this.getAllNewJobs();
          }
        });
      }
    });
  }

  getAllNewJobs(): void {
    this.jobService.getAllNewJobs().subscribe(
      (jobs) => {
        this.activeJobs = jobs;
        this.loadAllJobPictures();
      },
      (error) => {
        console.error('Error fetching active jobs:', error);
      }
    );
  }

  getAllJobsByKeyword(searchInput: string): void {
    this.jobService.getAllJobsByKeyword(searchInput).subscribe(
      (jobs) => {
        this.activeJobs = jobs;
        this.loadAllJobPictures();
      },
      (error) => {
        console.error('Error fetching jobs: ', error.error);
      }
    );
  }

  // getJobsByExpertiseArea(expertiseAreaId: number): void {
  //   this.jobService.getByExpertiseArea(expertiseAreaId).subscribe(
  //     (jobs) => {
  //       this.activeJobs = jobs;
  //       this.loadAllJobPictures();
  //     },
  //     (error) => {
  //       console.error('Error fetching jobs by expertise area:', error);
  //     }
  //   );
  // }

  getJobsByExpertiseArea(expertiseAreaId: number): void {
    this.jobService.getByExpertiseArea(expertiseAreaId).subscribe(
      (jobs) => {
        this.activeJobs = jobs.filter(
          (job) =>
            job.currentWorkflowStep === 'New' ||
            job.currentWorkflowStep === 'In Licitatie'
        );
        this.loadAllJobPictures();
      },
      (error) => {
        console.error('Error fetching jobs by expertise area:', error);
      }
    );
  }

  loadAllJobPictures(): void {
    this.activeJobs.forEach((job) => {
      this.jobService.getJobPictures(job.id).subscribe(
        (pictures) => {
          job.pictures = pictures.map((pic: any) => {
            if (pic.url) {
              return `/uploads/jobs/${pic.url.split('/').pop()}`;
            } else {
              console.error('Unexpected picture format:', pic);
              return '';
            }
          });
        },
        (error) => {
          console.error('Error fetching job pictures:', error);
        }
      );
    });
  }

  navigateToJobDetails(jobId: number) {
    this.router.navigate(['/job-details', jobId]);
  }

  back() {
    this.router.navigate(['/home']);
  }

  openModal(src: string): void {
    const modal = document.getElementById('myModal') as HTMLElement;
    const modalImg = document.getElementById('img01') as HTMLImageElement;
    const captionText = document.getElementById('caption') as HTMLElement;

    modal.style.display = 'block';
    modalImg.src = src;
    captionText.innerHTML = src;
  }

  closeModal(): void {
    const modal = document.getElementById('myModal') as HTMLElement;
    modal.style.display = 'none';
  }
}
