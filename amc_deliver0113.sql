/*
 Navicat Premium Data Transfer

 Source Server         : mydatabase
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : amc

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 14/01/2018 01:10:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_deliver
-- ----------------------------
DROP TABLE IF EXISTS `t_deliver`;
CREATE TABLE `t_deliver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deliverId` varchar(255) NOT NULL,
  `prepareId` varchar(255) NOT NULL,
  `createTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `amountMoney` decimal(65,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_deliverdetail
-- ----------------------------
DROP TABLE IF EXISTS `t_deliverdetail`;
CREATE TABLE `t_deliverdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deliverId` varchar(255) NOT NULL,
  `deliverdetailId` varchar(255) NOT NULL,
  `productId` varchar(255) NOT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `factoryId` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `shortNum` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
