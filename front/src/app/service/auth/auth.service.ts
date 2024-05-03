/* import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import {
  BACKEND_API_URL,
  getByUsername,
  userEndpoint,
  userLogin,
} from 'src/app/constants';
import * as bcrypt from 'bcryptjs';
import { Observable, BehaviorSubject } from 'rxjs';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  passedData: any;

  private loggedInUserSubject: BehaviorSubject<User | null> =
    new BehaviorSubject<User | null>(null);

  constructor(private http: HttpClient) {
    const storedUserData = localStorage.getItem('loggedInUser');
    if (storedUserData) {
      const parsedUserData: User = JSON.parse(storedUserData);
      this.setLoggedInUser(parsedUserData);
    }
  }

  authenticate(
    formData: any,
    onSuccess: () => void,
    onError: (error: any) => void
  ): void {
    this.http
      .post(BACKEND_API_URL + userEndpoint + userLogin, formData, {
        observe: 'response',
      })
      .subscribe(
        (response) => {
          this.passedData = formData;
          if (response.status === 200) {
            console.log('Login successful');
            this.getUserData(onSuccess, onError);
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

  private getUserData(
    onSuccess: () => void,
    onError: (error: any) => void
  ): void {
    this.http
      .get<User>(
        BACKEND_API_URL +
          userEndpoint +
          getByUsername +
          this.passedData.username
      )
      .subscribe(
        (userData) => {
          this.setLoggedInUser(userData);
          onSuccess();
        },
        (error) => {
          onError(error);
        }
      );
  }

  setLoggedInUser(user: User): void {
    this.loggedInUserSubject.next(user);
  }

  getLoggedInUser(): Observable<User | null> {
    return this.loggedInUserSubject.asObservable();
  }

  logout(): void {
    localStorage.removeItem('loggedInUser');
    this.loggedInUserSubject;
  }
}
*/
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../../models/user';
import {
  BACKEND_API_URL,
  getByUsername,
  userEndpoint,
  userLogin,
} from 'src/app/constants';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loggedInUserSubject: BehaviorSubject<User | null> =
    new BehaviorSubject<User | null>(null);

  constructor(private http: HttpClient) {
    this.loadInitialUserData();
  }

  private loadInitialUserData() {
    const storedUserData = localStorage.getItem('loggedInUser');
    if (storedUserData) {
      const user: User = JSON.parse(storedUserData);
      this.setLoggedInUser(user);
    }
  }

  authenticate(
    formData: any,
    onSuccess: () => void,
    onError: (error: any) => void
  ): void {
    this.http
      .post(`${BACKEND_API_URL}${userEndpoint}${userLogin}`, formData, {
        observe: 'response',
      })
      .subscribe({
        next: (response) => {
          if (response.status === 200) {
            console.log('Login successful');
            this.getUserData(formData.username, onSuccess, onError);
          } else {
            onError('Login failed');
          }
        },
        error: onError,
      });
  }

  private getUserData(
    username: string,
    onSuccess: () => void,
    onError: (error: any) => void
  ): void {
    this.http
      .get<User>(`${BACKEND_API_URL}${userEndpoint}/username/${username}`)
      .subscribe({
        next: (userData) => {
          this.setLoggedInUser(userData);
          localStorage.setItem('loggedInUser', JSON.stringify(userData));
          onSuccess();
        },
        error: onError,
      });
  }

  setLoggedInUser(user: User | null): void {
    this.loggedInUserSubject.next(user);
    if (user) {
      localStorage.setItem('loggedInUser', JSON.stringify(user));
    } else {
      localStorage.removeItem('loggedInUser');
    }
  }

  getLoggedInUser(): Observable<User | null> {
    return this.loggedInUserSubject.asObservable();
  }

  logout(): void {
    localStorage.removeItem('loggedInUser');
    this.loggedInUserSubject.next(null);
  }

  refreshUserData(): void {
    const currentUser = this.loggedInUserSubject.value;
    if (currentUser && currentUser.username) {
      this.http
        .get<User>(
          `${BACKEND_API_URL}${userEndpoint}${getByUsername}${currentUser.username}`
        )
        .subscribe({
          next: (user) => {
            this.setLoggedInUser(user);
            localStorage.setItem('loggedInUser', JSON.stringify(user));
            console.log('User data refreshed successfully');
          },
          error: (error) => console.error('Failed to refresh user data', error),
        });
    } else {
      console.error('No user logged in to refresh');
    }
  }

  public updateUserDetails(username: string): void {
    this.getUserData(
      username,
      () => {
        console.log('User data refreshed successfully after update.');
      },
      (error) => {
        console.error('Failed to fetch updated user data', error);
      }
    );
  }
}
