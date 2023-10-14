import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor() {}
  onSubmit() {
    // Add your login logic here
    console.log('Username:', this.username);
    console.log('Password:', this.password);
  }
}
