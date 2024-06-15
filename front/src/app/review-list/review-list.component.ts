import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReviewService } from '../service/review/review.service';
import { UserService } from '../service/user/user.service';
import { JobService } from '../service/job/job-service.service';
import { Review } from '../models/review';
import { forkJoin } from 'rxjs';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css'],
})
export class ReviewListComponent implements OnInit {
  specialistId!: number;
  reviews: Review[] = [];
  specialistName: string = '';
  reviewDetails: any[] = [];
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  constructor(
    private route: ActivatedRoute,
    private reviewService: ReviewService,
    private userService: UserService,
    private jobService: JobService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const specialistIdParam = params.get('specialistId');
      if (specialistIdParam !== null) {
        this.specialistId = parseInt(specialistIdParam);
        this.loadSpecialistName(this.specialistId);
        this.loadReviews(this.specialistId);
      } else {
        console.error('Specialist ID parameter is null.');
      }
    });
  }

  loadSpecialistName(specialistId: number): void {
    this.userService.getSpecialistName(specialistId).subscribe(
      (user) => {
        this.specialistName = `${user.firstName} ${user.lastName}`;
        console.log('Specialist Name:', this.specialistName); // Log the specialist name
      },
      (error) => {
        console.error('Error fetching specialist name:', error);
      }
    );
  }

  loadReviews(specialistId: number): void {
    this.reviewService.getAllByReceivedBySpecialist(specialistId).subscribe(
      (reviews) => {
        this.reviews = reviews;
        console.log('Fetched Reviews:', this.reviews); // Log fetched reviews
        this.loadReviewDetails();
      },
      (error) => {
        console.error('Error fetching reviews:', error);
      }
    );
  }

  loadReviewDetails(): void {
    const userRequests = this.reviews.map((review) =>
      this.userService.getById(review.givenByUser)
    );

    const jobRequests = this.reviews.map((review) =>
      this.jobService.getJobDetails(review.jobId!)
    );

    forkJoin([...userRequests, ...jobRequests]).subscribe(
      (results) => {
        const userResults = results.slice(0, this.reviews.length);
        const jobResults = results.slice(this.reviews.length);

        console.log('User Results:', userResults); // Log user results
        console.log('Job Results:', jobResults); // Log job results

        this.reviewDetails = this.reviews.map((review, index) => ({
          review,
          givenByUser: userResults[index],
          job: jobResults[index],
        }));

        console.log('Review Details:', this.reviewDetails); // Log combined review details
      },
      (error) => {
        console.error('Error fetching review details:', error);
      }
    );
  }
}
