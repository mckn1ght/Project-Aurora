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
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci