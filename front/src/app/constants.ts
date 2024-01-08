export const BACKEND_API_URL = 'http://localhost:8080';

export const getAllEndpoint = '/all';
export const addEnpoint = '/add';
export const getById = '/getById/';

//User
export const userEndpoint = '/user';
export const userLogin = '/login';
export const getByUsername = '/username/';
export const getBySpecialistId = '/getBySpecialistId/';

//ExpertiseArea
export const expertiseAreaEndpoint = '/expertiseArea';

//Job
export const jobEndpoint = '/job';
export const allJobsInWorkflow = '/inWorkflowStep/';
export const assignJobToSpecialist = '/assign/';
export const allJobsAssignedToSpecialist = '/getBySpecialistId/';

export const MAX_LOGIN_ATTEMPTS = 3;
export const APPLICATION_VERSION = '0.1.5-SHAPSHOT ';
export const APPLICATION_ENVIRONMENT = 'DEVELOPMENT';
