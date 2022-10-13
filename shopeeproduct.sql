/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : shopeeproduct

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 13/10/2022 20:03:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for delivers
-- ----------------------------
DROP TABLE IF EXISTS `delivers`;
CREATE TABLE `delivers`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `eventId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '爬取数据事件ID',
  `itemid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品ID',
  `shopid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所在商铺ID',
  `min_spend` decimal(10, 2) NULL DEFAULT NULL COMMENT '免运费金额',
  `min_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低运费',
  `max_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '最高运费',
  `median_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '记录支持的快递种类个数',
  `value_date` date NULL DEFAULT NULL COMMENT '插入日期yyyyMMdd',
  `insert_time` datetime NULL DEFAULT NULL COMMENT '插入时间yyyy-MM-dd HH:mm:ss.SSS',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of delivers
-- ----------------------------

-- ----------------------------
-- Table structure for detailmodels
-- ----------------------------
DROP TABLE IF EXISTS `detailmodels`;
CREATE TABLE `detailmodels`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `eventId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '爬取数据事件ID',
  `itemid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品ID',
  `shopid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所在商铺ID',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片网址',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '子选项名',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '子选项现价',
  `price_before_discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '子选项原价',
  `stock` int NOT NULL COMMENT '库存',
  `value_date` date NULL DEFAULT NULL COMMENT '插入日期yyyyMMdd',
  `insert_time` datetime NULL DEFAULT NULL COMMENT '插入时间yyyy-MM-dd HH:mm:ss.SSS',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of detailmodels
-- ----------------------------

-- ----------------------------
-- Table structure for detailshopvouchers
-- ----------------------------
DROP TABLE IF EXISTS `detailshopvouchers`;
CREATE TABLE `detailshopvouchers`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `eventId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '爬取数据事件ID',
  `shopid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '店铺ID',
  `min_spend` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠券生效金额',
  `discount_value` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠券抵扣金额',
  `value_date` date NULL DEFAULT NULL COMMENT '插入日期yyyyMMdd',
  `insert_time` datetime NULL DEFAULT NULL COMMENT '插入时间yyyy-MM-dd HH:mm:ss.SSS',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of detailshopvouchers
-- ----------------------------

-- ----------------------------
-- Table structure for productdetails
-- ----------------------------
DROP TABLE IF EXISTS `productdetails`;
CREATE TABLE `productdetails`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `eventId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '爬取数据事件ID',
  `itemid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品ID',
  `shopid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所在商铺ID',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品名',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '现价',
  `price_min` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低现价',
  `price_max` decimal(10, 2) NULL DEFAULT NULL COMMENT '最高现价',
  `price_before_discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '折前价格',
  `price_min_before_discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '折前最低价',
  `price_max_before_discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '折前最高价',
  `has_lowest_price_guarantee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否有价保',
  `discount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '折扣百分比',
  `min_purchase_limit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '单笔最低交易金额',
  `stock` int NULL DEFAULT NULL COMMENT '库存',
  `sold` int NULL DEFAULT NULL COMMENT '当月销量',
  `historical_sold` int NULL DEFAULT NULL COMMENT '历史销量',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品图片',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品详细页网址',
  `detailModelsId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '子选项集合数据ID',
  `shop_vouchersId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '优惠券集合数据ID',
  `value_date` date NULL DEFAULT NULL COMMENT '插入日期yyyyMMdd',
  `insert_time` datetime NULL DEFAULT NULL COMMENT '插入时间yyyy-MM-dd HH:mm:ss.SSS',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of productdetails
-- ----------------------------

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `eventId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '爬取数据事件ID',
  `itemid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品ID',
  `shopid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所在商铺ID',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品名',
  `stock` int NULL DEFAULT NULL COMMENT '库存',
  `like_count` int NULL DEFAULT NULL COMMENT '顾客like个数',
  `sold` int NULL DEFAULT NULL COMMENT '当月销量',
  `historical_sold` int NULL DEFAULT NULL COMMENT '历史销量',
  `currency` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '币种',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '现价',
  `price_min` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低现价',
  `price_max` decimal(10, 2) NULL DEFAULT NULL COMMENT '最高现价',
  `price_before_discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '折前价格',
  `price_min_before_discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '折前最低价',
  `price_max_before_discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '折前最高价',
  `show_discount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '折扣百分比',
  `tier_variations` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '子选项名字列表',
  `shopee_verified` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'shopee官方验证',
  `is_official_shop` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否官营店',
  `shop_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '店铺标记地区',
  `searchStr` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '搜索关键字',
  `value_date` date NULL DEFAULT NULL COMMENT '插入日期yyyyMMdd',
  `insert_time` datetime NULL DEFAULT NULL COMMENT '插入时间yyyy-MM-dd HH:mm:ss.SSS',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of products
-- ----------------------------

-- ----------------------------
-- Table structure for searchkey
-- ----------------------------
DROP TABLE IF EXISTS `searchkey`;
CREATE TABLE `searchkey`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `keyName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of searchkey
-- ----------------------------
INSERT INTO `searchkey` VALUES (1, '微型积木');
INSERT INTO `searchkey` VALUES (2, '街景积木');
INSERT INTO `searchkey` VALUES (3, '太空积木');
INSERT INTO `searchkey` VALUES (4, '赛车积木');

-- ----------------------------
-- Table structure for shopinfo
-- ----------------------------
DROP TABLE IF EXISTS `shopinfo`;
CREATE TABLE `shopinfo`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `eventId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '爬取数据事件ID',
  `shopid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '店铺ID',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '店铺名',
  `shop_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '店铺所在地区',
  `last_active_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '店主最后登录时间',
  `rating_star` decimal(10, 2) NULL DEFAULT NULL COMMENT '店铺评级',
  `item_count` int NULL DEFAULT NULL COMMENT '商品数量',
  `follower_count` int NULL DEFAULT NULL COMMENT '店铺关注数',
  `response_rate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '回应概率',
  `response_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '平均回应时间',
  `rating_bad` int NULL DEFAULT NULL COMMENT '差评比例',
  `rating_good` int NULL DEFAULT NULL COMMENT '好评比例',
  `rating_normal` int NULL DEFAULT NULL COMMENT '中评比例',
  `is_shopee_verified` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否shopee验证',
  `is_preferred_plus_seller` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否推荐店铺',
  `is_official_shop` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否官方直营店铺',
  `value_date` date NULL DEFAULT NULL COMMENT '插入日期yyyyMMdd',
  `insert_time` datetime NULL DEFAULT NULL COMMENT '插入时间yyyy-MM-dd HH:mm:ss.SSS',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shopinfo
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
