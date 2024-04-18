import { Injectable } from '@angular/core';

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import {
  BACKEND_API_URL,
  addEnpoint,
  expertiseAreaEndpoint,
  getAllEndpoint,
  jobExpertiseArea,
} from 'src/app/constants';

@Injectable({
  providedIn: 'root',
})
export class ExpertiseAreaService {
  constructor(private http: HttpClient) {}

  getAllExpertiseAreas(): Observable<any[]> {
    return this.http.get<any[]>(
      BACKEND_API_URL + expertiseAreaEndpoint + getAllEndpoint
    );
  }

  addJobExpertiseArea(jobId: number, expertiseAreas: any[]): Observable<any> {
    const payload = {
      jobId: jobId,
      expertiseAreas: expertiseAreas.map((area) => area.id), // Only send the IDs
    };

    console.log('Sending payload to backend:', payload);

    return this.http
      .post<any>(`${BACKEND_API_URL}/jobExpertiseArea/add`, payload)
      .pipe(
        catchError((error) => {
          console.error('Error adding job expertise areas:', error);
          console.error('Full error response:', error);
          throw error; // Rethrow the error to propagate it to the caller
        })
      );
  }
}
