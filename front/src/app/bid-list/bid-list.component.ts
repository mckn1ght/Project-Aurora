import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BidService } from '../service/bid/bid.service';
import { Bid } from '../models/bid';
import { Job } from '../models/job';
import { JobService } from '../service/job/job-service.service';
import { UserService } from '../service/user/user.service';
import { forkJoin, map, of, switchMap } from 'rxjs';
import { User } from '../models/user';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';

@Component({
  selector: 'app-bid-list',
  templateUrl: './bid-list.component.html',
  styleUrls: ['./bid-list.component.css'],
})
export class BidListComponent implements OnInit {
  selectedJobId!: number;
  bids!: Bid[];
  jobBid: { bid: Bid; job: Job }[] = [];
  userDetails: User[] = [];
  selectedJob!: Job;
  rejectStatus: string = 'rejected';
  acceptedStatus: string = 'accepted';
  activeBidCount = 0;

  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  constructor(
    private route: ActivatedRoute,
    private bidService: BidService,
    private jobService: JobService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const jobIdParam = params.get('id');
      if (jobIdParam !== null) {
        this.route.paramMap.subscribe((params) => {
          const jobIdParam = params.get('id');
          if (jobIdParam !== null) {
            this.selectedJobId = parseInt(jobIdParam);
            this.getSelectedJob(parseInt(jobIdParam)).subscribe((job: Job) => {
              this.selectedJob = job;
              this.getJobsCreatedByUserWithBids();
              this.getActiveBidsByJobId();
            });
          } else {
            console.error('Job ID parameter is null.');
          }
        });
      }
    });
  }

  loadBids() {
    this.bidService.getActiveBidsByJob(this.selectedJobId).subscribe((bids) => {
      this.bids = bids;
      this.loadUserDetails();
    });
  }

  getSelectedJob(jobId: number) {
    return this.jobService.getJobDetails(jobId).pipe(
      map((jobDetails: any) => {
        const job: Job = {
          id: jobDetails.id,
          title: jobDetails.title,
          description: jobDetails.description,
          location: jobDetails.location,
          createdByUser: jobDetails.createdByUser,
          assignedToSpecialist: jobDetails.assignedToSpecialist,
          creationDate: jobDetails.creationDate,
          lastUpdated: jobDetails.lastUpdated,
          currentWorkflowStep: jobDetails.currentWorkflowStep,
        };
        return job;
      })
    );
  }

  getJobsCreatedByUserWithBids() {
    this.bidService
      .getJobsCreatedByUserWithBids(this.selectedJobId)
      .subscribe((bids) => {
        this.bids = bids as Bid[];
        const userDetailsPromises = this.bids.map((bid) =>
          this.userService.getSpecialistName(bid.specialistId).toPromise()
        );
        Promise.all(userDetailsPromises).then((userDetails) => {
          this.userDetails = userDetails as User[];
          console.log('user details:', this.userDetails);
        });
      });
  }

  loadUserDetails() {
    const userRequests = this.bids.map((bid) =>
      this.userService.getSpecialistName(bid.specialistId)
    );

    forkJoin(userRequests).subscribe((users) => {
      this.userDetails = users;
    });
  }

  // acceptBid(bidId: number) {
  //   this.bidService
  //     .acceptBid(this.selectedJobId, bidId)
  //     .pipe(
  //       // Once the bid is accepted, switch to setting the workflow status
  //       switchMap(() =>
  //         this.jobService.setWorkflowStatus(this.selectedJobId, 'Preluat')
  //       )
  //     )
  //     .subscribe(
  //       () => {
  //         // After setting the workflow status, navigate to my-jobs
  //         this.router.navigate(['/my-jobs']);
  //       },
  //       (error) => {
  //         // Handle errors
  //         console.error('Error accepting bid:', error);
  //       }
  //     );
  // }

  acceptBid(bidId: number, bidSpecialistId: number) {
    this.bidService
      .acceptBid(this.selectedJobId, bidId)
      .pipe(
        switchMap(() =>
          this.jobService.setWorkflowStatus(this.selectedJobId, 'Preluat')
        ),
        switchMap(() =>
          this.jobService.assignJobToSpecialist(
            this.selectedJobId,
            bidSpecialistId
          )
        )
      )
      .subscribe(
        () => {
          // Success: Bid accepted and workflow status set
          console.log('Bid accepted and workflow status set successfully.');
          // Navigate to my-jobs
          this.router.navigate(['/my-jobs']);
        },
        (error) => {
          // Error handling
          console.error('Error accepting bid:', error);
        }
      );
  }

  rejectBid(bidId: number) {
    this.bidService.setBidStatus(bidId, this.rejectStatus).subscribe(
      (response) => {
        console.log('Bid status updated successfully:', response);
        this.getActiveBidsByJobId();
        this.refreshPage();
      },
      (error) => {
        console.error('Error updating bid status:', error);
      }
    );
  }

  getActiveBidsByJobId() {
    this.bidService.getActiveBidCountByJobId(this.selectedJob.id).subscribe(
      (count) => {
        this.activeBidCount = count;
        console.log('ACTIVE BIDS: ' + this.activeBidCount);
        this.changeJobStatus();
      },
      (error) => {
        console.error('Error fetching active bid count:', error);
      }
    );
  }

  changeJobStatus() {
    if (this.activeBidCount == 0) {
      this.jobService.setWorkflowStatus(this.selectedJob.id, 'New').subscribe(
        () => {
          console.log('Workflow status updated successfully.');
          this.router.navigate(['/my-jobs']);
        },
        (error) => {
          console.error('Error updating workflow status:', error);
        }
      );
    } else {
      // this.refreshPage();
    }
  }

  refreshPage(): void {
    window.location.reload();
  }

  navigateToProfile(specialistId: number) {}
}
