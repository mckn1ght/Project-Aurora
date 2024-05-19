import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './landing/landing.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { NewJobComponent } from './new-job/new-job.component';
import { JobListComponent } from './job-list/job-list.component';
import { JobDetailsComponent } from './job-details/job-details.component';
import { PasswordResetComponent } from './password-reset/password-reset.component';
import { MyJobsComponent } from './my-jobs/my-jobs.component';
import { BidListComponent } from './bid-list/bid-list.component';
import { ProfileComponent } from './profile/profile.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { NewSpecialistComponent } from './new-specialist/new-specialist.component';

const routes: Routes = [
  { path: '', redirectTo: '/landing', pathMatch: 'full' },
  { path: 'landing', component: LandingComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'new-job', component: NewJobComponent },
  { path: 'job-list', component: JobListComponent },
  { path: 'job-list/:expertiseAreaId', component: JobListComponent },
  { path: 'job-details/:id', component: JobDetailsComponent },
  { path: 'password-reset', component: PasswordResetComponent },
  { path: 'my-jobs', component: MyJobsComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'admin', component: AdminPanelComponent },
  { path: 'bid-list/:id', component: BidListComponent },
  { path: 'newSpecialist', component: NewSpecialistComponent },
  { path: '**', redirectTo: '/landing', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
