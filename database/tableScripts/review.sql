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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci