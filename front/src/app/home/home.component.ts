import { Component } from '@angular/core';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { ExpertiseAreaService } from '../service/expertiseArea/expertise-area.service';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { AuthService } from '../service/auth/auth.service';
import { ResourceService } from '../service/resource/resource.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  expertiseAreasList: any[] = [];
  searchInput: string = '';
  loggedInUser: User | null = null;

  constructor(
    private expertiseAreaService: ExpertiseAreaService,
    private router: Router,
    private authService: AuthService,
    private resourceService: ResourceService
  ) {}

  ngOnInit() {
    this.loadExpertiseAreas();

    this.refreshLoggedInUser();
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

  navigateToNewJob() {
    this.router.navigate(['/new-job']);
  }

  navigateToJobSearch() {
    this.router.navigate(['/job-list'], {
      queryParams: { searchInput: this.searchInput },
    });
  }

  createNewSpecialist() {
    this.router.navigate(['/newSpecialist']);
  }

  refreshLoggedInUser() {
    this.authService.getLoggedInUser().subscribe((user) => {
      this.loggedInUser = user;
    });
  }
}
