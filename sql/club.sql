-- MariaDB dump 10.19-11.0.2-MariaDB, for osx10.17 (arm64)
--
-- Host: localhost    Database: club_cmn
-- ------------------------------------------------------
-- Server version	11.0.2-MariaDB

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
-- Table structure for table `board_rec_tb`
--

DROP TABLE IF EXISTS `board_rec_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board_rec_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `board_uid` int(11) DEFAULT NULL,
  `rec_nrec` char(1) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `member_email` (`email`),
  KEY `board_rec_tb_ibfk_2` (`board_uid`),
  CONSTRAINT `board_rec_tb_ibfk_1` FOREIGN KEY (`email`) REFERENCES `member_tb` (`email`),
  CONSTRAINT `board_rec_tb_ibfk_2` FOREIGN KEY (`board_uid`) REFERENCES `board_tb` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_rec_tb`
--

LOCK TABLES `board_rec_tb` WRITE;
/*!40000 ALTER TABLE `board_rec_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `board_rec_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board_tb`
--

DROP TABLE IF EXISTS `board_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `writer` varchar(255) DEFAULT NULL,
  `category` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `regdate` datetime NOT NULL DEFAULT current_timestamp(),
  `view` int(11) DEFAULT 0,
  `reporting` int(11) DEFAULT 0,
  `rec` int(11) DEFAULT NULL,
  `nrec` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `FK_board_tb_category_tb` (`category`),
  KEY `FK_board_tb_member_tb` (`writer`),
  CONSTRAINT `FK_board_tb_category_tb` FOREIGN KEY (`category`) REFERENCES `category_tb` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_board_tb_member_tb` FOREIGN KEY (`writer`) REFERENCES `member_tb` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_tb`
--

LOCK TABLES `board_tb` WRITE;
/*!40000 ALTER TABLE `board_tb` DISABLE KEYS */;
INSERT INTO `board_tb` VALUES
(46,'rhkdehd97@gmail.com',1,'테스트1','<p>테스트1</p>','2023-11-21 16:55:14',4,0,NULL,NULL),
(47,'rhkdehd97@gmail.com',2,'테스트2','<p>테스트2</p>','2023-11-21 16:55:22',0,0,NULL,NULL),
(48,'rhkdehd97@gmail.com',3,'테스트3','<p>테스트3</p>','2023-11-21 16:55:32',0,0,NULL,NULL),
(49,'rhkdehd97@gmail.com',4,'테스트4','<p>테스트4</p>','2023-11-21 16:55:41',0,0,NULL,NULL),
(50,'rhkdehd97@gmail.com',1,'테스트5','<p>테스트5</p>','2023-11-21 16:55:49',0,0,NULL,NULL),
(51,'rhkdehd97@gmail.com',1,'테스트6','<p>테스트6</p>','2023-11-21 16:55:59',0,0,NULL,NULL),
(52,'rhkdehd97@gmail.com',1,'테스트7','<p>테스트7</p>','2023-11-21 16:56:07',0,0,NULL,NULL),
(53,'rhkdehd97@gmail.com',1,'테스트8','<p>테스트8</p>','2023-11-21 16:56:16',0,0,NULL,NULL),
(54,'rhkdehd97@gmail.com',1,'테스트9','<p>테스트9</p>','2023-11-21 16:56:24',0,0,NULL,NULL),
(55,'rhkdehd97@gmail.com',1,'테스트10','<p>테스트10</p>','2023-11-21 16:56:33',0,0,NULL,NULL),
(56,'rhkdehd97@gmail.com',1,'테스트11','<p>테스트11</p>','2023-11-21 16:56:40',0,0,NULL,NULL),
(57,'rhkdehd97@gmail.com',1,'테스트12','<p>테스트12</p>','2023-11-21 17:01:19',0,0,NULL,NULL),
(58,'rhkdehd97@gmail.com',1,'테스트13','<p>테스트13</p>','2023-11-21 17:01:27',0,0,NULL,NULL),
(59,'rhkdehd97@gmail.com',1,'테스트14','<p>테스트14</p>','2023-11-21 17:01:35',0,0,NULL,NULL),
(60,'rhkdehd97@gmail.com',1,'테스트15','<p>테스트15</p>','2023-11-21 17:01:45',0,0,NULL,NULL),
(61,'rhkdehd97@gmail.com',1,'테스트16','<p>테스트16</p>','2023-11-21 17:01:53',13,0,NULL,NULL);
/*!40000 ALTER TABLE `board_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_tb`
--

DROP TABLE IF EXISTS `category_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_tb`
--

LOCK TABLES `category_tb` WRITE;
/*!40000 ALTER TABLE `category_tb` DISABLE KEYS */;
INSERT INTO `category_tb` VALUES
(1,'잡담'),
(2,'질문'),
(3,'모집'),
(4,'정보');
/*!40000 ALTER TABLE `category_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cmt_rec_tb`
--

DROP TABLE IF EXISTS `cmt_rec_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmt_rec_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `cmt_uid` int(11) NOT NULL,
  `rec_nrec` char(1) NOT NULL,
  PRIMARY KEY (`uid`),
  KEY `fk_email` (`email`),
  KEY `fk_cmt_uid` (`cmt_uid`),
  CONSTRAINT `fk_cmt_uid` FOREIGN KEY (`cmt_uid`) REFERENCES `comment_tb` (`uid`) ON DELETE CASCADE,
  CONSTRAINT `fk_email` FOREIGN KEY (`email`) REFERENCES `member_tb` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmt_rec_tb`
--

LOCK TABLES `cmt_rec_tb` WRITE;
/*!40000 ALTER TABLE `cmt_rec_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `cmt_rec_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_tb`
--

DROP TABLE IF EXISTS `code_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_tb` (
  `code` varchar(255) NOT NULL,
  `codetime` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_tb`
--

LOCK TABLES `code_tb` WRITE;
/*!40000 ALTER TABLE `code_tb` DISABLE KEYS */;
INSERT INTO `code_tb` VALUES
('2c8ae60',3002000),
('5042556',3275000),
('514a87a',3243000),
('658dd13',3065000),
('7793641',3203000),
('a5ec9bc',3269000),
('c8619e8',263000),
('cc9dbec',3175000),
('e340f6a',2915000),
('f63716c',3286000),
('fc77f8f',2998000);
/*!40000 ALTER TABLE `code_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_tb`
--

DROP TABLE IF EXISTS `comment_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `writer` varchar(255) NOT NULL,
  `board_uid` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `regdate` datetime NOT NULL DEFAULT current_timestamp(),
  `rec` int(11) DEFAULT NULL,
  `nrec` int(11) DEFAULT NULL,
  `reporting` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`uid`),
  KEY `FK_comment_tb_board_tb` (`board_uid`),
  KEY `FK_comment_tb_member_tb` (`writer`),
  CONSTRAINT `FK_comment_tb_board_tb` FOREIGN KEY (`board_uid`) REFERENCES `board_tb` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_comment_tb_member_tb` FOREIGN KEY (`writer`) REFERENCES `member_tb` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_tb`
--

LOCK TABLES `comment_tb` WRITE;
/*!40000 ALTER TABLE `comment_tb` DISABLE KEYS */;
INSERT INTO `comment_tb` VALUES
(16,'rhkdehd97@gmail.com',61,'test','2023-11-21 18:20:30',NULL,NULL,0),
(17,'rhkdehd97@gmail.com',61,'test2','2023-11-21 18:31:19',NULL,NULL,0);
/*!40000 ALTER TABLE `comment_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_add_tb`
--

DROP TABLE IF EXISTS `member_add_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_add_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `payment_uid` int(11) NOT NULL,
  `file` longtext NOT NULL,
  PRIMARY KEY (`uid`),
  KEY `FK_member_add_tb_payment_tb` (`payment_uid`),
  CONSTRAINT `FK_member_add_tb_payment_tb` FOREIGN KEY (`payment_uid`) REFERENCES `payment_tb` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_add_tb`
--

LOCK TABLES `member_add_tb` WRITE;
/*!40000 ALTER TABLE `member_add_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_add_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_tb`
--

DROP TABLE IF EXISTS `member_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_tb` (
  `nickname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `createdate` date NOT NULL DEFAULT current_timestamp(),
  `salt` varchar(255) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_tb`
--

LOCK TABLES `member_tb` WRITE;
/*!40000 ALTER TABLE `member_tb` DISABLE KEYS */;
INSERT INTO `member_tb` VALUES
('test','rhkdehd97@gmail.com','$2a$10$EYbhMckF39X/uD0sy84XTuhqvZdhrjh6TxWSHKwwo9g8pej6USRAK','2023-11-21','pdnADmEnn61aqurGREyNkQ==');
/*!40000 ALTER TABLE `member_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_tb`
--

DROP TABLE IF EXISTS `payment_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) NOT NULL,
  `pay` varchar(255) NOT NULL,
  `account` varchar(50) NOT NULL DEFAULT '',
  `regdate` datetime NOT NULL DEFAULT current_timestamp(),
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`),
  KEY `FK_payment_tb_member_tb` (`nickname`),
  CONSTRAINT `FK_payment_tb_member_tb` FOREIGN KEY (`nickname`) REFERENCES `member_tb` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_tb`
--

LOCK TABLES `payment_tb` WRITE;
/*!40000 ALTER TABLE `payment_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply_tb`
--

DROP TABLE IF EXISTS `reply_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `cmt_uid` int(11) DEFAULT NULL,
  `writer` varchar(255) DEFAULT NULL,
  `up_point` int(11) DEFAULT NULL,
  `down_point` int(11) DEFAULT NULL,
  `regdate` timestamp NULL DEFAULT current_timestamp(),
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `writer` (`writer`),
  KEY `reply_tb_ibfk_1` (`cmt_uid`),
  CONSTRAINT `reply_tb_ibfk_1` FOREIGN KEY (`cmt_uid`) REFERENCES `comment_tb` (`uid`) ON DELETE CASCADE,
  CONSTRAINT `reply_tb_ibfk_3` FOREIGN KEY (`writer`) REFERENCES `member_tb` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply_tb`
--

LOCK TABLES `reply_tb` WRITE;
/*!40000 ALTER TABLE `reply_tb` DISABLE KEYS */;
INSERT INTO `reply_tb` VALUES
(5,16,'rhkdehd97@gmail.com',NULL,NULL,'2023-11-21 09:20:44','test');
/*!40000 ALTER TABLE `reply_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_tb`
--

DROP TABLE IF EXISTS `report_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `reporter` varchar(255) NOT NULL,
  `reporting_user` varchar(255) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `FK_report_tb_member_tb` (`reporter`),
  KEY `FK_report_tb_member_tb_2` (`reporting_user`),
  CONSTRAINT `FK_report_tb_member_tb` FOREIGN KEY (`reporter`) REFERENCES `member_tb` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_report_tb_member_tb_2` FOREIGN KEY (`reporting_user`) REFERENCES `member_tb` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_tb`
--

LOCK TABLES `report_tb` WRITE;
/*!40000 ALTER TABLE `report_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_tb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-27 13:21:55
