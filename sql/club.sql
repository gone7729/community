-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        11.0.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- club_cmn 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `club_cmn` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `club_cmn`;

-- 테이블 club_cmn.board_tb 구조 내보내기
CREATE TABLE IF NOT EXISTS `board_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `writer` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `regdate` datetime NOT NULL DEFAULT current_timestamp(),
  `view` int(11) NOT NULL DEFAULT 0,
  `up_point` int(11) NOT NULL DEFAULT 0,
  `down_point` int(11) NOT NULL DEFAULT 0,
  `reporting` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`uid`),
  KEY `FK_board_tb_member_tb` (`writer`),
  CONSTRAINT `FK_board_tb_member_tb` FOREIGN KEY (`writer`) REFERENCES `member_tb` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 club_cmn.board_tb:~0 rows (대략적) 내보내기

-- 테이블 club_cmn.comment_tb 구조 내보내기
CREATE TABLE IF NOT EXISTS `comment_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `board_uid` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `regdate` datetime NOT NULL DEFAULT current_timestamp(),
  `up_point` int(11) NOT NULL DEFAULT 0,
  `donw_point` int(11) NOT NULL DEFAULT 0,
  `reporting` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`uid`),
  KEY `FK_comment_tb_board_tb` (`board_uid`),
  KEY `FK_comment_tb_member_tb` (`email`),
  CONSTRAINT `FK_comment_tb_board_tb` FOREIGN KEY (`board_uid`) REFERENCES `board_tb` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_comment_tb_member_tb` FOREIGN KEY (`email`) REFERENCES `member_tb` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 club_cmn.comment_tb:~0 rows (대략적) 내보내기

-- 테이블 club_cmn.member_add_tb 구조 내보내기
CREATE TABLE IF NOT EXISTS `member_add_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `payment_uid` int(11) NOT NULL,
  `file` longtext NOT NULL,
  PRIMARY KEY (`uid`),
  KEY `FK_member_add_tb_payment_tb` (`payment_uid`),
  KEY `FK_member_add_tb_member_tb` (`email`),
  CONSTRAINT `FK_member_add_tb_member_tb` FOREIGN KEY (`email`) REFERENCES `member_tb` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_member_add_tb_payment_tb` FOREIGN KEY (`payment_uid`) REFERENCES `payment_tb` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 club_cmn.member_add_tb:~0 rows (대략적) 내보내기

-- 테이블 club_cmn.member_tb 구조 내보내기
CREATE TABLE IF NOT EXISTS `member_tb` (
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 club_cmn.member_tb:~0 rows (대략적) 내보내기

-- 테이블 club_cmn.payment_tb 구조 내보내기
CREATE TABLE IF NOT EXISTS `payment_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `pay` varchar(255) NOT NULL,
  `account` varchar(50) NOT NULL DEFAULT '',
  `regdate` datetime NOT NULL DEFAULT current_timestamp(),
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`),
  KEY `FK_payment_tb_member_tb` (`email`),
  CONSTRAINT `FK_payment_tb_member_tb` FOREIGN KEY (`email`) REFERENCES `member_tb` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 club_cmn.payment_tb:~0 rows (대략적) 내보내기

-- 테이블 club_cmn.report_tb 구조 내보내기
CREATE TABLE IF NOT EXISTS `report_tb` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `reporter` varchar(255) NOT NULL,
  `reporting_email` varchar(255) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `FK_report_tb_member_tb` (`reporter`),
  KEY `FK_report_tb_member_tb_2` (`reporting_email`),
  CONSTRAINT `FK_report_tb_member_tb_2` FOREIGN KEY (`reporting_email`) REFERENCES `member_tb` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 club_cmn.report_tb:~0 rows (대략적) 내보내기

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
