import { Component } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../service/user/user.service';
import { Router } from '@angular/router';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  user = {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    confirmPassword: '',
    email: '',
    phoneNumber: '',
  };

  userAfterValidation = {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
  };

  error = '';
  registerForm: FormGroup;

  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

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
      firstName: [
        '',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.pattern(/^[a-zA-Z]*$/),
        ],
      ],
      lastName: [
        '',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.pattern(/^[a-zA-Z]*$/),
        ],
      ],
      email: [
        '',
        [
          Validators.required,
          Validators.pattern('^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$'),
        ],
      ],
      phoneNumber: ['', [Validators.required]],
    });
  }

  onSubmit() {
    this.error = '';
    if (this.registerForm.valid && this.passwordsMatch()) {
      this.userAfterValidation.username = this.user.username;
      this.userAfterValidation.password = this.user.password;
      this.userAfterValidation.firstName = this.user.firstName;
      this.userAfterValidation.lastName = this.user.lastName;
      this.userAfterValidation.email = this.user.email;
      this.userAfterValidation.phoneNumber = this.user.phoneNumber;
      this.userService.register(this.userAfterValidation).subscribe(
        (response) => {
          console.log('Form submitted:', response);
          this.router.navigate(['/login']);
        },
        (error) => {
          console.error('Registration failed:', error.error);

          this.error = error.error;
        }
      );
    } else {
      console.error('Registration failed. Problem during API call:', Error);
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
