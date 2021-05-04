-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: salesbuddy
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `about`
--

DROP TABLE IF EXISTS `about`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `about` (
  `aInfoID` int NOT NULL AUTO_INCREMENT,
  `aBookshopName` varchar(45) NOT NULL,
  `aBookshopAddress` varchar(400) DEFAULT NULL,
  `aContactPerson` varchar(45) DEFAULT NULL,
  `aContactDesignation` varchar(45) DEFAULT NULL,
  `aContactEmail` varchar(45) DEFAULT NULL,
  `aBookshopNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`aInfoID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `about`
--

LOCK TABLES `about` WRITE;
/*!40000 ALTER TABLE `about` DISABLE KEYS */;
INSERT INTO `about` VALUES (1,'Sarasavi BookShop','104/75, Main Street, Gampaha, Sri Lanka','U D Liyanage','Manager','ud@yahoo.com','+94 33 222 2901');
/*!40000 ALTER TABLE `about` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `bID` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `bISBN` varchar(45) DEFAULT NULL,
  `bTitle` varchar(45) DEFAULT NULL,
  `bAuthor` varchar(45) DEFAULT NULL,
  `bDescription` varchar(400) DEFAULT NULL,
  `bLanguage` varchar(45) DEFAULT NULL,
  `bPublishYear` varchar(45) DEFAULT NULL,
  `bAvailability` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`bID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (00001,'135464321','Sample Title','Sample Author','Sample Description','English','Sample Publisher - 2021','Available'),(00002,'654451v6ss','Sample Title 4','Sample Author 2','Sample Description 2','Sinhala','Sample Publisher - 2021','Not Available'),(00004,'654451v6ss','Sample Title 1','Sample Author 2','Sample Description 2','Sinhala','Sample Publisher - 2021','Not Available'),(00005,'1236691431','Oliver Twist','Charles Dickens','English literature','English','Melhoramentos - 1995','Not Available');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookview`
--

DROP TABLE IF EXISTS `bookview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookview` (
  `vID` int NOT NULL AUTO_INCREMENT,
  `vBID` int DEFAULT NULL,
  `vDate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`vID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookview`
--

LOCK TABLES `bookview` WRITE;
/*!40000 ALTER TABLE `bookview` DISABLE KEYS */;
INSERT INTO `bookview` VALUES (1,1,'2021-04-24'),(2,1,'2021-04-28'),(3,4,'2021-05-04'),(4,1,'2021-05-04'),(5,4,'2021-05-04'),(6,4,'2021-05-04'),(7,4,'2021-05-04'),(8,4,'2021-05-04'),(9,4,'2021-05-04'),(10,4,'2021-05-04'),(11,5,'2021-05-04'),(12,5,'2021-05-04'),(13,5,'2021-05-04'),(14,5,'2021-05-04');
/*!40000 ALTER TABLE `bookview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `fID` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fCategory` varchar(20) DEFAULT NULL,
  `fTitle` varchar(45) DEFAULT NULL,
  `fDescription` varchar(500) DEFAULT NULL,
  `fDate` varchar(20) DEFAULT NULL,
  `fTime` varchar(20) DEFAULT NULL,
  `fStatus` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`fID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (00001,'Service Appreciation','vsdvsdv','sdsvsdvsvs','2021-05-03','23:03:14','Pending'),(00002,'Service Appreciation','Nice Service','Thanks for the service','2021-05-04','15:48:58','Pending');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `rID` int NOT NULL AUTO_INCREMENT,
  `rBName` varchar(45) DEFAULT NULL,
  `rDescription` varchar(400) DEFAULT NULL,
  `rBAuthor` varchar(45) DEFAULT NULL,
  `rBEdition` varchar(45) DEFAULT NULL,
  `rBPublisher` varchar(45) DEFAULT NULL,
  `rDate` varchar(20) DEFAULT NULL,
  `rStatus` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`rID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,'gerbeb','ebbfbg','nfgnfgn','gnfgnf','fgnfgnf','2021-05-03','Pending');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uEmail` varchar(45) NOT NULL,
  `uName` varchar(45) NOT NULL,
  `uPassword` varchar(200) NOT NULL,
  `uType` varchar(10) NOT NULL,
  PRIMARY KEY (`uEmail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('guest@yahoo.com','GUEST ','e10adc3949ba59abbe56e057f20f883e','Guest'),('ud@yahoo.com','Uthpala Liyanage','e10adc3949ba59abbe56e057f20f883e','Admin'),('uthpala@yahoo.com','Uthpala Liyanage','e10adc3949ba59abbe56e057f20f883e','Admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-04 15:56:28
