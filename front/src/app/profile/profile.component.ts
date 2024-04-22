import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { AuthService } from '../service/auth/auth.service';
import { JobService } from '../service/job/job-service.service';
import { Job } from '../models/job';
import { UserService } from '../service/user/user.service';
import { Observable, forkJoin } from 'rxjs';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  loggedInUser: User | null = null;
  createdDate: string = '';
  jobsCreatedByUser: Job[] = [];
  counterTotalJobs: number = 0;
  editMode: boolean = false;
  newEmail: string = '';
  confirmEmail: string = '';
  newPhoneNumber: string = '';
  error: string = 'Va rog verificati informatiile introduse';
  showError: boolean = false;
  // editForm: FormGroup;

  constructor(
    private authService: AuthService,
    private jobService: JobService,
    private userService: UserService,
    private formBuilder: FormBuilder
  ) {
    // this.editForm = this.formBuilder.group({
    //   // password: [
    //   //   '',
    //   //   [
    //   //     Validators.required,
    //   //     Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/),
    //   //   ],
    //   // ],
    //   // confirmPassword: ['', Validators.required],
    //   email: [
    //     '',
    //     [
    //       Validators.required,
    //       Validators.pattern('^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$'),
    //     ],
    //   ],
    //   phoneNumber: [
    //     '',
    //     [Validators.required],
    //     [
    //       Validators.pattern(
    //         '[+]?[0-9]{1,3}?[-\\s\\.]?[(]?[0-9]{1,3}[)]?[-\\s\\.]?[0-9]{1,4}[-\\s\\.]?[0-9]{1,4}[-\\s\\.]?[0-9]{1,9}'
    //       ),
    //     ],
    //   ],
    // });
  }

  ngOnInit(): void {
    this.authService.getLoggedInUser().subscribe((user) => {
      console.log(user);
      this.loggedInUser = user;
      this.createdDate = this.formatEuropeanDate(
        String(this.loggedInUser?.created)
      );
      if (this.loggedInUser) {
        const userId = this.loggedInUser.id;
      }
    });
    this.fetchJobsCreatedByUser();
    this.countJobsCreatedByUser();
  }

  formatEuropeanDate(isoDateString: string): string {
    if (!isoDateString) {
      return '';
    }

    const date = new Date(isoDateString);
    const day = date.getUTCDate().toString().padStart(2, '0');
    const month = (date.getUTCMonth() + 1).toString().padStart(2, '0'); // getUTCMonth returns 0-11
    const year = date.getUTCFullYear();
    const hours = date.getUTCHours().toString().padStart(2, '0');
    const minutes = date.getUTCMinutes().toString().padStart(2, '0');

    return `${day}/${month}/${year}, ${hours}:${minutes}`;
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
  countJobsCreatedByUser() {
    if (this.loggedInUser && this.loggedInUser.id) {
      this.jobService
        .countJobsCreatedByUser(this.loggedInUser.id)
        .subscribe((jobs: number) => {
          this.counterTotalJobs = jobs;
        });
    }
  }

  toggleEditMode() {
    this.editMode = !this.editMode;
  }

  saveProfile() {
    if (
      this.loggedInUser &&
      this.loggedInUser.id !== undefined &&
      this.newEmail == this.confirmEmail
    ) {
      const { id, email, phoneNumber } = this.loggedInUser;
      const updateUserObservable: Observable<any>[] = [];

      // Update email
      updateUserObservable.push(
        this.userService.updateEmail(this.loggedInUser.id, email!)
      );

      // Update phone number
      updateUserObservable.push(
        this.userService.updatePhoneNumber(this.loggedInUser.id, phoneNumber!)
      );

      // Subscribe to all update observables
      forkJoin(updateUserObservable).subscribe(
        () => {
          console.log('Profile updated successfully.');
          this.toggleEditMode(); // Turn off edit mode after save
        },
        (error) => {
          console.error('Failed to update profile.', error);
        }
      );
    } else {
      this.showError = true;
    }
  }

  checkValidity(): boolean {
    if (this.newEmail == this.confirmEmail && this.newPhoneNumber != '') {
      this.showError = false;
      return false;
    } else {
      return true;
    }
  }
}
