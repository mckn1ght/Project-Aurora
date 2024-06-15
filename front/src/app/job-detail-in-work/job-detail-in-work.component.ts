import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JobService } from '../service/job/job-service.service';
import { UserService } from '../service/user/user.service';
import { Job } from '../models/job';
import { User } from '../models/user';
import { Comment } from '../models/comment';
import { AuthService } from '../service/auth/auth.service';
import { CommentService } from '../service/comment/comment.service';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ReviewPopupComponent } from '../review-popup/review-popup.component';
import { ReviewService } from '../service/review/review.service';

@Component({
  selector: 'app-job-detail-in-work',
  templateUrl: './job-detail-in-work.component.html',
  styleUrls: ['./job-detail-in-work.component.css'],
})
export class JobDetailInWorkComponent implements OnInit {
  job: Job | undefined;
  comments: Comment[] = [];
  newCommentContent: string = '';
  loggedInUser: User | null = null;
  jobPictures: string[] = [];
  specialistName: string = '';
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  editCommentContent: string = '';
  editCommentId: number | null = null;

  editJobMode: boolean = false;
  editJobTitle: string = '';
  editJobDescription: string = '';
  selectedFiles: File[] = [];

  markedForValidation: boolean = false;
  markedAsValidated: boolean = false;
  canceledJob: boolean = false;

