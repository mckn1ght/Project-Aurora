import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { AuthService } from '../service/auth/auth.service';
import { JobService } from '../service/job/job-service.service';
import { Job } from '../models/job';
import { UserService } from '../service/user/user.service';
import { Observable, firstValueFrom, forkJoin } from 'rxjs';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ResourceService } from '../service/resource/resource.service';
import { Resource } from '../models/resource';
// import { Resource } from '../../../../back/cloudComputing/uploads/avatars/';

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
  avatarLocation: string = '';
  resource: Resource = new Resource();
  selectedFile: File | null = null;
  // editForm: FormGroup;

  constructor(
    private authService: AuthService,
    private jobService: JobService,
    private userService: UserService,
    private formBuilder: FormBuilder,
    private router: Router,
    private resourceService: ResourceService
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
    this.fetchAvatarLocation();
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

  async saveProfile() {
    if (
      this.loggedInUser &&
      this.loggedInUser.id !== undefined &&
      this.newEmail === this.confirmEmail
    ) {
      try {
        // Update email
        await firstValueFrom(
          this.userService.updateEmail(this.loggedInUser.id, this.newEmail)
        );
        // Update phone number
        await firstValueFrom(
          this.userService.updatePhoneNumber(
            this.loggedInUser.id,
            this.newPhoneNumber
          )
        );

        console.log('Profile updated successfully.');
        this.toggleEditMode();
      } catch (error) {
        console.error('Failed to update profile.', error);
        this.showError = true;
      }
    } else {
      this.showError = true;
    }
    this.loggedInUser!.email = this.newEmail;
    this.loggedInUser!.phoneNumber = this.newPhoneNumber;
  }

  checkValidity(): boolean {
    if (this.newEmail == this.confirmEmail && this.newPhoneNumber != '') {
      this.showError = false;
      return false;
    } else {
      return true;
    }
  }

  admin() {
    this.router.navigate(['/admin']);
  }

  fetchAvatarLocation() {
    this.resourceService
      .getByIdAndType(this.loggedInUser?.id!, 'avatar')
      .subscribe(
        (resource: Resource) => {
          this.resource = resource;
        },
        (error) => {
          console.error('Error fetching avatar location:', error);
        }
      );
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
    if (this.selectedFile) {
      this.uploadAvatar();
    }
  }

  uploadAvatar(): void {
    if (!this.selectedFile) return;

    const formData = new FormData();
    formData.append('avatar', this.selectedFile, this.selectedFile.name);

    // Assuming you have a method in your service to handle file uploads
    this.resourceService
      .uploadAvatar(this.loggedInUser!.id!, formData)
      .subscribe({
        next: (response) => {
          console.log('Avatar updated successfully:', response);
          this.fetchAvatarLocation(); // Refresh the displayed avatar
        },
        error: (error) => console.error('Error updating avatar:', error),
      });
  }
}
