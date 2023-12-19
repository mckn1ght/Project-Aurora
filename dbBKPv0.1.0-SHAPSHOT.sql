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
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expertiseArea`
--

LOCK TABLES `expertiseArea` WRITE;
/*!40000 ALTER TABLE `expertiseArea` DISABLE KEYS */;
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
  `description` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `createdByUser` int NOT NULL,
  `assignedToSpecialist` int DEFAULT NULL,
  `creationDate` datetime NOT NULL,
  `dueDate` datetime NOT NULL,
  `currentWorkflowStep` varchar(45) NOT NULL DEFAULT 'New',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_job_createdByUser` (`createdByUser`),
  KEY `fk_job_specialist` (`assignedToSpecialist`),
  CONSTRAINT `fk_job_createdByUser` FOREIGN KEY (`createdByUser`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_job_specialist` FOREIGN KEY (`assignedToSpecialist`) REFERENCES `specialist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
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
  KEY `expertiseAreaID` (`expertiseAreaID`),
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialist`
--

LOCK TABLES `specialist` WRITE;
/*!40000 ALTER TABLE `specialist` DISABLE KEYS */;
INSERT INTO `specialist` VALUES (1,0,'-');
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (9,'johndoe','password123','John','Doe','123 Main St','john.doe@example.com','123-456-7890','2023-12-19 20:10:25','2023-12-19 20:10:25','100',0,NULL),(22,'testtest12','$2a$10$Kk9v7TNLBXPD.1.e2tmASOZxrF3G5YMpLX0VFxsqaupdLGyGoCDGi','testtest','testtest',NULL,'testtest12@gmail.com','02738383','2023-12-19 20:32:37','2023-12-19 20:32:37','0.0',0,NULL);
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

-- Dump completed on 2023-12-19 20:54:08
