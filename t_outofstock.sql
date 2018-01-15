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

 Date: 09/01/2018 21:53:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_outofstock
-- ----------------------------
DROP TABLE IF EXISTS `t_outofstock`;
CREATE TABLE `t_outofstock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `outofstockId` varchar(50) CHARACTER SET utf8 NOT NULL,
  `orderId` varchar(20) CHARACTER SET utf8 NOT NULL,
  `customerId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL,
  `fitNum` int(11) DEFAULT NULL,
  `partfitNum` int(11) DEFAULT NULL,
  `outofstockNum` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `note` varchar(1024) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_outofstockdetail
-- ----------------------------
DROP TABLE IF EXISTS `t_outofstockdetail`;
CREATE TABLE `t_outofstockdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `outofstockdetailId` varchar(50) CHARACTER SET utf8 NOT NULL,
  `outofstockId` varchar(50) CHARACTER SET utf8 NOT NULL,
  `productId` varchar(20) CHARACTER SET utf8 NOT NULL,
  `quantityDemand` int(11) NOT NULL,
  `quantitySupplied` int(11) NOT NULL,
  `quantityNeeded` int(11) NOT NULL,
  `operatorName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `operateTime` datetime NOT NULL,
  `status` varchar(20) COLLATE utf8_bin NOT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
