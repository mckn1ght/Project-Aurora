CREATE TABLE `job_expertiseArea` (
  `id` int NOT NULL AUTO_INCREMENT,
  `jobID` int NOT NULL,
  `expertiseAreaID` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_job_expertiseArea_specialist` (`expertiseAreaID`),
  KEY `job_expertisearea_ibfk_1` (`jobID`),
  CONSTRAINT `fk_job_expertiseArea_specialist` FOREIGN KEY (`expertiseAreaID`) REFERENCES `specialist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `job_expertisearea_ibfk_1` FOREIGN KEY (`jobID`) REFERENCES `job` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `job_expertisearea_ibfk_2` FOREIGN KEY (`expertiseAreaID`) REFERENCES `expertiseArea` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci