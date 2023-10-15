import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth/auth.service';
import { HttpClient } from '@angular/common/http';

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

  constructor(
    private authService: AuthService,
    private router: Router,
    private http: HttpClient
  ) {}

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
        else this.error = 'Invalid credentials!';
      }
    );
  }

  showError(error: string): string {
    this.error = error;
    return error;
  }
}
