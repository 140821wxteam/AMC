/*
Navicat MySQL Data Transfer

Source Server         : cms
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : amc

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-19 22:41:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `customerId` varchar(20) COLLATE utf8_bin NOT NULL,
  `customerName` varchar(50) COLLATE utf8_bin NOT NULL,
  `customerAddr` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `contactPerson` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `customerTele` varchar(13) COLLATE utf8_bin DEFAULT NULL,
  `customerEmail` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('3', '1', '0', 'C0001', '上海五菱汽车修配厂有限公司', '上海市嘉定安亭镇墨玉路北', null, '(021)59577339', null, null);
INSERT INTO `t_customer` VALUES ('4', '1', '0', 'C0002', '杭州袁富汽车修配有限公司', '杭州市双浦镇轮渡路10号', '张幸军', '13357119793', null, null);
INSERT INTO `t_customer` VALUES ('6', '0', '0', 'C0003', '深圳市日兴达汽车修配有限公司', '广东省深圳市福田区滨河路3155号深华工业厂房一楼', '李先生', '0755-83610965', '', '');
