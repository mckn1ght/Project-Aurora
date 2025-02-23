-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (arm64)
--
-- Host: localhost    Database: CloudComputing
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bid` (
  `id` int NOT NULL AUTO_INCREMENT,
  `jobID` int NOT NULL,
  `specialistID` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'active',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jobID` (`jobID`),
  KEY `specialistID` (`specialistID`),
  CONSTRAINT `bid_ibfk_1` FOREIGN KEY (`jobID`) REFERENCES `job` (`id`),
  CONSTRAINT `bid_ibfk_2` FOREIGN KEY (`specialistID`) REFERENCES `specialist` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
INSERT INTO `bid` VALUES (24,83,3,0.00,'rejected','2024-03-16 22:15:57','2024-04-14 14:32:45'),(25,83,4,0.00,'accepted','2024-03-16 22:15:57','2024-04-14 12:12:12'),(32,83,2,0.00,'rejected','2024-04-14 14:33:13','2024-04-14 14:33:13'),(36,106,2,15.00,'accepted','2024-04-26 13:11:30','2024-04-26 13:11:30'),(37,99,2,10.00,'active','2024-04-27 21:18:34','2024-04-27 21:18:34'),(38,101,2,10.00,'active','2024-04-27 21:18:39','2024-04-27 21:18:39'),(39,78,2,10.00,'active','2024-04-27 21:18:46','2024-04-27 21:18:46'),(40,77,2,50.00,'active','2024-04-27 21:32:35','2024-04-27 21:32:35'),(61,115,2,100.00,'accepted','2024-06-05 22:18:08','2024-06-05 22:18:08');
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` varchar(255) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_user_idx` (`user_id`),
  KEY `fk_comment_job_idx` (`job_id`),
  CONSTRAINT `fk_comment_job` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (5,106,26,'Hello,\n\nThis is a test comment.\n\nRegards,\nRobert','2024-05-30 22:21:24'),(7,106,22,'test','2024-06-01 11:25:08'),(8,115,22,'Job terminat','2024-06-05 22:53:35'),(9,115,26,'Job ok','2024-06-05 22:54:00');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expertiseArea`
--

DROP TABLE IF EXISTS `expertiseArea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expertiseArea` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `iconName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expertiseArea`
--

LOCK TABLES `expertiseArea` WRITE;
/*!40000 ALTER TABLE `expertiseArea` DISABLE KEYS */;
INSERT INTO `expertiseArea` VALUES (1,'Vopsitor','paintbrush@2x'),(13,'Electrician','bolt@2x'),(14,'Instalator','pipe.and.drop@2x'),(15,'Tamplar','tree@2x'),(16,'Mecanic','wrench.and.screwdriver@2x'),(17,'Electronist','screwdriver@2x'),(18,'Frigotehnist','snowflake@2x'),(19,'Tehnician IT','desktopcomputer.trianglebadge.exclamationmark@2x'),(20,'Jardinist','leaf@2x'),(21,'Constructii','hammer@2x'),(22,'Diverse','square.grid.3x3.middle.filled@2x');
/*!40000 ALTER TABLE `expertiseArea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `description` varchar(255) NOT NULL,
  `location` varchar(45) NOT NULL,
  `createdByUser` int NOT NULL,
  `assignedToSpecialist` int DEFAULT '1',
  `creationDate` date NOT NULL,
  `dueDate` date NOT NULL,
  `lastUpdated` date NOT NULL,
  `currentWorkflowStep` varchar(45) NOT NULL DEFAULT 'New',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_job_createdByUser` (`createdByUser`),
  KEY `fk_job_specialist` (`assignedToSpecialist`),
  CONSTRAINT `fk_job_createdByUser` FOREIGN KEY (`createdByUser`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_job_specialist` FOREIGN KEY (`assignedToSpecialist`) REFERENCES `specialist` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (48,'Software Developerz','Developing software applicationsz','City XYZz',9,2,'2023-12-21','2023-12-30','2024-05-19','Preluat'),(67,'Software Engineer','Develop new features','San Francisco',9,2,'2023-01-01','2023-02-01','2024-01-13','Preluat'),(68,'Data Analyst','Analyze data trends','New York',9,2,'2023-01-02','2023-02-02','2024-01-08','Preluat'),(69,'Graphic Designer','Create visual assets','Los Angeles',9,2,'2023-01-03','2023-02-03','2024-01-19','Preluat'),(72,'Financial Analyst','Analyze financial data','Seattle',9,1,'2023-01-06','2023-02-06','2024-04-16','In Licitatie'),(73,'Customer Support Representative','Assist customers','Denver',9,1,'2023-01-07','2023-02-07','2024-04-14','In Licitatie'),(74,'Software Tester','Test software applications','Dallas',9,1,'2023-01-08','2023-02-08','2024-04-13','In Licitatie'),(75,'UX Designer','Design user experiences','Miami',9,1,'2023-01-09','2023-02-09','2024-04-16','In Licitatie'),(76,'Human Resources Specialist','Manage HR functions','Atlanta',9,1,'2023-01-10','2023-02-10','2024-04-19','In Licitatie'),(77,'Network Engineer','Maintain network infrastructure','Houston',9,1,'2023-01-11','2023-02-11','2024-04-27','In Licitatie'),(78,'Content Writer','Create engaging content','Philadelphia',9,1,'2023-01-12','2023-02-12','2024-04-27','In Licitatie'),(79,'Quality Assurance Specialist','Ensure product quality','Phoenix',9,1,'2023-01-13','2023-02-13','2023-01-13','New'),(80,'Legal Counsel','Provide legal advice','San Diego',9,1,'2023-01-14','2023-02-14','2023-01-14','New'),(81,'Healthcare Specialist','Manage healthcare initiatives Software','Boston',9,1,'2023-01-15','2023-02-15','2024-06-05','New'),(82,'Change electric cables for me','I need someone to change the electric cables for me','Constanta',26,1,'2024-03-05','2024-03-15','2024-03-05','Preluat'),(83,'Software Developing','We need a application for a ecommerce shop','Bucharest',26,4,'2024-03-16','2024-03-17','2024-04-14','Preluat'),(99,'Looking for gardener','Looking for gardener to water my flowers','Constanta',26,1,'2024-04-18','2024-04-19','2024-04-27','In Licitatie'),(100,'Garage door','We need to fix the Garage door','Bucharest',26,1,'2024-04-18','2024-04-19','2024-04-18','New'),(101,'Ceiling falling apart','Ceiling falling apart','Bucharest',26,1,'2024-04-18','2024-04-19','2024-04-27','In Licitatie'),(102,'Fix Drawer','Drawer door is no longer working','Bucharest',26,1,'2024-04-18','2024-04-19','2024-04-18','New'),(103,'Fix window ','I can no longer open the window','Bucharest',26,1,'2024-04-18','2024-04-19','2024-04-18','New'),(104,'I need to fix the barn','I need to fix the barn pipes','Bucharest',22,1,'2024-04-18','2024-04-19','2024-04-18','New'),(105,'I need someone to take care of my daughter','I need someone to take care of my daughter','Bucharest',26,1,'2024-04-22','2024-04-25','2024-05-04','New'),(106,'Leaking Pipes','The pipes are leaking! \n\nPlease check the pictures.\n\nThank you!\n','Bucuresti',26,2,'2024-04-26','2024-04-27','2024-06-02','Validated'),(107,'Leaking Sink','Leaking Sink','Bucuresti',26,1,'2024-05-15','2024-05-17','2024-05-15','New'),(109,'Leaking Toilet','Leaking Toilet','Constanta',26,1,'2024-05-15','2024-05-17','2024-05-15','New'),(110,'Problems with the bathroom','Problems with the bathroom','Cluj',26,1,'2024-05-15','2024-05-24','2024-05-15','New'),(111,'Problems with light','Problems with light','Cluj',26,1,'2024-05-15','2024-05-17','2024-05-15','New'),(112,'Problems with the wall','Problems with the wall','Constanta',26,1,'2024-05-15','2024-05-18','2024-05-15','New'),(113,'Problems with paint on the fence','Problems with paint on the fence','Constanta',26,1,'2024-05-15','2024-05-26','2024-05-15','New'),(114,'Problems with sink in kitchen','Problems with sink in kitchen','Constanta',26,1,'2024-05-15','2024-05-24','2024-05-15','New'),(115,'Leaking bathroom pipe ','Leaking bathroom pipe ','Bucuresti',26,2,'2024-05-15','2024-05-18','2024-06-05','Validated'),(116,'Paint has come off the wall','Paint has come off the wall','Constanta',26,1,'2024-05-15','2024-05-17','2024-05-19','New'),(117,'There is a hole in my wall','There is a hole in my wall','Constanta',26,1,'2024-05-16','2024-05-17','2024-05-25','In Licitatie'),(118,'I need someone to unlock my door','I need someone to unlock my door','Bucuresti',26,1,'2024-05-31','2024-06-15','2024-05-31','New');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_expertiseArea`
--

DROP TABLE IF EXISTS `job_expertiseArea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_expertiseArea` (
  `id` int NOT NULL AUTO_INCREMENT,
  `jobID` int NOT NULL,
  `expertiseAreaID` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `job_expertiseArea_unique` (`jobID`,`expertiseAreaID`),
  KEY `fk_job_expertiseArea_expertiseArea` (`expertiseAreaID`),
  CONSTRAINT `fk_job_expertiseArea_expertiseArea` FOREIGN KEY (`expertiseAreaID`) REFERENCES `expertiseArea` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_job_expertiseArea_job` FOREIGN KEY (`jobID`) REFERENCES `job` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_expertiseArea`
--

LOCK TABLES `job_expertiseArea` WRITE;
/*!40000 ALTER TABLE `job_expertiseArea` DISABLE KEYS */;
INSERT INTO `job_expertiseArea` VALUES (1,101,13),(2,101,14),(3,101,15),(4,102,13),(5,102,15),(6,102,16),(7,103,20),(9,103,21),(8,103,22),(10,104,13),(12,104,15),(11,104,16),(13,105,13),(14,105,14),(15,105,15),(16,106,14),(18,106,15),(17,106,16),(19,107,13),(21,109,1),(22,110,1),(23,111,14),(24,112,13),(25,113,13),(26,113,17),(27,114,1),(28,115,14),(29,116,13),(30,117,1),(31,118,13),(33,118,15),(32,118,16);
/*!40000 ALTER TABLE `job_expertiseArea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_expertiseArea_OLD`
--

DROP TABLE IF EXISTS `job_expertiseArea_OLD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_expertiseArea_OLD` (
  `jobID` int NOT NULL,
  `expertiseAreaID` int NOT NULL,
  PRIMARY KEY (`jobID`,`expertiseAreaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_expertiseArea_OLD`
--

LOCK TABLES `job_expertiseArea_OLD` WRITE;
/*!40000 ALTER TABLE `job_expertiseArea_OLD` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_expertiseArea_OLD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_picture`
--

