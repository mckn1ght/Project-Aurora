import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  authenticate(
    formData: any,
    onSuccess: () => void,
    onError: (error: any) => void
  ): void {
    this.http
      .post('http://localhost:8080/user/login', formData, {
        observe: 'response',
      })
      .subscribe(
        (response) => {
          if (response.status === 200) {
            console.log('Login successful');
            onSuccess();
          } else {
            console.error('Login failed with status code', response.status);
            onError('Login failed');
          }
        },
        (error) => {
          console.error('Login error', error);
          onError(error);
        }
      );
  }
}
