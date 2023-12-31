// job.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  BACKEND_API_URL,
  addEnpoint,
  jobEndpoint,
  allJobsInWorkflow,
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
}
