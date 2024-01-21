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
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (48,'Software Developer','Developing software applications','City XYZ',9,2,'2023-12-21','2023-12-30','2024-01-08','Preluat'),(67,'Software Engineer','Develop new features','San Francisco',9,2,'2023-01-01','2023-02-01','2024-01-13','Preluat'),(68,'Data Analyst','Analyze data trends','New York',9,2,'2023-01-02','2023-02-02','2024-01-08','Preluat'),(69,'Graphic Designer','Create visual assets','Los Angeles',9,2,'2023-01-03','2023-02-03','2024-01-19','Preluat'),(70,'Project Manager','Coordinate project tasks','Chicago',9,1,'2023-01-04','2023-02-04','2023-01-04','New'),(71,'Marketing Specialist','Plan marketing campaigns','Austin',9,1,'2023-01-05','2023-02-05','2023-01-05','New'),(72,'Financial Analyst','Analyze financial data','Seattle',9,1,'2023-01-06','2023-02-06','2023-01-06','New'),(73,'Customer Support Representative','Assist customers','Denver',9,1,'2023-01-07','2023-02-07','2023-01-07','New'),(74,'Software Tester','Test software applications','Dallas',9,1,'2023-01-08','2023-02-08','2023-01-08','New'),(75,'UX Designer','Design user experiences','Miami',9,1,'2023-01-09','2023-02-09','2023-01-09','New'),(76,'Human Resources Specialist','Manage HR functions','Atlanta',9,1,'2023-01-10','2023-02-10','2023-01-10','New'),(77,'Network Engineer','Maintain network infrastructure','Houston',9,1,'2023-01-11','2023-02-11','2023-01-11','New'),(78,'Content Writer','Create engaging content','Philadelphia',9,1,'2023-01-12','2023-02-12','2023-01-12','New'),(79,'Quality Assurance Specialist','Ensure product quality','Phoenix',9,1,'2023-01-13','2023-02-13','2023-01-13','New'),(80,'Legal Counsel','Provide legal advice','San Diego',9,1,'2023-01-14','2023-02-14','2023-01-14','New'),(81,'Healthcare Specialist','Manage healthcare initiatives Software','Boston',9,1,'2023-01-15','2023-02-15','2023-01-15','New');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_expertiseArea`
--

DROP TABLE IF EXISTS `job_expertiseArea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_expertiseArea` (
  `jobID` int NOT NULL,
  `expertiseAreaID` int NOT NULL,
  PRIMARY KEY (`jobID`,`expertiseAreaID`),
  KEY `fk_job_expertiseArea_specialist` (`expertiseAreaID`),
  CONSTRAINT `fk_job_expertiseArea_specialist` FOREIGN KEY (`expertiseAreaID`) REFERENCES `specialist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `job_expertisearea_ibfk_1` FOREIGN KEY (`jobID`) REFERENCES `job` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `job_expertisearea_ibfk_2` FOREIGN KEY (`expertiseAreaID`) REFERENCES `expertiseArea` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_expertiseArea`
--

LOCK TABLES `job_expertiseArea` WRITE;
/*!40000 ALTER TABLE `job_expertiseArea` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_expertiseArea` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_review_ReceivingSpecialist` (`receivedBySpecialist`),
  KEY `fk_review_user` (`givenByUser`),
  CONSTRAINT `fk_review_ReceivingSpecialist` FOREIGN KEY (`receivedBySpecialist`) REFERENCES `specialist` (`id`),
  CONSTRAINT `fk_review_user` FOREIGN KEY (`givenByUser`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (3,9,1,5,'Excelent serviciu!','Specialistul a fost foarte profesionist și a rezolvat problema rapid.'),(4,9,1,3,'Serviciul mediu','Specialistul a fost mediu.');
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
  `expertiseArea` int DEFAULT NULL,
  `coverageArea` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialist`
--

LOCK TABLES `specialist` WRITE;
/*!40000 ALTER TABLE `specialist` DISABLE KEYS */;
INSERT INTO `specialist` VALUES (1,0,'-'),(2,1,'3');
/*!40000 ALTER TABLE `specialist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialist_expertiseArea`
--

DROP TABLE IF EXISTS `specialist_expertiseArea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialist_expertiseArea` (
  `specialistID` int NOT NULL,
  `expertiseAreaID` int NOT NULL,
  PRIMARY KEY (`specialistID`,`expertiseAreaID`),
  KEY `expertiseAreaID` (`expertiseAreaID`),
  CONSTRAINT `specialist_expertisearea_ibfk_1` FOREIGN KEY (`specialistID`) REFERENCES `specialist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `specialist_expertisearea_ibfk_2` FOREIGN KEY (`expertiseAreaID`) REFERENCES `expertiseArea` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialist_expertiseArea`
--

LOCK TABLES `specialist_expertiseArea` WRITE;
/*!40000 ALTER TABLE `specialist_expertiseArea` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (9,'johndoe','password123','John','Doe','123 Main St','john.doe@example.com','123-456-7890','2023-12-19 20:10:25','2023-12-19 20:10:25','100',0,1),(22,'testtest12','$2a$10$Kk9v7TNLBXPD.1.e2tmASOZxrF3G5YMpLX0VFxsqaupdLGyGoCDGi','testtest','testtest',NULL,'testtest12@gmail.com','02738383','2023-12-19 20:32:37','2023-12-19 20:32:37','0.0',0,2),(23,'mckn1ght','testtest12','tessss','tesssss',NULL,'robertt@gmail.com','0723888899','2023-12-21 15:48:06','2023-12-21 15:48:06','0.0',0,NULL),(26,'usernosp12','$2a$10$TUbVuUjG9j6.REcX2iZuwezSkIvFlwvVekAclw4i93PhPW1WADiVW','usernosp','usernosp',NULL,'usernosp12@gmail.com','293129333','2024-01-13 10:54:47','2024-01-13 10:54:47','0.0',0,NULL);
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

-- Dump completed on 2024-01-21 10:26:18
