import { Component } from '@angular/core';
import { UserService } from '../service/user/user.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  user = {
    username: '',
    password: '',
    confirmPassword: '',
    email: '',
  };

  constructor(private userService: UserService, private router: Router) {}
  onSubmit() {
    this.userService.register(this.user).subscribe(
      (response) => {
        console.log('Registration successful:', response);
        this.router.navigate(['/login']);
      },
      (error) => {
        console.error('Registration failed:', error);
      }
    );
  }
}
