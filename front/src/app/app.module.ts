import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { LandingComponent } from './landing/landing.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NoSpaceDirective } from './directive/no-space.directive';
import { NewJobComponent } from './new-job/new-job.component';
import { JobListComponent } from './job-list/job-list.component';
import { JobDetailsComponent } from './job-details/job-details.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { PasswordResetComponent } from './password-reset/password-reset.component';
import { MyJobsComponent } from './my-jobs/my-jobs.component';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { PaginatorModule } from 'primeng/paginator';

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    NoSpaceDirective,
    NewJobComponent,
    JobListComponent,
    JobDetailsComponent,
    PasswordResetComponent,
    MyJobsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    ButtonModule,
    TableModule,
    PaginatorModule,
  ],
  providers: [],
  bootstrap: [AppComponent, MyJobsComponent],
})
export class AppModule {}
