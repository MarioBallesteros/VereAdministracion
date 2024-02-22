-- MariaDB dump 10.19-11.3.0-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: instituto
-- ------------------------------------------------------
-- Server version	11.3.0-MariaDB

DROP DATABASE IF EXISTS `Centro`;
Create database `Centro`;
use `Centro`;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumno` (
  `nia` int(11) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `edad` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `instituto_nif` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`nia`),
  KEY `instituto_nif` (`instituto_nif`),
  CONSTRAINT `alumno_ibfk_1` FOREIGN KEY (`instituto_nif`) REFERENCES `instituto` (`Nif`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES
(1,'Calle 1, Ciudad B',25,'alumno1@example.com','12345678A'),
(2,'Calle 2, Ciudad C',22,'alumno2@example.com','23456789B'),
(3,'Calle 3, Ciudad D',26,'alumno3@example.com','34567890C'),
(4,'Calle 4, Ciudad A',24,'alumno4@example.com','45678901D'),
(5,'Calle 5, Ciudad B',25,'alumno5@example.com','12345678A'),
(6,'Calle 6, Ciudad C',26,'alumno6@example.com','23456789B'),
(7,'Calle 7, Ciudad D',19,'alumno7@example.com','34567890C'),
(8,'Calle 8, Ciudad A',25,'alumno8@example.com','45678901D'),
(9,'Calle 9, Ciudad B',22,'alumno9@example.com','12345678A'),
(10,'Calle 10, Ciudad C',27,'alumno10@example.com','23456789B'),
(11,'Calle 11, Ciudad D',20,'alumno11@example.com','34567890C'),
(12,'Calle 12, Ciudad A',22,'alumno12@example.com','45678901D'),
(13,'Calle 13, Ciudad B',23,'alumno13@example.com','12345678A'),
(14,'Calle 14, Ciudad C',21,'alumno14@example.com','23456789B'),
(15,'Calle 15, Ciudad D',27,'alumno15@example.com','34567890C'),
(16,'Calle 16, Ciudad A',23,'alumno16@example.com','45678901D'),
(17,'Calle 17, Ciudad B',27,'alumno17@example.com','12345678A'),
(18,'Calle 18, Ciudad C',19,'alumno18@example.com','23456789B'),
(19,'Calle 19, Ciudad D',25,'alumno19@example.com','34567890C'),
(20,'Calle 20, Ciudad A',19,'alumno20@example.com','45678901D'),
(21,'Calle 21, Ciudad B',21,'alumno21@example.com','12345678A'),
(22,'Calle 22, Ciudad C',21,'alumno22@example.com','23456789B'),
(23,'Calle 23, Ciudad D',26,'alumno23@example.com','34567890C'),
(24,'Calle 24, Ciudad A',28,'alumno24@example.com','45678901D'),
(25,'Calle 25, Ciudad B',22,'alumno25@example.com','12345678A'),
(26,'Calle 26, Ciudad C',19,'alumno26@example.com','23456789B'),
(27,'Calle 27, Ciudad D',21,'alumno27@example.com','34567890C'),
(28,'Calle 28, Ciudad A',20,'alumno28@example.com','45678901D'),
(29,'Calle 29, Ciudad B',27,'alumno29@example.com','12345678A'),
(30,'Calle 30, Ciudad C',19,'alumno30@example.com','23456789B'),
(31,'Calle 31, Ciudad D',25,'alumno31@example.com','34567890C'),
(32,'Calle 32, Ciudad A',19,'alumno32@example.com','45678901D'),
(33,'Calle 33, Ciudad B',21,'alumno33@example.com','12345678A'),
(34,'Calle 34, Ciudad C',22,'alumno34@example.com','23456789B'),
(35,'Calle 35, Ciudad D',18,'alumno35@example.com','34567890C'),
(36,'Calle 36, Ciudad A',27,'alumno36@example.com','45678901D'),
(37,'Calle 37, Ciudad B',24,'alumno37@example.com','12345678A'),
(38,'Calle 38, Ciudad C',20,'alumno38@example.com','23456789B'),
(39,'Calle 39, Ciudad D',19,'alumno39@example.com','34567890C'),
(40,'Calle 40, Ciudad A',26,'alumno40@example.com','45678901D'),
(41,'Calle 41, Ciudad B',25,'alumno41@example.com','12345678A'),
(42,'Calle 42, Ciudad C',20,'alumno42@example.com','23456789B'),
(43,'Calle 43, Ciudad D',26,'alumno43@example.com','34567890C'),
(44,'Calle 44, Ciudad A',22,'alumno44@example.com','45678901D'),
(45,'Calle 45, Ciudad B',26,'alumno45@example.com','12345678A'),
(46,'Calle 46, Ciudad C',26,'alumno46@example.com','23456789B'),
(47,'Calle 47, Ciudad D',23,'alumno47@example.com','34567890C'),
(48,'Calle 48, Ciudad A',19,'alumno48@example.com','45678901D'),
(49,'Calle 49, Ciudad B',19,'alumno49@example.com','12345678A'),
(50,'Calle 50, Ciudad C',21,'alumno50@example.com','23456789B'),
(51,'Calle 51, Ciudad D',19,'alumno51@example.com','34567890C'),
(52,'Calle 52, Ciudad A',23,'alumno52@example.com','45678901D'),
(53,'Calle 53, Ciudad B',19,'alumno53@example.com','12345678A'),
(54,'Calle 54, Ciudad C',21,'alumno54@example.com','23456789B'),
(55,'Calle 55, Ciudad D',18,'alumno55@example.com','34567890C'),
(56,'Calle 56, Ciudad A',20,'alumno56@example.com','45678901D'),
(57,'Calle 57, Ciudad B',20,'alumno57@example.com','12345678A'),
(58,'Calle 58, Ciudad C',19,'alumno58@example.com','23456789B'),
(59,'Calle 59, Ciudad D',19,'alumno59@example.com','34567890C'),
(60,'Calle 60, Ciudad A',19,'alumno60@example.com','45678901D'),
(61,'Calle 61, Ciudad B',20,'alumno61@example.com','12345678A'),
(62,'Calle 62, Ciudad C',26,'alumno62@example.com','23456789B'),
(63,'Calle 63, Ciudad D',20,'alumno63@example.com','34567890C'),
(64,'Calle 64, Ciudad A',27,'alumno64@example.com','45678901D'),
(65,'Calle 65, Ciudad B',25,'alumno65@example.com','12345678A'),
(66,'Calle 66, Ciudad C',28,'alumno66@example.com','23456789B'),
(67,'Calle 67, Ciudad D',25,'alumno67@example.com','34567890C'),
(68,'Calle 68, Ciudad A',22,'alumno68@example.com','45678901D'),
(69,'Calle 69, Ciudad B',19,'alumno69@example.com','12345678A'),
(70,'Calle 70, Ciudad C',21,'alumno70@example.com','23456789B'),
(71,'Calle 71, Ciudad D',18,'alumno71@example.com','34567890C'),
(72,'Calle 72, Ciudad A',22,'alumno72@example.com','45678901D'),
(73,'Calle 73, Ciudad B',25,'alumno73@example.com','12345678A'),
(74,'Calle 74, Ciudad C',22,'alumno74@example.com','23456789B'),
(75,'Calle 75, Ciudad D',26,'alumno75@example.com','34567890C'),
(76,'Calle 76, Ciudad A',26,'alumno76@example.com','45678901D'),
(77,'Calle 77, Ciudad B',26,'alumno77@example.com','12345678A'),
(78,'Calle 78, Ciudad C',23,'alumno78@example.com','23456789B'),
(79,'Calle 79, Ciudad D',21,'alumno79@example.com','34567890C'),
(80,'Calle 80, Ciudad A',27,'alumno80@example.com','45678901D'),
(81,'Calle 81, Ciudad B',23,'alumno81@example.com','12345678A'),
(82,'Calle 82, Ciudad C',26,'alumno82@example.com','23456789B'),
(83,'Calle 83, Ciudad D',25,'alumno83@example.com','34567890C'),
(84,'Calle 84, Ciudad A',19,'alumno84@example.com','45678901D'),
(85,'Calle 85, Ciudad B',21,'alumno85@example.com','12345678A'),
(86,'Calle 86, Ciudad C',20,'alumno86@example.com','23456789B'),
(87,'Calle 87, Ciudad D',27,'alumno87@example.com','34567890C'),
(88,'Calle 88, Ciudad A',18,'alumno88@example.com','45678901D'),
(89,'Calle 89, Ciudad B',23,'alumno89@example.com','12345678A'),
(90,'Calle 90, Ciudad C',22,'alumno90@example.com','23456789B'),
(91,'Calle 91, Ciudad D',21,'alumno91@example.com','34567890C'),
(92,'Calle 92, Ciudad A',21,'alumno92@example.com','45678901D'),
(93,'Calle 93, Ciudad B',26,'alumno93@example.com','12345678A'),
(94,'Calle 94, Ciudad C',27,'alumno94@example.com','23456789B'),
(95,'Calle 95, Ciudad D',21,'alumno95@example.com','34567890C'),
(96,'Calle 96, Ciudad A',26,'alumno96@example.com','45678901D'),
(97,'Calle 97, Ciudad B',18,'alumno97@example.com','12345678A'),
(98,'Calle 98, Ciudad C',26,'alumno98@example.com','23456789B'),
(99,'Calle 99, Ciudad D',25,'alumno99@example.com','34567890C'),
(100,'Calle 100, Ciudad A',22,'alumno100@example.com','45678901D');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instituto`
--

DROP TABLE IF EXISTS `instituto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instituto` (
  `Nif` varchar(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `ciudad` varchar(50) NOT NULL,
  PRIMARY KEY (`Nif`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instituto`
--

LOCK TABLES `instituto` WRITE;
/*!40000 ALTER TABLE `instituto` DISABLE KEYS */;
INSERT INTO `instituto` VALUES
('12345678A','IES La Vereda','La Pobla de Vallbona'),
('23456789B','Camp de Turia','Lliria'),
('34567890C','Cheste','Cheste'),
('45678901D','Abastos','Valencia');
/*!40000 ALTER TABLE `instituto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 13:34:20
