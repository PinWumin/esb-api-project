CREATE SCHEMA `empseat` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs ;

DROP TABLE IF EXISTS `floor_info`;
CREATE TABLE `floor_info` (
  `floor_seat_seq` int NOT NULL,
  `floor_no` int NOT NULL,
  `seat_no` int NOT NULL,
  PRIMARY KEY (`floor_seat_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_cs;

DROP TABLE IF EXISTS `emp_info`;
CREATE TABLE `emp_info` (
  `emp_id` int NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NOT NULL,
  `floor_seat_seq` int DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `emp_info_ibfk_1_idx` (`floor_seat_seq`),
  CONSTRAINT `emp_info_ibfk_1` FOREIGN KEY (`floor_seat_seq`) REFERENCES `floor_info` (`floor_seat_seq`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_cs;


DELIMITER ;;
CREATE DEFINER=`user`@`localhost` PROCEDURE `editEmpFloorSeatSeq`(in `empId` int, in `floorSeatSeq` int)
BEGIN
    DECLARE floorSeatSeqVar int;
    
	IF   floorSeatSeq = 0  
	THEN SET floorSeatSeqVar = null;
    ELSE SET floorSeatSeqVar = floorSeatSeq;
    END IF;
    
	UPDATE emp_info SET `floor_seat_seq` = floorSeatSeqVar WHERE `emp_id` = empId;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE DEFINER=`user`@`localhost` PROCEDURE `getAllEmpInfo`()
BEGIN
	Select emp_id,name,email,floor_seat_seq From emp_info;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE DEFINER=`user`@`localhost` PROCEDURE `getAllFloorInfo`()
BEGIN
	SELECT floor_seat_seq, floor_no, seat_no FROM floor_info;
END ;;
DELIMITER ;