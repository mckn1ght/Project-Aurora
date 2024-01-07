// job.service.ts

import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import {
  BACKEND_API_URL,
  addEnpoint,
  jobEndpoint,
  allJobsInWorkflow,
  getById,
  assignJobToSpecialist,
} from 'src/app/constants';

@Injectable({
  providedIn: 'root',
})
export class JobService {
  constructor(private http: HttpClient) {}

  addJob(job: any): Observable<any> {
    return this.http.post(BACKEND_API_URL + jobEndpoint + addEnpoint, job);
  }

  getAllNewJobs(): Observable<any> {
    return this.http.get(
      BACKEND_API_URL + jobEndpoint + allJobsInWorkflow + 'New'
    );
  }

  getJobDetails(id: number): Observable<any> {
    return this.http.get(BACKEND_API_URL + jobEndpoint + getById + id);
  }

  // assignJobToSpecialist(jobId: number, specialistId: number) {
  //   return this.http.post(
  //     BACKEND_API_URL +
  //       jobEndpoint +
  //       '/' +
  //       jobId +
  //       assignJobToSpecialist +
  //       specialistId,
  //     {},
  //     { responseType: 'text' }
  //   );
  // }
  assignJobToSpecialist(jobId: number, specialistId: number) {
    return this.http.post<string>(
      BACKEND_API_URL +
        jobEndpoint +
        '/' +
        jobId +
        assignJobToSpecialist +
        specialistId,
      {}
    );
  }

  // assignJobToSpecialist(
  //   jobId: number,
  //   specialistId: number
  // ): Observable<string> {
  //   const url = `${BACKEND_API_URL}${jobEndpoint}/${jobId}${assignJobToSpecialist}${specialistId}`;

  //   return this.http.post(url, {}, { responseType: 'text' }).pipe(
  //     catchError((error: HttpErrorResponse) => {
  //       if (error.status === 400) {
  //         // The job is already assigned, extract the error message
  //         const errorMessage = error.error;
  //         return throwError(errorMessage);
  //       }
  //       // Handle other errors if needed
  //       return throwError('An error occurred while assigning the job.');
  //     })
  //   );
  // }
}
