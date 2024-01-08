import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JobService } from '../service/job/job-service.service';
import { Job } from '../models/job';
import { AuthService } from '../service/auth/auth.service';
import { User } from '../models/user';
import { isObservable } from 'rxjs';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { UserService } from '../service/user/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-job-details',
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css'],
})
export class JobDetailsComponent implements OnInit {
  job: Job | undefined;
  creatorUser: string = '';
  loggedInUser: User | undefined;
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;
  createdByUser: String = '';
  assignedToSpecialist: String = '';

  constructor(
    private route: ActivatedRoute,
    private jobService: JobService,
    private authService: AuthService,
    private userService: UserService,
    private router: Router
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
          this.getCreatorUserName();
          this.getSpecialistName();
        });
      }
    });

    // console.log(
    //   JSON.stringify(this.authService.getLoggedInUser()) + ' @@@@@@@@'
    // );
  }

  assignToMe(jobId: number, specialistId: number) {
    this.jobService.assignJobToSpecialist(jobId, specialistId).subscribe(
      (response) => {
        console.log('Job assigned successfully.', response);
        this.router.navigate(['/job-list']);
      },
      (error) => {
        console.error('Error assigning job:', error);
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

  getCreatorUserName() {
    if (this.job?.createdByUser) {
      this.userService.getById(this.job.createdByUser).subscribe(
        (response: User) => {
          this.creatorUser = response.firstName! + ' ' + response.lastName!;
        },
        (error) => {
          console.error('Error fetching creator user:', error.message);
        }
      );
    }
  }

  getSpecialistName() {
    if (this.job?.createdByUser) {
      if (
        this.job?.assignedToSpecialist != 1 &&
        this.job?.assignedToSpecialist != null
      ) {
        this.userService
          .getSpecialistName(this.job.assignedToSpecialist)
          .subscribe(
            (response: User) => {
              this.assignedToSpecialist =
                response.firstName! + ' ' + response.lastName!;
            },
            (error) => {
              console.log('creatorUser error ' + error.message);
            }
          );
      }
    }
  }
}
