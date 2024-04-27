import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BACKEND_API_URL } from 'src/app/constants';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private apiUrl = BACKEND_API_URL;

  constructor(private http: HttpClient) {}

  getAllBids(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/bid/all`);
  }

  getAllExpertiseAreas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/expertiseArea/all`);
  }

  getAllJobs(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/job/all`);
  }

  getAllJobExpertiseAreas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/jobExpertiseArea/all`);
  }

  getAllReviews(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/review/all`);
  }

  getAllSpecialists(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/specialist/all`);
  }

  getAllSpecialistExpertiseAreas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/specialistExpertiseArea/all`);
  }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/user/all`);
  }

  delete(tableName: string, id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${tableName}/delete/${id}`);
  }
}
