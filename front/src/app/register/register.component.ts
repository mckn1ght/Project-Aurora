import { Component } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../service/user/user.service';
import { Router } from '@angular/router';

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

  userAfterValidation = {
    username: '',
    password: '',
    email: '',
  };

  error = '';
  registerForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(5)]],
      password: [
        '',
        [
          Validators.required,
          Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/),
        ],
      ],
      confirmPassword: ['', Validators.required],
      email: [
        '',
        [
          Validators.required,
          Validators.pattern('^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$'),
        ],
      ],
    });
  }

  onSubmit() {
    this.error = '';
    if (this.registerForm.valid && this.passwordsMatch()) {
      this.userAfterValidation.username = this.user.username;
      this.userAfterValidation.password = this.user.password;
      this.userAfterValidation.email = this.user.email;
      this.userService.register(this.userAfterValidation).subscribe(
        (response) => {
          console.log('Form submitted:', response);
          this.router.navigate(['/login']);
        },
        (error) => {
          console.error('Registration failed1:', error.error);

          this.error = error.error;
        }
      );
    } else {
      console.error('Registration failed2:', Error);
    }
  }

  passwordsMatch(): boolean {
    const passwordControl = this.registerForm.get('password');
    const confirmPasswordControl = this.registerForm.get('confirmPassword');

    return (
      passwordControl?.value !== null &&
      confirmPasswordControl?.value !== null &&
      passwordControl?.value === confirmPasswordControl?.value
    );
  }
}
