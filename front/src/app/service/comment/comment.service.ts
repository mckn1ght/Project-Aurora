import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comment } from '../../models/comment';
import { BACKEND_API_URL } from 'src/app/constants';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  constructor(private http: HttpClient) {}

  getCommentsByJobId(jobId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${BACKEND_API_URL}/comments/job/${jobId}`);
  }

  addComment(comment: any): Observable<Comment> {
    return this.http.post<Comment>(`${BACKEND_API_URL}/comments/add`, comment);
  }
}
