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

 Date: 15/01/2018 20:25:10
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES (12, 5, 1, 'fc', 'fc@163.com', 'fc', 'f79a121e29600f0fb867a6c4eea6b29e', '2017-05-02 18:09:06', 1, 6);
INSERT INTO `account` VALUES (13, 0, 1, 'wx', 'wenxin_199511@126.com', 'wenxin', 'c0bbf3c4f4ae2172be3aaeb8a4852391', '2017-10-26 17:04:10', 1, 1);
INSERT INTO `account` VALUES (14, 1, 1, '赵雅萍', 'zyp@qq.com', 'zyp', 'a9e2e2a3da6cf7dba8727de483b7c917', '2017-11-04 09:46:55', 2, NULL);
INSERT INTO `account` VALUES (15, 0, 1, '景怡', 'kuran_king@163.com', '景怡', '141ad0fb9d1120484013f69aaeb3c822', '2017-12-18 16:46:50', 1, 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
INSERT INTO `authority` VALUES (31, 1, 0, '采购订单管理', '30,31', 0, '1', '/purchase/purchaseorder', '/purchase/purchaseorder', '', 30);
INSERT INTO `authority` VALUES (32, 1, 0, '新增采购订单', '30,31,32', 0, '1', '/purchase/purchaseorderadd', '/purchase/purchaseorderadd', NULL, 31);
INSERT INTO `authority` VALUES (33, 1, 0, '删除采购订单', '30,31,33', 0, '2', '/purchase/purchaseorderdelete', '/purchase/purchaseorderdelete', NULL, 31);
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
INSERT INTO `authority` VALUES (73, 2, 0, '发货单管理', '34,73', 6, '6', '/inventory/deliverlist', '/inventory/deliverlist', '', 34);
INSERT INTO `authority` VALUES (76, 1, 0, '顾客管理', '24,76', 0, '', '/basedata/customer', '/basedata/customer', '', 24);
INSERT INTO `authority` VALUES (77, 1, 0, '顾客列表', '24,76,77', 0, '', '/basedata/customer', '/basedata/customer', '', 76);
INSERT INTO `authority` VALUES (78, 1, 0, '添加顾客', '24,76,78', 0, '', '/basedata/customeradd', '/basedata/customeradd', '', 76);
INSERT INTO `authority` VALUES (79, 1, 0, '编辑顾客', '24,76,79', 0, '', '/basedata/customeredit', '/basedata/customeredit', '', 76);
INSERT INTO `authority` VALUES (85, 1, 0, '产品管理', '34,85', 0, '', '/basedata/product', '/basedata/product', '', 34);
INSERT INTO `authority` VALUES (86, 1, 0, '产品列表', '34,85,86', 0, '', '/basedata/product', '/basedata/product', '', 85);
INSERT INTO `authority` VALUES (87, 1, 0, '添加产品', '34,85,87', 0, '', '/basedata/productadd', '/basedata/productadd', '', 85);
INSERT INTO `authority` VALUES (88, 1, 0, '编辑产品', '34,85,88', 0, '', '/basedata/productedit', '/basedata/productedit', '', 85);
INSERT INTO `authority` VALUES (89, 1, 0, '供应商管理', '30,89', 0, '', '/basedata/vendor', '/basedata/vendor', '', 30);
INSERT INTO `authority` VALUES (90, 1, 0, '供应商列表', '30,89,90', 0, '', '/basedata/vendor', '/basedata/vendor', '', 89);
INSERT INTO `authority` VALUES (91, 1, 0, '添加供应商', '30,89,91', 0, '', '/basedata/vendoradd', '/basedata/vendoradd', '', 89);
INSERT INTO `authority` VALUES (92, 1, 0, '编辑供应商', '30,89,92', 0, '', '/basedata/vendoredit', '/basedata/vendoredit', '', 89);
INSERT INTO `authority` VALUES (93, 1, 0, '库存基本信息', '34,93', 0, '', '/inventory/list', '/inventory/list', '', 34);
INSERT INTO `authority` VALUES (94, 1, 0, '发货单详情', '34,73,94', 0, '', '/inventory/deliverdetail', '/inventory/deliverdetail', '', 73);
INSERT INTO `authority` VALUES (95, 2, 0, '打印预览', '34,69,95', 0, '', '/inventory/printoutofstock', '/inventory/printoutofstock', '', 69);
INSERT INTO `authority` VALUES (96, 1, 0, '进货单列表', '34,36,96', 0, '', '/inventory/stockin', '/inventory/stockin', '', 36);
INSERT INTO `authority` VALUES (97, 1, 0, '添加库存信息', '34,93,97', 0, '', '/inventory/inventoryadd', '/inventory/inventoryadd', '', 93);
INSERT INTO `authority` VALUES (98, 1, 0, '订单缺件表列表', '34,69,98', 0, '', '/inventory/outofstock', '/inventory/outofstock', '', 69);
INSERT INTO `authority` VALUES (99, 1, 0, '订单缺件表明细', '34,69,99', 0, '', '/inventory/outofstockdetail', '/inventory/outofstockdetail', '', 69);
INSERT INTO `authority` VALUES (100, 2, 0, '禁用角色', '3,7,100', 0, '', '/role/roledisable', '/role/roledisable', '', 7);
INSERT INTO `authority` VALUES (101, 2, 0, '启用角色', '3,7,101', 0, '', '/role/roleenable', '/role/roleenable', '', 7);
INSERT INTO `authority` VALUES (102, 1, 0, '删除角色', '3,7,102', 0, '', '/role/roledelete', '/role/roledelete', '', 7);
INSERT INTO `authority` VALUES (103, 1, 0, '启用用户', '3,4,103', 0, '', '/account/accountenable', '/account/accountenable', '', 4);
INSERT INTO `authority` VALUES (104, 1, 0, '禁用用户', '3,4,104', 0, '', '/account/accountdisable', '/account/accountdisable', '', 4);
INSERT INTO `authority` VALUES (105, 1, 0, '删除用户', '3,4,105', 0, '', '/account/accountdelete', '/account/accountdelete', '', 4);
INSERT INTO `authority` VALUES (106, 1, 0, '添加进货单', '34,36,106', 0, '', '/inventory/stockinadd', '/inventory/stockinadd', '', 36);
INSERT INTO `authority` VALUES (107, 1, 0, '编辑进货单', '34,36,107', 0, '', '/inventory/stockinedit', '/inventory/stockinedit', '', 36);
INSERT INTO `authority` VALUES (108, 1, 0, '确认入库', '34,36,108', 0, '', '/inventory/stockinconfirm', '/inventory/stockinconfirm', '', 36);
INSERT INTO `authority` VALUES (109, 1, 0, '备货单列表', '34,35,109', 0, '', '/inventory/prepare', '/inventory/prepare', '', 35);
INSERT INTO `authority` VALUES (110, 1, 0, '备货单明细', '34,35,110', 0, '', '/inventory/preparedetail', '/inventory/preparedetail', '', 35);
INSERT INTO `authority` VALUES (111, 1, 0, '刷新库存状态', '34,93,111', 0, '', '/inventory/inventoryrefresh', '/inventory/inventoryrefresh', '', 93);
INSERT INTO `authority` VALUES (112, 1, 0, '生成采购建议', '34,69,112', 0, '', '/inventory/advice', '/inventory/advice', '', 69);
INSERT INTO `authority` VALUES (122, 1, 0, '删除备货单', '34,35,122', 0, '', '/inventory/preparedelete', '/inventory/preparedelete', '', 35);
INSERT INTO `authority` VALUES (123, 1, 0, '删除缺件表', '34,69,123', 0, '', '/inventory/outofstockdelete', '/inventory/outofstockdelete', '', 69);
INSERT INTO `authority` VALUES (124, 1, 0, '备货单发货操作', '34,35,124', 0, '', '/inventory/todeliver', '/inventory/todeliver', '', 35);
INSERT INTO `authority` VALUES (125, 1, 0, '催款单管理', '70,125', 0, '', '/financial/cuikuan', '/financial/cuikuan', '', 70);
INSERT INTO `authority` VALUES (126, 1, 0, '查看催款单明细', '70,125,126', 0, '', '/financial/cuikuandetail', '/financial/cuikuandetail', '', 125);
INSERT INTO `authority` VALUES (127, 1, 0, '记应付帐', '70,125,127', 0, '', '/financial/changereceivable', '/financial/changereceivable', '', 125);
INSERT INTO `authority` VALUES (128, 1, 0, '修改支付状态', '70,125,128', 0, '', '/financial/changestatus', '/financial/changestatus', '', 125);
INSERT INTO `authority` VALUES (129, 1, 0, '失信处理', '70,125,129', 0, '', '/financial/editreputation', '/financial/editreputation', '', 125);
INSERT INTO `authority` VALUES (130, 1, 0, '催款单开发票', '70,125,130', 0, '', '/financial/toinvoice', '/financial/toinvoice', '', 125);
INSERT INTO `authority` VALUES (131, 1, 0, '发票管理', '70,131', 0, '', '/financial/invoice', '/financial/invoice', '', 70);
INSERT INTO `authority` VALUES (132, 1, 0, '发票列表', '70,131,132', 0, '', '/financial/invoice', '/financial/invoice', '', 131);
INSERT INTO `authority` VALUES (133, 1, 0, '发票明细', '70,131,133', 0, '', '/financial/invoicedetail', '/financial/invoicedetail', '', 131);
INSERT INTO `authority` VALUES (134, 1, 0, '寄送发票', '70,131,134', 0, '', '/financial/changeinvoicestatus', '/financial/changeinvoicestatus', '', 131);
INSERT INTO `authority` VALUES (135, 1, 0, '发票转采购业务账', '70,131,135', 0, '', '/financial/changepurchasebusiness', '/financial/changepurchasebusiness', '', 131);
INSERT INTO `authority` VALUES (136, 1, 0, '账目表管理', '70,136', 0, '', '/financial/accounttablelist', '/financial/accounttablelist', '', 70);
INSERT INTO `authority` VALUES (137, 1, 0, '生成催款单', '34,73,137', 0, '', '/inventory/tocuikuan', '/inventory/tocuikuan', '', 73);
INSERT INTO `authority` VALUES (138, 1, 0, '编辑采购订单', '30,31,138', 0, '', '/purchase/purchaseorderedit', '/purchase/purchaseorderedit', '', 31);
INSERT INTO `authority` VALUES (139, 1, 0, '销售预测', '24,139', 0, '', '/sales/listchanging', '/sales/listchanging', '', 24);
INSERT INTO `authority` VALUES (140, 1, 0, '新建发票', '70,131,140', 0, '', '/financial/invoiceadd', '/financial/invoiceadd', '', 131);
INSERT INTO `authority` VALUES (141, 1, 0, '查看采购订单明细', '30,31,141', 0, '', '/purchase/purchasedetail', '/purchase/purchasedetail', '', 31);
INSERT INTO `authority` VALUES (142, 1, 0, '编辑采购订单明细', '30,31,142', 0, '', '/purchase/purchasedetailedit', '/purchase/purchasedetailedit', '', 31);
INSERT INTO `authority` VALUES (143, 1, 0, '新建采购订单明细', '30,31,143', 0, '', '/purchase/purchasedetailadd', '/purchase/purchasedetailadd', '', 31);
INSERT INTO `authority` VALUES (144, 1, 0, '采购订单审核通过', '30,31,144', 0, '', '/purchase/orderconfirm', '/purchase/orderconfirm', '', 31);
INSERT INTO `authority` VALUES (145, 1, 0, '发送采购订单', '30,31,145', 0, '', '/purchase/ordersend', '/purchase/ordersend', '', 31);
INSERT INTO `authority` VALUES (146, 1, 0, '采购订单到货确认', '30,31,146', 0, '', '/purchase/productreceived', '/purchase/productreceived', '', 31);
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
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 69, '系统管理员', 1);
INSERT INTO `role` VALUES (2, 9, '顾客', 1);
INSERT INTO `role` VALUES (3, 0, '仓库管理员', 1);
INSERT INTO `role` VALUES (4, 0, '销售管理员', 1);
INSERT INTO `role` VALUES (5, 0, '采购管理员', 1);
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
INSERT INTO `role_authority` VALUES (1, 103);
INSERT INTO `role_authority` VALUES (1, 104);
INSERT INTO `role_authority` VALUES (1, 105);
INSERT INTO `role_authority` VALUES (1, 7);
INSERT INTO `role_authority` VALUES (1, 8);
INSERT INTO `role_authority` VALUES (1, 9);
INSERT INTO `role_authority` VALUES (1, 43);
INSERT INTO `role_authority` VALUES (1, 44);
INSERT INTO `role_authority` VALUES (1, 100);
INSERT INTO `role_authority` VALUES (1, 101);
INSERT INTO `role_authority` VALUES (1, 102);
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
INSERT INTO `role_authority` VALUES (1, 51);
INSERT INTO `role_authority` VALUES (1, 53);
INSERT INTO `role_authority` VALUES (1, 76);
INSERT INTO `role_authority` VALUES (1, 77);
INSERT INTO `role_authority` VALUES (1, 78);
INSERT INTO `role_authority` VALUES (1, 79);
INSERT INTO `role_authority` VALUES (1, 139);
INSERT INTO `role_authority` VALUES (1, 30);
INSERT INTO `role_authority` VALUES (1, 31);
INSERT INTO `role_authority` VALUES (1, 32);
INSERT INTO `role_authority` VALUES (1, 33);
INSERT INTO `role_authority` VALUES (1, 138);
INSERT INTO `role_authority` VALUES (1, 141);
INSERT INTO `role_authority` VALUES (1, 142);
INSERT INTO `role_authority` VALUES (1, 143);
INSERT INTO `role_authority` VALUES (1, 144);
INSERT INTO `role_authority` VALUES (1, 145);
INSERT INTO `role_authority` VALUES (1, 146);
INSERT INTO `role_authority` VALUES (1, 89);
INSERT INTO `role_authority` VALUES (1, 90);
INSERT INTO `role_authority` VALUES (1, 91);
INSERT INTO `role_authority` VALUES (1, 92);
INSERT INTO `role_authority` VALUES (1, 34);
INSERT INTO `role_authority` VALUES (1, 35);
INSERT INTO `role_authority` VALUES (1, 109);
INSERT INTO `role_authority` VALUES (1, 110);
INSERT INTO `role_authority` VALUES (1, 122);
INSERT INTO `role_authority` VALUES (1, 124);
INSERT INTO `role_authority` VALUES (1, 36);
INSERT INTO `role_authority` VALUES (1, 96);
INSERT INTO `role_authority` VALUES (1, 106);
INSERT INTO `role_authority` VALUES (1, 107);
INSERT INTO `role_authority` VALUES (1, 108);
INSERT INTO `role_authority` VALUES (1, 69);
INSERT INTO `role_authority` VALUES (1, 95);
INSERT INTO `role_authority` VALUES (1, 98);
INSERT INTO `role_authority` VALUES (1, 99);
INSERT INTO `role_authority` VALUES (1, 112);
INSERT INTO `role_authority` VALUES (1, 123);
INSERT INTO `role_authority` VALUES (1, 73);
INSERT INTO `role_authority` VALUES (1, 94);
INSERT INTO `role_authority` VALUES (1, 137);
INSERT INTO `role_authority` VALUES (1, 85);
INSERT INTO `role_authority` VALUES (1, 86);
INSERT INTO `role_authority` VALUES (1, 87);
INSERT INTO `role_authority` VALUES (1, 88);
INSERT INTO `role_authority` VALUES (1, 93);
INSERT INTO `role_authority` VALUES (1, 97);
INSERT INTO `role_authority` VALUES (1, 111);
INSERT INTO `role_authority` VALUES (1, 70);
INSERT INTO `role_authority` VALUES (1, 125);
INSERT INTO `role_authority` VALUES (1, 126);
INSERT INTO `role_authority` VALUES (1, 127);
INSERT INTO `role_authority` VALUES (1, 128);
INSERT INTO `role_authority` VALUES (1, 129);
INSERT INTO `role_authority` VALUES (1, 130);
INSERT INTO `role_authority` VALUES (1, 131);
INSERT INTO `role_authority` VALUES (1, 132);
INSERT INTO `role_authority` VALUES (1, 133);
INSERT INTO `role_authority` VALUES (1, 134);
INSERT INTO `role_authority` VALUES (1, 135);
INSERT INTO `role_authority` VALUES (1, 140);
INSERT INTO `role_authority` VALUES (1, 136);
COMMIT;

