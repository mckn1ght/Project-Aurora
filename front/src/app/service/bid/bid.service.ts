import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import {
  BACKEND_API_URL,
  addEnpoint,
  bidEndpoint,
  deleteById,
  getActiveBids,
  getAllByBiddingSpecialist,
  getAllByJobId,
} from 'src/app/constants';

@Injectable({
  providedIn: 'root',
})
export class BidService {
  constructor(private http: HttpClient) {}

  getAllBidsBySpecialist(id: number): Observable<any> {
    return this.http.get(
      BACKEND_API_URL + bidEndpoint + getAllByBiddingSpecialist + id
    );
  }

  addBid(bid: any): Observable<any> {
    return this.http.post(BACKEND_API_URL + bidEndpoint + addEnpoint, bid);
  }

  getActiveBids(specialistId: number): Observable<any> {
    return this.http.get(
      BACKEND_API_URL + bidEndpoint + getActiveBids + specialistId
    );
  }

  deleteBid(bidId: number): Observable<any> {
    return this.http
      .delete(BACKEND_API_URL + bidEndpoint + deleteById + bidId)
      .pipe(
        catchError((error) => {
          throw error;
        })
      );
  }

  getActiveBidsByJob(jobId: number): Observable<any> {
    return this.http.get(BACKEND_API_URL + bidEndpoint + getActiveBids + jobId);
  }

  getJobsCreatedByUserWithBids(jobId: number) {
    return this.http.get(BACKEND_API_URL + bidEndpoint + getAllByJobId + jobId);
  }
}
