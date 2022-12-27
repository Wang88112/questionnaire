CREATE TABLE `questionnaire` (
  `serial_number` int NOT NULL AUTO_INCREMENT,
  `caption` varchar(45) NOT NULL,
  `content` varchar(45) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`serial_number`)
  );
  
  
  CREATE TABLE `questions` (
  `caption` varchar(45) NOT NULL,
  `questions` varchar(45) NOT NULL,
  `potions` varchar(45) NOT NULL,
  PRIMARY KEY (`questions`,`caption`)
);


CREATE TABLE `user` (
  `user_name` varchar(45) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_name`)
);