-- ----------------------------
-- Table structure for t_accounttable
-- ----------------------------
DROP TABLE IF EXISTS `t_accounttable`;
CREATE TABLE `t_accounttable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `accounttableId` varchar(255) DEFAULT NULL,
  `orderId` varchar(255) DEFAULT NULL,
  `deliverId` varchar(255) DEFAULT NULL,
  `cuikuanId` varchar(255) DEFAULT NULL,
  `invoiceId` varchar(255) DEFAULT NULL,
  `customerId` varchar(255) DEFAULT NULL,
  `objection` int(11) DEFAULT NULL,
  `receivable` double DEFAULT NULL,
  `salesBusiness` double DEFAULT NULL,
  `payable` double DEFAULT NULL,
  `purchaseBusiness` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_cuikuan
-- ----------------------------
DROP TABLE IF EXISTS `t_cuikuan`;
CREATE TABLE `t_cuikuan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invoiceId` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cuikuanId` varchar(255) COLLATE utf8_bin NOT NULL,
  `deliverId` varchar(255) COLLATE utf8_bin NOT NULL,
  `cuikuanObjection` int(11) DEFAULT NULL,
  `customerId` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `orderId` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `orderReceiveDate` date DEFAULT NULL,
  `amountMoney` double DEFAULT NULL,
  `orderdetailid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `receiveDate` date DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_cuikuandetail
-- ----------------------------
DROP TABLE IF EXISTS `t_cuikuandetail`;
CREATE TABLE `t_cuikuandetail` (
  `version` int(11) NOT NULL DEFAULT '0',
  `enable` tinyint(4) NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cuikuanId` varchar(255) NOT NULL,
  `productId` varchar(255) NOT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `factoryId` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `money` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cuikuandetail
