import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JobService } from '../service/job/job-service.service';
import { Job } from '../models/job';
import { AuthService } from '../service/auth/auth.service';
import { User } from '../models/user';
import { isObservable } from 'rxjs';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
@Component({
  selector: 'app-job-details',
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css'],
})
export class JobDetailsComponent implements OnInit {
  job: Job | undefined;
  loggedInUser: User | undefined;
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;
  constructor(
    private route: ActivatedRoute,
    private jobService: JobService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      const jobId = +params['id'];
      if (jobId) {
        this.fetchJobDetails(jobId);
      } else {
        console.error('Invalid job ID:', jobId);
      }
    });
  }

  fetchJobDetails(jobId: number) {
    this.authService.getLoggedInUser().subscribe((loggedInUser) => {
      if (loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.jobService.getJobDetails(jobId).subscribe((details) => {
          this.job = details;
        });
      }
    });

    // console.log(
    //   JSON.stringify(this.authService.getLoggedInUser()) + ' @@@@@@@@'
    // );
  }

  // assignToMe(jobId: number, specialistId: number) {
  //   this.jobService.assignJobToSpecialist(jobId, specialistId).subscribe(
  //     () => {
  //       console.log('Job assigned successfully.');
  //       // Handle success, e.g., refresh job list
  //     },
  //     (error) => {
  //       console.error('Error assigning job:', error);
  //     }
  //   );
  // }

  // assignToMe(jobId: number, specialistId: number) {
  //   this.jobService.assignJobToSpecialist(jobId, specialistId).subscribe(
  //     () => {
  //       console.log('Job assigned successfully.');
  //       // Handle success, e.g., refresh job list
  //     },
  //     (error) => {
  //       console.error('Error assigning job:', error);

  //       // Check if the error is due to the job being already assigned
  //       if (error.includes('already assigned')) {
  //         // Display a specific error message to the user or handle it accordingly
  //         // For example, you might want to notify the user that the job is already assigned
  //         // and provide instructions on what to do next.
  //         // You can use a notification service or show an alert/modal.
  //         // For simplicity, let's log a message to the console here.
  //         console.error('The job is already assigned.');
  //       } else {
  //         // Handle other errors if needed
  //         console.error(
  //           'An unexpected error occurred while assigning the job.'
  //         );
  //       }
  //     }
  //   );
  // }

  assignToMe(jobId: number, specialistId: number) {
    this.jobService.assignJobToSpecialist(jobId, specialistId).subscribe(
      (response) => {
        console.log('Job assigned successfully.', response);
      },
      (error) => {
        console.error('Error assigning job:', error);
        console.error('Backend Error:', error);
      }
    );
  }

  showAssignToMeButton(): boolean {
    let loggedInUser: User | null = null;

    const subscription = this.authService
      .getLoggedInUser()
      .subscribe((user) => {
        loggedInUser = user;
        subscription.unsubscribe();
      });

    return loggedInUser !== null && loggedInUser !== 1;
  }
}
