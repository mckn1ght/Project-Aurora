import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, map, of } from 'rxjs';
import {
  BACKEND_API_URL,
  countReceivedBySpecialst,
  reviewEndpoint,
} from 'src/app/constants'; // Update with actual path
import { Review } from 'src/app/models/review';

@Injectable({
  providedIn: 'root',
})
export class ReviewService {
  private baseUrl = `${BACKEND_API_URL}/review`;

  constructor(private http: HttpClient) {}

  addReview(review: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/add`, review);
  }

  getReviewById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/getById/${id}`);
  }

  getAllReviews(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }

  getReviewsByGivenUser(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/givenBy/${userId}`);
  }

  getReviewsByReceivedSpecialist(specialistId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/receivedBy/${specialistId}`);
  }

  getReviewsByStars(stars: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllByStars/${stars}`);
  }

  getReviewsByStarsBefore(stars: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllByStarsBefore/${stars}`);
  }

  getReviewsByStarsAfter(stars: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllByStarsAfter/${stars}`);
  }

  getAverageRating(specialistId: number): Observable<number> {
    return this.http
      .get<number>(`${BACKEND_API_URL}/review/averageRating/${specialistId}`)
      .pipe(
        map((response) => response || 5), // Default to 5 if the response is null or undefined
        catchError(() => of(5)) // Default to 5 in case of an error
      );
  }

  getAllByReceivedBySpecialist(specialistId: number): Observable<Review[]> {
    return this.http.get<Review[]>(
      `${this.baseUrl}/receivedBy/${specialistId}`
    );
  }

  countReceivedBySpecialst(userId: number): Observable<any> {
    return this.http.get(
      BACKEND_API_URL + reviewEndpoint + countReceivedBySpecialst + userId
    );
  }
}