-- ----------------------------
BEGIN;
INSERT INTO `t_cuikuandetail` VALUES (0, 0, 1, 'CUI20180115014536', 'G001', '大号轮胎', 'V001', 1232, 0, 0);
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
  `credit` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `province` varchar(255) COLLATE utf8_bin NOT NULL,
  `reputation` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
BEGIN;
INSERT INTO `t_customer` VALUES (7, 0, 0, 'C001', '张先生', '北京市海淀区学院路37号', '张xx', '01086752635', 'zhangx@126.com', '', NULL, '北京', 0);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_inventory
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
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_invoice
-- ----------------------------
DROP TABLE IF EXISTS `t_invoice`;
CREATE TABLE `t_invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(4) NOT NULL,
  `invoiceId` varchar(255) NOT NULL,
  `objection` int(11) DEFAULT NULL,
  `orderId` varchar(255) DEFAULT NULL,
  `orderReceiveDate` date DEFAULT NULL,
  `amountMoney` double DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_invoicedetail
-- ----------------------------
DROP TABLE IF EXISTS `t_invoicedetail`;
CREATE TABLE `t_invoicedetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `invoiceId` varchar(20) COLLATE utf8_bin NOT NULL,
  `productId` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `productName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `factoryId` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `money` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_invoicedetail
-- ----------------------------
BEGIN;
INSERT INTO `t_invoicedetail` VALUES (1, 0, 0, 'I20180114175357', NULL, NULL, 'V0001', 9, 9, 600);
COMMIT;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `orderId` varchar(255) COLLATE utf8_bin NOT NULL,
  `createTime` datetime NOT NULL,
  `customerId` varchar(255) COLLATE utf8_bin NOT NULL,
  `totalPrice` decimal(65,2) NOT NULL,
  `status` varchar(20) COLLATE utf8_bin NOT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES (73, 1, 0, 'S20180115173935', '2018-01-15 17:39:56', 'C001', 200000.00, '已退回', '');
INSERT INTO `t_order` VALUES (74, 1, 0, 'S20180115192757', '2018-01-15 19:28:16', 'C001', 136974.00, '已退回', '');
COMMIT;

-- ----------------------------
-- Table structure for t_orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `t_orderdetail`;
CREATE TABLE `t_orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `orderdetailId` varchar(255) COLLATE utf8_bin NOT NULL,
  `orderId` varchar(255) COLLATE utf8_bin NOT NULL,
  `productId` varchar(255) COLLATE utf8_bin NOT NULL,
  `productName` varchar(255) COLLATE utf8_bin NOT NULL,
  `quantityDemand` int(11) NOT NULL,
  `quantitySupplied` int(11) NOT NULL,
  `unitPrice` decimal(65,2) NOT NULL,
  `totalPrice` decimal(65,2) NOT NULL,
  `status` varchar(20) COLLATE utf8_bin NOT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_order_orderId` (`orderId`),
  KEY `IDX_product_productId` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_orderdetail
-- ----------------------------
BEGIN;
INSERT INTO `t_orderdetail` VALUES (169, 1, 0, 'S20180115173935D173938', 'S20180115173935', 'PG001', '轮胎', 200, 0, 1000.00, 200000.00, '已退回', '');
INSERT INTO `t_orderdetail` VALUES (170, 1, 0, 'S20180115192757D192802', 'S20180115192757', 'PG001', '轮胎', 1234, 0, 111.00, 136974.00, '已退回', '');
COMMIT;

-- ----------------------------
-- Table structure for t_outofstock
-- ----------------------------
DROP TABLE IF EXISTS `t_outofstock`;
CREATE TABLE `t_outofstock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `outofstockId` varchar(255) CHARACTER SET utf8 NOT NULL,
  `orderId` varchar(255) CHARACTER SET utf8 NOT NULL,
  `customerId` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL,
  `fitNum` int(11) DEFAULT NULL,
  `partfitNum` int(11) DEFAULT NULL,
  `outofstockNum` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `note` varchar(1024) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_outofstockdetail
