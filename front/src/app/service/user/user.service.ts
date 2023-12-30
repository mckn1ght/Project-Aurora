import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as bcrypt from 'bcryptjs';

import { BACKEND_API_URL, addEnpoint, userEndpoint } from 'src/app/constants';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  register(user: any) {
    user.password = bcrypt.hashSync(user.password, 10);
    return this.http.post(BACKEND_API_URL + userEndpoint + addEnpoint, user);
  }
}
