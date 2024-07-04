import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpEvent,
  HttpHeaders,
  HttpResponse,
} from '@angular/common/http';
import * as bcrypt from 'bcryptjs';

import {
  BACKEND_API_URL,
  addEnpoint,
  userEndpoint,
  getById,
  getBySpecialistId,
  updateEmail,
  updatePhoneNumber,
  deleteById,
} from 'src/app/constants';
import { Observable } from 'rxjs/internal/Observable';
import { User } from 'src/app/models/user';
import { catchError, map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };
  constructor(private http: HttpClient) {}

  register(user: any) {
    user.password = bcrypt.hashSync(user.password, 10);
    return this.http.post(BACKEND_API_URL + userEndpoint + addEnpoint, user);
  }

  getById(id: any): Observable<User> {
    return this.http
      .get<User>(BACKEND_API_URL + userEndpoint + getById + id)
      .pipe(map((user: User) => user));
  }

  getSpecialistName(id: any): Observable<User> {
    return this.http
      .get<User>(BACKEND_API_URL + userEndpoint + getBySpecialistId + id)
      .pipe(map((user: User) => user));
  }

  updateCredit(userId: number, credit: number): Observable<any> {
    return this.http.put(
      `${BACKEND_API_URL}${userEndpoint}/updateCredit/${userId}`,
      { credit: credit.toString() }
    );
  }

  getUserFirstNameLastName(id: any): Observable<User> {
    return this.http
      .get<User>(BACKEND_API_URL + userEndpoint + getById + id)
      .pipe(map((user: User) => user));
  }

  // Service
  updateEmail(id: number, email: string): Observable<any> {
    return this.http.put(
      `${BACKEND_API_URL}${userEndpoint}/updateEmail/${id}`,
      { email: email }, // Send data as JSON object
      { headers: { 'Content-Type': 'application/json' } } // Set Content-Type to application/json
    );
  }

  updatePhoneNumber(id: number, phoneNumber: string): Observable<any> {
    return this.http.put(
      `${BACKEND_API_URL}${userEndpoint}/updatePhoneNumber/${id}`,
      { phoneNumber: phoneNumber }, // Send data as JSON object
      { headers: { 'Content-Type': 'application/json' } } // Set Content-Type to application/json
    );
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(
      `${BACKEND_API_URL}/user/update/${user.id}`,
      user
    );
  }

  addCredit(userId: number, credit: number): Observable<any> {
    return this.http.put(
      `${BACKEND_API_URL}${userEndpoint}/addCredit/${userId}`,
      { credit: credit.toString() },
      this.httpOptions
    );
  }
  addCreditSpecialist(specialistId: number, credit: number): Observable<any> {
    return this.http.put(
      `${BACKEND_API_URL}${userEndpoint}/addCreditSpecialist/${specialistId}`,
      { credit: credit.toString() },
      this.httpOptions
    );
  }

  subtractCredit(userId: number, credit: number): Observable<any> {
    return this.http.put(
      `${BACKEND_API_URL}${userEndpoint}/subtractCredit/${userId}`,
      { credit: credit.toString() },
      this.httpOptions
    );
  }

  deleteUser(userId: number): Observable<any> {
    return this.http
      .delete(BACKEND_API_URL + userEndpoint + deleteById + userId)
      .pipe(
        catchError((error) => {
          throw error;
        })
      );
  }
}
