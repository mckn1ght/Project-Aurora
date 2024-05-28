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
import {
  CommonModule,
  HashLocationStrategy,
  LocationStrategy,
} from '@angular/common';
import { PasswordResetComponent } from './password-reset/password-reset.component';
import { MyJobsComponent } from './my-jobs/my-jobs.component';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { PaginatorModule } from 'primeng/paginator';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { DialogModule } from 'primeng/dialog';
import { BidListComponent } from './bid-list/bid-list.component';
import { CardModule } from 'primeng/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { InputTextModule } from 'primeng/inputtext';
import { DoubleNumberInputDirective } from './directive/double-number-input.directive';
import { MultiSelectModule } from 'primeng/multiselect';
import { ProfileComponent } from './profile/profile.component';
import { OnlyNumbersDirective } from './directive/only-numbers.directive';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { AccordionModule } from 'primeng/accordion';
import { ConfirmDialogModule } from 'primeng/confirmdialog'; // Import the ConfirmDialogModule
import { ConfirmationService } from 'primeng/api';
import { UploadAvatarComponent } from './upload-avatar/upload-avatar.component';
import { FileUploadModule } from 'primeng/fileupload';
import { NewSpecialistComponent } from './new-specialist/new-specialist.component';
import { JobDetailInWorkComponent } from './job-detail-in-work/job-detail-in-work.component';

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
    ConfirmationDialogComponent,
    BidListComponent,
    DoubleNumberInputDirective,
    ProfileComponent,
    OnlyNumbersDirective,
    AdminPanelComponent,
    UploadAvatarComponent,
    NewSpecialistComponent,
    JobDetailInWorkComponent,
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
    BrowserModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    MatDialogModule,
    DialogModule,
    CardModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    InputTextModule,
    MultiSelectModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    TableModule,
    ButtonModule,
    AccordionModule,
    ConfirmDialogModule,
    FileUploadModule,
  ],
  providers: [ConfirmationService],
  bootstrap: [AppComponent], //, MyJobsComponent - > eroare my-jobs-component not found NG System
})
export class AppModule {}
