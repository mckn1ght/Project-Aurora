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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci