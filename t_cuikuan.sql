/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : amc

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-01-12 17:32:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_cuikuan`
-- ----------------------------
DROP TABLE IF EXISTS `t_cuikuan`;
CREATE TABLE `t_cuikuan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cuikuanId` varchar(255) COLLATE utf8_bin NOT NULL,
  `deliverId` varchar(255) COLLATE utf8_bin NOT NULL,
  `cuikuanObjection` int(11) DEFAULT NULL,
  `customerId` varchar(255) COLLATE utf8_bin NOT NULL,
  `orderId` varchar(255) COLLATE utf8_bin NOT NULL,
  `orderReceiveDate` date DEFAULT NULL,
  `amountMoney` double DEFAULT NULL,
  `orderdetailid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `receiveDate` date DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_cuikuan
-- ----------------------------
INSERT INTO `t_cuikuan` VALUES ('2', 'c001', 'd001', '0', 'c001', 'o001', '2018-01-12', '20', 'od001', '2018-01-12 17:27:35', '2018-01-12', '0', '0');
