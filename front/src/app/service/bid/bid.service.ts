import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, switchMap, throwError } from 'rxjs';
import {
  BACKEND_API_URL,
  addEnpoint,
  bidEndpoint,
  deleteById,
  except,
  getActiveBidCountByJobId,
  getActiveBids,
  getAllByBiddingSpecialist,
  getAllByJobId,
  job,
  setBidStatus,
} from 'src/app/constants';
import { Bid } from 'src/app/models/bid';
import { User } from 'src/app/models/user';
import { UserService } from '../user/user.service';

@Injectable({
  providedIn: 'root',
})
export class BidService {
  constructor(private http: HttpClient, private userService: UserService) {}

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

  getActiveBidCountByJobId(jobId: number): Observable<any> {
    return this.http.get(
      BACKEND_API_URL + bidEndpoint + getActiveBidCountByJobId + jobId
    );
  }

  getJobsCreatedByUserWithBids(jobId: number) {
    return this.http.get(BACKEND_API_URL + bidEndpoint + getAllByJobId + jobId);
  }

  setBidStatus(bidId: number, newStatus: string): Observable<any> {
    const url =
      BACKEND_API_URL + bidEndpoint + '/' + bidId + '/status/' + newStatus;
    const body = { newStatus };
    return this.http.post<any>(url, body).pipe(
      catchError((error) => {
        console.error('Error setting bid status:', error);
        return throwError(error);
      })
    );
  }

  acceptBid(jobId: number, acceptedBidId: number): Observable<string> {
    const url = `${BACKEND_API_URL}${bidEndpoint}/job/${jobId}/except/${acceptedBidId}`;
    console.log(url);
    return this.http.put(url, null, { responseType: 'text' }).pipe(
      catchError((error) => {
        return throwError('Error accepting bid: ' + error.statusText);
      })
    );
  }

  getByBidId(bidId: number): Observable<any> {
    const url = `${BACKEND_API_URL}${bidEndpoint}/getById/${bidId}`;
    return this.http.get(url);
  }

  getAcceptedBidByJobid(jobId: number): Observable<Bid> {
    const url = `${BACKEND_API_URL}${bidEndpoint}/getAcceptedByJobId/${jobId}`;
    return this.http.get(url);
  }

  restoreCredit(userId: number, creditAmount: number): Observable<any> {
    return this.userService.addCredit(userId, creditAmount).pipe(
      catchError((error) => {
        console.error('Error restoring credit:', error);
        return throwError(error);
      })
    );
  }

  restoreCreditBySpecialistId(
    specialistId: number,
    creditAmount: number
  ): Observable<any> {
    return this.userService.getSpecialistName(specialistId).pipe(
      switchMap((user: User) =>
        this.userService.addCredit(user.id!, creditAmount)
      ),
      catchError((error) => {
        console.error('Error restoring credit:', error);
        return throwError(error);
      })
    );
  }
}
