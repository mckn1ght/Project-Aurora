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
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';

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
  jobsCreatedByUser: Job[] = [];
  loggedInUser!: User;

  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  constructor(
    private jobService: JobService,
    private bidService: BidService,
    private authService: AuthService,
    private userService: UserService,
    private router: Router,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.authService.getLoggedInUser().subscribe((loggedInUser) => {
      if (loggedInUser) {
        this.loggedInUser = loggedInUser;
        if (this.loggedInUser.specialistId != null) {
          this.fetchActiveJobs();
          this.fetchBidsWithJobDetails();
        } else {
          this.fetchJobsCreatedByUser();
          this.fetchJobsCreatedByUserWithBids();
        }
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
    // this.router.navigate(['/job-details', jobId]);
  }

  deleteBid(bidId: number): void {
    this.bidService.deleteBid(bidId).subscribe(
      () => {
        console.log('Licitatie stearsa cu succes.');

        this.router
          .navigateByUrl('/', { skipLocationChange: true })
          .then(() => {
            this.router.navigate(['/my-jobs']);
          });
      },
      (error) => {
        console.error('Eroare stergere licitatie:', error);
      }
    );
  }

  deleteJob(jobId: number): void {
    this.jobService.deleteJob(jobId).subscribe(
      () => {
        console.log('Solicitare stearsa cu succes.');

        this.router
          .navigateByUrl('/', { skipLocationChange: true })
          .then(() => {
            this.router.navigate(['/my-jobs']);
          });
      },
      (error) => {
        console.error('Eroare stergere solicitare:', error);
      }
    );
  }
  openBidConfirmationDialog(bidId: number): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: { message: 'Doriti sa renuntati la aceasta licitatie?' },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.deleteBid(bidId);
      }
    });
  }
  openCreatorJobConfirmationDialog(jobId: number): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: { message: 'Doriti sa anulati aceasta solicitare?' },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.deleteJob(jobId);
      }
    });
  }

  openSpecialistJobConfirmationDialog(jobId: number): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: {
        message:
          'Solicitarea a fost asumata de dumneavoastra si va atrage repercursiuni daca nu o duceti la bun sfarsit. Doriti sa nu mai finalizati aceasta solicitare?',
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        // this.deleteJob(jobId); delete assumption
      }
    });
  }

  fetchJobsCreatedByUser() {
    if (this.loggedInUser && this.loggedInUser.id) {
      this.jobService
        .getJobsCreatedByUserAndInExec(this.loggedInUser.id)
        .subscribe((jobs) => {
          this.jobsCreatedByUser = jobs;
        });
    }
  }

  // fetchJobsCreatedByUserWithBids() {
  //   if (this.loggedInUser && this.loggedInUser.id) {
  //     this.bidService
  //       .getJobsCreatedByUserWithBids(this.loggedInUser.id)
  //       .subscribe((bids: Bid[]) => {
  //         bids.forEach((bid: Bid) => {
  //           if (bid.jobId !== undefined) {
  //             this.jobService.getJobDetails(bid.jobId).subscribe((job: Job) => {
  //               this.bidsWithJobDetails.push({ bid, job });
  //             });
  //           }
  //         });
  //       });
  //   }
  // }

  // fetchJobsCreatedByUserWithBids(): void {
  //   if (this.loggedInUser.id)
  //     this.jobService
  //       .getJobsWithBidsCreatedByUser(this.loggedInUser.id)
  //       .subscribe((data) => {
  //         this.bidsWithJobDetails = data;
  //         console.log('Jobs with bids:', this.bidsWithJobDetails);
  //       });
  // }

  fetchJobsCreatedByUserWithBids(): void {
    if (this.loggedInUser.id) {
      this.jobService
        .getJobsWithBidsCreatedByUser(this.loggedInUser.id)
        .subscribe((data: any[]) => {
          // Parse the data into JSON format
          this.bidsWithJobDetails = data.map(
            ([
              id,
              title,
              description,
              location,
              createdByUser,
              assignedToSpecialist,
              creationDate,
              dueDate,
              lastUpdated,
              currentWorkflowStep,
              bidId,
              jobId,
              specialistId,
              amount,
              status,
              created_at,
              updated_at,
            ]: any) => {
              return {
                job: {
                  id: jobId,
                  title,
                  description,
                  location,
                  createdByUser,
                  assignedToSpecialist,
                  creationDate,
                  dueDate,
                  lastUpdated,
                  currentWorkflowStep,
                },
                bid: {
                  id: bidId,
                  jobId,
                  specialistId,
                  amount,
                  status,
                  created_at,
                  updated_at,
                },
              };
            }
          );
          console.log('Jobs with bids:', this.bidsWithJobDetails);
        });
    }
  }
}
