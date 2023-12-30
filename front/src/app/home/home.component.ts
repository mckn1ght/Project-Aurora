import { Component } from '@angular/core';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { ExpertiseAreaService } from '../service/expertiseArea/expertise-area.service';
import { Router } from '@angular/router';
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

  constructor(
    private expertiseAreaService: ExpertiseAreaService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadExpertiseAreas();
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
}
