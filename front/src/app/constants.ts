export const BACKEND_API_URL = 'http://localhost:8080';

//Global
export const getAllEndpoint = '/all';
export const addEnpoint = '/add';
export const getById = '/getById/';
export const deleteById = '/deleteById/';
export const createdByUser = '/createdBy/';

//User
export const userEndpoint = '/user';
export const userLogin = '/login';
export const getByUsername = '/username/';
export const getBySpecialistId = '/getBySpecialistId/';
export const updateEmail = '/updateEmail/';
export const updatePhoneNumber = '/updatePhoneNumber/';

//ExpertiseArea
export const expertiseAreaEndpoint = '/expertiseArea';

//Job_ExpertiseArea
export const jobExpertiseArea = '/jobExpertiseArea';

//Job
export const jobEndpoint = '/job';
export const allJobsInWorkflow = '/inWorkflowStep/';
export const allNewCreatedByUser = '/allNewCreatedByUser/';
export const assignJobToSpecialist = '/assign/';
export const allJobsAssignedToSpecialist = '/assignedTo/';
export const getAllByLocationContaining = '/location/';
export const getAllByDescriptionContaining = '/description/';
export const getAllByTitleContaining = '/title/';
export const preluatOrInExec = '/preluat-or-in-executie';
export const workflow = '/workflow/';
export const withBids = '/withBids';
export const countCreatedBy = '/countCreatedBy/';
export const getByExpertiseArea = '/getByExpertiseArea/';

//Bid
export const bidEndpoint = '/bid';
export const getAllByBiddingSpecialist = '/biddingSpecialist/';
export const getActiveBids = '/activeCount/';
export const getAllByJobId = '/getAllByJobId/';
export const setBidStatus = '/status/';
export const getActiveBidCountByJobId = '/getActiveBidCountByJobId/';
export const job = '/job/';
export const except = '/except/';

//Resource
// @GetMapping("/getByUserId/{userId}")
// @GetMapping("/getByUserId/{userId}/type/{type}")
export const resourceEndpoint = '/resource';
export const getByUserId = '/getByUserId/';
export const type = '/type/';

//System
export const MAX_LOGIN_ATTEMPTS = 3;
export const APPLICATION_VERSION = '0.2.0-SHAPSHOT ';
export const APPLICATION_ENVIRONMENT = 'DEVELOPMENT';
