import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth/auth.service';
import { HttpClient } from '@angular/common/http';
import * as bcrypt from 'bcryptjs';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';
import { Observable, BehaviorSubject } from 'rxjs';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  formData = {
    username: '',
    password: '',
  };

  error: string = '';

  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.error = '';
    this.authService.authenticate(
      this.formData,
      () => {
        console.log('Redirecting to home...');
        this.router.navigate(['/home']);
      },
      (error) => {
        if (this.formData.username == '') this.error = 'Username is required!';
        else if (this.formData.password == '')
          this.error = 'Password is required!';
        else this.error = error.error.message;
      }
    );
  }
}
