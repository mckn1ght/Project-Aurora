CREATE TABLE `specialist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expertiseArea` int DEFAULT NULL,
  `coverageArea` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci