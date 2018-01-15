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

 Date: 09/01/2018 21:53:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_stockin
-- ----------------------------
DROP TABLE IF EXISTS `t_stockin`;
CREATE TABLE `t_stockin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `stockinId` varchar(20) COLLATE utf8_bin NOT NULL,
  `productId` varchar(20) COLLATE utf8_bin NOT NULL,
  `amount` int(11) NOT NULL,
  `vendorId` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `status` varchar(20) COLLATE utf8_bin NOT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
