/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50087
Source Host           : localhost:3306
Source Database       : cardbox

Target Server Type    : MYSQL
Target Server Version : 50087
File Encoding         : 65001

Date: 2015-06-28 22:17:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `card`
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` int(11) NOT NULL auto_increment,
  `game_id` int(11) default NULL COMMENT '????ID',
  `game_name` varchar(50) default NULL COMMENT '????',
  `name` varchar(50) NOT NULL default '' COMMENT '????',
  `icon` varchar(255) default NULL COMMENT '?????',
  `description` text COMMENT '????',
  `flow` text COMMENT '????',
  `total` int(11) default NULL COMMENT '?????',
  `assign_total` int(11) default NULL COMMENT '??????',
  `type` varchar(20) default NULL COMMENT '?????',
  `score` int(11) default NULL COMMENT '??????',
  `recommend` bit(1) NOT NULL default b'0' COMMENT '????',
  `platform` varchar(20) default NULL COMMENT '?????android?ios',
  `closed` bit(1) NOT NULL default b'0' COMMENT '?????',
  `sort` int(11) default '0',
  `valid` bit(1) default NULL,
  `create_time` bigint(20) default NULL COMMENT '????',
  `expire_time` bigint(20) default NULL COMMENT '????',
  `open_time` bigint(20) default NULL COMMENT '????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('1', '1', '金刚葫芦娃', '葫芦娃-周末大派送', 'http://kk.7k7k.com', '金刚葫芦娃是一款flash经典小游戏', '首先下载葫芦娃flash，然后获取激活码，进入 游戏激活', '10', '0', 'SCORE', '20', '', 'ALL', '', '0', null, '1434458909073', '1434458999073', '1434458939073');
INSERT INTO `card` VALUES ('2', '2', '斗地主', '斗地主-金豆', 'http://kk.7k7k.com', '斗地主礼包', '首先下载斗地主flash，然后获取激活码，进入 游戏激活', '10', '0', 'SCORE', '20', '', 'android', '', '0', null, '1434458909073', '1434458999073', '1434458939073');
INSERT INTO `card` VALUES ('3', '3', '史上最贱小游戏13关', '史上最强武器', 'http://kk.7k7k.com', '13关是一款flash经典小游戏', '首先下载13关flash，然后获取激活码，进入 游戏激活', '8', '0', 'SCORE', '20', '', 'iOS', '', '0', null, '1434458909073', '1434458999073', '1434458939073');
INSERT INTO `card` VALUES ('4', '4', '老爹三明治店', '老爹三明治店-周末大派送', 'http://kk.7k7k.com', '老爹三明治店是一款flash经典小游戏', '首先下载老爹三明治店flash，然后获取激活码，进入 游戏激活', '10', '0', 'SCORE', '20', '', 'ALL', '', '0', null, '1434458909073', '1434458999073', '1434458939073');
INSERT INTO `card` VALUES ('5', '5', 'TH穿越火线1.45', '装备库', 'http://kk.7k7k.com', 'TH穿越火线1.45 装备库，相当的用用', '先下载flash，然后输入装备库的激活码', '5', '0', 'FREE', '0', '', 'ALL', '', '0', null, '1434458909073', '1434458999073', '1434458939073');
INSERT INTO `card` VALUES ('6', '13', 'aaa', 'asdf', 'sdf', 'sd', 'sdf', '3', '0', 'FREE', '32', '', null, '', '0', null, '1435147444208', '1434458999073', '1434458999073');
INSERT INTO `card` VALUES ('7', '13', 'aaa', 'sfd', 'asdf', 'asdf', 'sadf', '3', '0', 'FREE', '333', '', null, '', '0', null, '1435147640178', '1435248000000', '1433779200000');
INSERT INTO `card` VALUES ('8', '3', '???????13?', 'dd', 'http://localhost:8080/upload/20150628175642_493.jpg', 'dd', 'dd', '0', '0', 'FREE', '22', '', 'android', '', '42', '', '1435408158416', '1435593600000', '1435248000000');
INSERT INTO `card` VALUES ('9', '22', 'sdf', 'ds', 'df', 'sdf', 'sdf', '0', '0', 'FREE', '3', '', null, '', '3', '', '1435413239836', '1419696000000', '1419696000000');
INSERT INTO `card` VALUES ('10', '14', 'bb', 'sdf', 'sd', 'sdf', 'sd', '0', '0', 'FREE', '2', '', 'ALL', '', '0', '', '1435414008731', '1419696000000', '1419696000000');
INSERT INTO `card` VALUES ('11', '14', 'bb', 'sdf', 'http://kk.7k7k.com/icon.jpg', 'sdf', 'sdf', '0', '0', 'FREE', '33', '', 'ALL', '', '0', '', '1435414745987', '1419696000000', '1419696000000');
INSERT INTO `card` VALUES ('12', '20', 'dfa', 'aa', 'http://kk.7k7k.com/icon.jpg', 'a', 'a', '0', '0', 'FREE', '3', '', 'ALL', '', '0', '', '1435415730021', '2435852800000', '1335334400000');
INSERT INTO `card` VALUES ('13', '22', 'sdf', '333', 'http://kk.7k7k.com/icon.jpg', 'sdf', 'sdf', '0', '0', 'FREE', '33', '', 'ALL', '', '3', '', '1435424658979', '1435852800000', '1435593600000');
INSERT INTO `card` VALUES ('14', '22', 'sdf', 'afff', 'http://localhost:8080/upload/20150628181055_559.jpg', 'sdf', 'sdf', '0', '0', 'FREE', '22', '', 'iOS', '', '0', '', '1435486230076', '1435680000000', '1435420800000');

-- ----------------------------
-- Table structure for `code`
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` int(11) NOT NULL auto_increment,
  `card_id` int(11) default NULL COMMENT '??ID',
  `phone` varchar(11) default NULL COMMENT '?????',
  `code` varchar(20) default NULL COMMENT '???',
  `used` bit(1) NOT NULL default b'0' COMMENT '??????',
  `assigned` bit(1) NOT NULL default b'0' COMMENT '??????',
  `card_name` varchar(50) default NULL,
  `game_name` varchar(50) default NULL,
  `game_id` int(11) default NULL COMMENT '????ID',
  `create_time` bigint(20) default NULL COMMENT '????',
  `assign_time` bigint(20) default NULL COMMENT '????',
  `use_time` bigint(20) default NULL COMMENT '????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code
