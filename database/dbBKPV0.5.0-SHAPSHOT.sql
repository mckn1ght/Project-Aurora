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
  KEY `bid_ibfk_1` (`jobID`),
  KEY `bid_ibfk_2` (`specialistID`),
  CONSTRAINT `bid_ibfk_1` FOREIGN KEY (`jobID`) REFERENCES `job` (`id`) ON DELETE CASCADE,
  CONSTRAINT `bid_ibfk_2` FOREIGN KEY (`specialistID`) REFERENCES `specialist` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
INSERT INTO `bid` VALUES (24,83,3,0.00,'rejected','2024-03-16 22:15:57','2024-04-14 14:32:45'),(25,83,4,0.00,'accepted','2024-03-16 22:15:57','2024-04-14 12:12:12'),(32,83,2,0.00,'rejected','2024-04-14 14:33:13','2024-04-14 14:33:13'),(36,106,2,15.00,'accepted','2024-04-26 13:11:30','2024-04-26 13:11:30'),(61,115,2,100.00,'accepted','2024-06-05 22:18:08','2024-06-05 22:18:08'),(65,105,2,50.00,'accepted','2024-06-10 22:55:58','2024-06-10 22:55:58'),(70,114,2,5.00,'rejected','2024-06-13 15:03:09','2024-06-13 15:03:47'),(71,118,13,5.00,'active','2024-06-13 15:05:12','2024-06-13 15:05:12'),(72,118,2,5.00,'active','2024-06-13 15:15:25','2024-06-13 15:15:25'),(73,119,2,1.00,'accepted','2024-06-29 16:00:25','2024-06-29 16:00:25'),(74,120,2,1.00,'rejected','2024-06-30 17:28:14','2024-06-30 17:55:49'),(75,120,4,1.00,'rejected','2024-06-30 17:29:43','2024-06-30 17:29:43'),(76,120,2,2.00,'accepted','2024-06-30 18:01:29','2024-06-30 18:01:29'),(88,121,2,2.00,'accepted','2024-07-04 00:32:40','2024-07-04 00:32:40'),(89,121,4,10.00,'rejected','2024-07-04 00:33:09','2024-07-04 00:33:09'),(90,116,2,1.00,'active','2024-07-04 11:10:44','2024-07-04 11:10:44');
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
  CONSTRAINT `fk_comment_job` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (5,106,26,'Hello,\n\nThis is a test comment.\n\nRegards,\nRobert','2024-05-30 22:21:24'),(7,106,22,'test','2024-06-01 11:25:08'),(8,115,22,'Job terminat','2024-06-05 22:53:35'),(9,115,26,'Job ok','2024-06-05 22:54:00'),(12,120,22,'testss','2024-06-30 18:28:33'),(13,83,26,'tste','2024-07-01 21:11:45'),(14,121,26,'TEst Disertatie','2024-07-04 00:33:51'),(15,121,22,'OK FINALIZAT','2024-07-04 00:34:10');
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
  KEY `fk_job_specialist` (`assignedToSpecialist`),
  KEY `fk_job_createdByUser` (`createdByUser`),
  CONSTRAINT `fk_job_createdByUser` FOREIGN KEY (`createdByUser`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_job_specialist` FOREIGN KEY (`assignedToSpecialist`) REFERENCES `specialist` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (83,'Software Developing','We need a application for a ecommerce shops','Bucharest',26,4,'2024-03-16','2024-03-17','2024-07-01','Preluat'),(100,'Garage door','We need to fix the Garage door','Bucharest',26,1,'2024-04-18','2024-04-19','2024-06-30','New'),(101,'Ceiling falling apart','Ceiling falling apart','Bucharest',26,1,'2024-04-18','2024-04-19','2024-06-30','In Licitatie'),(102,'Fix Drawer','Drawer door is no longer working','Bucharest',26,1,'2024-04-18','2024-04-19','2024-06-30','New'),(103,'Fix window ','I can no longer open the window','Bucharest',26,1,'2024-04-18','2024-04-19','2024-06-10','New'),(104,'I need to fix the barn','I need to fix the barn pipes','Bucharest',22,1,'2024-04-18','2024-04-19','2024-06-10','In Licitatie'),(105,'I need someone to take care of my daughter','I need someone to take care of my daughters','Bucharest',26,2,'2024-04-22','2024-04-25','2024-06-13','Validated'),(106,'Leaking Pipes','The pipes are leaking! \n\nPlease check the pictures.\n\nThank you!\n','Bucuresti',26,2,'2024-04-26','2024-04-27','2024-06-02','Validated'),(107,'Leaking Sink','Leaking Sink','Bucuresti',26,1,'2024-05-15','2024-05-17','2024-05-15','New'),(109,'Leaking Toilet','Leaking Toilet','Constanta',26,1,'2024-05-15','2024-05-17','2024-06-10','New'),(111,'Problems with light','Problems with light','Cluj',26,1,'2024-05-15','2024-05-17','2024-05-15','New'),(112,'Problems with the wall','Problems with the wall','Constanta',26,1,'2024-05-15','2024-05-18','2024-05-15','New'),(113,'Problems with paint on the fence','Problems with paint on the fence','Constanta',26,1,'2024-05-15','2024-05-26','2024-05-15','New'),(114,'Problems with sink in kitchen','Problems with sink in kitchen','Constanta',26,1,'2024-05-15','2024-05-24','2024-06-29','New'),(115,'Leaking bathroom pipe ','Leaking bathroom pipe s','Bucuresti',26,2,'2024-05-15','2024-05-18','2024-06-05','Validated'),(116,'Paint has come off the wall','Paint has come off the wall','Constanta',26,1,'2024-05-15','2024-05-17','2024-07-04','In Licitatie'),(117,'There is a hole in my wall','There is a hole in my wall','Constanta',26,1,'2024-05-16','2024-05-17','2024-05-25','In Licitatie'),(118,'I need someone to unlock my door','I need someone to unlock my door','Bucuresti',26,1,'2024-05-31','2024-06-15','2024-06-13','In Licitatie'),(119,'testFinaltestFinal','testFinaltestFinaltestFinaltestFinaltestFinal','Bucharest',26,2,'2024-06-29','2024-06-30','2024-06-29','Validated'),(120,'testfinal12','testfinal12testfinal12testfinal12','Bucharest',26,2,'2024-06-29','2024-06-30','2024-06-30','Validated'),(121,'test Disertatie','test Disertatietest Disertatietest Disertatietest Disertatie','Bucuresti Calea Vitan 232',26,2,'2024-07-04','2024-07-20','2024-07-04','Validated');
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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_expertiseArea`
--

LOCK TABLES `job_expertiseArea` WRITE;
/*!40000 ALTER TABLE `job_expertiseArea` DISABLE KEYS */;
INSERT INTO `job_expertiseArea` VALUES (1,101,13),(2,101,14),(3,101,15),(4,102,13),(5,102,15),(6,102,16),(7,103,20),(9,103,21),(8,103,22),(10,104,13),(12,104,15),(11,104,16),(13,105,13),(14,105,14),(15,105,15),(16,106,14),(18,106,15),(17,106,16),(19,107,13),(21,109,1),(23,111,14),(24,112,13),(25,113,13),(26,113,17),(27,114,1),(28,115,14),(29,116,13),(30,117,1),(31,118,13),(33,118,15),(32,118,16),(35,119,1),(34,119,13),(36,120,13),(37,120,15),(38,121,14),(39,121,16);
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_picture`
--

LOCK TABLES `job_picture` WRITE;
/*!40000 ALTER TABLE `job_picture` DISABLE KEYS */;
INSERT INTO `job_picture` VALUES (3,116,'/Users/robertcostache/ProjectAurora/back/cloudComputing/uploads/jobs/JOB116-picture1.png'),(4,116,'/Users/robertcostache/ProjectAurora/back/cloudComputing/uploads/jobs/JOB116-picture2.png'),(5,116,'/Users/robertcostache/ProjectAurora/back/cloudComputing/uploads/jobs/JOB116-picture3.png'),(6,116,'/Users/robertcostache/ProjectAurora/back/cloudComputing/uploads/jobs/JOB116-picture4.png'),(7,117,'uploads/jobs/JOB117-picture1.png'),(8,106,'uploads/jobs/JOB106-picture1.jpeg'),(17,106,'uploads/jobs/JOB106-picture32e7f815-3514-422b-926e-694fdc0d4e91.webp'),(21,118,'uploads/jobs/JOB118-picture3b7696fa-edc3-41d2-be05-067ed3e159cf.webp'),(22,118,'uploads/jobs/JOB118-picture28b97e92-6479-467e-9b66-ba1a65ce4663.jpeg'),(23,118,'uploads/jobs/JOB118-picturef44fc918-78d5-48b3-b30c-bda2c6a11e96.webp'),(24,118,'uploads/jobs/JOB118-picture18cfb2f4-1fca-4234-bd1d-8b3bb1df1306.webp'),(25,118,'uploads/jobs/JOB118-pictured405944b-8917-4302-bd46-a49f468ed45d.png'),(26,118,'uploads/jobs/JOB118-pictureec3eec24-7f77-4495-b94d-4264f04bd78e.png'),(27,118,'uploads/jobs/JOB118-picture5be0c9ce-fa4d-4c2f-9a94-a1a2f8b22897.png'),(28,118,'uploads/jobs/JOB118-picture42f5d630-112e-4b14-9260-c2a0747df63d.png'),(29,115,'uploads/jobs/JOB115-pictureb1c086b8-5bb8-43b0-a840-4dd5cde4d79d.jpeg'),(30,105,'uploads/jobs/JOB105-picture2b09b707-da7a-471f-914a-b6e2eb81fcd1.jpg'),(31,119,'uploads/jobs/JOB119-picturebb41492d-4d10-4f79-a6b9-ea54cc4c6ad1.jpeg'),(32,120,'uploads/jobs/JOB120-pictured7a42719-63ce-4ed1-aaed-10d45479aaf7.webp'),(33,121,'uploads/jobs/JOB121-picture996d76ff-dfac-4511-b2ab-d8715dd9ebba.png'),(34,121,'uploads/jobs/JOB121-picturefa909d03-507f-4a80-8f9f-2c419f796cac.png');
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
  CONSTRAINT `userIdForeignKey` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES (1,26,'avatar','uploads/avatars/26_1719858457403.jpeg','2024-04-28 11:03:02','2024-07-01 21:27:37'),(8,33,'avatar','uploads/avatars/33_1714325837347.png','2024-04-28 20:34:58','2024-04-28 20:37:17'),(9,22,'avatar','uploads/avatars/22_1717230755769.jpeg','2024-04-28 20:34:58','2024-06-01 11:32:36'),(10,34,'avatar','uploads/avatars/34_1716585543566.jpeg','2024-05-23 22:18:08','2024-05-25 00:19:04'),(13,37,'avatar','uploads/avatars/37_1716658653723.jpeg','2024-05-25 12:45:20','2024-05-25 20:37:34'),(14,38,'avatar','uploads/avatars/defaultAvatar.png','2024-07-01 21:38:16','2024-07-01 21:38:16'),(15,39,'avatar','uploads/avatars/defaultAvatar.png','2024-07-02 11:13:03','2024-07-02 11:13:03');
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
  KEY `fk_review_job_idx` (`jobId`),
  KEY `fk_review_ReceivingSpecialist` (`receivedBySpecialist`),
  KEY `fk_review_user` (`givenByUser`),
  CONSTRAINT `fk_review_job` FOREIGN KEY (`jobId`) REFERENCES `job` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_review_ReceivingSpecialist` FOREIGN KEY (`receivedBySpecialist`) REFERENCES `specialist` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_review_user` FOREIGN KEY (`givenByUser`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (5,26,2,3,'3/5','OK.',105),(6,26,2,5,'5/5','Totul a decurs foarte bine',115),(11,26,2,4,'specialist ok','recomand',119),(12,26,2,1,'Specialist not ok','not ok',120),(13,26,2,5,'Specialist foarte ok','Foarte ok.',121);
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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (22,'testtest12','$2a$10$Kk9v7TNLBXPD.1.e2tmASOZxrF3G5YMpLX0VFxsqaupdLGyGoCDGi','Johnny ','Depp',NULL,'testtest12@gmail.com','0722989996','2023-12-19 20:32:37','2024-07-04 11:10:44','14.0',1,2),(26,'usernosp12','$2a$10$TUbVuUjG9j6.REcX2iZuwezSkIvFlwvVekAclw4i93PhPW1WADiVW','usernosp','usernosp',NULL,'usernosp12@gmail.com','2931293367','2024-01-13 10:54:47','2024-07-04 00:34:47','125.0',0,NULL),(27,'robert','$2a$10$low4TWlh9vQ/A1xF7FAZaOk.FjNo5JUBPzivjnLsoo6CS43x1DEOW','robert','Costache',NULL,'testtest122@gmail.com','82323232323','2024-03-11 20:33:29','2024-07-04 00:33:09','89.0',0,4),(33,'testtest123','$2a$10$JX3Qwb0IVMZAAcTdF3dvqO/kUCwohot91My2kyJCtJAyjrMCbG7ta','testtest','testtest',NULL,'testtest123@gmail.com','0922375123','2024-04-28 20:34:58','2024-04-29 17:44:08','0.0',0,NULL),(34,'usernosp3','$2a$10$uH9gO0FpFjEUI/.cA6oLPOZmMZFZaQF5vfjKSjWkQoxfe7RemNj2O','usernosp','usernosp',NULL,'usernosp3@gmail.com','23123123123','2024-05-23 22:18:08','2024-05-25 00:18:30','100.0',0,NULL),(37,'usernosp4','$2a$10$C/1z1AHRAEJrYdrpk8q6..RuktUKkU55A0q1OGW5eNE7v4T3TTAG2','Mihai','Andre',NULL,'usernosp4@gmail.com','097238893','2024-05-25 12:45:20','2024-06-13 15:05:12','95.0',0,13),(38,'usertest12','$2a$10$p0ZHeEuiPkMBgCvqQaQZvu/GLHFqlo0OfC4u3FXUr19tnpxLie47a','usertest','usertest',NULL,'usertest12@gmail.com','0723480009','2024-07-01 21:38:16','2024-07-01 21:38:16','100.0',0,NULL),(39,'usertest123','$2a$10$ynto.8E1WjMjVLP4i4gBS.p.BGZZGn7MJs9VxPhu16J8rn1cFJJCS','usertest','usertest',NULL,'usertest123@gmail.com','0723489990','2024-07-02 11:13:03','2024-07-02 11:13:03','100.0',0,NULL);
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

-- Dump completed on 2025-02-23 10:54:36
