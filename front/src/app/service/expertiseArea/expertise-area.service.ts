import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  BACKEND_API_URL,
  expertiseAreaEndpoint,
  getAllEndpoint,
} from 'src/app/constants';

@Injectable({
  providedIn: 'root',
})
export class ExpertiseAreaService {
  constructor(private http: HttpClient) {}

  getAllExpertiseAreas(): Observable<any[]> {
    return this.http.get<any[]>(
      BACKEND_API_URL + expertiseAreaEndpoint + getAllEndpoint
    );
  }
}
