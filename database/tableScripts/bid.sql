CREATE TABLE `bid` (
  `id` int NOT NULL AUTO_INCREMENT,
  `jobID` int NOT NULL,
  `specialistID` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'pending',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`jobID`) REFERENCES `job` (`id`),
  FOREIGN KEY (`specialistID`) REFERENCES `specialist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
