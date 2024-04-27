import { Component, OnInit } from '@angular/core';
import { DataService } from '../service/data/data.service';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { ConfirmationService } from 'primeng/api';

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
  };
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  constructor(
    private dataService: DataService,
    private confirmationService: ConfirmationService
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
}
