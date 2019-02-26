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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `userid` bigint(20) NOT NULL,
  `mobile` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (19980208,'17812345678','9136f8f230e20ca9afc410d14c826586','管理员','babycoder@163.com');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1,1,NULL,1,1,1),(2,1,NULL,2,5,2),(3,1,NULL,2,11,2),(4,1,NULL,2,15,3),(5,1,NULL,6,11,5),(6,1,NULL,5,3,3),(7,1,NULL,6,18,1),(8,1,NULL,4,5,1),(9,1,NULL,4,13,6),(12,0,'2017-11-17 11:32:47',24,14,NULL),(13,0,'2017-11-17 20:36:09',25,15,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,3,'C++是C语言的继承，它既可以进行C语言的过程化程序设计，又可以进行以抽象数据类型为特点的基于对象的程序设计，还可以进行以继承和多态为特点的面向对象的程序设计','2017-11-14 22:47:42',1,5),(2,2,'Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念','2017-11-14 22:47:42',1,17),(3,1,'Python是纯粹的自由软件， 源代码和解释器CPython遵循 GPL(GNU General Public License)协议','2017-11-14 22:47:42',2,11),(4,3,'C++不仅拥有计算机高效运行的实用性特征，同时还致力于提高大规模程序的编程质量与程序设计语言的问题描述能力','2017-11-14 22:47:42',3,18),(5,3,'这是一些关于Java工程师的评论，这个职位需要丰富的阅历的和工作经验','2017-11-14 22:47:42',5,2),(6,2,'又要写测试评论，写点啥呢？布吉岛','2017-11-14 22:47:42',6,8),(7,3,'数据挖掘一般是指从大量的数据中通过算法搜索隐藏于其中信息的过程','2017-11-14 22:47:42',6,14),(8,3,'Java具有简单性、面向对象、分布式、健壮性、安全性、平台独立与可移植性、多线程、动态性等特点','2017-11-14 22:47:42',6,17),(9,2,'Python 已经成为最受欢迎的程序设计语言之一','2017-11-14 22:47:42',1,10),(10,3,'<p>.NET是 Microsoft XML Web services 平台</p>','2017-11-14 22:47:42',2,9),(19,3,'IndexRecruit大透明弱弱地评论一句~','2017-11-16 22:37:26',7,13),(21,3,'学习数据挖掘小白应该从哪个方面入手？萌新小白求教~','2017-11-17 11:32:32',24,14),(23,3,'<p><span><u><b>XML Web services 允许应用程序通过 Internet 进行通讯和共享数据，而不管所采用的是哪种操作系统、设备或编程语言</b></u></span></p>','2017-11-17 21:45:42',25,9),(24,3,'<i>阿里今年双十一赚大发了。。。</i>','2017-11-17 21:56:58',25,1);
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
INSERT INTO `company` VALUES (1,'阿里巴巴',1,'阿里巴巴网络技术有限公司（简称：阿里巴巴集团）是以曾担任英语教师的马云为首的18人于1999年在浙江杭州创立',1,'AL85685'),(2,'滴滴出行',2,'滴滴出行是涵盖出租车、专车、 快车、顺风车、代驾及 大巴等多项业务在内的一站式出行平台，2015年9月9日由“滴滴打车”更名而来',1,'DD36526'),(3,'搜狐媒体',3,'搜狐公司是中国领先的互联网品牌，是中国最主要的新闻提供商，搜狐的网络资产给众多用户在信息、娱乐以及交流方面提供了广泛的选择',1,'SH74524'),(4,'京东',4,'京东致力于成为一家为社会创造最大价值的公司。经过13年砥砺前行，京东在商业领域一次又一次突破创新，取得了跨越式发展',1,'JD86635'),(5,'网易',5,'网易公司（NASDAQ: NTES）是中国的互联网公司，利用互联网技术，加强人与人之间信息的交流和共享，实现“网聚人的力量”',1,'WY53265'),(6,'爱奇艺',6,'自成立伊始，爱奇艺坚持“悦享品质”的公司理念，以“用户体验”为生命，专注为用户提供清晰、流畅、界面友好的观映体验',1,'AQ86532');
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
INSERT INTO `department` VALUES (1,'技术部','负责对新产品的设计和开发的控制，编制各类技术文件',1),(2,'行政部','负责行政事务和办公事务',1),(3,'市场部','对销售预测，提出未来市场的分析、发展方向和规划',1),(4,'技术部','负责对新产品的设计和开发的控制，编制各类技术文件',2),(5,'行政部','负责行政事务和办公事务',2),(6,'市场部','对销售预测，提出未来市场的分析、发展方向和规划',2),(7,'技术部','负责对新产品的设计和开发的控制，编制各类技术文件',3),(8,'行政部','负责行政事务和办公事务',3),(9,'市场部','对销售预测，提出未来市场的分析、发展方向和规划',3),(10,'技术部','负责对新产品的设计和开发的控制，编制各类技术文件',4),(11,'行政部','负责行政事务和办公事务',4),(12,'市场部','对销售预测，提出未来市场的分析、发展方向和规划',4),(13,'技术部','负责对新产品的设计和开发的控制，编制各类技术文件',5),(14,'行政部','负责行政事务和办公事务',5),(15,'市场部','对销售预测，提出未来市场的分析、发展方向和规划',5),(16,'技术部','负责对新产品的设计和开发的控制，编制各类技术文件',6),(17,'行政部','负责行政事务和办公事务',6),(18,'市场部','对销售预测，提出未来市场的分析、发展方向和规划',6);
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favor`
--

LOCK TABLES `favor` WRITE;
/*!40000 ALTER TABLE `favor` DISABLE KEYS */;
INSERT INTO `favor` VALUES (1,1,16),(2,1,2),(3,1,8),(4,2,2),(5,2,15),(6,2,3),(7,2,5),(8,3,1),(9,4,6),(10,4,8),(11,4,8),(12,6,10),(13,6,11),(14,6,18),(17,24,2);
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
INSERT INTO `position` VALUES (1,'Java工程师','熟练使用RPC框架，具备相关的分布式开发经验',3,'北京市',12000,7000,'2017-10-27',NULL,1,142,1,1,1),(2,'Java工程师','接收应届实习生，实习期满应聘上岗接收应届实习生，实习期满应聘上岗收应届实习生，实习期满应聘上岗',5,'上海市',16000,11000,'2017-11-07',NULL,1,121,1,4,2),(3,'Java工程师','有扎实的java基础，熟悉分布式、缓存、异步消息等原理和应用',15,'天津市',13000,10000,'2017-09-30',NULL,1,3,1,7,3),(4,'Java工程师','JAVA WEB方向2年+经验',2,'南京市',16000,12000,'2017-09-25',NULL,1,7,1,10,4),(5,'C++工程师','可接收计算机相关专业应届生，表现优秀者加薪转正',30,'南京市',8000,5000,'2017-10-16',NULL,1,13,2,10,4),(6,'C++工程师','3-5年工作经验，计算机相关专业毕业',1,'上海市',16000,8000,'2017-11-03',NULL,1,4,2,4,2),(7,'PHP工程师','一年以上PHP开发经验 （项目经验丰富的，也可以升级为高级开发工程师）',10,'上海市',11000,8000,'2017-11-10',NULL,1,82,3,4,2),(8,'PHP工程师','熟悉LNMP/WNMP开发环境 , 熟练使用Yaf, Yii, ThinkPHP等一种或多种框架',5,'上海市',12000,7000,'2017-11-01',NULL,1,1,3,4,2),(9,'.NET工程师','熟悉WinForm/WPF窗体开发并有实际项目经验',2,'北京市',13000,11000,'2017-11-02',NULL,1,11,4,1,1),(10,'Python数据分析','熟练使用Linux，可以快速上手编写shell、powershell、cmd等操作系统脚本',2,'北京市',23000,18000,'2017-10-09',NULL,1,43,5,1,1),(11,'Python开发','4年以上互联网产品后台研发经验，2年以上后台构架经验',2,'北京市',22000,18000,'2017-09-23',NULL,1,0,5,1,1),(12,'Python开发','精通Python，2年或以上Python项目经验',3,'天津市',16000,14000,'2017-07-27',NULL,1,35,5,7,3),(13,'数据挖掘工程师','熟悉 Linux平台上的编程环境，精通Java开发，精通 Python/Shell等脚本语言',12,'天津市',22000,15000,'2017-11-05',NULL,1,4,6,7,3),(14,'数据挖掘工程师','熟悉Hadoop、Hive、Spark、流式计算、实时计算等大数据相关技术者优先，熟悉时序挖掘、文本挖掘、网络挖掘等优先',2,'北京市',32000,28000,'2017-11-06',NULL,1,7,6,1,1),(15,'数据挖掘工程师','精通Python，熟悉PHP/GO/Java/C++/C等语言中的一种或几种',2,'杭州市',26000,14000,'2017-11-08',NULL,1,20,6,13,5),(16,'Java工程师','熟悉Spring、Freemark、Struts、Hibernate 等开源框架',13,'杭州市',18000,15000,'2017-11-11',NULL,1,4,1,17,6),(17,'Java后端开发','熟练使用Mybatis，SpringMVC，SpringCloud等框架',5,'杭州市',21000,18000,'2017-10-23',NULL,1,3,1,13,5),(18,'C++后端开发','熟练linux系统操作，熟练gcc,gdb,vim, eclipse等开发工具',5,'北京市',12000,9000,'2017-10-28',NULL,1,0,2,1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume`
--

