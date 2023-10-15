// book.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { bookEndpoint } from 'src/app/constants';

@Injectable({
  providedIn: 'root',
})
export class BookService {
  // private apiUrl = 'http://localhost:8080/book/all';

  constructor(private http: HttpClient) {}

  getAllBooks(): Observable<any[]> {
    return this.http.get<any[]>(bookEndpoint + '/all');
  }
}