-- ----------------------------
INSERT INTO `code` VALUES ('1', '1', '13910661166', 'sdf', '', '', 'a', 'd', '1', '12222', null, null);

-- ----------------------------
-- Table structure for `databasechangelog`
-- ----------------------------
DROP TABLE IF EXISTS `databasechangelog`;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `COMMENTS` varchar(255) default NULL,
  `TAG` varchar(255) default NULL,
  `LIQUIBASE` varchar(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of databasechangelog
-- ----------------------------
INSERT INTO `databasechangelog` VALUES ('1434706714720-1', 'lianghongbin (generated)', 'src/main/resources/databaseChangelog.xml', '2015-06-19 21:49:46', '1', 'EXECUTED', '7:55cde64ab8e3dfd42efebf9b7aafc47c', 'createTable', '', null, '3.3.2');
INSERT INTO `databasechangelog` VALUES ('1434706714720-2', 'lianghongbin (generated)', 'src/main/resources/databaseChangelog.xml', '2015-06-19 21:49:46', '2', 'EXECUTED', '7:5896d794ec94d329d966145cdaeda584', 'createTable', '', null, '3.3.2');
INSERT INTO `databasechangelog` VALUES ('1434706714720-3', 'lianghongbin (generated)', 'src/main/resources/databaseChangelog.xml', '2015-06-19 21:49:46', '3', 'EXECUTED', '7:23b8392c08da5ab16f28906538c272c3', 'createTable', '', null, '3.3.2');
INSERT INTO `databasechangelog` VALUES ('1434706714720-4', 'lianghongbin (generated)', 'src/main/resources/databaseChangelog.xml', '2015-06-19 21:49:46', '4', 'EXECUTED', '7:cd9a7abffabb2c80d2a1c5011e252d2f', 'createTable', '', null, '3.3.2');
INSERT INTO `databasechangelog` VALUES ('1434706714720-5', 'lianghongbin (generated)', 'src/main/resources/databaseChangelog.xml', '2015-06-19 21:49:46', '5', 'EXECUTED', '7:a4284ba8ba4432a321d3973a86ec2066', 'createTable', '', null, '3.3.2');
INSERT INTO `databasechangelog` VALUES ('1434706714720-6', 'lianghongbin (generated)', 'src/main/resources/databaseChangelog.xml', '2015-06-19 21:49:46', '6', 'EXECUTED', '7:4fb1b175204af63abfe287471488d626', 'createTable', '', null, '3.3.2');
INSERT INTO `databasechangelog` VALUES ('1434706714720-7', 'lianghongbin (generated)', 'src/main/resources/databaseChangelog.xml', '2015-06-19 21:49:46', '7', 'EXECUTED', '7:ee0d59b5fdae480e02fe57271608c1de', 'createTable', '', null, '3.3.2');
INSERT INTO `databasechangelog` VALUES ('1434706714720-8', 'lianghongbin (generated)', 'src/main/resources/databaseChangelog.xml', '2015-06-19 21:49:46', '8', 'EXECUTED', '7:904b4793021839fa7606bd81aba82e2b', 'createTable', '', null, '3.3.2');
INSERT INTO `databasechangelog` VALUES ('1434706714720-9', 'lianghongbin (generated)', 'src/main/resources/databaseChangelog.xml', '2015-06-19 21:49:46', '9', 'EXECUTED', '7:92614e1b2c9ec86e6e74f2aa4724fbb3', 'createTable', '', null, '3.3.2');
INSERT INTO `databasechangelog` VALUES ('1434706714720-10', 'lianghongbin (generated)', 'src/main/resources/databaseChangelog.xml', '2015-06-19 21:49:46', '10', 'EXECUTED', '7:8814d43380e975e93a573974a3a9164e', 'createTable', '', null, '3.3.2');
INSERT INTO `databasechangelog` VALUES ('1434706714720-11', 'lianghongbin (generated)', 'src/main/resources/databaseChangelog.xml', '2015-06-19 21:49:47', '11', 'EXECUTED', '7:7be392bed8d696e3d2563fef7848b8d5', 'createTable', '', null, '3.3.2');

-- ----------------------------
-- Table structure for `databasechangeloglock`
-- ----------------------------
DROP TABLE IF EXISTS `databasechangeloglock`;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime default NULL,
  `LOCKEDBY` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of databasechangeloglock
-- ----------------------------
INSERT INTO `databasechangeloglock` VALUES ('1', '', null, null);

-- ----------------------------
-- Table structure for `feedback`
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL COMMENT '???ID',
  `content` text COMMENT '????',
  `processed` bit(1) default b'0' COMMENT '?????',
  `remark` varchar(255) default NULL COMMENT '????',
  `create_time` bigint(20) default NULL COMMENT '????',
  `process_time` bigint(20) default NULL COMMENT '????',
  `phone` varchar(11) default NULL COMMENT '????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------

-- ----------------------------
-- Table structure for `flow`
-- ----------------------------
DROP TABLE IF EXISTS `flow`;
CREATE TABLE `flow` (
  `id` int(11) NOT NULL auto_increment,
  `score` int(11) default NULL COMMENT '????',
  `type` int(11) default NULL COMMENT '????: 0???1??',
  `user_id` int(11) default NULL COMMENT '??ID',
  `phone` varchar(11) default NULL COMMENT '????',
  `method` varchar(20) default NULL COMMENT '????',
  `create_time` bigint(20) default NULL COMMENT '????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flow
-- ----------------------------

-- ----------------------------
-- Table structure for `focus`
-- ----------------------------
DROP TABLE IF EXISTS `focus`;
CREATE TABLE `focus` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(20) default NULL COMMENT '?????????/??',
  `photo` varchar(255) default NULL COMMENT '???',
  `url` varchar(100) default NULL,
  `sort` int(11) default NULL COMMENT '???????????',
  `item_id` int(11) default NULL COMMENT '??????ID',
  `enabled` bit(1) default NULL COMMENT '????',
  `create_time` bigint(20) default NULL COMMENT '????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of focus
-- ----------------------------
INSERT INTO `focus` VALUES ('1', 'GAME', 'http://kk.7k7k.com/a.jpg', 'a', '1', '1', '', '1434458939073');
INSERT INTO `focus` VALUES ('2', 'GAME', 'http://kk.7k7k.com/b.jpg', 'bb', '53', '2', '', '1434458939073');
INSERT INTO `focus` VALUES ('3', 'GAME', 'http://kk.7k7k.com/c.jpg', 'c', '3', '3', '', '1434458939073');
INSERT INTO `focus` VALUES ('4', 'CARD', 'http://kk.7k7k.com/d.jpg', 'd', '2', '4', '', '1434458939073');
INSERT INTO `focus` VALUES ('5', null, 'http://kk.7k7k.com/upload/20150628152844_111.jpg', 'e', '0', null, '', '1435476544076');

-- ----------------------------
-- Table structure for `game`
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL default '' COMMENT '?????',
  `icon` varchar(255) default NULL COMMENT '??????',
  `description` text COMMENT '?????',
  `url` varchar(255) default NULL COMMENT '???????',
  `closed` bit(1) default b'0',
  `ios_id` varchar(20) default NULL COMMENT '????ID',
  `recommend` bit(1) NOT NULL default b'0' COMMENT '????',
  `identifier` varchar(100) default NULL COMMENT '??????',
  `total` int(11) default NULL COMMENT '?????????',
  `sort` int(11) default NULL COMMENT '??',
  `platform` varchar(10) default NULL COMMENT '????',
  `score` float(11,1) default NULL COMMENT '?????1-5?',
  `create_time` bigint(20) default NULL COMMENT '????',
  `modify_time` bigint(20) default NULL COMMENT '??????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES ('1', '金刚葫芦娃', 'http://kk.7k7k.com/a.jpg', '金刚葫芦娃', 'http://kk.7k7k.com/download/ddz.zip', '', '32d', '', 'com.7k7k.jghlw', '12', '12', 'iOS', '4.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('2', '斗地主', 'http://kk.7k7k.com/a.jpg', '斗地主真的很好玩', 'http://kk.7k7k.com/download/ddz.zip', '', '234455', '', 'com.7k7k.ddz', '15', '11', 'android', '3.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('3', '史上最贱小游戏13关', 'http://kk.7k7k.com/upload/20150627181038_708.jpg', '史上最贱小游戏13关', 'http://kk.7k7k.com/download/ddz.zip', '', 'fse', '', 'com.7k7k.zzqxyx', '0', '3', 'ALL', '2.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('4', '老爹三明治店', 'http://kk.7k7k.com/a.jpg', '老爹三明治店的很好玩', 'http://kk.7k7k.com/download/ddz.zip', '', 'd2e', '', 'com.7k7k.ldsmz', '25', '4', 'android', '5.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('5', 'TH穿越火线1.45', 'http://kk.7k7k.com/a.jpg', 'TH穿越火线1.453333aadfasdf', 'http://kk.7k7k.com/download/ddz.zip', '', '2344dda55', '', 'com.7k7k.cyhj', '135', '5', 'android', '4.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('6', '冒险王之神兵传奇终极无敌速升版', 'http://kk.7k7k.com/a.jpg', '冒险王之神兵传奇终极无敌速升版', 'http://kk.7k7k.com/download/ddz.zip', '', '2dfadsf', '', 'com.7k7k.mxw', '33', '6', 'iOS', '3.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('7', '逗小猴开心忍者篇2', 'http://kk.7k7k.com/a.jpg', '逗小猴开心忍者篇2', 'http://kk.7k7k.com/download/ddz.zip', '', 'httes', '', 'com.7k7k.dxhkx', '15', '7', 'android', '1.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('8', '护卫kk队队长', 'http://kk.7k7k.com/a.jpg', '护卫队队长护卫队队长护卫队队长', 'http://kk.7k7k.com/download/ddz.zip', '', 'dfa', '', 'com.7k7k.hwdz', '150', '8', 'iOS', '1.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('9', '??', 'http://kk.7k7k.com/a.jpg', '?????2????????2???', 'http://kk.7k7k.com/download/ddz.zip', '', 'fw244a', '', 'com.7k7k.zczjs', '125', '9', 'android', '433.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('10', '????3', 'http://kk.7k7k.com/a.jpg', '我的世界3我的世界3', 'http://kk.7k7k.com/download/ddz.zip', '', 'hsasdfw', '', 'com.7k7k.wdsj', '15', '10', 'android', '5.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('11', 'aaa', 'http://kk.7k7k.com/upload/20150625235044_153.jpg', 'fff', 'http://kk.7k7k.com/download22/ddz.zip', '', '235aadf33', '', 'com.7k7k.ddt3', '153', '11', 'android', '53.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('12', '超级熊大冒险', 'http://kk.7k7k.com/a.jpg', '超级熊大冒险真的很好玩', 'http://kk.7k7k.com/download/ddz.zip', '', 'ffw3156', '', 'com.7k7k.ccxdmx', '20', '1', 'ALL', '5.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('13', 'aaa', 'http://localhost:8080/upload/20150628184545_201.jpg', 'aaabbb', 'aaa', '', '33', '', 'aa', '0', '2', 'android', '3.0', '1434458939073', '1435488542688');
INSERT INTO `game` VALUES ('14', 'bb', 'http://kk.7k7k.com/upload/20150626003432_995.jpg', 'bb', 'bb', '', null, '', 'bb', '0', '4', 'android', '44.0', '1434458939073', '1434458939073');
INSERT INTO `game` VALUES ('15', 'as', '', 'adsf', 'aa', '', null, '', 'asdf', '0', '0', 'ALL', '2.0', '1435403044264', null);
INSERT INTO `game` VALUES ('16', 'as', '', 'adsf', 'aa', '', null, '', 'asdf', '0', '0', 'ALL', '2.0', '1435403098302', null);
INSERT INTO `game` VALUES ('17', 'as', '', 'adsf', 'aa', '', 'ss', '', 'asdf', '0', '0', 'ALL', '2.0', '1435403118475', null);
INSERT INTO `game` VALUES ('18', 'sdf', '', 'sdf', 'sdf', '', '', '', '', '0', '0', 'ALL', '0.0', '1435403902172', null);
INSERT INTO `game` VALUES ('19', 'df', '', 'dfa', 'df', '', '', '', '', '0', '0', 'ALL', '0.0', '1435405223976', null);
INSERT INTO `game` VALUES ('20', 'dfa', '', 'df', 'dfa', '', '', '', '', '0', '0', 'ALL', '2.0', '1435405287669', null);
INSERT INTO `game` VALUES ('21', 'df', '', 'sdf', 'sdf', '', '', '', '', '0', '0', 'ALL', '3.0', '1435405332800', null);
INSERT INTO `game` VALUES ('22', 'sdf', '', 'sdf', 'sdf', '', '', '', '', '0', '0', 'ALL', '4.5', '1435405476358', null);
INSERT INTO `game` VALUES ('23', 'vbvb', 'http://localhost:8080/upload/20150628184631_296.jpg', 'sdf', 'gg', '', '', '', '', '0', '0', 'ALL', '0.5', '1435488509084', null);

-- ----------------------------
-- Table structure for `photo`
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(10) default NULL COMMENT '????????????',
  `url` varchar(255) default NULL COMMENT '????',
  `item_id` int(11) default NULL COMMENT '?????ID',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo
-- ----------------------------
INSERT INTO `photo` VALUES ('1', 'GAME', 'http://kk.7k7k.com/upload/20150626003540_376.jpg', '14');
INSERT INTO `photo` VALUES ('2', 'GAME', 'http://kk.7k7k.com/upload/20150626003541_249.jpg', '14');
INSERT INTO `photo` VALUES ('3', 'GAME', 'http://localhost:8080/upload/20150628193345_267.jpg', '23');
INSERT INTO `photo` VALUES ('4', 'GAME', 'http://localhost:8080/upload/20150628193348_995.jpg', '23');
INSERT INTO `photo` VALUES ('5', 'GAME', 'http://localhost:8080/upload/20150628194230_938.jpg', '23');
INSERT INTO `photo` VALUES ('6', 'GAME', 'http://localhost:8080/upload/20150628194230_330.jpg', '23');
INSERT INTO `photo` VALUES ('7', 'GAME', 'http://localhost:8080/upload/20150628194706_237.jpg', '23');
INSERT INTO `photo` VALUES ('9', 'GAME', 'http://localhost:8080/upload/20150628201327_682.jpg', '23');
INSERT INTO `photo` VALUES ('10', 'GAME', 'http://localhost:8080/upload/20150628201506_92.jpg', '23');

-- ----------------------------
-- Table structure for `setting`
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` int(11) NOT NULL auto_increment,
  `us` tinytext COMMENT '????',
  `weixin` int(11) default NULL COMMENT '????????',
  `qq` int(11) default NULL COMMENT '????QQ??',
  `daily` int(11) default NULL COMMENT '??????',
  `download` int(11) default NULL,
  `registry` int(11) default NULL COMMENT '????',
  `v` varchar(10) default NULL COMMENT '????',
  `create_time` bigint(20) default NULL,
  `announce` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES ('1', 'aaa', '10', '10', '100', '100', '300', '1_0', '22222222222222', 'sadf');
INSERT INTO `setting` VALUES ('2', 'a', '4', '5', '10', '6', '3', 'v_2', '1435152629886', 'b');

-- ----------------------------
-- Table structure for `share`
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL COMMENT '????ID',
  `card_id` int(11) default NULL COMMENT '????ID',
  `game_id` int(11) default NULL COMMENT '?????ID',
  `score` int(11) default NULL COMMENT '??????',
  `create_time` bigint(20) default NULL COMMENT '????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share
-- ----------------------------

-- ----------------------------
-- Table structure for `splash`
-- ----------------------------
DROP TABLE IF EXISTS `splash`;
CREATE TABLE `splash` (
  `id` int(11) NOT NULL auto_increment,
  `photo` varchar(255) default NULL COMMENT '????',
  `url` varchar(255) default NULL COMMENT '????????',
  `enabled` bit(1) default NULL COMMENT '????',
  `sort` int(11) default NULL COMMENT '????????',
  `create_time` bigint(20) default NULL COMMENT '????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of splash
-- ----------------------------
INSERT INTO `splash` VALUES ('1', 'http://kk.7k7k.com/first.jpg', 'http://kk.7k7k.com', '', '1', '1434458939073');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(30) NOT NULL default '' COMMENT '????',
  `password` varchar(64) default '' COMMENT '??',
  `score` int(11) default NULL COMMENT '??',
  `device` varchar(64) default NULL COMMENT '?????',
  `type` varchar(20) default NULL COMMENT '????',
  `phone` varchar(11) default NULL COMMENT '????',
  `head` varchar(255) default NULL COMMENT '????',
  `create_time` bigint(20) default NULL COMMENT '????/????',
  `last_time` bigint(20) default NULL COMMENT '????????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '13910661166', null, '200', 'adddbbd', 'iOS', '13910661166', 'http://kk.7k7k.com/lhb/head.jpg', '1434458939073', '1434458939074');