LOCK TABLES `resume` WRITE;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
INSERT INTO `resume` VALUES (1,NULL,NULL,NULL,NULL,NULL,1),(2,NULL,NULL,NULL,NULL,NULL,2),(3,NULL,NULL,NULL,NULL,NULL,3),(4,NULL,NULL,NULL,NULL,NULL,4),(5,NULL,NULL,NULL,NULL,NULL,5),(6,NULL,NULL,NULL,NULL,NULL,6),(7,NULL,NULL,NULL,NULL,NULL,7),(8,NULL,NULL,NULL,NULL,NULL,8),(9,NULL,NULL,NULL,NULL,NULL,9),(10,NULL,NULL,NULL,NULL,NULL,10),(11,NULL,NULL,NULL,NULL,NULL,11),(12,NULL,NULL,NULL,NULL,NULL,12),(13,NULL,NULL,NULL,NULL,NULL,13),(14,NULL,NULL,NULL,NULL,NULL,14),(17,NULL,NULL,NULL,NULL,NULL,17),(21,NULL,NULL,NULL,NULL,NULL,21),(24,'写点什么。。。','写点什么。。。','写点什么。。。','写点什么。。。','写点什么。。。',24),(25,'专业能力么。。。也就能悄悄代码','还没毕业，也没啥实习经历~','无实际工作经历~','拿过几次奖学金吧','有一份别太累稳定的工作就好。。。',25);
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
  `gender` int(11) DEFAULT NULL,
  `birthYear` int(11) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `eduDegree` varchar(50) DEFAULT NULL,
  `graduation` varchar(100) DEFAULT NULL,
  `graYear` int(11) DEFAULT NULL,
  `major` varchar(50) DEFAULT NULL,
  `dirDesire` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `user_id_UNIQUE` (`userId`),
  UNIQUE KEY `user_mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'13754258565','4QrcOUm6Wau+VuBX8g+IPg==','夏高兴',0,NULL,'云淡天高','xiagaoxin@163.com','北京市','北京市','硕士','中南财经政法大学',NULL,NULL,3),(2,'16873315255','4QrcOUm6Wau+VuBX8g+IPg==','沈茂德',0,NULL,'冷酷的云','shenmaode@163.com','北京市','北京市','本科','湖南师范大学',NULL,NULL,3),(3,'16535356412','4QrcOUm6Wau+VuBX8g+IPg==','杜文瑞',0,NULL,'我心寂寞','duwenrui@163.com','上海市','上海市','本科','东北财经大学',NULL,NULL,1),(4,'15785658371','4QrcOUm6Wau+VuBX8g+IPg==','彭友卉',0,NULL,'尘封记忆','pengyouhui@163.com','上海市','上海市','本科','西南大学',NULL,NULL,2),(5,'16735259632','4QrcOUm6Wau+VuBX8g+IPg==','崔谷槐',0,NULL,'飘雪无垠','cuiguhuai@163.com','上海市','上海市','本科','苏州大学',NULL,NULL,2),(6,'17898763255','4QrcOUm6Wau+VuBX8g+IPg==','魏茂材',0,NULL,'风过无痕','weimaocai@163.com','广东省','广州市','本科','西北大学',NULL,NULL,6),(7,'17563522636','4QrcOUm6Wau+VuBX8g+IPg==','侯成文',0,1997,'星月相随','huochengwen','湖北省','武汉市','硕士','上海财经大学',2019,'国际金融',3),(8,'18936258863','4QrcOUm6Wau+VuBX8g+IPg==','邵夜云',1,NULL,'低调沉默者','shaoyeyun@163.com','北京市','北京市','本科','江苏大学',NULL,NULL,2),(9,'13752533625','4QrcOUm6Wau+VuBX8g+IPg==','方彭湃',1,NULL,'梦醒童话','fangpengpai@163.com','天津市','天津市','硕士','西南政法大学',NULL,NULL,4),(10,'15763968252','4QrcOUm6Wau+VuBX8g+IPg==','熊新觉',1,NULL,'咖啡的味道','xiongxinjue@163.com','广东省','广州市','本科','重庆医科大学',NULL,NULL,3),(11,'13685259986','4QrcOUm6Wau+VuBX8g+IPg==','肖又香',1,NULL,'悬世尘埃','xiaoyouxiang@163.com','浙江省','杭州市','大专','福建师范大学',NULL,NULL,2),(12,'15788875236','4QrcOUm6Wau+VuBX8g+IPg==','严经纶',0,NULL,'冰封夕阳','yanjinlun@163.com','浙江省','杭州市','本科','广州中医药大学',NULL,NULL,6),(13,'18766635865','4QrcOUm6Wau+VuBX8g+IPg==','邓和豫',1,NULL,'隐水酣龙','dengheyu@163.com','浙江省','杭州市','本科','哈尔滨工程大学',NULL,NULL,1),(14,'15623527861','4QrcOUm6Wau+VuBX8g+IPg==','邓雪风',0,NULL,'一顿小皮锤','dengxuefeng@163.com','江苏省','南京市','本科','暨南大学',NULL,NULL,4),(17,'15726928003','4QrcOUm6Wau+VuBX8g+IPg==','龟龟',0,NULL,'龟龟','guigui@163.com','广东省','中山市','本科','华东理工大学',NULL,NULL,0),(21,'13957336750','4QrcOUm6Wau+VuBX8g+IPg==','木木',0,NULL,'木木','mumu@163.com','湖北省','武汉市','本科','天津科技大学',NULL,NULL,0),(24,'17863954768','4QrcOUm6Wau+VuBX8g+IPg==','轩',0,1997,'轩','xuan@163.com','山东省','青岛市','本科','青岛科技大学',2019,'软件工程',2),(25,'17812345687','4QrcOUm6Wau+VuBX8g+IPg==','青柠',0,1997,'青柠','babycoder@foxmail.com','浙江省','杭州市','本科','青岛科技大学',2019,'软件',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2017-11-17 22:01:49
