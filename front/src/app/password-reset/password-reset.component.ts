import { Component } from '@angular/core';
import { AuthService } from '../service/auth/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';

@Component({
  selector: 'app-password-reset',
  templateUrl: './password-reset.component.html',
  styleUrls: ['./password-reset.component.css'],
})
export class PasswordResetComponent {
  newPassword: string = '';
  confirmPassword: string = '';
  errorMessage: string = '';
  resetToken: string | null = '';

  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.resetToken = params.get('token');
    });
  }

  // validateResetToken() {
  //   this.authService.validateResetToken(this.resetToken).subscribe(
  //     (response) => {},
  //     (error) => {}
  //   );
  // }
  resetPassword() {}
  cancel() {
    this.router.navigate(['/login']);
  }
}
