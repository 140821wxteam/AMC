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

 Date: 21/11/2017 19:19:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
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

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES (1, 1, 1, 'MissWhen...', 'liukemng@sina.com', 'liukemng', '7FE8A1AE00C5C2A0785A5071DF996D8C', '2014-04-02 23:26:40', 1, 1);
INSERT INTO `account` VALUES (2, 0, 0, 'AAA', '123@123.com', 'AAAA', '562363ED4A17A4894D4162A0E64DB7C4', '2014-04-03 14:38:38', NULL, NULL);
INSERT INTO `account` VALUES (3, 0, 0, 'WWWWW', '123@123.com', 'FFFFFF', 'CFBDA3E623045D2C2130D653909397E0', '2014-04-03 14:39:00', NULL, NULL);
INSERT INTO `account` VALUES (4, 0, 0, 'TTTT', '123@123.com', 'SSSDD', '50F86F5725447FE015467D74A7D7DB11', '2014-04-03 14:39:18', NULL, NULL);
INSERT INTO `account` VALUES (5, 0, 0, 'OOOO', '123@123.com', 'LLLL', '1F5297672E6BF686ED906F69DBD9B509', '2014-04-03 14:39:36', NULL, NULL);
INSERT INTO `account` VALUES (6, 0, 0, 'CCCCC', '123@123.com', 'RRRR', 'DDF8448BC7A6BEC245B7293CE4EBDA74', '2014-04-03 14:40:16', NULL, NULL);
INSERT INTO `account` VALUES (7, 0, 0, 'NNNN', '123@123.com', 'MMMM', 'A9C51ACEFD41BB51F1A7546B358BF1BD', '2014-04-03 14:40:32', NULL, NULL);
INSERT INTO `account` VALUES (8, 0, 0, 'ZZZZZ', '123@123.com', 'TTTTTT', '8B5CC63F5053E982AD5EB6A461F69209', '2014-04-03 14:40:49', NULL, NULL);
INSERT INTO `account` VALUES (9, 0, 0, 'KKKKK', '123@123.com', 'PPPPP', '78EB5B2DDCC23A6E13A48AEF3B28C87D', '2014-04-03 14:41:10', NULL, NULL);
INSERT INTO `account` VALUES (10, 0, 0, 'XXXXX', '123@123.com', 'DDDF', 'AF8B0A62C9844F4B4E339230D85ECBE0', '2014-04-03 14:41:49', NULL, NULL);
INSERT INTO `account` VALUES (11, 0, 0, 'RTYV', '123@123.com', 'WWW', 'B04874C43B023CB87F753717D73C4D6D', '2014-04-03 14:42:07', NULL, NULL);
INSERT INTO `account` VALUES (12, 0, 1, 'fc', 'fc@163.com', 'fc', 'f79a121e29600f0fb867a6c4eea6b29e', '2017-05-02 18:09:06', 1, 1);
INSERT INTO `account` VALUES (13, 0, 1, 'wx', 'wenxin_199511@126.com', 'wenxin', 'c0bbf3c4f4ae2172be3aaeb8a4852391', '2017-10-26 17:04:10', 1, 1);
INSERT INTO `account` VALUES (14, 1, 1, '赵雅萍', 'zyp@qq.com', 'zyp', 'a9e2e2a3da6cf7dba8727de483b7c917', '2017-11-04 09:46:55', 2, NULL);
COMMIT;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of authority
-- ----------------------------
BEGIN;
INSERT INTO `authority` VALUES (1, 3, 0, '欢迎使用', '1', 0, '1', '/home', '^/home$', 'icon-home', NULL);
INSERT INTO `authority` VALUES (2, 2, 0, '首页', '1,2', 0, '1', '/home/index', '/home/index', '', 1);
INSERT INTO `authority` VALUES (3, 2, 0, '系统设置', '3', 0, '2', '/setting', '^/setting$', 'icon-cogs', NULL);
INSERT INTO `authority` VALUES (4, 4, 0, '用户管理', '3,4', 0, '1', '/account/list', '^/account$', '', 3);
INSERT INTO `authority` VALUES (5, 1, 0, '用户列表', '3,4,5', 0, '1', '/account/list', '/account/list', '', 4);
INSERT INTO `authority` VALUES (6, 2, 0, '账户绑定', '3,4,6', 0, '2', '/account/authorize', '/account/authorize', '', 4);
INSERT INTO `authority` VALUES (7, 1, 0, '角色管理', '3,7', 0, '2', '/role/list', '^/role$', '', 3);
INSERT INTO `authority` VALUES (8, 1, 0, '角色列表', '3,7,8', 0, '1', '/role/list', '/role/list', '', 7);
INSERT INTO `authority` VALUES (9, 1, 0, '权限绑定', '3,7,9', 0, '2', '/role/bind', '/role/bind', '', 7);
INSERT INTO `authority` VALUES (10, 1, 0, '权限管理', '3,10', 0, '3', '/authority/chain', '^/authority$', '', 3);
INSERT INTO `authority` VALUES (11, 1, 0, '权限添加', '3,10,11', 0, '1', '/authority/add', '/authority/add', '', 10);
INSERT INTO `authority` VALUES (12, 1, 0, '权限编辑', '3,10,12', 0, '2', '/authority/edit', '/authority/edit', '', 10);
INSERT INTO `authority` VALUES (13, 1, 0, '权限删除', '3,10,13', 0, '3', '/authority/delete', '/authority/delete', '', 10);
INSERT INTO `authority` VALUES (14, 1, 0, '组织机构管理', '3,14', 0, '4', '/organization/chain', '^/organization$', '', 3);
INSERT INTO `authority` VALUES (15, 1, 0, '组织机构树', '3,14,15', 0, '1', '/organization/chain', '/organization/chain', '', 14);
INSERT INTO `authority` VALUES (16, 1, 0, '组织机构添加', '3,14,16', 0, '2', '/organization/add', '/organization/add', '', 14);
INSERT INTO `authority` VALUES (17, 1, 0, '组织机构编辑', '3,14,17', 0, '3', '/organization/edit', '/organization/edit', '', 14);
INSERT INTO `authority` VALUES (18, 1, 0, '组织机构删除', '3,14,18', 0, '4', '/organization/delete', '/organization/delete', '', 14);
INSERT INTO `authority` VALUES (19, 1, 0, '权限树', '3,10,19', 0, '4', '/authority/chain', '/authority/chain', '', 10);
INSERT INTO `authority` VALUES (21, 1, 0, '微信管理', '21', 0, '3', '/weixinsend', '^/weixinsend$', 'icon-comments', NULL);
INSERT INTO `authority` VALUES (22, 1, 0, '创建菜单', '21,22', 0, '1', '/weixinsend/createmenu', '/weixinsend/createmenu', '', 21);
INSERT INTO `authority` VALUES (23, 4, 0, '查询菜单', '21,23', 0, '2', '/weixinsend/getmenu', '/weixinsend/getmenu', '', 21);
INSERT INTO `authority` VALUES (24, 2, 0, '销售子系统', '24', 0, '4', '/sales', '^/sales$', '', NULL);
INSERT INTO `authority` VALUES (25, 2, 0, '销售订单管理', '24,25', 0, '1', '/sales/order', '/sales/order', '', 24);
INSERT INTO `authority` VALUES (26, 2, 0, '基础信息管理', '26', 0, '6', '/basedata', '^/basedata$', 'icon-folder-close', NULL);
INSERT INTO `authority` VALUES (27, 4, 0, '产品信息', '26,27', 0, '1', '/basedata/product', '^/basedata$', '', 26);
INSERT INTO `authority` VALUES (28, 10, 0, '顾客信息', '26,28', 0, '2', '/basedata/customer', '/basedata/customer', '', 26);
INSERT INTO `authority` VALUES (29, 2, 0, '供应商信息', '26,29', 0, '2', '/basedata/vendor', '^/basedata$', '', 26);
INSERT INTO `authority` VALUES (30, 1, 0, '采购子系统', '30', 0, '4', '/purchase/list', '^/purchase/list$', '', NULL);
INSERT INTO `authority` VALUES (31, 1, 0, '采购订单管理', '30,31', 0, '1', '/purchase/orderlist', '/purchase/orderlist', '', 30);
INSERT INTO `authority` VALUES (32, 1, 0, '采购单据管理', '30,32', 0, '2', '/purchase/ordercontract', '/purchase/ordercontract', '', 30);
INSERT INTO `authority` VALUES (33, 1, 0, '销售合同管理', '24,33', 0, '2', '/sales/salescontract', '/sales/salescontract', '', 24);
INSERT INTO `authority` VALUES (34, 1, 0, '库存子系统', '34', 0, '7', '/inventory/list', '/inventory/list', '', NULL);
INSERT INTO `authority` VALUES (35, 1, 0, '备货单管理', '34,35', 0, '1', '/inventory/prepare', '/inventory/prepare', '', 34);
INSERT INTO `authority` VALUES (36, 1, 0, '进货单管理', '34,36', 0, '2', '/inventory/stockin', '/inventory/stockin', '', 34);
INSERT INTO `authority` VALUES (37, 1, 0, '出库单管理', '34,37', 0, '3', '/inventory/stockout', '/inventory/stockout', '', 34);
INSERT INTO `authority` VALUES (38, 1, 0, '包装发货管理', '34,38', 0, '4', '/inventory/package', '/inventory/package', '', 34);
INSERT INTO `authority` VALUES (39, 1, 0, '供应商列表', '26,29,39', 0, '1', '/basedata/vendor', '/basedata/vendor', '', 29);
INSERT INTO `authority` VALUES (40, 1, 0, '增加供应商', '26,29,40', 0, '2', '/basedata/vendoradd', '/basedata/vendoradd', '', 29);
INSERT INTO `authority` VALUES (41, 1, 0, '编辑供应商', '26,29,41', 0, '3', '/basedata/vendoredit', '/basedata/vendoredit', '', 29);
INSERT INTO `authority` VALUES (42, 1, 0, '删除供应商', '26,29,42', 0, '4', '/basdata/vendordelete', '/basdata/vendordelete', '', 29);
INSERT INTO `authority` VALUES (43, 1, 0, '新增角色', '3,7,43', 0, '3', '/role/add', '/role/add', '', 7);
INSERT INTO `authority` VALUES (44, 1, 0, '修改角色', '3,7,44', 0, '4', '/role/edit', '/role/edit', '', 7);
INSERT INTO `authority` VALUES (45, 1, 0, '产品列表', '26,27,45', 0, '1', '/basedata/product', '/basedata/product', '', 27);
INSERT INTO `authority` VALUES (46, 1, 0, '增加产品', '26,27,46', 0, '2', '/basedata/productadd', '/basedata/productadd', '', 27);
INSERT INTO `authority` VALUES (47, 1, 0, '编辑产品', '26,27,47', 0, '3', '/basedata/productedit', '/basedata/productedit', '', 27);
INSERT INTO `authority` VALUES (48, 1, 0, '删除产品', '26,27,48', 0, '4', '/basedata/productdelete', '/basedata/productdelete', '', 27);
INSERT INTO `authority` VALUES (49, 1, 0, '订单列表', '24,25,49', 0, '1', '/sales/order', '/sales/order', '', 25);
INSERT INTO `authority` VALUES (50, 1, 0, '查看订单明细', '24,25,50', 0, '1', '/sales/orderdetail', '/sales/orderdetail', '', 25);
INSERT INTO `authority` VALUES (51, 1, 0, '增加订单', '24,25,51', 0, '3', '/sales/orderaddnew', '/sales/orderaddnew', '', 25);
INSERT INTO `authority` VALUES (53, 2, 0, '新建订单明细', '24,25,53', 0, '4', '/sales/orderdetailadd', '/sales/orderdetailadd', '', 25);
COMMIT;

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
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

