LOCK TABLES `floor_info` WRITE;
INSERT INTO `floor_info` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,1),(6,2,2),(7,2,3),(8,2,4),(9,3,1),(10,3,2),(11,3,3),(12,3,4),(13,4,1),(14,4,2),(15,4,3),(16,4,4);
UNLOCK TABLES;

LOCK TABLES `emp_info` WRITE;
INSERT INTO `emp_info` VALUES (11221,'員工E','empE@gmail.com',12),(12006,'員工A','empA@gmail.com',3),(12258,'員工G','empG@gmail.com',NULL),(13040,'員工C','empC@gmail.com',9),(14582,'員工H','empH@gmail.com',NULL),(15478,'員工J','empJ@gmail.com',NULL),(16142,'員工B','empB@gmail.com',7),(16722,'員工F','empF@gmail.com',15),(17081,'員工D','empD@gmail.com',10),(19552,'員工I','empI@gmail.com',NULL);
UNLOCK TABLES;