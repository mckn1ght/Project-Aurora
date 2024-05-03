export class Job {
  id?: any;
  title?: string;
  description?: string;
  location?: string;
  createdByUser?: any;
  assignedToSpecialist?: number;
  creationDate?: Date;
  dueDate?: Date;
  lastUpdated?: Date;
  currentWorkflowStep?: string;
  editing?: boolean;
}
