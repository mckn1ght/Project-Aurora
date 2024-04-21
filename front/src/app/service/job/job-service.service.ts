// job.service.ts

import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, forkJoin, of, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import {
  BACKEND_API_URL,
  addEnpoint,
  jobEndpoint,
  allJobsInWorkflow,
  getById,
  assignJobToSpecialist,
  allJobsAssignedToSpecialist,
  getAllByLocationContaining,
  getAllByDescriptionContaining,
  getAllByTitleContaining,
  deleteById,
  createdByUser,
  preluatOrInExec,
  allNewCreatedByUser,
  workflow,
  countCreatedBy,
} from 'src/app/constants';
import { Job } from 'src/app/models/job';

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

  getAllJobsByKeyword(searchInput: string): Observable<Job[]> {
    const title$ = this.getAllJobsByTitle(searchInput);
    const description$ = this.getAllJobsByDescription(searchInput);
    const location$ = this.getAllJobsByLocation(searchInput);

    return forkJoin([title$, description$, location$]).pipe(
      map(([titleResults, descriptionResults, locationResults]) => {
        const combinedResults: (Job | undefined)[] = [
          ...(titleResults || []),
          ...(descriptionResults || []),
          ...(locationResults || []),
        ];

        // Filter out undefined values
        const filteredResults = combinedResults.filter(
          (result) =>
            result !== undefined && result.currentWorkflowStep == 'New'
        ) as Job[];

        // Remove duplicates
        const uniqueResults = Array.from(
          new Set(filteredResults.map((job) => job.id))
        ).map((id) => filteredResults.find((job) => job.id === id)) as Job[];

        return uniqueResults;
      }),
      catchError((error: HttpErrorResponse) => {
        if (error.status === 404) {
          console.warn('No jobs found for the given search criteria.');
          return of([]);
        } else {
          console.error(
            'Error in forkJoin:',
            error.error?.message || 'Unknown error'
          );
          return throwError(error.error?.message || 'Unknown error');
        }
      })
    );
  }

  getAllJobsByTitle(title: string): Observable<Job[]> {
    return this.http
      .get<Job[]>(
        BACKEND_API_URL + jobEndpoint + getAllByTitleContaining + title
      )
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 404) {
            console.warn(`No jobs found by title '${title}'.`);
            return of([]);
          } else {
            console.error(
              `Error fetching jobs by title '${title}':`,
              error.error?.message || 'Unknown error'
            );
            return throwError(error.error?.message || 'Unknown error');
          }
        })
      );
  }
  getAllJobsByDescription(description: string): Observable<Job[]> {
    return this.http
      .get<Job[]>(
        BACKEND_API_URL +
          jobEndpoint +
          getAllByDescriptionContaining +
          description
      )
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 404) {
            console.warn(`No jobs found by description '${description}'.`);
            return of([]);
          } else {
            console.error(
              `Error fetching jobs by description '${description}':`,
              error.error?.message || 'Unknown error'
            );
            return throwError(error.error?.message || 'Unknown error');
          }
        })
      );
  }

  getAllJobsByExperiseArea(expertiseArea: string) {}

  getAllJobsByLocation(location: string): Observable<Job[]> {
    return this.http
      .get<Job[]>(
        BACKEND_API_URL + jobEndpoint + getAllByLocationContaining + location
      )
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 404) {
            console.warn(`No jobs found by location '${location}'.`);
            return of([]);
          } else {
            console.error(
              `Error fetching jobs by location '${location}':`,
              error.error?.message || 'Unknown error'
            );
            return throwError(error.error?.message || 'Unknown error');
          }
        })
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

  deleteJob(jobId: number): Observable<any> {
    return this.http
      .delete(BACKEND_API_URL + jobEndpoint + deleteById + jobId)
      .pipe(
        catchError((error) => {
          throw error;
        })
      );
  }

  getJobsCreatedByUserAndInExec(userId: number): Observable<any> {
    return this.http.get(
      BACKEND_API_URL +
        jobEndpoint +
        createdByUser +
        userId +
        '/status/' +
        preluatOrInExec
    );
  }
  getJobsWithBidsCreatedByUser(userId: number): Observable<any> {
    return this.http.get(
      BACKEND_API_URL + jobEndpoint + createdByUser + userId + '/withBids'
    );
  }

  getNewJobsCreatedByUser(userId: number): Observable<any> {
    return this.http.get(
      BACKEND_API_URL + jobEndpoint + allNewCreatedByUser + userId
    );
  }

  setWorkflowStatus(jobId: number, workflowStatus: string): Observable<any> {
    const url =
      BACKEND_API_URL + jobEndpoint + '/' + jobId + workflow + workflowStatus;
    const body = { workflowStatus };

    return this.http.post<any>(url, body).pipe(
      catchError((error) => {
        console.error('Error setting workflow status:', error);
        return throwError(error);
      })
    );
  }

  countJobsCreatedByUser(userId: number): Observable<any> {
    console.log(BACKEND_API_URL + jobEndpoint + countCreatedBy + userId + '/');
    return this.http.get(
      BACKEND_API_URL + jobEndpoint + countCreatedBy + userId + '/'
    );
  }
}
