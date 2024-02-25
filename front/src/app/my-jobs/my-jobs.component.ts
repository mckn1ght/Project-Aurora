import { Component, OnInit } from '@angular/core';
import { JobService } from '../service/job/job-service.service';
import { Job } from '../models/job';
import { User } from '../models/user';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { AuthService } from '../service/auth/auth.service';
import { UserService } from '../service/user/user.service';
import { BidService } from '../service/bid/bid.service';
import { Router } from '@angular/router';
import { Bid } from '../models/bid';

@Component({
  selector: 'app-my-jobs',
  templateUrl: './my-jobs.component.html',
  styleUrls: ['./my-jobs.component.css'],
})
export class MyJobsComponent implements OnInit {
  bidsWithJobDetails: { bid: Bid; job: Job }[] = [];
  bidsWithJobDetailsCopy: { bid: Bid; job: Job }[] = [];
  bid: Bid[] = [];
  activeJobs: Job[] = [];
  biddingJobs: Job[] = [];
  loggedInUser!: User;

  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  constructor(
    private jobService: JobService,
    private bidService: BidService,
    private authService: AuthService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit() {
    this.authService.getLoggedInUser().subscribe((loggedInUser) => {
      if (loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.fetchActiveJobs();
        this.fetchBidsWithJobDetails();
      }
    });
  }

  fetchActiveJobs() {
    if (this.loggedInUser && this.loggedInUser.specialistId !== undefined) {
      this.jobService
        .getAllAssignedToSpecialist(this.loggedInUser.specialistId)
        .subscribe((jobs) => {
          this.activeJobs = jobs;
          this.getCreatorUserName();
        });
    } else {
      console.error('Specialist ID is undefined or not available.');
    }
  }

  fetchBidsWithJobDetails() {
    if (this.loggedInUser && this.loggedInUser.specialistId !== undefined) {
      this.bidService
        .getAllBidsBySpecialist(this.loggedInUser.specialistId)
        .subscribe((bids: Bid[]) => {
          bids.forEach((bid: Bid) => {
            if (bid.jobId !== undefined) {
              this.jobService.getJobDetails(bid.jobId).subscribe((job: Job) => {
                this.userService.getById(job.createdByUser).subscribe(
                  (user: User) => {
                    job.createdByUser = user.firstName + ' ' + user.lastName;
                  },
                  (error) => {
                    console.error(
                      'Error fetching creator user:',
                      error.message
                    );
                  }
                );
                this.bidsWithJobDetails.push({ bid, job });
              });
            }
          });
        });
    } else {
      console.error('Specialist ID is undefined or not available.');
    }
  }

  getCreatorUserName() {
    this.activeJobs.forEach((job) => {
      if (job.createdByUser) {
        this.userService.getById(job.createdByUser).subscribe(
          (user: User) => {
            job.createdByUser = user.firstName + ' ' + user.lastName;
          },
          (error) => {
            console.error('Error fetching creator user:', error.message);
          }
        );
      }
    });
  }
  display() {
    console.log(this.bidsWithJobDetails);
  }
  goToJobDetails(jobId: number) {
    this.router.navigate(['/job-details', jobId]);
  }

  deleteBid(bidId: number) {
    this.bidService.deleteBid(bidId).subscribe(
      () => {
        console.log('Bid deleted successfully.');
      },
      (error) => {
        console.error('Error deleting bid:', error);
        // You can handle errors appropriately, such as displaying an error message
      }
    );
  }
}
