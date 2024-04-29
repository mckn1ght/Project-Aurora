import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import {
  BACKEND_API_URL,
  getByUserId,
  resourceEndpoint,
  type,
} from 'src/app/constants';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Resource } from 'src/app/models/resource';

@Injectable({
  providedIn: 'root',
})
export class ResourceService {
  constructor(private http: HttpClient) {}
  selectedFile: File | null = null;
  apiUrl: string = BACKEND_API_URL;

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

  // onFileSelected(event: any): void {
  //   this.selectedFile = event.target.files[0];
  // }

  // uploadAvatar(userId: number, formData: FormData) {
  //   // In your Angular service, make sure headers are set correctly if needed
  //   const httpOptions = {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'multipart/form-data', // Usually not needed as Angular sets it with the boundary
  //     }),
  //   };

  //   return this.http.post(
  //     `${this.apiUrl}/users/${userId}/upload-avatar`,
  //     formData,
  //     {
  //       reportProgress: true,
  //       observe: 'events',
  //     }
  //   );
  // }

  uploadAvatar(userId: number, formData: FormData) {
    return this.http.post(
      `http://localhost:8080/resource/users/${userId}/upload-avatar`,
      formData,
      {
        reportProgress: true,
        observe: 'events',
      }
    );
  }
}
