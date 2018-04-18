-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: riskassessment
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aircraft`
--

DROP TABLE IF EXISTS `aircraft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aircraft` (
  `Aircraft_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Aircraft_Code` varchar(5) NOT NULL,
  `Aircraft_Type` int(11) NOT NULL,
  `Manufacture_Date` datetime NOT NULL,
  `Registration_No` varchar(45) NOT NULL,
  PRIMARY KEY (`Aircraft_Id`),
  UNIQUE KEY `Aircraft_Code_UNIQUE` (`Aircraft_Code`),
  KEY `Aircraft_Type_idx` (`Aircraft_Type`),
  CONSTRAINT `Aircraft_Type` FOREIGN KEY (`Aircraft_Type`) REFERENCES `aircraft_type_detail` (`Aircraft_Type_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft`
--

LOCK TABLES `aircraft` WRITE;
/*!40000 ALTER TABLE `aircraft` DISABLE KEYS */;
INSERT INTO `aircraft` VALUES (1001,'AC001',1,'2000-12-12 00:00:00','JA8089'),(1002,'AC002',1,'2000-12-12 00:00:00','JA8090'),(1003,'AC003',2,'2000-12-12 00:00:00','JA9089'),(1004,'AC004',2,'2000-12-13 00:00:00','JA9090'),(1005,'AC005',2,'2001-01-10 00:00:00','JA9099'),(1006,'AC006',1,'2001-01-10 00:00:00','JB0002'),(1007,'AC007',2,'2001-02-02 00:00:00','JB0009');
/*!40000 ALTER TABLE `aircraft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aircraft_checklist`
--

DROP TABLE IF EXISTS `aircraft_checklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aircraft_checklist` (
  `CHECKLIST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Aircraft_Code` varchar(5) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Auto_Pilot` tinyint(4) NOT NULL DEFAULT '0',
  `Storm_Scope` tinyint(4) NOT NULL DEFAULT '0',
  `Weather_Radar` tinyint(4) NOT NULL DEFAULT '0',
  `De_Ice` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`CHECKLIST_ID`),
  KEY `Aircraft_Id_checklist_idx` (`Aircraft_Code`),
  CONSTRAINT `Aircraft_Id_checklist` FOREIGN KEY (`Aircraft_Code`) REFERENCES `aircraft` (`Aircraft_Code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft_checklist`
--

LOCK TABLES `aircraft_checklist` WRITE;
/*!40000 ALTER TABLE `aircraft_checklist` DISABLE KEYS */;
INSERT INTO `aircraft_checklist` VALUES (1,'AC002','2018-03-04 18:30:00',1,1,0,1),(2,'AC003','2018-03-04 18:30:00',0,1,1,1),(3,'AC004','2018-03-05 18:30:00',1,1,1,1),(4,'AC002','2018-03-02 18:30:00',1,1,0,1),(5,'AC003','2018-02-26 18:30:00',0,1,1,1),(6,'AC004','2018-01-31 18:30:00',1,1,1,1);
/*!40000 ALTER TABLE `aircraft_checklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aircraft_type_detail`
--

DROP TABLE IF EXISTS `aircraft_type_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aircraft_type_detail` (
  `Aircraft_Type_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Aircraft_Type` varchar(45) NOT NULL,
  `Aircraft_Description` varchar(100) NOT NULL,
  PRIMARY KEY (`Aircraft_Type_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft_type_detail`
--

LOCK TABLES `aircraft_type_detail` WRITE;
/*!40000 ALTER TABLE `aircraft_type_detail` DISABLE KEYS */;
INSERT INTO `aircraft_type_detail` VALUES (1,'Boeing 757F','Max Payload: 60,000 lbs./27,215kg, Range: 1,900 statute miles/3,057km'),(2,'Boeing 767F','Max Payload:120,00 lbs./54,432kg, Range: 3,500 statute miles/5,600km'),(3,'Boeing 777F','Max Payload:230,00 lbs./104,326kg, Range: 5,400 statute miles/8,689km'),(4,'MD-10-30F','Max Payload:170,000 lbs./77,110kg, Range: 3,600 statute miles/5,792km'),(5,'MD-11F','Max Payload:180,000 lbs./81,646kg, Range: 4,000 statute miles/6,436km'),(6,'Airbus A300-600F','Max Payload:105,000 lbs./47,627kg, Range: 2,700 statute miles/4,344km');
/*!40000 ALTER TABLE `aircraft_type_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airport` (
  `Airport_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Airport_Code` varchar(5) NOT NULL,
  `Airport_Name` varchar(45) NOT NULL,
  `Latitude` float NOT NULL,
  `Longitude` float NOT NULL,
  `Description` varchar(100) DEFAULT NULL,
  `Is_Mountain` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`Airport_Id`),
  UNIQUE KEY `Airport_Code_UNIQUE` (`Airport_Code`),
  UNIQUE KEY `Latitude_UNIQUE` (`Latitude`,`Longitude`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES (11,'MEM','Memphis International Airport',35.0421,89.9792,' home to the FedEx Express global hub',0),(12,'CDG',' Charles de Gaulle Airport',49.0097,-2.54778,NULL,0),(13,'EWR','Newark Liberty International Airport',40.6925,-74.1686,NULL,0),(14,'YYZ','Toronto Pearson International Airport',43.6767,-79.6306,NULL,0),(15,'KIX','Kansai International Airport',34.4342,135.233,NULL,1),(16,'MIA','Miami International Airport',25.7933,-80.2906,NULL,0),(17,'CGN','Cologne Bonn Airport',50.8658,7.14278,NULL,0),(18,'ANC','Ted Stevens Anchorage International Airport',61.1742,-149.998,NULL,0),(19,'DXB','Dubai International Airport',25.2528,55.3644,NULL,0),(20,'SIN','Singapore Changi Airport',1.35921,103.989,NULL,0);
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew`
--

DROP TABLE IF EXISTS `crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crew` (
  `Crew_Member_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Crew_Member_Name` varchar(45) DEFAULT NULL,
  `Mobile` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Crew_Member_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10007 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew`
--

LOCK TABLES `crew` WRITE;
/*!40000 ALTER TABLE `crew` DISABLE KEYS */;
INSERT INTO `crew` VALUES (10001,'Donna Dent',9876543210),(10002,'Sheila Dail',8765432109),(10003,'Eric',7654321098),(10004,'John',6543210987),(10005,'Roy',5432109876),(10006,'Doreen',4321098765);
/*!40000 ALTER TABLE `crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_schedule`
--

DROP TABLE IF EXISTS `flight_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight_schedule` (
  `Flight_Schedule_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Aircraft_Code` varchar(5) NOT NULL,
  `Source_Airport_Code` varchar(5) NOT NULL,
  `Destination_Airport_Code` varchar(5) NOT NULL,
  `Date_of_Departure` date NOT NULL,
  `Time_Departure` time NOT NULL,
  `Time_Arrival` time NOT NULL,
  `Duration` int(11) NOT NULL,
  PRIMARY KEY (`Flight_Schedule_Id`),
  KEY `Airport_code_flight_Schedule_idx` (`Source_Airport_Code`),
  KEY `Destination_Airport_code_flight_Schedule_idx` (`Destination_Airport_Code`),
  KEY `Aircraft_code_flight_schedule_idx` (`Aircraft_Code`),
  CONSTRAINT `Aircraft_code_flight_schedule` FOREIGN KEY (`Aircraft_Code`) REFERENCES `aircraft` (`Aircraft_Code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Destination_Airport_code_flight_Schedule` FOREIGN KEY (`Destination_Airport_Code`) REFERENCES `airport` (`Airport_Code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Source_Airport_code_flight_Schedule` FOREIGN KEY (`Source_Airport_Code`) REFERENCES `airport` (`Airport_Code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_schedule`
--

LOCK TABLES `flight_schedule` WRITE;
/*!40000 ALTER TABLE `flight_schedule` DISABLE KEYS */;
INSERT INTO `flight_schedule` VALUES (1,'AC002','MEM','CDG','2018-03-05','13:12:00','10:10:00',1318),(2,'AC003','CDG','EWR','2018-03-05','09:00:00','13:15:00',255),(3,'AC004','MEM','EWR','2018-03-06','10:00:00','17:00:00',420),(4,'AC002','CDG','MEM','2018-03-03','08:20:00','16:20:00',480),(5,'AC003','CDG','MEM','2018-02-27','08:20:00','16:30:00',490),(6,'AC004','MEM','CDG','2018-02-01','13:22:00','10:30:00',1328);
/*!40000 ALTER TABLE `flight_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_schedule_crew`
--

DROP TABLE IF EXISTS `flight_schedule_crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight_schedule_crew` (
  `Crew_Member_Id` int(11) NOT NULL,
  `Flight_Schedule_id` int(11) NOT NULL,
  PRIMARY KEY (`Crew_Member_Id`,`Flight_Schedule_id`),
  KEY `flight_schedule_crew_idx` (`Flight_Schedule_id`),
  CONSTRAINT `crew_flight_schedule` FOREIGN KEY (`Crew_Member_Id`) REFERENCES `crew` (`Crew_Member_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flight_schedule_crew` FOREIGN KEY (`Flight_Schedule_id`) REFERENCES `flight_schedule` (`Flight_Schedule_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_schedule_crew`
--

LOCK TABLES `flight_schedule_crew` WRITE;
/*!40000 ALTER TABLE `flight_schedule_crew` DISABLE KEYS */;
INSERT INTO `flight_schedule_crew` VALUES (10001,1),(10002,1),(10003,2),(10004,2),(10005,3),(10006,3),(10001,4),(10002,4),(10003,5),(10004,5),(10005,6),(10006,6);
/*!40000 ALTER TABLE `flight_schedule_crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_schedule_pilot`
--

DROP TABLE IF EXISTS `flight_schedule_pilot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight_schedule_pilot` (
  `Flight_Schedule_id` int(11) NOT NULL,
  `Pilot_id` int(11) NOT NULL,
  PRIMARY KEY (`Flight_Schedule_id`,`Pilot_id`),
  KEY `pilot_id_flight_schedule_idx` (`Pilot_id`),
  CONSTRAINT `Flight_schedule_pilot_id` FOREIGN KEY (`Flight_Schedule_id`) REFERENCES `flight_schedule` (`Flight_Schedule_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pilot_id_flight_schedule` FOREIGN KEY (`Pilot_id`) REFERENCES `pilot` (`Pilot_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_schedule_pilot`
--

LOCK TABLES `flight_schedule_pilot` WRITE;
/*!40000 ALTER TABLE `flight_schedule_pilot` DISABLE KEYS */;
INSERT INTO `flight_schedule_pilot` VALUES (1,1),(4,1),(1,2),(2,3),(5,3),(2,4),(5,4),(3,5),(6,5),(3,6),(6,6);
/*!40000 ALTER TABLE `flight_schedule_pilot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pilot`
--

DROP TABLE IF EXISTS `pilot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pilot` (
  `Pilot_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Pilot_name` varchar(45) NOT NULL,
  `Pilot_Designation_Code` varchar(5) NOT NULL,
  `Mobile` bigint(20) DEFAULT NULL,
  `Qualification` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Pilot_Id`),
  KEY `pilot_designation_code_idx` (`Pilot_Designation_Code`),
  CONSTRAINT `pilot_designation_code` FOREIGN KEY (`Pilot_Designation_Code`) REFERENCES `pilot_designation` (`Pilot_Designation_Code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pilot`
--

LOCK TABLES `pilot` WRITE;
/*!40000 ALTER TABLE `pilot` DISABLE KEYS */;
INSERT INTO `pilot` VALUES (1,'James H. Doolittle','PD1',9999999999,NULL),(2,'Noel Wien','PD2',8888888888,NULL),(3,'Robert A. Hoover','PD2',7777777777,NULL),(4,'Charles A. Lindbergh','PD1',9999988888,NULL),(5,'Charles E. Yeager','PD1',7777788888,NULL),(6,'Scott Crossfield','PD2',7788994455,NULL);
/*!40000 ALTER TABLE `pilot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pilot_designation`
--

DROP TABLE IF EXISTS `pilot_designation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pilot_designation` (
  `Pilot_Designation_Code` varchar(5) NOT NULL,
  `Pilot_Designation_Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Pilot_Designation_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pilot_designation`
--

LOCK TABLES `pilot_designation` WRITE;
/*!40000 ALTER TABLE `pilot_designation` DISABLE KEYS */;
INSERT INTO `pilot_designation` VALUES ('PD1','Captain'),('PD2','First Pilot');
/*!40000 ALTER TABLE `pilot_designation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rest_detail`
--

DROP TABLE IF EXISTS `rest_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rest_detail` (
  `Member_Id` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Rest_Minutes` int(11) NOT NULL,
  PRIMARY KEY (`Member_Id`,`Date`),
  CONSTRAINT `Crew_Member_id_Crew_Rest_Detail` FOREIGN KEY (`Member_Id`) REFERENCES `crew` (`Crew_Member_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rest_detail`
--

LOCK TABLES `rest_detail` WRITE;
/*!40000 ALTER TABLE `rest_detail` DISABLE KEYS */;
INSERT INTO `rest_detail` VALUES (10001,'2018-03-02',530),(10001,'2018-03-04',550),(10002,'2018-03-02',580),(10002,'2018-03-04',640),(10003,'2018-02-26',440),(10003,'2018-03-04',400),(10004,'2018-02-26',670),(10004,'2018-03-04',500),(10005,'2018-01-31',590),(10005,'2018-03-05',590),(10006,'2018-01-31',740),(10006,'2018-03-05',430);
/*!40000 ALTER TABLE `rest_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `User_Name` varchar(45) NOT NULL,
  `Email_Id` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Mobile_No` varchar(45) NOT NULL,
  PRIMARY KEY (`User_Name`),
  UNIQUE KEY `Email_Id_UNIQUE` (`Email_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','pj@gmail.com','admin','8056109998'),('piyush','jp@gmail.com','jangir','8003214011');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'riskassessment'
--

--
-- Dumping routines for database 'riskassessment'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-05 11:40:30
