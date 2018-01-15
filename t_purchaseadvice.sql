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

 Date: 09/01/2018 21:53:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_purchaseadvice
-- ----------------------------
DROP TABLE IF EXISTS `t_purchaseadvice`;
CREATE TABLE `t_purchaseadvice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `productId` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `productName` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `inventoryLevel` int(11) DEFAULT NULL,
  `demand` int(11) DEFAULT NULL,
  `advice` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
