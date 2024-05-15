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
import { BidService } from '../service/bid/bid.service';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationService } from 'primeng/api';
// import { BidPopupComponent } from '../bid-popup/bid-popup.component';

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
  showBidAmountPopUp: boolean = false;
  expertiseAreas: string[] = [];

  popupVisible: boolean = false;
  bidAmount: number = 0;

  newBid: any = {
    jobId: '',
    specialistId: '',
    amount: 0,
    status: 'active',
  };

  activeBidCount: number = 0;
  toastMessage: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private jobService: JobService,
    private authService: AuthService,
    private userService: UserService,
    private bidService: BidService,
    private router: Router,
    private toastr: ToastrService,
    public dialog: MatDialog,
    private confirmationService: ConfirmationService
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      const id = +params['id'];
      if (id) {
        this.fetchJobDetails(id);
        this.loadExpertiseAreas(id);
      } else {
        console.error('Invalid job ID:', id);
      }
    });
    this.getActiveBids();
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

  addBid() {
    if (this.activeBidCount < 5) {
      this.showBidAmountPopUp = true;
      if (this.job) {
        this.newBid.jobId = this.job.id;
        this.newBid.specialistId = this.loggedInUser?.specialistId;
        this.newBid.amount = this.bidAmount;
        this.newBid.status = 'active';

        this.bidService.addBid(this.newBid).subscribe(
          (response) => {
            console.log('Bid added successfully.', response);
            this.jobService
              .setWorkflowStatus(this.job?.id, 'In Licitatie')
              .subscribe(
                () => {
                  console.log('Workflow status updated successfully.');
                  this.router.navigate(['/my-jobs']);
                },
                (error) => {
                  console.error('Error updating workflow status:', error);
                }
              );
          },
          (error) => {
            console.error('Error adding bid:', error);
          }
        );
      } else {
        this.displayToast('Ati atins numarul maxim de licitatii concomitente.');
      }
      this.closePopup();
    }
  }

  getActiveBids() {
    this.authService.getLoggedInUser().subscribe((loggedInUser) => {
      if (loggedInUser && loggedInUser.specialistId !== undefined) {
        this.bidService.getActiveBids(loggedInUser.specialistId).subscribe(
          (count) => {
            this.activeBidCount = count;
          },
          (error) => {
            console.error('Error fetching active bid count:', error);
          }
        );
      }
    });
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

  displayToast(message: string) {
    this.toastr.success(message);
  }

  openPopup() {
    if (this.activeBidCount < 5) {
      this.popupVisible = true;
    } else {
      this.confirmationService.confirm({
        message: 'Ati atins numarul maxim de 5 licitatii concomitente.',
        header: 'Limita de licitatii stinsa',
        icon: 'pi pi-exclamation-triangle',
        acceptLabel: 'OK',
        rejectVisible: false,
        accept: () => {},
      });
    }
  }

  closePopup() {
    this.popupVisible = false;
  }

  loadExpertiseAreas(id: number) {
    console.log(this.job);
    console.log(this.job?.id);
    this.jobService.getExpertiseAreaNames(id).subscribe({
      next: (areas) => {
        this.expertiseAreas = areas;
      },
      error: (error) => console.error('Error fetching expertise areas:', error),
    });
  }

  updateCredit(item: any): void {
    this.userService
      .updateCredit(item.id, item.credit - this.bidAmount)
      .subscribe({
        next: () => {
          console.log('Credit updated successfully');
          this.authService.refreshUserData(); // Refresh the user data from the server
        },
        error: (error) => console.error('Error updating credit:', error),
      });
  }
}
