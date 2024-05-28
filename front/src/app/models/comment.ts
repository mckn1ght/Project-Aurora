import { User } from './user';
import { Job } from './job';

export interface Comment {
  id: number;
  job: Job;
  user: User;
  content: string;
  timestamp: Date;
}
