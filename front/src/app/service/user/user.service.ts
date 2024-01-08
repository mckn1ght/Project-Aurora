import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpResponse } from '@angular/common/http';
import * as bcrypt from 'bcryptjs';

import {
  BACKEND_API_URL,
  addEnpoint,
  userEndpoint,
  getById,
  getBySpecialistId,
} from 'src/app/constants';
import { Observable } from 'rxjs/internal/Observable';
import { User } from 'src/app/models/user';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
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
}
