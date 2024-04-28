import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import {
  BACKEND_API_URL,
  getByUserId,
  resourceEndpoint,
  type,
} from 'src/app/constants';
import { HttpClient } from '@angular/common/http';
import { Resource } from 'src/app/models/resource';

@Injectable({
  providedIn: 'root',
})
export class ResourceService {
  constructor(private http: HttpClient) {}

  getByIdAndType(id: number, resourceType: string): Observable<Resource> {
    return this.http
      .get<Resource>(
        BACKEND_API_URL +
          resourceEndpoint +
          getByUserId +
          id +
          type +
          resourceType
      )
      .pipe(map((resource: Resource) => resource));
  }

  getByUserId(userId: number) {
    return this.http
      .get<Resource>(BACKEND_API_URL + resourceEndpoint + getByUserId + userId)
      .pipe(map((resource: Resource) => resource));
  }
}