  creatortName: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private jobService: JobService,
    private userService: UserService,
    private authService: AuthService,
    private commentService: CommentService,
    private reviewService: ReviewService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const jobId = +params['id'];
      if (jobId) {
        console.log(`Job ID from route params: ${jobId}`);
        this.authService.getLoggedInUser().subscribe((loggedInUser) => {
          this.loggedInUser = loggedInUser;
          console.log(`Logged-in user: ${JSON.stringify(this.loggedInUser)}`);
          this.fetchJobDetails(jobId);
          this.loadJobPictures(jobId);
          this.loadComments(jobId);
        });
      } else {
        console.warn('No job ID found in route parameters.');
        this.router.navigate(['/landing']);
      }
    });
  }

  fetchJobDetails(jobId: number) {
    this.canceledJob = false;
    if (this.loggedInUser) {
      this.jobService.getJobDetails(jobId).subscribe(
        (job: Job) => {
          this.job = job;
          if (this.job.currentWorkflowStep == 'Canceled') {
            this.canceledJob = true;
            this.markedAsValidated = true;
            this.markedForValidation = true;
          } else if (this.job.currentWorkflowStep == 'Preluat') {
            this.markedAsValidated = false;
            this.markedForValidation = false;
          } else if (this.job.currentWorkflowStep == 'Waiting for validation') {
            this.markedAsValidated = false;
            this.markedForValidation = true;
          }
          console.log(`Fetched job details: ${JSON.stringify(this.job)}`);
          if (
            this.loggedInUser?.id === this.job?.createdByUser ||
            this.loggedInUser?.specialistId === this.job?.assignedToSpecialist
          ) {
            console.log('User has permission to view the job.');
            this.loadSpecialistName(this.job.assignedToSpecialist);
            this.loadCreatortName(this.job.createdByUser);
          } else {
            console.warn('User does not have permission to view the job.');
            this.router.navigate(['/landing']);
          }
        },
        (error) => {
          console.error('Error fetching job details:', error);
          this.router.navigate(['/landing']);
        }
      );
    } else {
      console.error('No logged-in user found.');
      this.router.navigate(['/landing']);
    }
  }

  loadComments(jobId: number) {
    this.commentService.getCommentsByJobId(jobId).subscribe((comments) => {
      this.comments = comments;
    });
  }

  addComment() {
    if (this.loggedInUser && this.job) {
      const commentPayload = {
        jobId: this.job.id,
        userId: this.loggedInUser.id,
        content: this.newCommentContent,
      };
      this.commentService.addComment(commentPayload).subscribe(
        (comment) => {
          this.comments.push(comment);
          this.newCommentContent = '';
        },
        (error) => {
          console.error('Error adding comment:', error);
        }
      );
    }
  }

  loadJobPictures(jobId: number) {
    this.jobService.getJobPictures(jobId).subscribe(
      (pictures) => {
        console.log(pictures); // Inspect the structure of the returned data
        this.jobPictures = pictures.map((pic: any) => {
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
  }

  openModal(src: string): void {
    const modal = document.getElementById('myModal') as HTMLElement;
    const modalImg = document.getElementById('img01') as HTMLImageElement;
    const captionText = document.getElementById('caption') as HTMLElement;

    modal.style.display = 'block';
    modalImg.src = src;
    captionText.innerHTML = src;

    const span = document.getElementsByClassName('close')[0] as HTMLElement;
    span.onclick = () => {
      modal.style.display = 'none';
    };
  }

  closeModal(event: Event): void {
    const modal = document.getElementById('myModal') as HTMLElement;
    if (event.target === modal) {
      modal.style.display = 'none';
    }
  }

  loadSpecialistName(specialistId: number | undefined) {
    if (specialistId) {
      this.userService.getSpecialistName(specialistId).subscribe(
        (user: User) => {
          this.specialistName = `${user.firstName} ${user.lastName}`;
          console.log('Specialist name:', this.specialistName);
        },
        (error) => {
          console.error('Error fetching specialist name:', error);
        }
      );
    }
  }

  loadCreatortName(userId: number | undefined) {
    if (userId) {
      this.userService.getUserFirstNameLastName(userId).subscribe(
        (user: User) => {
          this.creatortName = `${user.firstName} ${user.lastName}`;
          console.log('User name:', this.creatortName);
        },
        (error) => {
          console.error('Error fetching user full name:', error);
        }
      );
    }
  }

  deleteComment(commentId: number) {
    if (confirm('Doriti sa stergeti acest comentariu?')) {
      this.commentService.deleteComment(commentId).subscribe(
        () => {
          this.comments = this.comments.filter(
            (comment) => comment.id !== commentId
          );
        },
        (error) => {
          console.error('Error deleting comment:', error);
        }
      );
    }
  }

  editComment(comment: Comment) {
    this.editCommentId = comment.id;
    this.editCommentContent = comment.content;
  }

  updateComment() {
    if (this.editCommentId && this.editCommentContent.trim() !== '') {
      this.commentService
        .updateComment(this.editCommentId, this.editCommentContent)
        .subscribe(
          (updatedComment) => {
            const index = this.comments.findIndex(
              (comment) => comment.id === this.editCommentId
            );
            if (index !== -1) {
              this.comments[index] = updatedComment;
            }
            this.editCommentId = null;
            this.editCommentContent = '';
          },
          (error) => {
            console.error('Error updating comment:', error);
          }
        );
    }
  }

  updateJobDetails() {
    if (this.job) {
      const updatedJob = {
        ...this.job,
        title: this.editJobTitle,
        description: this.editJobDescription,
      };
      this.jobService.updateJob(this.job.id, updatedJob).subscribe(
        (job) => {
          this.job = job;
          this.editJobMode = false;
        },
        (error) => {
          console.error('Error updating job details:', error);
        }
      );
    }
  }

  isJobCreator(): boolean {
    return this.loggedInUser?.id === this.job?.createdByUser;
  }

  onFileSelected(event: any): void {
    this.selectedFiles = Array.from(event.target.files);
  }

  uploadPictures() {
    if (this.job && this.selectedFiles.length > 0) {
      this.jobService
        .uploadJobPictures(this.job.id, this.selectedFiles)
        .subscribe(
          (event) => {
            if (event.type === HttpEventType.UploadProgress) {
              const progress = Math.round((100 * event.loaded) / event.total);
              console.log(`Upload is ${progress}% complete.`);
            } else if (event instanceof HttpResponse) {
              console.log('Upload complete:', event.body);
              if (
                typeof event.body === 'string' &&
                event.body.includes('Pictures uploaded successfully')
              ) {
                this.loadJobPictures(this.job!.id); // Refresh the pictures
                window.location.reload();
              }
            }
          },
          (error) => {
            console.error('Error uploading pictures:', error);
            window.location.reload();
          }
        );
    }
  }

  deletePicture(pictureUrl: string, index: number) {
    this.jobService.deleteJobPicture(pictureUrl).subscribe(
      () => {
        // Remove the picture from the array after deletion
        this.jobPictures.splice(index, 1);
        window.location.reload();
      },
      (error) => {
        console.error('Error deleting picture:', error);
        window.location.reload();
      }
    );
  }

  markForValidation(jobId: number) {
    console.log('markForValidation called');
    this.jobService
      .setWorkflowStatus(jobId, 'Waiting for validation')
      .subscribe(
        (response) => {
          console.log(response.message);
          this.markedForValidation = true;
          this.fetchJobDetails(this.job!.id);
          window.location.reload();
        },
        (error) => {
          console.error('Error setting workflow status:', error);
          window.location.reload();
        }
      );
  }

  // markAsValidated(jobId: number) {
  //   console.log('markAsValidated called');
  //   this.jobService.setWorkflowStatus(jobId, 'Validated').subscribe(
  //     (response) => {
  //       console.log(response.message);
  //       this.markedAsValidated = true;
  //       this.fetchJobDetails(this.job!.id);
  //       window.location.reload();
  //     },
  //     (error) => {
  //       console.error('Error setting workflow status:', error);
  //       window.location.reload();
  //     }
  //   );
  // }

  markAsValidated(jobId: number) {
    console.log('markAsValidated called');

    const dialogRef = this.dialog.open(ReviewPopupComponent, {
      width: '400px',
      data: { jobId: jobId },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        const reviewPayload = {
          givenByUser: this.loggedInUser!.id,
          receivedBySpecialist: this.job!.assignedToSpecialist,
          stars: result.stars,
          title: result.title,
          description: result.description,
          jobId: jobId,
        };

        this.reviewService.addReview(reviewPayload).subscribe(
          () => {
            console.log('Review submitted successfully.');
            this.jobService.setWorkflowStatus(jobId, 'Validated').subscribe(
              (response) => {
                console.log(response.message);
                this.markedAsValidated = true;
                this.fetchJobDetails(this.job!.id);
                window.location.reload();
              },
              (error) => {
                console.error('Error setting workflow status:', error);
                window.location.reload();
              }
            );
          },
          (error) => {
            console.error('Error submitting review:', error);
          }
        );
      }
    });
  }

  cancelJob(jobId: number) {
    console.log('cancelJob called');
    this.jobService.setWorkflowStatus(jobId, 'Canceled').subscribe(
      (response) => {
        console.log(response.message);
        this.fetchJobDetails(this.job!.id);
        window.location.reload();
        // Additional logic if needed
      },
      (error) => {
        console.error('Error setting workflow status:', error);
        window.location.reload();
      }
    );
  }

  returnToSpecialist(jobId: number) {
    this.jobService.setWorkflowStatus(jobId, 'Preluat').subscribe(
      (response) => {
        console.log(response.message);
        this.markedForValidation = false;
        this.markedAsValidated = false;
        this.fetchJobDetails(this.job!.id);
        window.location.reload();
      },
      (error) => {
        console.error('Error setting workflow status:', error);
        window.location.reload();
      }
    );
  }
}
