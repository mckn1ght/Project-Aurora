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
  allJobsAssignedToSpecialist,
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

  getAllAssignedToSpecialist(id: number): Observable<any> {
    return this.http.get(
      BACKEND_API_URL + jobEndpoint + allJobsAssignedToSpecialist + id
    );
  }

  getJobDetails(id: number): Observable<any> {
    return this.http.get(BACKEND_API_URL + jobEndpoint + getById + id);
  }

  assignJobToSpecialist(
    jobId: number,
    specialistId: number
  ): Observable<string> {
    return this.http.post(
      BACKEND_API_URL +
        jobEndpoint +
        '/' +
        jobId +
        assignJobToSpecialist +
        specialistId,
      {},
      { responseType: 'text' }
    ) as Observable<string>;
  }
}
