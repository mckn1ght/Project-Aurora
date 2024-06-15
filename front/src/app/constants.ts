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
export const specialistExpertiseAreas =
  '/specialistExpertiseArea/specialistId/';

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
export const countJobsFinishedBySpecialist = '/countJobsFinishedBySpecialist/';
export const countJobsFinishedByUser = '/countJobsFinishedByUser/';
export const countJobsCanceledByUser = '/countJobsCanceledByUser/';
export const countActiveJobsInWorkBySpecialist =
  '/countActiveJobsInWorkBySpecialist/';

//JobPicture
export const jobPicture = '/jobPicture';

//Bid
export const bidEndpoint = '/bid';
export const getAllByBiddingSpecialist = '/biddingSpecialist/';
export const getActiveBids = '/activeCount/';
export const getAllByJobId = '/getAllByJobId/';
export const setBidStatus = '/status/';
export const getActiveBidCountByJobId = '/getActiveBidCountByJobId/';
export const job = '/job/';
export const except = '/except/';
export const acceptedBid = '/getAcceptedByJobId/';

//Resource
// @GetMapping("/getByUserId/{userId}")
// @GetMapping("/getByUserId/{userId}/type/{type}")
export const resourceEndpoint = '/resource';
export const getByUserId = '/getByUserId/';
export const type = '/type/';

//Review
export const reviewEndpoint = '/review';
export const countReceivedBySpecialst = '/countReceivedBySpecialst/';

//System
export const MAX_LOGIN_ATTEMPTS = 3;
export const APPLICATION_VERSION = '0.2.0-SHAPSHOT ';
export const APPLICATION_ENVIRONMENT = 'DEVELOPMENT';
