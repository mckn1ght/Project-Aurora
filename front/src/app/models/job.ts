export class Job {
  id?: number;
  title?: string;
  description?: string;
  location?: string;
  createdByUser?: number;
  assignedToSpecialist?: number;
  creationDate?: Date;
  dueDate?: Date;
  lastUpdated?: Date;
  currentWorkflowStep?: string;
}
