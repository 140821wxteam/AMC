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

 Date: 29/12/2017 10:25:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
INSERT INTO `authority` VALUES (24, 2, 0, '销售子系统', '24', 0, '4', '/sales', '^/sales$', 'icon-barcode', NULL);
INSERT INTO `authority` VALUES (25, 2, 0, '销售订单管理', '24,25', 0, '1', '/sales/order', '/sales/order', '', 24);
INSERT INTO `authority` VALUES (30, 1, 0, '采购子系统', '30', 0, '4', '/purchase/list', '^/purchase/list$', 'icon-shopping-cart', NULL);
INSERT INTO `authority` VALUES (31, 1, 0, '采购订单管理', '30,31', 0, '1', '/purchase/orderlist', '/purchase/orderlist', '', 30);
INSERT INTO `authority` VALUES (34, 1, 0, '库存子系统', '34', 0, '7', '/inventory/list', '/inventory/list', '', NULL);
INSERT INTO `authority` VALUES (35, 1, 0, '备货单管理', '34,35', 2, '2', '/inventory/prepare', '/inventory/prepare', '', 34);
INSERT INTO `authority` VALUES (36, 1, 0, '进货单管理', '34,36', 3, '3', '/inventory/stockin', '/inventory/stockin', '', 34);
INSERT INTO `authority` VALUES (43, 1, 0, '新增角色', '3,7,43', 0, '3', '/role/add', '/role/add', '', 7);
INSERT INTO `authority` VALUES (44, 1, 0, '修改角色', '3,7,44', 0, '4', '/role/edit', '/role/edit', '', 7);
INSERT INTO `authority` VALUES (49, 1, 0, '订单列表', '24,25,49', 0, '1', '/sales/order', '/sales/order', '', 25);
INSERT INTO `authority` VALUES (50, 1, 0, '查看订单明细', '24,25,50', 0, '1', '/sales/orderdetail', '/sales/orderdetail', '', 25);
INSERT INTO `authority` VALUES (51, 1, 0, '增加订单', '24,25,51', 0, '3', '/sales/orderaddnew', '/sales/orderaddnew', '', 25);
INSERT INTO `authority` VALUES (53, 2, 0, '新建订单明细', '24,25,53', 0, '4', '/sales/orderdetailadd', '/sales/orderdetailadd', '', 25);
INSERT INTO `authority` VALUES (69, 2, 0, '订单缺件表管理', '34,69', 3, '3', '/inventory/outofstock', '/inventory/outofstock', '', 34);
INSERT INTO `authority` VALUES (70, 3, 0, '财务子系统', '70', 0, '1', '/financial/invoice', '/financial/invoice', '', NULL);
INSERT INTO `authority` VALUES (71, 2, 0, '发票管理', '70,71', 0, '1', '/financial/invoice', '/financial/invoice', '', 70);
INSERT INTO `authority` VALUES (73, 2, 0, '发货单管理', '34,73', 6, '6', '/inventory/deliverlist', '/inventory/deliverlist', '', 34);
INSERT INTO `authority` VALUES (75, 1, 0, '发票列表', '70,71,75', 0, '', '/financial/invoice', '/financial/invoice', '', 71);
INSERT INTO `authority` VALUES (76, 1, 0, '顾客管理', '24,76', 0, '', '/basedata/customer', '/basedata/customer', '', 24);
INSERT INTO `authority` VALUES (77, 1, 0, '顾客列表', '24,76,77', 0, '', '/basedata/customer', '/basedata/customer', '', 76);
INSERT INTO `authority` VALUES (78, 1, 0, '添加顾客', '24,76,78', 0, '', '/basedata/customeradd', '/basedata/customeradd', '', 76);
INSERT INTO `authority` VALUES (79, 1, 0, '编辑顾客', '24,76,79', 0, '', '/basedata/customeredit', '/basedata/customeredit', '', 76);
INSERT INTO `authority` VALUES (80, 1, 0, '催款单管理', '70,80', 0, '', '/financial/reminder', '/financial/reminder', '', 70);
INSERT INTO `authority` VALUES (82, 1, 0, '销售业务账管理', '70,82', 0, '', '/financial/salesaccount', '/financial/salesaccount', '', 70);
INSERT INTO `authority` VALUES (83, 1, 0, '采购业务账管理', '70,83', 0, '', '/financial/purchaseaccount', '/financial/purchaseaccount', '', 70);
INSERT INTO `authority` VALUES (84, 1, 0, '失信记录管理', '70,84', 0, '', '/financial/liquidate', '/financial/liquidate', '', 70);
INSERT INTO `authority` VALUES (85, 1, 0, '产品管理', '34,85', 0, '', '/basedata/product', '/basedata/product', '', 34);
INSERT INTO `authority` VALUES (86, 1, 0, '产品列表', '34,85,86', 0, '', '/basedata/product', '/basedata/product', '', 85);
INSERT INTO `authority` VALUES (87, 1, 0, '添加产品', '34,85,87', 0, '', '/basedata/productadd', '/basedata/productadd', '', 85);
INSERT INTO `authority` VALUES (88, 1, 0, '编辑产品', '34,85,88', 0, '', '/basedata/productedit', '/basedata/productedit', '', 85);
INSERT INTO `authority` VALUES (89, 1, 0, '供应商管理', '30,89', 0, '', '/basedata/vendor', '/basedata/vendor', '', 30);
INSERT INTO `authority` VALUES (90, 1, 0, '供应商列表', '30,89,90', 0, '', '/basedata/vendoradd', '/basedata/vendoradd', '', 89);
INSERT INTO `authority` VALUES (91, 1, 0, '添加供应商', '30,89,91', 0, '', '/basedata/vendoradd', '/basedata/vendoradd', '', 89);
INSERT INTO `authority` VALUES (92, 1, 0, '编辑供应商', '30,89,92', 0, '', '/basedata/vendoredit', '/basedata/vendoredit', '', 89);
INSERT INTO `authority` VALUES (93, 1, 0, '库存基本信息', '34,93', 0, '', '/inventory/list', '/inventory/list', '', 34);
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
INSERT INTO `role_authority` VALUES (2, 1);
INSERT INTO `role_authority` VALUES (2, 2);
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
INSERT INTO `role_authority` VALUES (1, 76);
INSERT INTO `role_authority` VALUES (1, 77);
INSERT INTO `role_authority` VALUES (1, 78);
INSERT INTO `role_authority` VALUES (1, 79);
INSERT INTO `role_authority` VALUES (1, 30);
INSERT INTO `role_authority` VALUES (1, 31);
INSERT INTO `role_authority` VALUES (1, 89);
INSERT INTO `role_authority` VALUES (1, 90);
INSERT INTO `role_authority` VALUES (1, 91);
INSERT INTO `role_authority` VALUES (1, 92);
INSERT INTO `role_authority` VALUES (1, 34);
INSERT INTO `role_authority` VALUES (1, 35);
INSERT INTO `role_authority` VALUES (1, 36);
INSERT INTO `role_authority` VALUES (1, 69);
INSERT INTO `role_authority` VALUES (1, 73);
INSERT INTO `role_authority` VALUES (1, 85);
INSERT INTO `role_authority` VALUES (1, 86);
INSERT INTO `role_authority` VALUES (1, 87);
INSERT INTO `role_authority` VALUES (1, 88);
INSERT INTO `role_authority` VALUES (1, 93);
INSERT INTO `role_authority` VALUES (1, 70);
INSERT INTO `role_authority` VALUES (1, 71);
INSERT INTO `role_authority` VALUES (1, 75);
INSERT INTO `role_authority` VALUES (1, 80);
INSERT INTO `role_authority` VALUES (1, 82);
INSERT INTO `role_authority` VALUES (1, 83);
INSERT INTO `role_authority` VALUES (1, 84);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
