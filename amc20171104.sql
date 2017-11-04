-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: amc
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `registerTime` datetime NOT NULL,
  `roleId` int(11) DEFAULT NULL,
  `organizationId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gex1lmaqpg0ir5g1f5eftyaa1` (`username`),
  KEY `IDX_account_roleId` (`roleId`),
  KEY `IDX_account_organizationId` (`organizationId`),
  KEY `FK_account_role` (`roleId`),
  KEY `FK_account_organization` (`organizationId`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`organizationId`) REFERENCES `organization` (`id`),
  CONSTRAINT `account_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,1,'MissWhen...','liukemng@sina.com','liukemng','7FE8A1AE00C5C2A0785A5071DF996D8C','2014-04-02 23:26:40',1,1),(2,0,0,'AAA','123@123.com','AAAA','562363ED4A17A4894D4162A0E64DB7C4','2014-04-03 14:38:38',NULL,NULL),(3,0,0,'WWWWW','123@123.com','FFFFFF','CFBDA3E623045D2C2130D653909397E0','2014-04-03 14:39:00',NULL,NULL),(4,0,0,'TTTT','123@123.com','SSSDD','50F86F5725447FE015467D74A7D7DB11','2014-04-03 14:39:18',NULL,NULL),(5,0,0,'OOOO','123@123.com','LLLL','1F5297672E6BF686ED906F69DBD9B509','2014-04-03 14:39:36',NULL,NULL),(6,0,0,'CCCCC','123@123.com','RRRR','DDF8448BC7A6BEC245B7293CE4EBDA74','2014-04-03 14:40:16',NULL,NULL),(7,0,0,'NNNN','123@123.com','MMMM','A9C51ACEFD41BB51F1A7546B358BF1BD','2014-04-03 14:40:32',NULL,NULL),(8,0,0,'ZZZZZ','123@123.com','TTTTTT','8B5CC63F5053E982AD5EB6A461F69209','2014-04-03 14:40:49',NULL,NULL),(9,0,0,'KKKKK','123@123.com','PPPPP','78EB5B2DDCC23A6E13A48AEF3B28C87D','2014-04-03 14:41:10',NULL,NULL),(10,0,0,'XXXXX','123@123.com','DDDF','AF8B0A62C9844F4B4E339230D85ECBE0','2014-04-03 14:41:49',NULL,NULL),(11,0,0,'RTYV','123@123.com','WWW','B04874C43B023CB87F753717D73C4D6D','2014-04-03 14:42:07',NULL,NULL),(12,0,1,'fc','fc@163.com','fc','f79a121e29600f0fb867a6c4eea6b29e','2017-05-02 18:09:06',1,1),(13,0,1,'wx','wenxin_199511@126.com','wenxin','c0bbf3c4f4ae2172be3aaeb8a4852391','2017-10-26 17:04:10',1,1),(14,1,1,'赵雅萍','zyp@qq.com','zyp','a9e2e2a3da6cf7dba8727de483b7c917','2017-11-04 09:46:55',2,NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `levelCode` varchar(255) CHARACTER SET utf8 NOT NULL,
  `position` int(11) NOT NULL,
  `theValue` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 NOT NULL,
  `matchUrl` varchar(255) CHARACTER SET utf8 NOT NULL,
  `itemIcon` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_authority_parentId_authority` (`parentId`),
  CONSTRAINT `authority_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,3,0,'欢迎使用','1',0,'1','/home','^/home$','icon-home',NULL),(2,2,0,'首页','1,2',0,'1','/home/index','/home/index','',1),(3,2,0,'系统设置','3',0,'2','/setting','^/setting$','icon-cogs',NULL),(4,4,0,'用户管理','3,4',0,'1','/account/list','^/account$','',3),(5,1,0,'用户列表','3,4,5',0,'1','/account/list','/account/list','',4),(6,2,0,'账户绑定','3,4,6',0,'2','/account/authorize','/account/authorize','',4),(7,1,0,'角色管理','3,7',0,'2','/role/list','^/role$','',3),(8,1,0,'角色列表','3,7,8',0,'1','/role/list','/role/list','',7),(9,1,0,'权限绑定','3,7,9',0,'2','/role/bind','/role/bind','',7),(10,1,0,'权限管理','3,10',0,'3','/authority/chain','^/authority$','',3),(11,1,0,'权限添加','3,10,11',0,'1','/authority/add','/authority/add','',10),(12,1,0,'权限编辑','3,10,12',0,'2','/authority/edit','/authority/edit','',10),(13,1,0,'权限删除','3,10,13',0,'3','/authority/delete','/authority/delete','',10),(14,1,0,'组织机构管理','3,14',0,'4','/organization/chain','^/organization$','',3),(15,1,0,'组织机构树','3,14,15',0,'1','/organization/chain','/organization/chain','',14),(16,1,0,'组织机构添加','3,14,16',0,'2','/organization/add','/organization/add','',14),(17,1,0,'组织机构编辑','3,14,17',0,'3','/organization/edit','/organization/edit','',14),(18,1,0,'组织机构删除','3,14,18',0,'4','/organization/delete','/organization/delete','',14),(19,1,0,'权限树','3,10,19',0,'4','/authority/chain','/authority/chain','',10),(21,1,0,'微信管理','21',0,'3','/weixinsend','^/weixinsend$','icon-comments',NULL),(22,1,0,'创建菜单','21,22',0,'1','/weixinsend/createmenu','/weixinsend/createmenu','',21),(23,4,0,'查询菜单','21,23',0,'2','/weixinsend/getmenu','/weixinsend/getmenu','',21),(24,2,0,'销售子系统','24',0,'4','/sales','^/sales$','',NULL),(25,1,0,'销售订单管理','24,25',0,'1','/sales/list','^/sales/list$','',24),(26,2,0,'基础信息管理','26',0,'6','/basedata','^/basedata$','icon-folder-close',NULL),(27,4,0,'产品信息','26,27',0,'1','/basedata/product','^/basedata$','',26),(28,10,0,'顾客信息','26,28',0,'2','/basedata/customerinfo','^/basedata/customerinfo','',26),(29,2,0,'供应商信息','26,29',0,'2','/basedata/vendor','^/basedata$','',26),(30,1,0,'采购子系统','30',0,'4','/purchase/list','^/purchase/list$','',NULL),(31,1,0,'采购订单管理','30,31',0,'1','/purchase/orderlist','/purchase/orderlist','',30),(32,1,0,'采购单据管理','30,32',0,'2','/purchase/ordercontract','/purchase/ordercontract','',30),(33,1,0,'销售合同管理','24,33',0,'2','/sales/salescontract','/sales/salescontract','',24),(34,1,0,'库存子系统','34',0,'7','/inventory/list','/inventory/list','',NULL),(35,1,0,'备货单管理','34,35',0,'1','/inventory/prepare','/inventory/prepare','',34),(36,1,0,'进货单管理','34,36',0,'2','/inventory/stockin','/inventory/stockin','',34),(37,1,0,'出库单管理','34,37',0,'3','/inventory/stockout','/inventory/stockout','',34),(38,1,0,'包装发货管理','34,38',0,'4','/inventory/package','/inventory/package','',34),(39,1,0,'供应商列表','26,29,39',0,'1','/basedata/vendor','/basedata/vendor','',29),(40,1,0,'增加供应商','26,29,40',0,'2','/basedata/vendoradd','/basedata/vendoradd','',29),(41,1,0,'编辑供应商','26,29,41',0,'3','/basedata/vendoredit','/basedata/vendoredit','',29),(42,1,0,'删除供应商','26,29,42',0,'4','/basdata/vendordelete','/basdata/vendordelete','',29),(43,1,0,'新增角色','3,7,43',0,'3','/role/add','/role/add','',7),(44,1,0,'修改角色','3,7,44',0,'4','/role/edit','/role/edit','',7),(45,1,0,'产品列表','26,27,45',0,'1','/basedata/product','/basedata/product','',27),(46,1,0,'增加产品','26,27,46',0,'2','/basedata/productadd','/basedata/productadd','',27),(47,1,0,'编辑产品','26,27,47',0,'3','/basedata/productedit','/basedata/productedit','',27),(48,1,0,'删除产品','26,27,48',0,'4','/basedata/productdelete','/basedata/productdelete','',27);
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `levelCode` varchar(255) NOT NULL,
  `position` int(11) NOT NULL,
  `theValue` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_organization_parentId_organization` (`parentId`),
  CONSTRAINT `organization_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` VALUES (1,1,0,'总机构','1',0,'1',NULL),(4,1,0,'总机构2','4',0,'2',NULL),(5,2,0,'组织机构2-1','4,5',0,'1',4),(6,1,0,'销售科','1,6',0,'1',1),(7,1,0,'供应科','1,7',0,'2',1),(8,1,0,'会计科','1,8',0,'3',1),(10,1,0,'采购科','1,10',0,'4',1);
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,15,'系统管理员',0),(2,2,'顾客',0),(3,0,'仓库管理员',0),(4,0,'销售管理员',0),(5,0,'采购管理员',0),(6,0,'供应商',0),(7,0,'会计',0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_authority`
--

DROP TABLE IF EXISTS `role_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_authority` (
  `roleId` int(11) NOT NULL,
  `authorityId` int(11) NOT NULL,
  KEY `FK_sccf4fx8omb6jlsy2ra75xxer` (`authorityId`),
  KEY `FK_fftr98ew5vtbdpcfetn7xd715` (`roleId`),
  CONSTRAINT `role_authority_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `role_authority_ibfk_2` FOREIGN KEY (`authorityId`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_authority`
--

LOCK TABLES `role_authority` WRITE;
/*!40000 ALTER TABLE `role_authority` DISABLE KEYS */;
INSERT INTO `role_authority` VALUES (2,1),(2,2),(2,26),(2,27),(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,43),(1,44),(1,10),(1,11),(1,12),(1,13),(1,19),(1,14),(1,15),(1,16),(1,17),(1,18),(1,24),(1,25),(1,33),(1,26),(1,27),(1,45),(1,46),(1,47),(1,48),(1,28),(1,29),(1,39),(1,40),(1,41),(1,42),(1,30),(1,31),(1,32),(1,34),(1,35),(1,36),(1,37),(1,38);
/*!40000 ALTER TABLE `role_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_customerinfo`
--

DROP TABLE IF EXISTS `t_customerinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customerinfo` (
  `id` int(10) NOT NULL,
  `englishName` varchar(128) DEFAULT NULL,
  `chineseName` varchar(128) NOT NULL,
  `address` varchar(128) NOT NULL,
  `phonenum` varchar(25) NOT NULL,
  `contactPerson` varchar(128) NOT NULL,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_customerinfo`
--

LOCK TABLES `t_customerinfo` WRITE;
/*!40000 ALTER TABLE `t_customerinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_customerinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product`
--

DROP TABLE IF EXISTS `t_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `productId` varchar(20) COLLATE utf8_bin NOT NULL,
  `productName` varchar(50) COLLATE utf8_bin NOT NULL,
  `productType` varchar(1) COLLATE utf8_bin NOT NULL,
  `productUnit` varchar(10) COLLATE utf8_bin NOT NULL,
  `safeStock` int(11) DEFAULT '0',
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product`
--

LOCK TABLES `t_product` WRITE;
/*!40000 ALTER TABLE `t_product` DISABLE KEYS */;
INSERT INTO `t_product` VALUES (1,1,0,'G001','大螺丝','G','个',100,'');
/*!40000 ALTER TABLE `t_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_vendor`
--

DROP TABLE IF EXISTS `t_vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `vendorId` varchar(20) COLLATE utf8_bin NOT NULL,
  `vendorName` varchar(50) COLLATE utf8_bin NOT NULL,
  `vendorAddr` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `contactPerson` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `vendorTele` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `vendorEmail` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_vendor`
--

LOCK TABLES `t_vendor` WRITE;
/*!40000 ALTER TABLE `t_vendor` DISABLE KEYS */;
INSERT INTO `t_vendor` VALUES (1,4,0,'V0001','螺丝厂','北京市海淀区','陈先生','12689768667','qq@123.com',''),(2,2,0,'V002','车轮厂','天津','李先生','123453578','13@126.com','');
/*!40000 ALTER TABLE `t_vendor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-04 11:42:54
