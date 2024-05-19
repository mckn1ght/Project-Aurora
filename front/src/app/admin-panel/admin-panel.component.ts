import { Component, OnInit } from '@angular/core';
import { DataService } from '../service/data/data.service';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { ConfirmationService } from 'primeng/api';
import { UserService } from '../service/user/user.service';
import { User } from '../models/user';
import { AuthService } from '../service/auth/auth.service';
import { JobService } from '../service/job/job-service.service';
import { Job } from '../models/job';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css'],
})
export class AdminPanelComponent implements OnInit {
  data: Record<string, any[]> = {
    bid: [],
    expertiseArea: [],
    job: [],
    jobExpertiseArea: [],
    review: [],
    specialist: [],
    specialistExpertiseArea: [],
    user: [],
    resource: [],
  };

  editingStates: Record<string, Set<number>> = {
    bid: new Set<number>(),
    expertiseArea: new Set<number>(),
    job: new Set<number>(),
    jobExpertiseArea: new Set<number>(),
    review: new Set<number>(),
    specialist: new Set<number>(),
    specialistExpertiseArea: new Set<number>(),
    user: new Set<number>(),
    resource: new Set<number>(),
  };

  newCredit: number = 0;
  userId: number = 0;
  loggedInUser: User | null = null;

  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  constructor(
    private dataService: DataService,
    private confirmationService: ConfirmationService,
    private userService: UserService,
    private authService: AuthService,
    private jobService: JobService
  ) {}

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData(): void {
    const fetchers = {
      bid: this.dataService.getAllBids(),
      expertiseArea: this.dataService.getAllExpertiseAreas(),
      job: this.dataService.getAllJobs(),
      jobExpertiseArea: this.dataService.getAllJobExpertiseAreas(),
      review: this.dataService.getAllReviews(),
      specialist: this.dataService.getAllSpecialists(),
      specialistExpertiseArea:
        this.dataService.getAllSpecialistExpertiseAreas(),
      user: this.dataService.getAllUsers(),
      resource: this.dataService.getAllResources(),
    };

    Object.entries(fetchers).forEach(([key, observable]) => {
      observable.subscribe({
        next: (response) => {
          this.data[key] = response;
        },
        error: (error) => {
          console.error(`Failed to fetch data for ${key}:`, error);
        },
      });
    });
  }

  getKeys(obj: any): string[] {
    return Object.keys(obj);
  }

  deleteEntry(table: string, id: number): void {
    this.confirmationService.confirm({
      message: 'Doriti sa stergeti aceasta intrare?',
      header: 'Atentie!',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.dataService.delete(table, id).subscribe({
          next: () => {
            this.data[table] = this.data[table].filter(
              (item) => item.id !== id
            );
            console.log(`${table} entry deleted successfully.`);
          },
          error: (error) => {
            console.error(`Failed to delete ${table} entry:`, error);
          },
        });
      },
    });
  }

  updateUserCredit(user: any): void {
    if (user.id && user.credit) {
      this.userService.updateCredit(user.id, user.credit).subscribe({
        next: () => {
          console.log('Credit updated successfully for user ID:', user.id);
        },
        error: (error) => {
          console.error('Failed to update credit', error);
        },
      });
    }
  }

  saveCredit(item: any): void {
    this.userService.updateCredit(item.id, item.credit).subscribe({
      next: () => {
        console.log('Credit updated successfully');
        this.authService.refreshUserData(); // Refresh the user data from the server
        this.disableEdit('user', item.id);
      },
      error: (error) => console.error('Error updating credit:', error),
    });
  }

  fetchUserData() {
    this.authService.getLoggedInUser().subscribe((user) => {
      console.log(user);
      this.loggedInUser = user;
    });
  }

  saveUser(item: any): void {
    this.userService.updateUser(item).subscribe({
      next: (updatedUser) => {
        console.log('User updated successfully');
        this.authService.setLoggedInUser(updatedUser); // Updated user with potentially new username
        this.authService.refreshUserData(); // Optionally refresh if needed
        this.disableEdit('user', item.id);
      },
      error: (error) => console.error('Error updating user:', error),
    });
  }

  saveJob(job: Job): void {
    this.jobService.updateJob(job.id, job).subscribe({
      next: (updatedJob) => {
        console.log('Job updated successfully:', updatedJob);
        this.disableEdit('job', job.id);
      },
      error: (error) => {
        console.error('Error updating job:', error);
      },
    });
  }

  disableEdit(table: string, itemId: number): void {
    this.editingStates[table].delete(itemId);
  }

  enableEdit(table: string, itemId: number): void {
    this.editingStates[table].add(itemId);
  }

  isEditing(table: string, itemId: number): boolean {
    return this.editingStates[table].has(itemId);
  }
}
