/*
Navicat MySQL Data Transfer

Source Server         : AMC
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : amcdb

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-01-15 17:26:01
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
INSERT INTO `authority` VALUES ('34', '1', '0', '库存子系统', '34', '0', '7', '/inventory/list', '/inventory/list', '', null);
INSERT INTO `authority` VALUES ('35', '1', '0', '备货单管理', '34,35', '2', '2', '/inventory/prepare', '/inventory/prepare', '', '34');
INSERT INTO `authority` VALUES ('36', '1', '0', '进货单管理', '34,36', '3', '3', '/inventory/stockin', '/inventory/stockin', '', '34');
INSERT INTO `authority` VALUES ('37', '1', '0', '编辑采购订单', '30,31,37', '0', '3', '/purchase/purchaseorderedit', '/purchase/purchaseorderedit', null, '31');
INSERT INTO `authority` VALUES ('38', '1', '0', '查看采购订单明细', '30,31,38', '0', '1', '/purchase/purchasedetail', '/purchase/purchasedetail', null, '31');
INSERT INTO `authority` VALUES ('39', '1', '0', '编辑采购订单明细', '30,31,39', '0', '2', '/purchase/purchasedetailedit', '/purchase/purchasedetailedit', '', '31');
INSERT INTO `authority` VALUES ('40', '1', '0', '新建订单明细', '30,31,40', '0', '3', '/purchase/purchasedetailadd', '/purchase/purchasedetailadd', null, '31');
INSERT INTO `authority` VALUES ('41', '1', '0', '采购订单审核通过', '30,31,41', '0', '4', '/purchase/orderconfirm', '/purchase/orderconfirm', null, '31');
INSERT INTO `authority` VALUES ('42', '1', '0', '发送采购订单', '30,31,42', '0', '5', '/purchase/ordersend', '/purchase/ordersend', null, '31');
INSERT INTO `authority` VALUES ('43', '1', '0', '新增角色', '3,7,43', '0', '3', '/role/add', '/role/add', '', '7');
INSERT INTO `authority` VALUES ('44', '1', '0', '修改角色', '3,7,44', '0', '4', '/role/edit', '/role/edit', '', '7');
INSERT INTO `authority` VALUES ('45', '1', '0', '采购到货确认', '30,31,45', '0', '6', '/purchase/productreceived', '/purchase/productreceived', null, '31');
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
INSERT INTO `authority` VALUES ('80', '1', '0', '催款单管理', '70,80', '0', '', '/financial/cuikuan', '/financial/cuikuan', '', '70');
INSERT INTO `authority` VALUES ('82', '1', '0', '销售业务账管理', '70,82', '0', '', '/financial/salesaccount', '/financial/salesaccount', '', '70');
INSERT INTO `authority` VALUES ('83', '1', '0', '采购业务账管理', '70,83', '0', '', '/financial/purchaseaccount', '/financial/purchaseaccount', '', '70');
INSERT INTO `authority` VALUES ('85', '1', '0', '产品管理', '34,85', '0', '', '/basedata/product', '/basedata/product', '', '34');
INSERT INTO `authority` VALUES ('86', '1', '0', '产品列表', '34,85,86', '0', '', '/basedata/product', '/basedata/product', '', '85');
INSERT INTO `authority` VALUES ('87', '1', '0', '添加产品', '34,85,87', '0', '', '/basedata/productadd', '/basedata/productadd', '', '85');
INSERT INTO `authority` VALUES ('88', '1', '0', '编辑产品', '34,85,88', '0', '', '/basedata/productedit', '/basedata/productedit', '', '85');
INSERT INTO `authority` VALUES ('89', '1', '0', '供应商管理', '30,89', '0', '', '/basedata/vendor', '/basedata/vendor', '', '30');
INSERT INTO `authority` VALUES ('90', '1', '0', '供应商列表', '30,89,90', '0', '', '/basedata/vendor', '/basedata/vendor', '', '89');
INSERT INTO `authority` VALUES ('91', '1', '0', '添加供应商', '30,89,91', '0', '', '/basedata/vendoradd', '/basedata/vendoradd', '', '89');
INSERT INTO `authority` VALUES ('92', '1', '0', '编辑供应商', '30,89,92', '0', '', '/basedata/vendoredit', '/basedata/vendoredit', '', '89');
INSERT INTO `authority` VALUES ('93', '1', '0', '库存基本信息', '34,93', '0', '', '/inventory/list', '/inventory/list', '', '34');
INSERT INTO `authority` VALUES ('94', '1', '0', '发货单详情', '34,73,94', '0', '', '/inventory/deliverdetail', '/inventory/deliverdetail', '', '73');
INSERT INTO `authority` VALUES ('95', '2', '0', '打印预览', '34,69,95', '0', '', '/inventory/printoutofstock', '/inventory/printoutofstock', '', '69');
INSERT INTO `authority` VALUES ('96', '1', '0', '进货单列表', '34,36,96', '0', '', '/inventory/stockin', '/inventory/stockin', '', '36');
INSERT INTO `authority` VALUES ('97', '1', '0', '添加库存信息', '34,93,97', '0', '', '/inventory/inventoryadd', '/inventory/inventoryadd', '', '93');
INSERT INTO `authority` VALUES ('98', '1', '0', '订单缺件表列表', '34,69,98', '0', '', '/inventory/outofstock', '/inventory/outofstock', '', '69');
INSERT INTO `authority` VALUES ('99', '1', '0', '订单缺件表明细', '34,69,99', '0', '', '/inventory/outofstockdetail', '/inventory/outofstockdetail', '', '69');
INSERT INTO `authority` VALUES ('100', '2', '0', '禁用角色', '3,7,100', '0', '', '/role/roledisable', '/role/roledisable', '', '7');
INSERT INTO `authority` VALUES ('101', '2', '0', '启用角色', '3,7,101', '0', '', '/role/roleenable', '/role/roleenable', '', '7');
INSERT INTO `authority` VALUES ('102', '1', '0', '删除角色', '3,7,102', '0', '', '/role/roledelete', '/role/roledelete', '', '7');
INSERT INTO `authority` VALUES ('103', '1', '0', '启用用户', '3,4,103', '0', '', '/account/accountenable', '/account/accountenable', '', '4');
INSERT INTO `authority` VALUES ('104', '1', '0', '禁用用户', '3,4,104', '0', '', '/account/accountdisable', '/account/accountdisable', '', '4');
INSERT INTO `authority` VALUES ('105', '1', '0', '删除用户', '3,4,105', '0', '', '/account/accountdelete', '/account/accountdelete', '', '4');
INSERT INTO `authority` VALUES ('106', '1', '0', '添加进货单', '34,36,106', '0', '', '/inventory/stockinadd', '/inventory/stockinadd', '', '36');
INSERT INTO `authority` VALUES ('107', '1', '0', '编辑进货单', '34,36,107', '0', '', '/inventory/stockinedit', '/inventory/stockinedit', '', '36');
INSERT INTO `authority` VALUES ('108', '1', '0', '确认入库', '34,36,108', '0', '', '/inventory/stockinconfirm', '/inventory/stockinconfirm', '', '36');
INSERT INTO `authority` VALUES ('109', '1', '0', '备货单列表', '34,35,109', '0', '', '/inventory/prepare', '/inventory/prepare', '', '35');
INSERT INTO `authority` VALUES ('110', '1', '0', '备货单明细', '34,35,110', '0', '', '/inventory/preparedetail', '/inventory/preparedetail', '', '35');
INSERT INTO `authority` VALUES ('111', '1', '0', '刷新库存状态', '34,93,111', '0', '', '/inventory/inventoryrefresh', '/inventory/inventoryrefresh', '', '93');
INSERT INTO `authority` VALUES ('112', '1', '0', '生成采购建议', '34,69,112', '0', '', '/inventory/advice', '/inventory/advice', '', '69');
INSERT INTO `authority` VALUES ('113', '4', '0', '销售预测', '24,113', '0', '', '/sales/listchanging', '/sales/listchanging', '', '24');
INSERT INTO `authority` VALUES ('114', '1', '0', '账目管理', '70,114', '0', '', '/financial/accounttablelist', '/financial/accounttablelist', '', '70');
INSERT INTO `authority` VALUES ('115', '1', '0', '发货操作', '34,35,115', '0', '', '/inventory/todeliver', '/inventory/todeliver', '', '35');

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
INSERT INTO `role_authority` VALUES ('1', '103');
INSERT INTO `role_authority` VALUES ('1', '104');
INSERT INTO `role_authority` VALUES ('1', '105');
INSERT INTO `role_authority` VALUES ('1', '7');
INSERT INTO `role_authority` VALUES ('1', '8');
INSERT INTO `role_authority` VALUES ('1', '9');
INSERT INTO `role_authority` VALUES ('1', '44');
INSERT INTO `role_authority` VALUES ('1', '100');
INSERT INTO `role_authority` VALUES ('1', '101');
INSERT INTO `role_authority` VALUES ('1', '102');
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
INSERT INTO `role_authority` VALUES ('1', '51');
INSERT INTO `role_authority` VALUES ('1', '53');
INSERT INTO `role_authority` VALUES ('1', '76');
INSERT INTO `role_authority` VALUES ('1', '77');
INSERT INTO `role_authority` VALUES ('1', '78');
INSERT INTO `role_authority` VALUES ('1', '79');
INSERT INTO `role_authority` VALUES ('1', '113');
INSERT INTO `role_authority` VALUES ('1', '30');
INSERT INTO `role_authority` VALUES ('1', '31');
INSERT INTO `role_authority` VALUES ('1', '32');
INSERT INTO `role_authority` VALUES ('1', '89');
INSERT INTO `role_authority` VALUES ('1', '90');
INSERT INTO `role_authority` VALUES ('1', '91');
INSERT INTO `role_authority` VALUES ('1', '92');
INSERT INTO `role_authority` VALUES ('1', '34');
INSERT INTO `role_authority` VALUES ('1', '35');
INSERT INTO `role_authority` VALUES ('1', '109');
INSERT INTO `role_authority` VALUES ('1', '110');
INSERT INTO `role_authority` VALUES ('1', '115');
INSERT INTO `role_authority` VALUES ('1', '36');
INSERT INTO `role_authority` VALUES ('1', '96');
INSERT INTO `role_authority` VALUES ('1', '106');
INSERT INTO `role_authority` VALUES ('1', '107');
INSERT INTO `role_authority` VALUES ('1', '108');
INSERT INTO `role_authority` VALUES ('1', '69');
INSERT INTO `role_authority` VALUES ('1', '95');
INSERT INTO `role_authority` VALUES ('1', '98');
INSERT INTO `role_authority` VALUES ('1', '99');
INSERT INTO `role_authority` VALUES ('1', '112');
INSERT INTO `role_authority` VALUES ('1', '73');
INSERT INTO `role_authority` VALUES ('1', '94');
INSERT INTO `role_authority` VALUES ('1', '85');
INSERT INTO `role_authority` VALUES ('1', '86');
INSERT INTO `role_authority` VALUES ('1', '87');
INSERT INTO `role_authority` VALUES ('1', '88');
INSERT INTO `role_authority` VALUES ('1', '93');
INSERT INTO `role_authority` VALUES ('1', '97');
INSERT INTO `role_authority` VALUES ('1', '111');
INSERT INTO `role_authority` VALUES ('1', '70');
INSERT INTO `role_authority` VALUES ('1', '71');
INSERT INTO `role_authority` VALUES ('1', '75');
INSERT INTO `role_authority` VALUES ('1', '80');
INSERT INTO `role_authority` VALUES ('1', '82');
INSERT INTO `role_authority` VALUES ('1', '83');
INSERT INTO `role_authority` VALUES ('1', '114');
INSERT INTO `role_authority` VALUES ('1', '37');
INSERT INTO `role_authority` VALUES ('1', '38');
INSERT INTO `role_authority` VALUES ('1', '39');
INSERT INTO `role_authority` VALUES ('1', '40');
INSERT INTO `role_authority` VALUES ('1', '41');
INSERT INTO `role_authority` VALUES ('1', '42');
INSERT INTO `role_authority` VALUES ('1', '43');
INSERT INTO `role_authority` VALUES ('1', '45');

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
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_purchasedetail
-- ----------------------------
INSERT INTO `t_purchasedetail` VALUES ('107', '3', '0', 'S20180109181451D181502', 'S20180109181451', 'G001', '大号轮胎', '2500', '120.00', '300000.00', '');
INSERT INTO `t_purchasedetail` VALUES ('108', '2', '0', 'S20180109181451D181521', 'S20180109181451', 'G002', '大螺丝', '1000', '20.00', '20000.00', '');
INSERT INTO `t_purchasedetail` VALUES ('109', '3', '0', 'S20180109234319D234325', 'S20180109234319', 'G001', '大号轮胎', '789', '7.90', '6233.10', '');
INSERT INTO `t_purchasedetail` VALUES ('110', '0', '0', 'S20180113172356D172421', 'S20180113172356', 'G001', '大号轮胎', '0', '0.00', '0.00', '');
INSERT INTO `t_purchasedetail` VALUES ('112', '3', '0', 'P20180114222540D231135', 'P20180114222540', 'G002', '大螺丝', '400', '3.00', '1200.00', '备注');
INSERT INTO `t_purchasedetail` VALUES ('113', '1', '0', 'P20171225215627D232909', 'P20171225215627', 'G001', '大号轮胎', '150', '3.00', '450.00', '');
INSERT INTO `t_purchasedetail` VALUES ('115', '1', '0', 'P20171225215627D011529', 'P20171225215627', 'G002', '大螺丝', '200', '5.00', '1000.00', '');
INSERT INTO `t_purchasedetail` VALUES ('116', '1', '0', 'P20171225215627D013513', 'P20171225215627', 'G002', '大螺丝', '0', '0.00', '0.00', '');
INSERT INTO `t_purchasedetail` VALUES ('117', '0', '0', 'P20180115103758D104221', 'P20180115103758', 'G002', '大螺丝', '0', '0.00', '0.00', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_purchaseorder
-- ----------------------------
INSERT INTO `t_purchaseorder` VALUES ('6', '7', '0', 'P20171225215627', '2018-01-14', 'V0001', '车轮厂', '0.00', '已收货', '备注');
INSERT INTO `t_purchaseorder` VALUES ('7', '4', '0', 'P20171225222158', '2018-01-14', 'V002', '车轮厂', '0.00', '已收货', '备注2');
INSERT INTO `t_purchaseorder` VALUES ('8', '1', '0', 'P20180114222540', '2018-01-15', 'V002', '车轮厂', '900.00', '已收货', '备注3');
INSERT INTO `t_purchaseorder` VALUES ('11', '1', '0', 'P20180115151038', '2018-01-15', 'V002', '车轮厂', '0.00', '已发送待收货', '');
