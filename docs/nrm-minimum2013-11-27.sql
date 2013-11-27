-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: nrm_minimum
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.13.10.1
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DUMMY_IMAGE_EXIF_DATA`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DUMMY_IMAGE_EXIF_DATA` (
  `id` int(11) NOT NULL,
  `image_UUID` varchar(45) DEFAULT NULL,
  `exif` mediumtext COMMENT 'for testing: Save the exif as a JSON-string',
  PRIMARY KEY (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DUMMY_TAXON`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DUMMY_TAXON` (
  `ID` int(11) NOT NULL,
  `COMMON_NAME` text,
  `SCIENTIFIC_NAME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `IMAGE`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IMAGE` (
  `UUID` varchar(255) NOT NULL,
  PRIMARY KEY (`UUID`),
  CONSTRAINT `FK_IMAGE_UUID` FOREIGN KEY (`UUID`) REFERENCES `MEDIA` (`UUID`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `MEDIA`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MEDIA` (
  `UUID` varchar(255) NOT NULL,
  `DTYPE` varchar(31) DEFAULT NULL,
  `FILENAME` varchar(255) DEFAULT NULL,
  `MIME_TYE` varchar(50) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `VISIBILITY` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UUID`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TAG`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TAG` (
  `ID` int(11) NOT NULL,
  `TAG_KEY` varchar(255) DEFAULT NULL,
  `TAG_POINTER` varchar(245) DEFAULT NULL,
  `EXTERNAL_SYSTEM` varchar(255) DEFAULT NULL,
  `DATE_CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MEDIA_UUID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-27 16:49:41