-- ----------------------------
-- Records of organization
-- ----------------------------
BEGIN;
INSERT INTO `organization` VALUES (1, 1, 0, '总机构', '1', 0, '1', NULL);
INSERT INTO `organization` VALUES (4, 1, 0, '总机构2', '4', 0, '2', NULL);
INSERT INTO `organization` VALUES (5, 2, 0, '组织机构2-1', '4,5', 0, '1', 4);
INSERT INTO `organization` VALUES (6, 1, 0, '销售科', '1,6', 0, '1', 1);
INSERT INTO `organization` VALUES (7, 1, 0, '供应科', '1,7', 0, '2', 1);
INSERT INTO `organization` VALUES (8, 1, 0, '会计科', '1,8', 0, '3', 1);
INSERT INTO `organization` VALUES (10, 1, 0, '采购科', '1,10', 0, '4', 1);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 21, '系统管理员', 0);
INSERT INTO `role` VALUES (2, 3, '顾客', 0);
INSERT INTO `role` VALUES (3, 0, '仓库管理员', 0);
INSERT INTO `role` VALUES (4, 0, '销售管理员', 0);
INSERT INTO `role` VALUES (5, 0, '采购管理员', 0);
INSERT INTO `role` VALUES (6, 0, '供应商', 0);
INSERT INTO `role` VALUES (7, 0, '会计', 0);
COMMIT;

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority` (
  `roleId` int(11) NOT NULL,
  `authorityId` int(11) NOT NULL,
  KEY `FK_sccf4fx8omb6jlsy2ra75xxer` (`authorityId`),
  KEY `FK_fftr98ew5vtbdpcfetn7xd715` (`roleId`),
  CONSTRAINT `role_authority_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `role_authority_ibfk_2` FOREIGN KEY (`authorityId`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_authority
-- ----------------------------
BEGIN;
INSERT INTO `role_authority` VALUES (1, 1);
INSERT INTO `role_authority` VALUES (1, 2);
INSERT INTO `role_authority` VALUES (1, 3);
INSERT INTO `role_authority` VALUES (1, 4);
INSERT INTO `role_authority` VALUES (1, 5);
INSERT INTO `role_authority` VALUES (1, 6);
INSERT INTO `role_authority` VALUES (1, 7);
INSERT INTO `role_authority` VALUES (1, 8);
INSERT INTO `role_authority` VALUES (1, 9);
INSERT INTO `role_authority` VALUES (1, 43);
INSERT INTO `role_authority` VALUES (1, 44);
INSERT INTO `role_authority` VALUES (1, 10);
INSERT INTO `role_authority` VALUES (1, 11);
INSERT INTO `role_authority` VALUES (1, 12);
INSERT INTO `role_authority` VALUES (1, 13);
INSERT INTO `role_authority` VALUES (1, 19);
INSERT INTO `role_authority` VALUES (1, 14);
INSERT INTO `role_authority` VALUES (1, 15);
INSERT INTO `role_authority` VALUES (1, 16);
INSERT INTO `role_authority` VALUES (1, 17);
INSERT INTO `role_authority` VALUES (1, 18);
INSERT INTO `role_authority` VALUES (1, 24);
INSERT INTO `role_authority` VALUES (1, 25);
INSERT INTO `role_authority` VALUES (1, 49);
INSERT INTO `role_authority` VALUES (1, 50);
INSERT INTO `role_authority` VALUES (1, 53);
INSERT INTO `role_authority` VALUES (1, 33);
INSERT INTO `role_authority` VALUES (1, 26);
INSERT INTO `role_authority` VALUES (1, 27);
INSERT INTO `role_authority` VALUES (1, 45);
INSERT INTO `role_authority` VALUES (1, 46);
INSERT INTO `role_authority` VALUES (1, 47);
INSERT INTO `role_authority` VALUES (1, 48);
INSERT INTO `role_authority` VALUES (1, 28);
INSERT INTO `role_authority` VALUES (1, 29);
INSERT INTO `role_authority` VALUES (1, 39);
INSERT INTO `role_authority` VALUES (1, 40);
INSERT INTO `role_authority` VALUES (1, 41);
INSERT INTO `role_authority` VALUES (1, 42);
INSERT INTO `role_authority` VALUES (1, 30);
INSERT INTO `role_authority` VALUES (1, 31);
INSERT INTO `role_authority` VALUES (1, 32);
INSERT INTO `role_authority` VALUES (1, 34);
INSERT INTO `role_authority` VALUES (1, 35);
INSERT INTO `role_authority` VALUES (1, 36);
INSERT INTO `role_authority` VALUES (1, 37);
INSERT INTO `role_authority` VALUES (1, 38);
INSERT INTO `role_authority` VALUES (2, 1);
INSERT INTO `role_authority` VALUES (2, 2);
INSERT INTO `role_authority` VALUES (2, 26);
INSERT INTO `role_authority` VALUES (2, 27);
INSERT INTO `role_authority` VALUES (2, 45);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
BEGIN;
INSERT INTO `t_customer` VALUES (3, 2, 0, 'C0001', '上海五菱汽车修配厂有限公司', '上海市嘉定安亭镇墨玉路北', '', '(021)59577339', '', '');
INSERT INTO `t_customer` VALUES (4, 1, 0, 'C0002', '杭州袁富汽车修配有限公司', '杭州市双浦镇轮渡路10号', '张幸军', '13357119793', NULL, NULL);
INSERT INTO `t_customer` VALUES (6, 0, 0, 'C0003', '深圳市日兴达汽车修配有限公司', '广东省深圳市福田区滨河路3155号深华工业厂房一楼', '李先生', '0755-83610965', '', '');
COMMIT;

-- ----------------------------
-- Table structure for t_customerinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_customerinfo`;
CREATE TABLE `t_customerinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `englishName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `chineseName` varchar(255) COLLATE utf8_bin NOT NULL,
  `address` varchar(255) COLLATE utf8_bin NOT NULL,
  `phone` varchar(255) COLLATE utf8_bin NOT NULL,
  `contactPerson` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `orderId` varchar(50) COLLATE utf8_bin NOT NULL,
  `createTime` datetime NOT NULL,
  `customerId` varchar(20) COLLATE utf8_bin NOT NULL,
  `totalPrice` decimal(65,2) NOT NULL,
  `status` varchar(20) COLLATE utf8_bin NOT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES (38, 0, 0, 'S20171121190446', '2017-11-21 19:05:33', 'C001', 532324.00, '未完成', '无');
COMMIT;

-- ----------------------------
-- Table structure for t_orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `t_orderdetail`;
CREATE TABLE `t_orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `orderdetailId` varchar(50) COLLATE utf8_bin NOT NULL,
  `orderId` varchar(50) COLLATE utf8_bin NOT NULL,
  `productId` varchar(20) COLLATE utf8_bin NOT NULL,
  `productName` varchar(50) COLLATE utf8_bin NOT NULL,
  `quantityDemand` int(11) NOT NULL,
  `quantitySupplied` int(11) NOT NULL,
  `unitPrice` decimal(65,2) NOT NULL,
  `totalPrice` decimal(65,2) NOT NULL,
  `status` varchar(20) COLLATE utf8_bin NOT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_order_orderId` (`orderId`),
  KEY `IDX_product_productId` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_orderdetail
-- ----------------------------
BEGIN;
INSERT INTO `t_orderdetail` VALUES (73, 0, 0, 'S20171121161651D161653', 'S20171121161651', 'G001', '大螺丝', 100, 0, 0.90, 90.00, '未完成', '');
INSERT INTO `t_orderdetail` VALUES (74, 0, 0, 'S20171121164220D164223', 'S20171121164220', 'G001', '大螺丝', 100, 0, 0.78, 78.00, '未完成', '');
INSERT INTO `t_orderdetail` VALUES (75, 0, 0, 'S20171121164220D164235', 'S20171121164220', 'G0002', '大螺丝', 2220, 0, 0.90, 1998.00, '未完成', '无');
INSERT INTO `t_orderdetail` VALUES (76, 0, 0, 'S20171121164450D164454', 'S20171121164450', 'G001', '大车轮', 198, 0, 0.90, 178.20, '未完成', 'w');
INSERT INTO `t_orderdetail` VALUES (77, 0, 0, 'S20171121164644D164648', 'S20171121164644', 'G0002', '中螺丝', 109, 0, 2.90, 316.10, '未完成', '优秀供应商');
INSERT INTO `t_orderdetail` VALUES (78, 0, 0, 'S20171121165017D165021', 'S20171121165017', 'G001', '中螺丝', 0, 0, 0.00, 0.00, '未完成', '优秀供应商');
INSERT INTO `t_orderdetail` VALUES (79, 0, 0, 'S20171121165245D165303', 'S20171121165245', 'G0002', '大车轮', 0, 0, 0.00, 0.00, '未完成', '无');
INSERT INTO `t_orderdetail` VALUES (80, 0, 0, 'S20171121165922D165928', 'S20171121165922', 'G0002', '中螺丝', 100, 0, 0.80, 80.00, '未完成', '信用良好');
INSERT INTO `t_orderdetail` VALUES (81, 0, 0, 'S20171121170620D170623', 'S20171121170620', 'G001', '中螺丝', 109, 0, 0.90, 98.10, '未完成', '优秀供应商');
INSERT INTO `t_orderdetail` VALUES (82, 0, 0, 'S20171121171101D171105', 'S20171121171101', 'G0002', '中螺丝', 89, 0, 0.90, 80.10, '未完成', '优秀供应商');
INSERT INTO `t_orderdetail` VALUES (83, 0, 0, 'S20171121172831D172835', 'S20171121172831', 'G001', '中螺丝', 100, 0, 0.80, 80.00, '未完成', '优秀供应商');
INSERT INTO `t_orderdetail` VALUES (84, 0, 0, 'S20171121172831D172854', 'S20171121172831', 'M001', '大螺丝', 200, 0, 90.00, 18000.00, '未完成', '优秀');
INSERT INTO `t_orderdetail` VALUES (85, 0, 0, 'S20171121173052D173059', 'S20171121173052', 'G0002', '中螺丝', 100, 0, 8.00, 800.00, '未完成', '优秀供应商');
INSERT INTO `t_orderdetail` VALUES (86, 0, 0, 'S20171121173421D173427', 'S20171121173421', 'G0002', '大车轮', 6798, 0, 0.80, 5438.40, '未完成', '无');
INSERT INTO `t_orderdetail` VALUES (87, 0, 0, 'S20171121173850D173857', 'S20171121173850', 'G0002', '中螺丝', 78, 0, 99.00, 7722.00, '未完成', '');
INSERT INTO `t_orderdetail` VALUES (88, 0, 0, 'S20171121174021D174028', 'S20171121174021', 'G001', '大螺丝', 990, 0, 0.90, 891.00, '未完成', '');
INSERT INTO `t_orderdetail` VALUES (89, 0, 0, 'S20171121190212D190215', 'S20171121190212', 'G0002', '大车轮', 1231, 0, 23.00, 28313.00, '未完成', '优秀供应商');
INSERT INTO `t_orderdetail` VALUES (90, 0, 0, 'S20171121190446D190452', 'S20171121190446', 'G0002', 'fs', 1232, 0, 432.00, 532224.00, '未完成', '优秀供应商');
INSERT INTO `t_orderdetail` VALUES (91, 0, 0, 'S20171121190446D190507', 'S20171121190446', 'G0002', '大车轮', 100, 0, 1.00, 100.00, '未完成', '');
COMMIT;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_product
-- ----------------------------
BEGIN;
INSERT INTO `t_product` VALUES (1, 6, 0, 'G001', '大螺丝', 'S', '个', 1000, '');
INSERT INTO `t_product` VALUES (2, 0, 0, 'M0019', '大螺丝', 'M', '个', 800, '');
INSERT INTO `t_product` VALUES (3, 0, 0, 'G001', '大螺丝', 'G', '个', 2008, '优秀供应商');
COMMIT;

-- ----------------------------
-- Table structure for t_vendor
-- ----------------------------
DROP TABLE IF EXISTS `t_vendor`;
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

-- ----------------------------
-- Records of t_vendor
-- ----------------------------
BEGIN;
INSERT INTO `t_vendor` VALUES (1, 4, 0, 'V0001', '螺丝厂', '北京市海淀区', '陈先生', '12689768667', 'qq@123.com', '');
INSERT INTO `t_vendor` VALUES (2, 2, 0, 'V002', '车轮厂', '天津', '李先生', '123453578', '13@126.com', '');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
