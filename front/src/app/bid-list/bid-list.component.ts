import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BidService } from '../service/bid/bid.service';
import { Bid } from '../models/bid';
import { Job } from '../models/job';
import { JobService } from '../service/job/job-service.service';
import { UserService } from '../service/user/user.service';
import { forkJoin, map, of } from 'rxjs';
import { User } from '../models/user';

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

  acceptBid(bidId: number) {
    //daca e acceptata , de pus toate bidurile jobului in rejected.
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
    console.log('@@@@@@@@@OUTSIDE IF@@@@@');
    if (this.activeBidCount == 0) {
      console.log('@@@@@@@@@IN IF@@@@@');
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
      console.log('@@@@@@ Else');
      // this.refreshPage();
    }
  }

  refreshPage(): void {
    window.location.reload();
  }

  navigateToProfile(specialistId: number) {}
}
