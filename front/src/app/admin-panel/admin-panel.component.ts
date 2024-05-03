import { Component, OnInit } from '@angular/core';
import { DataService } from '../service/data/data.service';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { ConfirmationService } from 'primeng/api';
import { UserService } from '../service/user/user.service';
import { User } from '../models/user';
import { AuthService } from '../service/auth/auth.service';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css'],
})
export class AdminPanelComponent implements OnInit {
  // data: Record<string, any[]> = {};
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
  newCredit: number = 0;
  userId: number = 0;
  loggedInUser: User | null = null;

  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  constructor(
    private dataService: DataService,
    private confirmationService: ConfirmationService,
    private userService: UserService,
    private authService: AuthService
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
          this;
          // You might want to refresh the data or indicate success in the UI
        },
        error: (error) => {
          console.error('Failed to update credit', error);
          // Handle errors here, such as displaying a user-facing message
        },
      });
    }
  }
  enableEdit(item: any): void {
    item.editing = true; // Enables the edit mode
  }

  // saveCredit(item: any): void {
  //   this.userService.updateCredit(item.id, item.credit).subscribe({
  //     next: () => {
  //       console.log('Credit updated successfully');
  //       item.editing = false; // Disable editing mode on successful update
  //       this.fetchUserData();
  //       this.refreshUser();
  //     },
  //     error: (error) => console.error('Error updating credit:', error),
  //   });
  // }

  saveCredit(item: any): void {
    this.userService.updateCredit(item.id, item.credit).subscribe({
      next: () => {
        console.log('Credit updated successfully');
        item.editing = false; // Disable editing mode on successful update
        this.authService.refreshUserData(); // Refresh the user data from the server
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

  refreshUser() {
    this.authService.getLoggedInUser().subscribe((user) => {
      console.log(user);
      this.loggedInUser = user;
      if (this.loggedInUser) {
        const userId = this.loggedInUser.id;
      }
    });
  }

  saveUser(item: any): void {
    // Example of handling response after updating user details
    this.userService.updateUser(item).subscribe({
      next: (updatedUser) => {
        console.log('User updated successfully');
        this.authService.setLoggedInUser(updatedUser); // Updated user with potentially new username
        this.authService.refreshUserData(); // Optionally refresh if needed
        item.editing = false;
      },
      error: (error) => console.error('Error updating user:', error),
    });
  }
}
