/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : amc

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-01-11 19:11:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_accounttable`
-- ----------------------------
DROP TABLE IF EXISTS `t_accounttable`;
CREATE TABLE `t_accounttable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `accounttableId` varchar(255) NOT NULL,
  `orderId` varchar(255) NOT NULL,
  `deliverId` varchar(255) NOT NULL,
  `cuikuanId` varchar(255) NOT NULL,
  `customerId` varchar(255) NOT NULL,
  `objection` int(11) DEFAULT NULL,
  `receivable` double DEFAULT NULL,
  `salesBusiness` double DEFAULT NULL,
  `payable` double DEFAULT NULL,
  `purchaseBusiness` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_accounttable
-- ----------------------------
INSERT INTO `t_accounttable` VALUES ('2', null, null, 'at001', 'o001', 'd001', 'c001', 'c001', '0', null, null, null, null);
