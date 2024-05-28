import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JobService } from '../service/job/job-service.service';
import { UserService } from '../service/user/user.service';
import { Job } from '../models/job';
import { User } from '../models/user';
import { Comment } from '../models/comment';
import { AuthService } from '../service/auth/auth.service';
import { CommentService } from '../service/comment/comment.service';

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

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private jobService: JobService,
    private userService: UserService,
    private authService: AuthService,
    private commentService: CommentService
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
        });
      } else {
        console.warn('No job ID found in route parameters.');
        this.router.navigate(['/landing']);
      }
    });
  }

  fetchJobDetails(jobId: number) {
    if (this.loggedInUser) {
      this.jobService.getJobDetails(jobId).subscribe(
        (job: Job) => {
          this.job = job;
          console.log(`Fetched job details: ${JSON.stringify(this.job)}`);
          if (
            this.loggedInUser?.id === this.job?.createdByUser ||
            this.loggedInUser?.specialistId === this.job?.assignedToSpecialist
          ) {
            console.log('User has permission to view the job.');
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
        job: this.job,
        user: this.loggedInUser,
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
}