-- ----------------------------
DROP TABLE IF EXISTS `t_outofstockdetail`;
CREATE TABLE `t_outofstockdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `outofstockdetailId` varchar(255) CHARACTER SET utf8 NOT NULL,
  `outofstockId` varchar(255) CHARACTER SET utf8 NOT NULL,
  `productId` varchar(255) CHARACTER SET utf8 NOT NULL,
  `quantityDemand` int(11) NOT NULL,
  `quantitySupplied` int(11) NOT NULL,
  `quantityNeeded` int(11) NOT NULL,
  `operatorName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `operateTime` datetime NOT NULL,
  `status` varchar(20) COLLATE utf8_bin NOT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_prepare
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_preparedetail
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `productId` varchar(255) COLLATE utf8_bin NOT NULL,
  `productName` varchar(255) COLLATE utf8_bin NOT NULL,
  `productType` varchar(1) COLLATE utf8_bin NOT NULL,
  `productSpecification` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `productOrigin` varchar(255) COLLATE utf8_bin NOT NULL,
  `productUnit` varchar(10) COLLATE utf8_bin NOT NULL,
  `safeStock` int(11) DEFAULT '0',
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `images` varchar(1024) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_product
-- ----------------------------
BEGIN;
INSERT INTO `t_product` VALUES (8, 0, 0, 'PG001', '轮胎', 'G', 'G11-00', 'V001', '个', 1000, '', '');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_purchasedetail
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
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_purchasedetail
-- ----------------------------
BEGIN;
INSERT INTO `t_purchasedetail` VALUES (111, 0, 0, 'P20180115182416D182421', 'P20180115182416', 'PG001', '轮胎', 900, 12.00, 10800.00, '');
INSERT INTO `t_purchasedetail` VALUES (112, 0, 0, 'P20180115185937D185941', 'P20180115185937', 'PG001', '轮胎', 890, 89.00, 79210.00, '');
COMMIT;