DROP TABLE IF EXISTS `job_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_picture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `jobId` int NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `job_picture_ibfk_1` (`jobId`),
  CONSTRAINT `job_picture_ibfk_1` FOREIGN KEY (`jobId`) REFERENCES `job` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_picture`
--

LOCK TABLES `job_picture` WRITE;
/*!40000 ALTER TABLE `job_picture` DISABLE KEYS */;
INSERT INTO `job_picture` VALUES (3,116,'/Users/robertcostache/ProjectAurora/back/cloudComputing/uploads/jobs/JOB116-picture1.png'),(4,116,'/Users/robertcostache/ProjectAurora/back/cloudComputing/uploads/jobs/JOB116-picture2.png'),(5,116,'/Users/robertcostache/ProjectAurora/back/cloudComputing/uploads/jobs/JOB116-picture3.png'),(6,116,'/Users/robertcostache/ProjectAurora/back/cloudComputing/uploads/jobs/JOB116-picture4.png'),(7,117,'uploads/jobs/JOB117-picture1.png'),(8,106,'uploads/jobs/JOB106-picture1.jpeg'),(17,106,'uploads/jobs/JOB106-picture32e7f815-3514-422b-926e-694fdc0d4e91.webp'),(21,118,'uploads/jobs/JOB118-picture3b7696fa-edc3-41d2-be05-067ed3e159cf.webp'),(22,118,'uploads/jobs/JOB118-picture28b97e92-6479-467e-9b66-ba1a65ce4663.jpeg'),(23,118,'uploads/jobs/JOB118-picturef44fc918-78d5-48b3-b30c-bda2c6a11e96.webp'),(24,118,'uploads/jobs/JOB118-picture18cfb2f4-1fca-4234-bd1d-8b3bb1df1306.webp'),(25,118,'uploads/jobs/JOB118-pictured405944b-8917-4302-bd46-a49f468ed45d.png'),(26,118,'uploads/jobs/JOB118-pictureec3eec24-7f77-4495-b94d-4264f04bd78e.png'),(27,118,'uploads/jobs/JOB118-picture5be0c9ce-fa4d-4c2f-9a94-a1a2f8b22897.png'),(28,118,'uploads/jobs/JOB118-picture42f5d630-112e-4b14-9260-c2a0747df63d.png'),(29,115,'uploads/jobs/JOB115-pictureb1c086b8-5bb8-43b0-a840-4dd5cde4d79d.jpeg');
/*!40000 ALTER TABLE `job_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `type` varchar(45) NOT NULL DEFAULT 'avatar',
  `location` varchar(45) NOT NULL DEFAULT 'uploads/avatars/defaultAvatar.png',
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId_idx` (`userId`),
  CONSTRAINT `userIdForeignKey` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES (1,26,'avatar','uploads/avatars/26_1714323861469.png','2024-04-28 11:03:02','2024-04-28 20:04:21'),(8,33,'avatar','uploads/avatars/33_1714325837347.png','2024-04-28 20:34:58','2024-04-28 20:37:17'),(9,22,'avatar','uploads/avatars/22_1717230755769.jpeg','2024-04-28 20:34:58','2024-06-01 11:32:36'),(10,34,'avatar','uploads/avatars/34_1716585543566.jpeg','2024-05-23 22:18:08','2024-05-25 00:19:04'),(13,37,'avatar','uploads/avatars/37_1716658653723.jpeg','2024-05-25 12:45:20','2024-05-25 20:37:34');
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `givenByUser` int NOT NULL,
  `receivedBySpecialist` int NOT NULL,
  `stars` int NOT NULL,
  `title` varchar(45) NOT NULL,
  `description` varchar(255) NOT NULL,
  `jobId` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_review_ReceivingSpecialist` (`receivedBySpecialist`),
  KEY `fk_review_user` (`givenByUser`),
  KEY `fk_review_job_idx` (`jobId`),
  CONSTRAINT `fk_review_job` FOREIGN KEY (`jobId`) REFERENCES `job` (`id`),
  CONSTRAINT `fk_review_ReceivingSpecialist` FOREIGN KEY (`receivedBySpecialist`) REFERENCES `specialist` (`id`),
  CONSTRAINT `fk_review_user` FOREIGN KEY (`givenByUser`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (3,9,1,5,'Excelent serviciu!','Specialistul a fost foarte profesionist și a rezolvat problema rapid.',116),(4,9,1,3,'Serviciul mediu','Specialistul a fost mediu.',115);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialist`
--

DROP TABLE IF EXISTS `specialist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rating` int NOT NULL DEFAULT '5',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialist`
--

LOCK TABLES `specialist` WRITE;
/*!40000 ALTER TABLE `specialist` DISABLE KEYS */;
INSERT INTO `specialist` VALUES (1,5),(2,3),(3,3),(4,3),(11,5),(12,5),(13,5);
/*!40000 ALTER TABLE `specialist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialist_expertiseArea`
--

DROP TABLE IF EXISTS `specialist_expertiseArea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialist_expertiseArea` (
  `id` int NOT NULL AUTO_INCREMENT,
  `specialistID` int NOT NULL,
  `expertiseAreaID` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `expertiseAreaID` (`expertiseAreaID`),
  KEY `specialist_expertisearea_ibfk_1` (`specialistID`),
  CONSTRAINT `specialist_expertisearea_ibfk_1` FOREIGN KEY (`specialistID`) REFERENCES `specialist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `specialist_expertisearea_ibfk_2` FOREIGN KEY (`expertiseAreaID`) REFERENCES `expertiseArea` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialist_expertiseArea`
--

LOCK TABLES `specialist_expertiseArea` WRITE;
/*!40000 ALTER TABLE `specialist_expertiseArea` DISABLE KEYS */;
INSERT INTO `specialist_expertiseArea` VALUES (1,1,1),(16,11,14),(17,11,16),(18,12,13),(19,12,16),(20,12,15),(21,13,13),(22,13,15),(23,2,13),(24,2,14);
/*!40000 ALTER TABLE `specialist_expertiseArea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `credit` varchar(45) NOT NULL DEFAULT '0.00',
  `isAdmin` tinyint NOT NULL DEFAULT '0',
  `specialistId` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `phoneNumber_UNIQUE` (`phoneNumber`),
  UNIQUE KEY `specialistId` (`specialistId`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `specialistID_idx` (`specialistId`),
  CONSTRAINT `fk_user_specialist` FOREIGN KEY (`specialistId`) REFERENCES `specialist` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (9,'johndoe','password123','John','Doe','123 Main St','john.doee@example.com','123-456-7890','2023-12-19 20:10:25','2024-05-19 18:33:55','100.0',0,1),(22,'testtest12','$2a$10$Kk9v7TNLBXPD.1.e2tmASOZxrF3G5YMpLX0VFxsqaupdLGyGoCDGi','Johnny ','Depp',NULL,'testtest12@gmail.com','0722989996','2023-12-19 20:32:37','2024-05-03 19:14:14','501.0',1,2),(23,'mckn1ght','testtest12','tessss','tesssss',NULL,'robertt@gmail.com','0723888899','2023-12-21 15:48:06','2023-12-21 15:48:06','0.0',0,3),(26,'usernosp12','$2a$10$TUbVuUjG9j6.REcX2iZuwezSkIvFlwvVekAclw4i93PhPW1WADiVW','usernosp','usernosp',NULL,'usernosp12@gmail.com','293129336','2024-01-13 10:54:47','2024-04-27 19:52:45','130.0',0,NULL),(27,'robert','$2a$10$low4TWlh9vQ/A1xF7FAZaOk.FjNo5JUBPzivjnLsoo6CS43x1DEOW','robert','Costache',NULL,'testtest122@gmail.com','82323232323','2024-03-11 20:33:29','2024-03-11 20:33:29','0.0',0,4),(33,'testtest123','$2a$10$JX3Qwb0IVMZAAcTdF3dvqO/kUCwohot91My2kyJCtJAyjrMCbG7ta','testtest','testtest',NULL,'testtest123@gmail.com','0922375123','2024-04-28 20:34:58','2024-04-29 17:44:08','0.0',0,NULL),(34,'usernosp3','$2a$10$uH9gO0FpFjEUI/.cA6oLPOZmMZFZaQF5vfjKSjWkQoxfe7RemNj2O','usernosp','usernosp',NULL,'usernosp3@gmail.com','23123123123','2024-05-23 22:18:08','2024-05-25 00:18:30','100.0',0,NULL),(37,'usernosp4','$2a$10$C/1z1AHRAEJrYdrpk8q6..RuktUKkU55A0q1OGW5eNE7v4T3TTAG2','Mihai','Andre',NULL,'usernosp4@gmail.com','097238893','2024-05-25 12:45:20','2024-05-25 21:34:53','100.0',0,13);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-10 10:41:46
