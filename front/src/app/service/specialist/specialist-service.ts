import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BACKEND_API_URL, specialistExpertiseAreas } from 'src/app/constants';

@Injectable({
  providedIn: 'root',
})
export class SpecialistService {
  private apiUrl = 'http://localhost:8080/specialist';

  constructor(private http: HttpClient) {}

  addSpecialist(userId: number, expertiseAreas: number[]): Observable<any> {
    return this.http.post(
      `${this.apiUrl}/add?userId=${userId}`,
      expertiseAreas
    );
  }

  getExpertiseAreasBySpecialistId(specialistId: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + specialistId + '/expertiseAreas/');
  }
}