-- ----------------------------
-- Table structure for t_purchaseorder
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_purchaseorder
-- ----------------------------
BEGIN;
INSERT INTO `t_purchaseorder` VALUES (3, 3, 0, 'P20180115182416', '2018-01-15', 'V002', '车轮厂', 10800.00, '已收货', '');
INSERT INTO `t_purchaseorder` VALUES (4, 0, 0, 'P20180115185937', '2018-01-15', 'V002', '车轮厂', 79210.00, '待审核', '');
COMMIT;

-- ----------------------------
-- Table structure for t_stockin
-- ----------------------------
DROP TABLE IF EXISTS `t_stockin`;
CREATE TABLE `t_stockin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `stockinId` varchar(255) COLLATE utf8_bin NOT NULL,
  `productId` varchar(255) COLLATE utf8_bin NOT NULL,
  `amount` int(11) NOT NULL,
  `vendorId` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `status` varchar(20) COLLATE utf8_bin NOT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_stockin
-- ----------------------------
BEGIN;
INSERT INTO `t_stockin` VALUES (21, 0, 0, 'SI20180115183502', 'PG001', 1000, 'V002', '2018-01-15 18:35:11', '未处理', '');
INSERT INTO `t_stockin` VALUES (22, 0, 0, 'SI201801151853420', 'PG001', 900, 'V002', '2018-01-15 18:53:42', '未处理', '');
COMMIT;

-- ----------------------------
-- Table structure for t_vendor
-- ----------------------------
DROP TABLE IF EXISTS `t_vendor`;
CREATE TABLE `t_vendor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `vendorId` varchar(255) COLLATE utf8_bin NOT NULL,
  `vendorName` varchar(255) COLLATE utf8_bin NOT NULL,
  `vendorAddr` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `contactPerson` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `vendorTele` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `vendorEmail` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `note` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_vendor
-- ----------------------------
BEGIN;
INSERT INTO `t_vendor` VALUES (2, 2, 0, 'V002', '车轮厂', '天津', '李先生', '123453578', '13@126.com', '');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
