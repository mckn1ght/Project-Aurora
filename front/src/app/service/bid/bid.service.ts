import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  BACKEND_API_URL,
  bidEndpoint,
  getAllByBiddingSpecialist,
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
}
