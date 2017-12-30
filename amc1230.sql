/*
Navicat MySQL Data Transfer

Source Server         : AMC
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : amcdb

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2017-12-30 21:22:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '1', '1', 'MissWhen...', 'liukemng@sina.com', 'liukemng', '7FE8A1AE00C5C2A0785A5071DF996D8C', '2014-04-02 23:26:40', '1', '1');
INSERT INTO `account` VALUES ('2', '0', '0', 'AAA', '123@123.com', 'AAAA', '562363ED4A17A4894D4162A0E64DB7C4', '2014-04-03 14:38:38', null, null);
INSERT INTO `account` VALUES ('3', '0', '0', 'WWWWW', '123@123.com', 'FFFFFF', 'CFBDA3E623045D2C2130D653909397E0', '2014-04-03 14:39:00', null, null);
INSERT INTO `account` VALUES ('4', '0', '0', 'TTTT', '123@123.com', 'SSSDD', '50F86F5725447FE015467D74A7D7DB11', '2014-04-03 14:39:18', null, null);
INSERT INTO `account` VALUES ('5', '0', '0', 'OOOO', '123@123.com', 'LLLL', '1F5297672E6BF686ED906F69DBD9B509', '2014-04-03 14:39:36', null, null);
INSERT INTO `account` VALUES ('6', '0', '0', 'CCCCC', '123@123.com', 'RRRR', 'DDF8448BC7A6BEC245B7293CE4EBDA74', '2014-04-03 14:40:16', null, null);
INSERT INTO `account` VALUES ('7', '0', '0', 'NNNN', '123@123.com', 'MMMM', 'A9C51ACEFD41BB51F1A7546B358BF1BD', '2014-04-03 14:40:32', null, null);
INSERT INTO `account` VALUES ('8', '0', '0', 'ZZZZZ', '123@123.com', 'TTTTTT', '8B5CC63F5053E982AD5EB6A461F69209', '2014-04-03 14:40:49', null, null);
INSERT INTO `account` VALUES ('9', '0', '0', 'KKKKK', '123@123.com', 'PPPPP', '78EB5B2DDCC23A6E13A48AEF3B28C87D', '2014-04-03 14:41:10', null, null);
INSERT INTO `account` VALUES ('10', '0', '0', 'XXXXX', '123@123.com', 'DDDF', 'AF8B0A62C9844F4B4E339230D85ECBE0', '2014-04-03 14:41:49', null, null);
INSERT INTO `account` VALUES ('11', '0', '0', 'RTYV', '123@123.com', 'WWW', 'B04874C43B023CB87F753717D73C4D6D', '2014-04-03 14:42:07', null, null);
INSERT INTO `account` VALUES ('12', '0', '1', 'fc', 'fc@163.com', 'fc', 'f79a121e29600f0fb867a6c4eea6b29e', '2017-05-02 18:09:06', '1', '1');
INSERT INTO `account` VALUES ('13', '0', '1', 'wx', 'wenxin_199511@126.com', 'wenxin', 'c0bbf3c4f4ae2172be3aaeb8a4852391', '2017-10-26 17:04:10', '1', '1');
INSERT INTO `account` VALUES ('14', '1', '1', '赵雅萍', 'zyp@qq.com', 'zyp', 'a9e2e2a3da6cf7dba8727de483b7c917', '2017-11-04 09:46:55', '2', null);
INSERT INTO `account` VALUES ('15', '0', '1', '景怡', 'kuran_king@163.com', '景怡', '141ad0fb9d1120484013f69aaeb3c822', '2017-12-18 16:46:50', '1', '1');

-- ----------------------------
-- Table structure for `authority`
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
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', '3', '0', '欢迎使用', '1', '0', '1', '/home', '^/home$', 'icon-home', null);
INSERT INTO `authority` VALUES ('2', '2', '0', '首页', '1,2', '0', '1', '/home/index', '/home/index', '', '1');
INSERT INTO `authority` VALUES ('3', '2', '0', '系统设置', '3', '0', '2', '/setting', '^/setting$', 'icon-cogs', null);
INSERT INTO `authority` VALUES ('4', '4', '0', '用户管理', '3,4', '0', '1', '/account/list', '^/account$', '', '3');
INSERT INTO `authority` VALUES ('5', '1', '0', '用户列表', '3,4,5', '0', '1', '/account/list', '/account/list', '', '4');
INSERT INTO `authority` VALUES ('6', '2', '0', '账户绑定', '3,4,6', '0', '2', '/account/authorize', '/account/authorize', '', '4');
INSERT INTO `authority` VALUES ('7', '1', '0', '角色管理', '3,7', '0', '2', '/role/list', '^/role$', '', '3');
INSERT INTO `authority` VALUES ('8', '1', '0', '角色列表', '3,7,8', '0', '1', '/role/list', '/role/list', '', '7');
INSERT INTO `authority` VALUES ('9', '1', '0', '权限绑定', '3,7,9', '0', '2', '/role/bind', '/role/bind', '', '7');
INSERT INTO `authority` VALUES ('10', '1', '0', '权限管理', '3,10', '0', '3', '/authority/chain', '^/authority$', '', '3');
INSERT INTO `authority` VALUES ('11', '1', '0', '权限添加', '3,10,11', '0', '1', '/authority/add', '/authority/add', '', '10');
INSERT INTO `authority` VALUES ('12', '1', '0', '权限编辑', '3,10,12', '0', '2', '/authority/edit', '/authority/edit', '', '10');
INSERT INTO `authority` VALUES ('13', '1', '0', '权限删除', '3,10,13', '0', '3', '/authority/delete', '/authority/delete', '', '10');
INSERT INTO `authority` VALUES ('14', '1', '0', '组织机构管理', '3,14', '0', '4', '/organization/chain', '^/organization$', '', '3');
INSERT INTO `authority` VALUES ('15', '1', '0', '组织机构树', '3,14,15', '0', '1', '/organization/chain', '/organization/chain', '', '14');
INSERT INTO `authority` VALUES ('16', '1', '0', '组织机构添加', '3,14,16', '0', '2', '/organization/add', '/organization/add', '', '14');
INSERT INTO `authority` VALUES ('17', '1', '0', '组织机构编辑', '3,14,17', '0', '3', '/organization/edit', '/organization/edit', '', '14');
INSERT INTO `authority` VALUES ('18', '1', '0', '组织机构删除', '3,14,18', '0', '4', '/organization/delete', '/organization/delete', '', '14');
INSERT INTO `authority` VALUES ('19', '1', '0', '权限树', '3,10,19', '0', '4', '/authority/chain', '/authority/chain', '', '10');
INSERT INTO `authority` VALUES ('21', '1', '0', '微信管理', '21', '0', '3', '/weixinsend', '^/weixinsend$', 'icon-comments', null);
INSERT INTO `authority` VALUES ('22', '1', '0', '创建菜单', '21,22', '0', '1', '/weixinsend/createmenu', '/weixinsend/createmenu', '', '21');
INSERT INTO `authority` VALUES ('23', '4', '0', '查询菜单', '21,23', '0', '2', '/weixinsend/getmenu', '/weixinsend/getmenu', '', '21');
INSERT INTO `authority` VALUES ('24', '2', '0', '销售子系统', '24', '0', '4', '/sales', '^/sales$', 'icon-barcode', null);
INSERT INTO `authority` VALUES ('25', '2', '0', '销售订单管理', '24,25', '0', '1', '/sales/order', '/sales/order', '', '24');
INSERT INTO `authority` VALUES ('30', '1', '0', '采购子系统', '30', '0', '4', '/purchase/list', '^/purchase/list$', 'icon-shopping-cart', null);
INSERT INTO `authority` VALUES ('31', '1', '0', '采购订单管理', '30,31', '0', '1', '/purchase/purchaseorder', '/purchase/purchaseorder', '', '30');
INSERT INTO `authority` VALUES ('32', '1', '0', '新增采购订单', '30,31,32', '0', '1', '/purchase/purchaseorderadd', '/purchase/purchaseorderadd', null, '31');
INSERT INTO `authority` VALUES ('33', '1', '0', '删除采购订单', '30,31,33', '0', '2', '/purchase/purchaseorderdelete', '/purchase/purchaseorderdelete', null, '31');
INSERT INTO `authority` VALUES ('34', '1', '0', '库存子系统', '34', '0', '7', '/inventory/list', '/inventory/list', '', null);
INSERT INTO `authority` VALUES ('35', '1', '0', '备货单管理', '34,35', '2', '2', '/inventory/prepare', '/inventory/prepare', '', '34');
INSERT INTO `authority` VALUES ('36', '1', '0', '进货单管理', '34,36', '3', '3', '/inventory/stockin', '/inventory/stockin', '', '34');
INSERT INTO `authority` VALUES ('43', '1', '0', '新增角色', '3,7,43', '0', '3', '/role/add', '/role/add', '', '7');
INSERT INTO `authority` VALUES ('44', '1', '0', '修改角色', '3,7,44', '0', '4', '/role/edit', '/role/edit', '', '7');
INSERT INTO `authority` VALUES ('49', '1', '0', '订单列表', '24,25,49', '0', '1', '/sales/order', '/sales/order', '', '25');
INSERT INTO `authority` VALUES ('50', '1', '0', '查看订单明细', '24,25,50', '0', '1', '/sales/orderdetail', '/sales/orderdetail', '', '25');
INSERT INTO `authority` VALUES ('51', '1', '0', '增加订单', '24,25,51', '0', '3', '/sales/orderaddnew', '/sales/orderaddnew', '', '25');
INSERT INTO `authority` VALUES ('53', '2', '0', '新建订单明细', '24,25,53', '0', '4', '/sales/orderdetailadd', '/sales/orderdetailadd', '', '25');
INSERT INTO `authority` VALUES ('69', '2', '0', '订单缺件表管理', '34,69', '3', '3', '/inventory/outofstock', '/inventory/outofstock', '', '34');
INSERT INTO `authority` VALUES ('70', '3', '0', '财务子系统', '70', '0', '1', '/financial/invoice', '/financial/invoice', '', null);
INSERT INTO `authority` VALUES ('71', '2', '0', '发票管理', '70,71', '0', '1', '/financial/invoice', '/financial/invoice', '', '70');
INSERT INTO `authority` VALUES ('73', '2', '0', '发货单管理', '34,73', '6', '6', '/inventory/deliverlist', '/inventory/deliverlist', '', '34');
INSERT INTO `authority` VALUES ('75', '1', '0', '发票列表', '70,71,75', '0', '', '/financial/invoice', '/financial/invoice', '', '71');
INSERT INTO `authority` VALUES ('76', '1', '0', '顾客管理', '24,76', '0', '', '/basedata/customer', '/basedata/customer', '', '24');
INSERT INTO `authority` VALUES ('77', '1', '0', '顾客列表', '24,76,77', '0', '', '/basedata/customer', '/basedata/customer', '', '76');
INSERT INTO `authority` VALUES ('78', '1', '0', '添加顾客', '24,76,78', '0', '', '/basedata/customeradd', '/basedata/customeradd', '', '76');
INSERT INTO `authority` VALUES ('79', '1', '0', '编辑顾客', '24,76,79', '0', '', '/basedata/customeredit', '/basedata/customeredit', '', '76');
INSERT INTO `authority` VALUES ('80', '1', '0', '催款单管理', '70,80', '0', '', '/financial/reminder', '/financial/reminder', '', '70');
INSERT INTO `authority` VALUES ('82', '1', '0', '销售业务账管理', '70,82', '0', '', '/financial/salesaccount', '/financial/salesaccount', '', '70');
INSERT INTO `authority` VALUES ('83', '1', '0', '采购业务账管理', '70,83', '0', '', '/financial/purchaseaccount', '/financial/purchaseaccount', '', '70');
INSERT INTO `authority` VALUES ('84', '1', '0', '失信记录管理', '70,84', '0', '', '/financial/liquidate', '/financial/liquidate', '', '70');
INSERT INTO `authority` VALUES ('85', '1', '0', '产品管理', '34,85', '0', '', '/basedata/product', '/basedata/product', '', '34');
INSERT INTO `authority` VALUES ('86', '1', '0', '产品列表', '34,85,86', '0', '', '/basedata/product', '/basedata/product', '', '85');
INSERT INTO `authority` VALUES ('87', '1', '0', '添加产品', '34,85,87', '0', '', '/basedata/productadd', '/basedata/productadd', '', '85');
INSERT INTO `authority` VALUES ('88', '1', '0', '编辑产品', '34,85,88', '0', '', '/basedata/productedit', '/basedata/productedit', '', '85');
INSERT INTO `authority` VALUES ('89', '1', '0', '供应商管理', '30,89', '0', '', '/basedata/vendor', '/basedata/vendor', '', '30');
INSERT INTO `authority` VALUES ('90', '1', '0', '供应商列表', '30,89,90', '0', '', '/basedata/vendoradd', '/basedata/vendoradd', '', '89');
INSERT INTO `authority` VALUES ('91', '1', '0', '添加供应商', '30,89,91', '0', '', '/basedata/vendoradd', '/basedata/vendoradd', '', '89');
INSERT INTO `authority` VALUES ('92', '1', '0', '编辑供应商', '30,89,92', '0', '', '/basedata/vendoredit', '/basedata/vendoredit', '', '89');
INSERT INTO `authority` VALUES ('93', '1', '0', '库存基本信息', '34,93', '0', '', '/inventory/list', '/inventory/list', '', '34');

-- ----------------------------
-- Table structure for `organization`
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
INSERT INTO `organization` VALUES ('1', '1', '0', '总机构', '1', '0', '1', null);
INSERT INTO `organization` VALUES ('4', '1', '0', '总机构2', '4', '0', '2', null);
INSERT INTO `organization` VALUES ('5', '2', '0', '组织机构2-1', '4,5', '0', '1', '4');
INSERT INTO `organization` VALUES ('6', '1', '0', '销售科', '1,6', '0', '1', '1');
INSERT INTO `organization` VALUES ('7', '1', '0', '供应科', '1,7', '0', '2', '1');
INSERT INTO `organization` VALUES ('8', '1', '0', '会计科', '1,8', '0', '3', '1');
INSERT INTO `organization` VALUES ('10', '1', '0', '采购科', '1,10', '0', '4', '1');

-- ----------------------------
-- Table structure for `role`
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
INSERT INTO `role` VALUES ('1', '28', '系统管理员', '0');
INSERT INTO `role` VALUES ('2', '3', '顾客', '0');
INSERT INTO `role` VALUES ('3', '0', '仓库管理员', '0');
INSERT INTO `role` VALUES ('4', '0', '销售管理员', '0');
INSERT INTO `role` VALUES ('5', '0', '采购管理员', '0');
INSERT INTO `role` VALUES ('6', '0', '供应商', '0');
INSERT INTO `role` VALUES ('7', '0', '会计', '0');

-- ----------------------------
-- Table structure for `role_authority`
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
INSERT INTO `role_authority` VALUES ('2', '1');
INSERT INTO `role_authority` VALUES ('2', '2');
INSERT INTO `role_authority` VALUES ('1', '1');
INSERT INTO `role_authority` VALUES ('1', '2');
INSERT INTO `role_authority` VALUES ('1', '3');
INSERT INTO `role_authority` VALUES ('1', '4');
INSERT INTO `role_authority` VALUES ('1', '5');
INSERT INTO `role_authority` VALUES ('1', '6');
INSERT INTO `role_authority` VALUES ('1', '7');
INSERT INTO `role_authority` VALUES ('1', '8');
INSERT INTO `role_authority` VALUES ('1', '9');
INSERT INTO `role_authority` VALUES ('1', '43');
INSERT INTO `role_authority` VALUES ('1', '44');
INSERT INTO `role_authority` VALUES ('1', '10');
INSERT INTO `role_authority` VALUES ('1', '11');
INSERT INTO `role_authority` VALUES ('1', '12');
INSERT INTO `role_authority` VALUES ('1', '13');
INSERT INTO `role_authority` VALUES ('1', '19');
INSERT INTO `role_authority` VALUES ('1', '14');
INSERT INTO `role_authority` VALUES ('1', '15');
INSERT INTO `role_authority` VALUES ('1', '16');
INSERT INTO `role_authority` VALUES ('1', '17');
INSERT INTO `role_authority` VALUES ('1', '18');
INSERT INTO `role_authority` VALUES ('1', '24');
INSERT INTO `role_authority` VALUES ('1', '25');
INSERT INTO `role_authority` VALUES ('1', '49');
INSERT INTO `role_authority` VALUES ('1', '50');
INSERT INTO `role_authority` VALUES ('1', '53');
INSERT INTO `role_authority` VALUES ('1', '76');
INSERT INTO `role_authority` VALUES ('1', '77');
INSERT INTO `role_authority` VALUES ('1', '78');
INSERT INTO `role_authority` VALUES ('1', '79');
INSERT INTO `role_authority` VALUES ('1', '30');
INSERT INTO `role_authority` VALUES ('1', '31');
INSERT INTO `role_authority` VALUES ('1', '89');
INSERT INTO `role_authority` VALUES ('1', '90');
INSERT INTO `role_authority` VALUES ('1', '91');
INSERT INTO `role_authority` VALUES ('1', '92');
INSERT INTO `role_authority` VALUES ('1', '34');
INSERT INTO `role_authority` VALUES ('1', '35');
INSERT INTO `role_authority` VALUES ('1', '36');
INSERT INTO `role_authority` VALUES ('1', '69');
INSERT INTO `role_authority` VALUES ('1', '73');
INSERT INTO `role_authority` VALUES ('1', '85');
INSERT INTO `role_authority` VALUES ('1', '86');
INSERT INTO `role_authority` VALUES ('1', '87');
INSERT INTO `role_authority` VALUES ('1', '88');
INSERT INTO `role_authority` VALUES ('1', '93');
INSERT INTO `role_authority` VALUES ('1', '70');
INSERT INTO `role_authority` VALUES ('1', '71');
INSERT INTO `role_authority` VALUES ('1', '75');
INSERT INTO `role_authority` VALUES ('1', '80');
INSERT INTO `role_authority` VALUES ('1', '82');
INSERT INTO `role_authority` VALUES ('1', '83');
INSERT INTO `role_authority` VALUES ('1', '84');

-- ----------------------------
-- Table structure for `t_backoder`
-- ----------------------------
DROP TABLE IF EXISTS `t_backoder`;
CREATE TABLE `t_backoder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `backoderId` varchar(20) COLLATE utf8_bin NOT NULL,
  `productId` varchar(20) COLLATE utf8_bin NOT NULL,
  `productName` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `factoryId` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `size` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_backoder
-- ----------------------------

-- ----------------------------
-- Table structure for `t_customer`
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
  `credit` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('3', '2', '0', 'C0001', '上海五菱汽车修配厂有限公司', '上海市嘉定安亭镇墨玉路北', '', '(021)59577339', '', '', null);
INSERT INTO `t_customer` VALUES ('4', '1', '0', 'C0002', '杭州袁富汽车修配有限公司', '杭州市双浦镇轮渡路10号', '张幸军', '13357119793', null, null, null);
INSERT INTO `t_customer` VALUES ('6', '0', '0', 'C0003', '深圳市日兴达汽车修配有限公司', '广东省深圳市福田区滨河路3155号深华工业厂房一楼', '李先生', '0755-83610965', '', '', null);

-- ----------------------------
-- Table structure for `t_customerinfo`
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
-- Records of t_customerinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `t_deliver`
-- ----------------------------
DROP TABLE IF EXISTS `t_deliver`;
CREATE TABLE `t_deliver` (
  `id` int(11) NOT NULL,
  `deliverId` varchar(255) NOT NULL,
  `prepareId` varchar(255) NOT NULL,
  `deliverDetailId` varchar(255) NOT NULL,
  `createTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `amountMoney` decimal(65,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_deliver
-- ----------------------------
INSERT INTO `t_deliver` VALUES ('1', 'd01', 'P001', 'dt01', '2017-12-27 17:16:51', '20.20', null, '运送中');

-- ----------------------------
-- Table structure for `t_deliverdetail`
-- ----------------------------
DROP TABLE IF EXISTS `t_deliverdetail`;
CREATE TABLE `t_deliverdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deliverId` varchar(255) NOT NULL,
  `no` int(11) NOT NULL,
  `productId` varchar(255) NOT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `factoryId` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `shortNum` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_deliverdetail
-- ----------------------------
INSERT INTO `t_deliverdetail` VALUES ('1', 'd01', '1', 'P001', 'name', 'factory', '10', '5', null);

-- ----------------------------
-- Table structure for `t_inventory`
-- ----------------------------
DROP TABLE IF EXISTS `t_inventory`;
CREATE TABLE `t_inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `productId` varchar(20) COLLATE utf8_bin NOT NULL,
  `productName` varchar(50) COLLATE utf8_bin NOT NULL,
  `inventoryLevel` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  `status` varchar(20) COLLATE utf8_bin NOT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_inventory
-- ----------------------------
INSERT INTO `t_inventory` VALUES ('2', '1', '0', 'G001', 'dls', '2000', '2017-12-05 11:24:28', '充足', null);
INSERT INTO `t_inventory` VALUES ('3', '1', '0', 'G001', 'dls', '2043', '2017-12-05 11:25:14', '充足', null);
INSERT INTO `t_inventory` VALUES ('4', '1', '0', 'C002', 'ddd', '2324', '2017-12-05 11:28:21', '充足', null);
INSERT INTO `t_inventory` VALUES ('5', '1', '0', 'C002', 'ddd', '435', '2017-12-05 11:28:45', '充足', null);
INSERT INTO `t_inventory` VALUES ('6', '1', '0', 'G001', 'dls', '128', '2017-12-11 09:44:21', '不足', null);
INSERT INTO `t_inventory` VALUES ('7', '1', '0', 'G001', 'dls', '342', '2017-12-11 09:44:53', '不足', null);
INSERT INTO `t_inventory` VALUES ('8', '1', '0', 'G001', 'dls', '3253', '2017-12-11 09:46:01', '充足', null);
INSERT INTO `t_inventory` VALUES ('9', '1', '0', 'G001', 'dls', '100', '2017-12-11 09:46:30', '不足', null);

-- ----------------------------
-- Table structure for `t_invoice`
-- ----------------------------
DROP TABLE IF EXISTS `t_invoice`;
CREATE TABLE `t_invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `invoiceId` varchar(20) COLLATE utf8_bin NOT NULL,
  `factoryId` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `sumPrice` double NOT NULL,
  `status` varchar(255) COLLATE utf8_bin NOT NULL,
  `note` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_invoice
-- ----------------------------
INSERT INTO `t_invoice` VALUES ('1', '0', '0', 'S20171203172351', 'V0001', '2017-12-03 17:32:27', '600', '未完成', '');

-- ----------------------------
-- Table structure for `t_invoicedetail`
-- ----------------------------
DROP TABLE IF EXISTS `t_invoicedetail`;
CREATE TABLE `t_invoicedetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `invoicedetailId` varchar(255) NOT NULL,
  `invoiceId` varchar(255) NOT NULL,
  `productId` varchar(255) NOT NULL,
  `productName` varchar(255) NOT NULL,
  `factoryId` varchar(255) NOT NULL,
  `amount` int(11) NOT NULL,
  `unitPrice` double NOT NULL,
  `totalPrice` double NOT NULL,
  `status` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_invoicedetail
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order`
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('40', '6', '0', 'S20171127164424', '2017-11-27 16:45:29', 'C001', '492.00', '审核通过', 'test2');
INSERT INTO `t_order` VALUES ('41', '5', '0', 'S20171203112816', '2017-12-03 11:29:23', 'C001', '9562.00', '审核通过', '优秀供应商');
INSERT INTO `t_order` VALUES ('42', '5', '0', 'S20171204230844', '2017-12-04 23:09:18', 'C001', '2400.00', '审核通过', '优秀供应商');

-- ----------------------------
-- Table structure for `t_orderdetail`
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
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_orderdetail
-- ----------------------------
INSERT INTO `t_orderdetail` VALUES ('98', '5', '0', 'S20171127164424D164452', 'S20171127164424', 'G001', '大螺丝', '123', '0', '4.00', '492.00', '审核通过', '');
INSERT INTO `t_orderdetail` VALUES ('99', '2', '0', 'S20171203112816D112841', 'S20171203112816', 'G001', '大车轮', '98', '0', '78.00', '3510.00', '审核通过', '');
INSERT INTO `t_orderdetail` VALUES ('100', '2', '0', 'S20171203112816D112858', 'S20171203112816', 'C002', '大螺丝', '2325', '0', '89.00', '6052.00', '审核通过', '');
INSERT INTO `t_orderdetail` VALUES ('101', '3', '0', 'S20171204230844D230859', 'S20171204230844', 'G001', '大螺丝', '120', '0', '20.00', '2400.00', '审核通过', '');

-- ----------------------------
-- Table structure for `t_outofstock`
-- ----------------------------
DROP TABLE IF EXISTS `t_outofstock`;
CREATE TABLE `t_outofstock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `outofstockId` varchar(20) CHARACTER SET utf8 NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_outofstock
-- ----------------------------
INSERT INTO `t_outofstock` VALUES ('5', '0', '0', 'S20171127164424OOS', 'S20171127164424', 'C001', '1', '0', '1', '0', '2017-12-14 19:32:29', '处理中', null);
INSERT INTO `t_outofstock` VALUES ('6', '0', '0', 'S20171204230844OOS', 'S20171204230844', 'C001', '1', '0', '1', '0', '2017-12-14 22:44:05', '处理中', null);
INSERT INTO `t_outofstock` VALUES ('7', '0', '0', 'S20171203112816OOS', 'S20171203112816', 'C001', '2', '1', '1', '0', '2017-12-14 22:49:15', '处理中', null);

-- ----------------------------
-- Table structure for `t_outofstockdetail`
-- ----------------------------
DROP TABLE IF EXISTS `t_outofstockdetail`;
CREATE TABLE `t_outofstockdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `outofstockdetailId` varchar(50) CHARACTER SET utf8 NOT NULL,
  `outofstockId` varchar(20) CHARACTER SET utf8 NOT NULL,
  `productId` varchar(20) CHARACTER SET utf8 NOT NULL,
  `quantityDemand` int(11) NOT NULL,
  `quantitySupplied` int(11) NOT NULL,
  `quantityNeeded` int(11) NOT NULL,
  `operatorName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `operateTime` datetime NOT NULL,
  `status` varchar(20) COLLATE utf8_bin NOT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_outofstockdetail
-- ----------------------------
INSERT INTO `t_outofstockdetail` VALUES ('1', '1', '0', 'OS100D123', 'OS100', 'P001', '1234', '34', '1200', 'ws', '2017-12-12 22:25:05', '未完成', null);
INSERT INTO `t_outofstockdetail` VALUES ('2', '0', '0', 'S20171127164424OOSD193229', 'S20171127164424OOS', 'G001', '123', '100', '23', null, '2017-12-14 19:32:29', '待处理', null);
INSERT INTO `t_outofstockdetail` VALUES ('3', '0', '0', 'S20171204230844OOSD224405', 'S20171204230844OOS', 'G001', '120', '100', '20', null, '2017-12-14 22:44:05', '待处理', null);
INSERT INTO `t_outofstockdetail` VALUES ('4', '0', '0', 'S20171203112816OOSD224915', 'S20171203112816OOS', 'C002', '2325', '435', '1890', null, '2017-12-14 22:49:15', '待处理', null);

-- ----------------------------
-- Table structure for `t_prepare`
-- ----------------------------
DROP TABLE IF EXISTS `t_prepare`;
CREATE TABLE `t_prepare` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `prepareId` varchar(255) NOT NULL,
  `orderId` varchar(255) NOT NULL,
  `createTime` datetime NOT NULL,
  `customerId` varchar(255) NOT NULL,
  `orderNum` int(11) NOT NULL,
  `fitNum` int(11) NOT NULL,
  `partfitNum` int(11) NOT NULL,
  `outofstockNum` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `receivePers` varchar(255) DEFAULT NULL,
  `receiveAddr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_prepare
-- ----------------------------
INSERT INTO `t_prepare` VALUES ('1', '1', '1', 'P001', 'O001', '2017-12-26 20:03:56', 'C0001', '1', '1', '1', '1', '完成', null, null, null);

-- ----------------------------
-- Table structure for `t_preparedetail`
-- ----------------------------
DROP TABLE IF EXISTS `t_preparedetail`;
CREATE TABLE `t_preparedetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `preparedetailId` varchar(255) NOT NULL,
  `prepareId` varchar(255) NOT NULL,
  `productId` varchar(255) NOT NULL,
  `productName` varchar(255) NOT NULL,
  `factoryId` varchar(255) NOT NULL,
  `preparePers` varchar(255) NOT NULL,
  `amount` int(11) NOT NULL,
  `size` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_preparedetail
-- ----------------------------

-- ----------------------------
-- Table structure for `t_product`
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `productId` varchar(20) COLLATE utf8_bin NOT NULL,
  `productName` varchar(50) COLLATE utf8_bin NOT NULL,
  `productType` varchar(1) COLLATE utf8_bin NOT NULL,
  `productSpecification` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `productOrigin` varchar(20) COLLATE utf8_bin NOT NULL,
  `productUnit` varchar(10) COLLATE utf8_bin NOT NULL,
  `safeStock` int(11) DEFAULT '0',
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('1', '6', '0', 'C001', '大螺丝', 'S', 'S00989-098', 'V001', '个', '1000', '');
INSERT INTO `t_product` VALUES ('2', '0', '0', 'M0019', '大螺丝', 'M', null, '', '个', '800', '');
INSERT INTO `t_product` VALUES ('3', '0', '0', 'G001', '大螺丝', 'G', null, '', '个', '2008', '优秀供应商');

-- ----------------------------
-- Table structure for `t_purchaseorder`
-- ----------------------------
DROP TABLE IF EXISTS `t_purchaseorder`;
CREATE TABLE `t_purchaseorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `orderId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `orderDate` date NOT NULL,
  `vendorId` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `vendorName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `totalPrice` decimal(65,2) NOT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `note` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `vendorId` (`vendorId`),
  KEY `vendorName` (`vendorName`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_purchaseorder
-- ----------------------------
INSERT INTO `t_purchaseorder` VALUES ('6', '0', '0', 'P20171225215627', '2017-12-25', 'V0001', '螺丝厂', '0.00', '未完成', '备注');
INSERT INTO `t_purchaseorder` VALUES ('7', '0', '0', 'P20171225222158', '2017-12-25', 'V002', '车轮厂', '0.00', '未完成', '一个很长长长长长长长长长长长长长长长长长长长长长长的备注');

-- ----------------------------
-- Table structure for `t_vendor`
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
INSERT INTO `t_vendor` VALUES ('2', '2', '0', 'V002', '车轮厂', '天津', '李先生', '123453578', '13@126.com', '');
