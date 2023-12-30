// new-job.component.ts

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JobService } from '../service/job/job-service.service';
import { ExpertiseAreaService } from '../service/expertiseArea/expertise-area.service';
import { AuthService } from '../service/auth/auth.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-new-job',
  templateUrl: './new-job.component.html',
  styleUrls: ['./new-job.component.css'],
})
export class NewJobComponent {
  loggedInUser: User | null = null;
  newJob: any = {
    title: '',
    description: '',
    location: '',
    createdByUser: '',
    assignedToSpecialist: 1,
    creationDate: '',
    dueDate: '',
    lastUpdated: '',
    currentWorkflowStep: 'New',
  };

  expertiseAreasList: any[] = [];

  constructor(
    private jobService: JobService,
    private router: Router,
    private expertiseAreaService: ExpertiseAreaService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.loadExpertiseAreas();
    this.authService.getLoggedInUser().subscribe((user) => {
      this.loggedInUser = user;
      if (this.loggedInUser) {
        const userId = this.loggedInUser.id;
        console.log('Logged-in user id:', userId);
      }
    });
  }

  addJob() {
    console.log('Add job!');
    this.newJob.creationDate = new Date();
    this.newJob.lastUpdated = new Date();
    this.newJob.createdByUser = this.loggedInUser?.id;
    console.log(
      'this id is : ' +
        this.loggedInUser?.id +
        ' and the username: ' +
        this.loggedInUser?.username
    );
    this.jobService.addJob(this.newJob).subscribe(
      (response) => {
        console.log('Job added successfully:', response);
        this.router.navigate(['/jobs']);
      },
      (error) => {
        console.error('Error adding job:', error);
      }
    );
  }

  loadExpertiseAreas() {
    this.expertiseAreaService.getAllExpertiseAreas().subscribe(
      (data) => {
        this.expertiseAreasList = data;
      },
      (error) => {
        console.error('Error fetching expertise areas:', error);
      }
    );
  }
}
