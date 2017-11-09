CREATE DATABASE  IF NOT EXISTS `recruit` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `recruit`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: recruit
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `applicationId` int(11) NOT NULL AUTO_INCREMENT,
  `state` int(11) DEFAULT NULL,
  `recentTime` datetime DEFAULT NULL,
  `resumeId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  `hrId` int(11) DEFAULT NULL,
  PRIMARY KEY (`applicationId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1,1,NULL,1,1,1),(2,1,NULL,2,5,2),(3,1,NULL,2,11,2),(4,1,NULL,2,15,3),(5,1,NULL,6,11,5),(6,1,NULL,5,3,3),(7,1,NULL,6,18,1),(8,1,NULL,4,5,1),(9,1,NULL,4,13,6);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(50) NOT NULL,
  `description` longtext,
  PRIMARY KEY (`categoryId`),
  UNIQUE KEY `categoryName_UNIQUE` (`categoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Java','Java开发'),(2,'C++','C++开发'),(3,'PHP','PHP开发'),(4,'.NET','.NET开发'),(5,'Python','Python开发'),(6,'数据挖掘','数据挖掘');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `content` longtext,
  `releaseTime` datetime DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,3,NULL,NULL,1,5),(2,2,NULL,NULL,1,17),(3,1,NULL,NULL,2,11),(4,3,NULL,NULL,3,18),(5,3,NULL,NULL,5,2),(6,2,NULL,NULL,6,8),(7,3,NULL,NULL,6,14),(8,3,NULL,NULL,6,17),(9,2,NULL,NULL,1,10),(10,3,NULL,NULL,2,9);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `companyId` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(100) DEFAULT NULL,
  `companyLogo` int(11) DEFAULT NULL,
  `description` longtext,
  `state` int(11) DEFAULT NULL,
  `companyCode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'阿里巴巴',1,'阿里巴巴',1,'AL85685'),(2,'滴滴出行',2,'滴滴出行',1,'DD36526'),(3,'搜狐媒体',3,'搜狐媒体',1,'SH74524'),(4,'京东',4,'京东',1,'JD86635'),(5,'网易',5,'网易',1,'WY53265'),(6,'爱奇艺',6,'爱奇艺',1,'AQ86532');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `departmentId` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(50) DEFAULT NULL,
  `description` longtext,
  `companyId` int(11) NOT NULL,
  PRIMARY KEY (`departmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'技术部','技术部',1),(2,'行政部','行政部',1),(3,'市场部','市场部',1),(4,'技术部','技术部',2),(5,'行政部','行政部',2),(6,'市场部','市场部',2),(7,'技术部','技术部',3),(8,'行政部','行政部',3),(9,'市场部','市场部',3),(10,'技术部','技术部',4),(11,'行政部','行政部',4),(12,'市场部','市场部',4),(13,'技术部','技术部',5),(14,'行政部','行政部',5),(15,'市场部','市场部',5),(16,'技术部','技术部',6),(17,'行政部','行政部',6),(18,'市场部','市场部',6);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favor`
--

DROP TABLE IF EXISTS `favor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favor` (
  `favorId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  PRIMARY KEY (`favorId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favor`
--

LOCK TABLES `favor` WRITE;
/*!40000 ALTER TABLE `favor` DISABLE KEYS */;
INSERT INTO `favor` VALUES (1,1,16),(2,1,2),(3,1,8),(4,2,2),(5,2,15),(6,2,3),(7,2,5),(8,3,1),(9,4,6),(10,4,8),(11,4,8),(12,6,10),(13,6,11),(14,6,18);
/*!40000 ALTER TABLE `favor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr`
--

DROP TABLE IF EXISTS `hr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr` (
  `hrId` int(11) NOT NULL AUTO_INCREMENT,
  `hrMobile` varchar(11) NOT NULL,
  `hrPassword` varchar(500) NOT NULL,
  `hrName` varchar(50) DEFAULT NULL,
  `hrEmail` varchar(50) DEFAULT NULL,
  `description` longtext,
  `departmentId` int(11) NOT NULL,
  PRIMARY KEY (`hrId`),
  UNIQUE KEY `mobile_UNIQUE` (`hrMobile`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr`
--

LOCK TABLES `hr` WRITE;
/*!40000 ALTER TABLE `hr` DISABLE KEYS */;
INSERT INTO `hr` VALUES (1,'15786586352','e10adc3949ba59abbe56e057f20f883e','董一鸣','dongyiming@163.com','行政部HR',2),(2,'13685653625','e10adc3949ba59abbe56e057f20f883e','张帆','zhangfan@163.com','行政部HR',5),(3,'18596475235','e10adc3949ba59abbe56e057f20f883e','李斌','libin@163.com','行政部HR',8),(4,'16785253625','e10adc3949ba59abbe56e057f20f883e','王语意','wangyuyi@163.com','行政部HR',11),(5,'17865253625','e10adc3949ba59abbe56e057f20f883e','李星泽','lixingze@163.com','行政部HR',14),(6,'13958726395','e10adc3949ba59abbe56e057f20f883e','程瑜','chengyu@163.com','行政部HR',17);
/*!40000 ALTER TABLE `hr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `positionId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `requirement` longtext,
  `quantity` int(11) DEFAULT NULL,
  `workCity` varchar(50) DEFAULT NULL,
  `salaryUp` int(11) DEFAULT NULL,
  `salaryDown` int(11) DEFAULT NULL,
  `releaseDate` date DEFAULT NULL,
  `validDate` date DEFAULT NULL,
  `statePub` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT '0',
  `categoryId` int(11) NOT NULL,
  `departmentId` int(11) NOT NULL,
  `hrIdPub` int(11) NOT NULL,
  PRIMARY KEY (`positionId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'Java工程师',NULL,3,'北京',7500,12000,NULL,NULL,1,0,1,1,1),(2,'Java工程师',NULL,5,'上海',10500,16000,NULL,NULL,1,0,1,4,2),(3,'Java工程师',NULL,15,'天津',7000,13500,NULL,NULL,1,0,1,7,3),(4,'Java工程师',NULL,2,'南京',7000,13500,NULL,NULL,1,0,1,10,4),(5,'C++工程师',NULL,30,'南京',5000,8000,NULL,NULL,1,0,2,10,4),(6,'C++工程师',NULL,1,'上海',12500,16500,NULL,NULL,1,0,2,4,2),(7,'PHP工程师',NULL,10,'上海',16000,18000,NULL,NULL,1,0,3,4,2),(8,'PHP工程师',NULL,5,'上海',5500,6500,NULL,NULL,1,0,3,4,2),(9,'.NET工程师',NULL,2,'北京',7800,8600,NULL,NULL,1,0,4,1,1),(10,'Python数据分析师',NULL,2,'北京',8500,12500,NULL,NULL,1,0,5,1,1),(11,'Python开发',NULL,2,'北京',9500,12500,NULL,NULL,1,0,5,1,1),(12,'Python开发',NULL,3,'天津',10500,12500,NULL,NULL,1,0,5,7,3),(13,'数据挖掘工程师',NULL,12,'天津',7500,13500,NULL,NULL,1,0,6,7,3),(14,'数据挖掘工程师',NULL,2,'北京',7500,12500,NULL,NULL,1,0,6,1,1),(15,'数据挖掘工程师',NULL,2,'杭州',6500,10500,NULL,NULL,1,0,6,13,5),(16,'Java工程师',NULL,13,'杭州',3500,7500,NULL,NULL,1,0,1,17,6),(17,'JavaWEB后台开发',NULL,5,'杭州',4500,8500,NULL,NULL,1,0,1,13,5),(18,'C++后台开发',NULL,5,'北京',8500,11500,NULL,NULL,1,0,2,1,1);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resume`
--

DROP TABLE IF EXISTS `resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resume` (
  `resumeId` int(11) NOT NULL AUTO_INCREMENT,
  `ability` longtext,
  `internship` longtext,
  `workExperience` longtext,
  `certificate` longtext,
  `jobDesire` longtext,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`resumeId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume`
--

LOCK TABLES `resume` WRITE;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
INSERT INTO `resume` VALUES (1,NULL,NULL,NULL,NULL,NULL,1),(2,NULL,NULL,NULL,NULL,NULL,2),(3,NULL,NULL,NULL,NULL,NULL,3),(4,NULL,NULL,NULL,NULL,NULL,4),(5,NULL,NULL,NULL,NULL,NULL,5),(6,NULL,NULL,NULL,NULL,NULL,6),(7,NULL,NULL,NULL,NULL,NULL,7),(8,NULL,NULL,NULL,NULL,NULL,8),(9,NULL,NULL,NULL,NULL,NULL,9),(10,NULL,NULL,NULL,NULL,NULL,10),(11,NULL,NULL,NULL,NULL,NULL,11),(12,NULL,NULL,NULL,NULL,NULL,12),(13,NULL,NULL,NULL,NULL,NULL,13),(14,NULL,NULL,NULL,NULL,NULL,14);
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) NOT NULL,
  `password` varchar(500) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `eduDegree` varchar(50) DEFAULT NULL,
  `graduation` varchar(100) DEFAULT NULL,
  `dirDesire` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `user_id_UNIQUE` (`userId`),
  UNIQUE KEY `user_mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'13754258565','e10adc3949ba59abbe56e057f20f883e','夏高兴','云淡天高','xiagaoxin@163.com','北京','北京','硕士','中南财经政法大学',3),(2,'16873315255','e10adc3949ba59abbe56e057f20f883e','沈茂德','冷酷的云','shenmaode@163.com','北京','北京','本科','湖南师范大学',3),(3,'16535356412','e10adc3949ba59abbe56e057f20f883e','杜文瑞','我心寂寞','duwenrui@163.com','上海','上海','本科','东北财经大学',1),(4,'15785658371','e10adc3949ba59abbe56e057f20f883e','彭友卉','尘封记忆','pengyouhui@163.com','上海','上海','本科','西南大学',2),(5,'16735259632','e10adc3949ba59abbe56e057f20f883e','崔谷槐','飘雪无垠','cuiguhuai@163.com','上海','上海','本科','苏州大学',2),(6,'17898763255','e10adc3949ba59abbe56e057f20f883e','魏茂材','风过无痕','weimaocai@163.com','广东','广州','本科','西北大学',6),(7,'17563522636','e10adc3949ba59abbe56e057f20f883e','侯成文','星月相随','huochengwen','湖北','武汉','硕士','上海财经大学',5),(8,'18936258863','e10adc3949ba59abbe56e057f20f883e','邵夜云','低调沉默者','shaoyeyun@163.com','北京','北京','本科','江苏大学',2),(9,'13752533625','e10adc3949ba59abbe56e057f20f883e','方彭湃','梦醒童话','fangpengpai@163.com','天津','天津','硕士','西南政法大学',4),(10,'15763968252','e10adc3949ba59abbe56e057f20f883e','熊新觉','咖啡的味道','xiongxinjue@163.com','广东','广州','本科','重庆医科大学',3),(11,'13685259986','e10adc3949ba59abbe56e057f20f883e','肖又香','悬世尘埃','xiaoyouxiang@163.com','浙江','杭州','大专','福建师范大学',2),(12,'15788875236','e10adc3949ba59abbe56e057f20f883e','严经纶','冰封夕阳','yanjinlun@163.com','浙江','杭州','本科','广州中医药大学',6),(13,'18766635865','e10adc3949ba59abbe56e057f20f883e','邓和豫','隐水酣龙','dengheyu@163.com','浙江','杭州','本科','哈尔滨工程大学',1),(14,'15623527861','e10adc3949ba59abbe56e057f20f883e','邓雪风','一顿小皮锤','dengxuefeng@163.com','江苏','南京','本科','暨南大学',4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


drop table if exists `admin`;
create table `admin` (
  `userid` bigint NOT NULL,
  `mobile` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员信息';

insert into `admin` values (19980208,'17806228320',md5('java01'),'管理员','lb19961218@163.com');

--
-- Dumping events for database 'recruit'
--

--
-- Dumping routines for database 'recruit'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-05 22:54:30
