/*
Navicat MySQL Data Transfer

Source Server         : AMC
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : amcdb

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-01-14 21:39:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_purchasedetail`
-- ----------------------------
DROP TABLE IF EXISTS `t_purchasedetail`;
CREATE TABLE `t_purchasedetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `orderdetailId` varchar(50) COLLATE utf8_bin NOT NULL,
  `orderId` varchar(50) COLLATE utf8_bin NOT NULL,
  `productId` varchar(20) COLLATE utf8_bin NOT NULL,
  `productName` varchar(50) COLLATE utf8_bin NOT NULL,
  `quantity` int(11) NOT NULL,
  `unitPrice` decimal(65,2) NOT NULL,
  `totalPrice` decimal(65,2) NOT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_order_orderId` (`orderId`),
  KEY `IDX_product_productId` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_purchasedetail
-- ----------------------------
INSERT INTO `t_purchasedetail` VALUES ('107', '3', '0', 'S20180109181451D181502', 'S20180109181451', 'G001', '大号轮胎', '2500', '120.00', '300000.00', '');
INSERT INTO `t_purchasedetail` VALUES ('108', '2', '0', 'S20180109181451D181521', 'S20180109181451', 'G002', '大螺丝', '1000', '20.00', '20000.00', '');
INSERT INTO `t_purchasedetail` VALUES ('109', '3', '0', 'S20180109234319D234325', 'S20180109234319', 'G001', '大号轮胎', '789', '7.90', '6233.10', '');
INSERT INTO `t_purchasedetail` VALUES ('110', '0', '0', 'S20180113172356D172421', 'S20180113172356', 'G001', '大号轮胎', '0', '0.00', '0.00', '');
