# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.24)
# Database: cardbox
# Generation Time: 2015-06-25 10:39:03 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table card
# ------------------------------------------------------------

DROP TABLE IF EXISTS `card`;

CREATE TABLE `card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) DEFAULT NULL COMMENT '所属游戏ID',
  `game_name` varchar(50) DEFAULT NULL COMMENT '游戏名称',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '礼包名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '礼包代表图',
  `description` text COMMENT '礼包描述',
  `flow` text COMMENT '领取流程',
  `total` int(11) DEFAULT NULL COMMENT '总礼包数量',
  `assign_total` int(11) NOT NULL DEFAULT '0' COMMENT '分发出去数量',
  `type` varchar(20) DEFAULT NULL COMMENT '礼品卡类别',
  `score` int(11) DEFAULT NULL COMMENT '兑换所需分数',
  `recommend` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否推荐',
  `platform` varchar(20) DEFAULT NULL COMMENT '平台类型：android、ios',
  `closed` bit(1) NOT NULL DEFAULT b'0' COMMENT '卡是否被锁',
  `valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效：当游戏被下线时，置为无效',
  `create_time` bigint(20) DEFAULT NULL COMMENT '添加时间',
  `close_time` bigint(20) DEFAULT NULL COMMENT '锁定时间',
  `open_time` bigint(20) DEFAULT NULL COMMENT '打开时间',
  `expire_time` bigint(20) DEFAULT NULL COMMENT '到期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;

INSERT INTO `card` (`id`, `game_id`, `game_name`, `name`, `icon`, `description`, `flow`, `total`, `assign_total`, `type`, `score`, `recommend`, `platform`, `closed`, `valid`, `create_time`, `close_time`, `open_time`, `expire_time`)
VALUES
	(1,1,'金刚葫芦娃','葫芦娃-周末大派送','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','首先下载葫芦娃flash，然后获取激活码，进入 游戏激活',10,8,'SCORE',20,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(2,2,'斗地主','斗地主-金豆','http://kk.7k7k.com','斗地主礼包','首先下载斗地主flash，然后获取激活码，进入 游戏激活',10,3,'SCORE',20,00000001,'android',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(3,3,'史上最贱小游戏13关','史上最强武器','http://kk.7k7k.com','13关是一款flash经典小游戏','首先下载13关flash，然后获取激活码，进入 游戏激活',8,0,'SCORE',20,00000001,'iOS',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(4,4,'老爹三明治店','老爹三明治店-周末大派送','http://kk.7k7k.com','老爹三明治店是一款flash经典小游戏','首先下载老爹三明治店flash，然后获取激活码，进入 游戏激活',10,0,'SCORE',20,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(5,5,'TH穿越火线1.45','装备库','http://kk.7k7k.com','TH穿越火线1.45 装备库，相当的用用','先下载flash，然后输入装备库的激活码',5,0,'FREE',0,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(6,1,'金刚葫芦娃','金刚葫芦娃-元旦大礼包','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',10,3,'FREE',24,00000000,'iOS',00000001,1,1434458909073,1434458909073,1434458939073,1444458999073),
	(7,12,'222','ad','http://kk.7k7k.com','3','3',33,0,'FREE',3,00000001,'ALL',00000000,1,1435024316232,NULL,1444458999073,1444458999073),
	(8,12,'超级熊大冒险','a','http://kk.7k7k.com','3','4',22,0,'FREE',33,00000001,'ALL',00000000,1,1435025107162,NULL,1434988800000,1435248000000),
	(9,1,'金刚葫芦娃','金刚葫芦娃-元旦大礼包','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',10,0,'FREE',24,00000001,'ALL',00000001,1,1435027030106,NULL,1434384000000,1444406400000),
	(10,1,'金刚葫芦娃','金刚葫芦娃-2','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',23,0,'FREE',2,00000000,'ALL',00000000,1,1435027030106,NULL,1434384000000,1444406400000),
	(11,1,'金刚葫芦娃','葫芦娃-周末大派送','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','首先下载葫芦娃flash，然后获取激活码，进入 游戏激活',10,8,'SCORE',20,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(12,2,'斗地主','斗地主-金豆','http://kk.7k7k.com','斗地主礼包','首先下载斗地主flash，然后获取激活码，进入 游戏激活',10,3,'SCORE',20,00000001,'android',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(13,3,'史上最贱小游戏13关','史上最强武器','http://kk.7k7k.com','13关是一款flash经典小游戏','首先下载13关flash，然后获取激活码，进入 游戏激活',8,0,'SCORE',20,00000001,'iOS',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(14,4,'老爹三明治店','老爹三明治店-周末大派送','http://kk.7k7k.com','老爹三明治店是一款flash经典小游戏','首先下载老爹三明治店flash，然后获取激活码，进入 游戏激活',10,0,'SCORE',20,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(15,5,'TH穿越火线1.45','装备库','http://kk.7k7k.com','TH穿越火线1.45 装备库，相当的用用','先下载flash，然后输入装备库的激活码',5,0,'FREE',0,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(16,1,'金刚葫芦娃','金刚葫芦娃-元旦大礼包','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',10,3,'FREE',24,00000000,'iOS',00000001,1,1434458909073,1434458909073,1434458939073,1444458999073),
	(17,12,'222','ad','http://kk.7k7k.com','3','3',33,0,'FREE',3,00000001,'ALL',00000000,1,1435024316232,NULL,1444458999073,1444458999073),
	(18,12,'超级熊大冒险','a','http://kk.7k7k.com','3','4',22,0,'FREE',33,00000001,'ALL',00000000,1,1435025107162,NULL,1434988800000,1435248000000),
	(19,1,'金刚葫芦娃','金刚葫芦娃-元旦大礼包','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',10,0,'FREE',24,00000001,'ALL',00000001,1,1435027030106,NULL,1434384000000,1444406400000),
	(20,1,'金刚葫芦娃','金刚葫芦娃-2','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',23,0,'FREE',2,00000000,'ALL',00000000,1,1435027030106,NULL,1434384000000,1444406400000),
	(21,1,'金刚葫芦娃','葫芦娃-周末大派送','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','首先下载葫芦娃flash，然后获取激活码，进入 游戏激活',10,8,'SCORE',20,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(22,2,'斗地主','斗地主-金豆','http://kk.7k7k.com','斗地主礼包','首先下载斗地主flash，然后获取激活码，进入 游戏激活',10,3,'SCORE',20,00000001,'android',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(23,3,'史上最贱小游戏13关','史上最强武器','http://kk.7k7k.com','13关是一款flash经典小游戏','首先下载13关flash，然后获取激活码，进入 游戏激活',8,0,'SCORE',20,00000001,'iOS',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(24,4,'老爹三明治店','老爹三明治店-周末大派送','http://kk.7k7k.com','老爹三明治店是一款flash经典小游戏','首先下载老爹三明治店flash，然后获取激活码，进入 游戏激活',10,0,'SCORE',20,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(25,5,'TH穿越火线1.45','装备库','http://kk.7k7k.com','TH穿越火线1.45 装备库，相当的用用','先下载flash，然后输入装备库的激活码',5,0,'FREE',0,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(26,1,'金刚葫芦娃','金刚葫芦娃-元旦大礼包','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',10,3,'FREE',24,00000000,'iOS',00000001,1,1434458909073,1434458909073,1434458939073,1444458999073),
	(27,12,'222','ad','http://kk.7k7k.com','3','3',33,0,'FREE',3,00000001,'ALL',00000000,1,1435024316232,NULL,1444458999073,1444458999073),
	(28,12,'超级熊大冒险','a','http://kk.7k7k.com','3','4',22,0,'FREE',33,00000001,'ALL',00000000,1,1435025107162,NULL,1434988800000,1435248000000),
	(29,1,'金刚葫芦娃','金刚葫芦娃-元旦大礼包','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',10,0,'FREE',24,00000001,'ALL',00000001,1,1435027030106,NULL,1434384000000,1444406400000),
	(30,1,'金刚葫芦娃','金刚葫芦娃-2','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',23,0,'FREE',2,00000000,'ALL',00000000,1,1435027030106,NULL,1434384000000,1444406400000),
	(31,1,'金刚葫芦娃','葫芦娃-周末大派送','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','首先下载葫芦娃flash，然后获取激活码，进入 游戏激活',10,8,'SCORE',20,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(32,2,'斗地主','斗地主-金豆','http://kk.7k7k.com','斗地主礼包','首先下载斗地主flash，然后获取激活码，进入 游戏激活',10,3,'SCORE',20,00000001,'android',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(33,3,'史上最贱小游戏13关','史上最强武器','http://kk.7k7k.com','13关是一款flash经典小游戏','首先下载13关flash，然后获取激活码，进入 游戏激活',8,0,'SCORE',20,00000001,'iOS',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(34,4,'老爹三明治店','老爹三明治店-周末大派送','http://kk.7k7k.com','老爹三明治店是一款flash经典小游戏','首先下载老爹三明治店flash，然后获取激活码，进入 游戏激活',10,0,'SCORE',20,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(35,5,'TH穿越火线1.45','装备库','http://kk.7k7k.com','TH穿越火线1.45 装备库，相当的用用','先下载flash，然后输入装备库的激活码',5,0,'FREE',0,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(36,1,'金刚葫芦娃','金刚葫芦娃-元旦大礼包','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',10,3,'FREE',24,00000000,'iOS',00000001,1,1434458909073,1434458909073,1434458939073,1444458999073),
	(37,12,'222','ad','http://kk.7k7k.com','3','3',33,0,'FREE',3,00000001,'ALL',00000000,1,1435024316232,NULL,1444458999073,1444458999073),
	(38,12,'超级熊大冒险','a','http://kk.7k7k.com','3','4',22,0,'FREE',33,00000001,'ALL',00000000,1,1435025107162,NULL,1434988800000,1435248000000),
	(39,1,'金刚葫芦娃','金刚葫芦娃-元旦大礼包','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',10,0,'FREE',24,00000001,'ALL',00000001,1,1435027030106,NULL,1434384000000,1444406400000),
	(40,1,'金刚葫芦娃','金刚葫芦娃-2','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',23,0,'FREE',2,00000000,'ALL',00000000,1,1435027030106,NULL,1434384000000,1444406400000),
	(41,1,'金刚葫芦娃','葫芦娃-周末大派送','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','首先下载葫芦娃flash，然后获取激活码，进入 游戏激活',10,8,'SCORE',20,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(42,2,'斗地主','斗地主-金豆','http://kk.7k7k.com','斗地主礼包','首先下载斗地主flash，然后获取激活码，进入 游戏激活',10,3,'SCORE',20,00000001,'android',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(43,3,'史上最贱小游戏13关','史上最强武器','http://kk.7k7k.com','13关是一款flash经典小游戏','首先下载13关flash，然后获取激活码，进入 游戏激活',8,0,'SCORE',20,00000001,'iOS',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(44,4,'老爹三明治店','老爹三明治店-周末大派送','http://kk.7k7k.com','老爹三明治店是一款flash经典小游戏','首先下载老爹三明治店flash，然后获取激活码，进入 游戏激活',20,0,'SCORE',20,00000001,'ALL',00000000,1,1434458909073,1434458939073,1434458939073,1444458999073),
	(45,5,'TH穿越火线1.45','装备库','http://kk.7k7k.com','TH穿越火线1.45 装备库，相当的用用','先下载flash，然后输入装备库的激活码',5,0,'FREE',0,00000001,'ALL',00000000,1,1434458909073,1434458939073,1419696360000,1419696600000),
	(46,1,'金刚葫芦娃','金刚葫芦娃-元旦大礼包','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',10,3,'FREE',24,00000000,'iOS',00000001,1,1434458909073,1434458909073,1434458939073,1444458999073),
	(47,12,'222','ad','http://kk.7k7k.com','3','3',33,0,'FREE',3,00000001,'ALL',00000000,1,1435024316232,NULL,1444458999073,1444458999073),
	(48,12,'超级熊大冒险','a','http://kk.7k7k.com','3','4',22,0,'FREE',33,00000001,'ALL',00000001,1,1435025107162,NULL,1434988800000,1435248000000),
	(49,1,'金刚葫芦娃','金刚葫芦娃-元旦大礼包','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',10,0,'FREE',24,00000001,'ALL',00000001,1,1435027030106,NULL,1434384000000,1444406400000),
	(50,1,'金刚葫芦娃','金刚葫芦娃-2','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','先下载flash，然后输入装备库的激活码',23,0,'FREE',2,00000000,'ALL',00000000,1,1435027030106,NULL,1434384000000,1444406400000);

/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table code
# ------------------------------------------------------------

DROP TABLE IF EXISTS `code`;

CREATE TABLE `code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_id` int(11) DEFAULT NULL COMMENT '卡包ID',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户手机号',
  `code` varchar(20) DEFAULT NULL COMMENT '激活码',
  `used` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已经使用',
  `assigned` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已分发过',
  `game_id` int(11) DEFAULT NULL COMMENT '所属游戏ID',
  `card_name` varchar(50) DEFAULT NULL COMMENT '礼包名称',
  `game_name` varchar(50) DEFAULT NULL COMMENT '游戏名称',
  `create_time` bigint(20) DEFAULT NULL COMMENT '添加时间',
  `assign_time` bigint(20) DEFAULT NULL COMMENT '分发时间',
  `use_time` bigint(20) DEFAULT NULL COMMENT '使用时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `code` WRITE;
/*!40000 ALTER TABLE `code` DISABLE KEYS */;

INSERT INTO `code` (`id`, `card_id`, `phone`, `code`, `used`, `assigned`, `game_id`, `card_name`, `game_name`, `create_time`, `assign_time`, `use_time`)
VALUES
	(1,1,'13910661166','aabbcc',00000000,00000001,1,'33',NULL,NULL,1434592848081,NULL),
	(2,1,'18500346345','bbccd',00000000,00000001,1,NULL,NULL,NULL,1434606070174,NULL),
	(3,1,NULL,'abcde',00000000,00000000,1,NULL,'3',NULL,NULL,NULL),
	(4,2,NULL,'aabb',00000000,00000001,2,NULL,NULL,NULL,NULL,NULL),
	(5,1,NULL,'ddea',00000000,00000000,1,NULL,NULL,NULL,NULL,NULL),
	(6,1,NULL,'fdsadf',00000000,00000000,1,NULL,NULL,NULL,NULL,NULL),
	(7,1,NULL,'2dadf',00000000,00000000,1,NULL,NULL,NULL,NULL,NULL),
	(8,1,NULL,'fadf',00000000,00000000,1,NULL,NULL,NULL,NULL,NULL),
	(9,1,NULL,'fdee',00000000,00000000,1,NULL,NULL,NULL,NULL,NULL),
	(10,1,NULL,'aaff',00000000,00000000,1,NULL,NULL,NULL,NULL,NULL),
	(11,1,NULL,'dfadf',00000000,00000000,1,NULL,NULL,NULL,NULL,NULL),
	(12,2,'13910661166','23adfa',00000000,00000001,2,NULL,NULL,NULL,1434524184538,NULL),
	(13,2,'18500346345','42adf',00000000,00000001,2,NULL,NULL,NULL,1434524652877,NULL),
	(14,2,NULL,'abce',00000000,00000000,2,NULL,NULL,NULL,NULL,NULL),
	(15,2,NULL,'afew',00000000,00000000,2,NULL,NULL,NULL,NULL,NULL),
	(16,2,NULL,'adfew',00000000,00000000,2,NULL,NULL,NULL,NULL,NULL),
	(17,2,NULL,'tqee',00000000,00000000,2,NULL,NULL,NULL,NULL,NULL),
	(18,2,NULL,'fdzc',00000000,00000000,2,NULL,NULL,NULL,NULL,NULL),
	(19,44,NULL,'d',00000000,00000000,4,'老爹三明治店-周末大派送','老爹三明治店',1435137254520,NULL,NULL),
	(20,44,NULL,'a',00000000,00000000,4,'老爹三明治店-周末大派送','老爹三明治店',1435137254547,NULL,NULL),
	(21,44,NULL,'ab',00000000,00000000,4,'老爹三明治店-周末大派送','老爹三明治店',1435138247434,NULL,NULL),
	(22,44,NULL,'b',00000000,00000000,4,'老爹三明治店-周末大派送','老爹三明治店',1435138247446,NULL,NULL),
	(23,44,NULL,'c',00000000,00000000,4,'老爹三明治店-周末大派送','老爹三明治店',1435138247450,NULL,NULL),
	(24,44,NULL,'sdfasdf',00000000,00000000,4,'老爹三明治店-周末大派送','老爹三明治店',1435138512652,NULL,NULL),
	(25,44,NULL,'32211',00000000,00000000,4,'老爹三明治店-周末大派送','老爹三明治店',1435138512672,NULL,NULL),
	(26,44,NULL,'3d3d3d',00000000,00000000,4,'老爹三明治店-周末大派送','老爹三明治店',1435138512678,NULL,NULL),
	(27,44,NULL,'asdf',00000000,00000000,4,'老爹三明治店-周末大派送','老爹三明治店',1435138590710,NULL,NULL),
	(28,44,NULL,'adsf',00000000,00000000,4,'老爹三明治店-周末大派送','老爹三明治店',1435138590728,NULL,NULL),
	(29,44,NULL,'fdaasdf',00000000,00000000,4,'老爹三明治店-周末大派送','老爹三明治店',1435138590739,NULL,NULL),
	(30,44,NULL,'asdf',00000000,00000000,4,'老爹三明治店-周末大派送','老爹三明治店',1435138595532,NULL,NULL);

/*!40000 ALTER TABLE `code` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table DATABASECHANGELOG
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DATABASECHANGELOG`;

CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;

INSERT INTO `DATABASECHANGELOG` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`)
VALUES
	('1435018750749-1','lianghongbin (generated)','src/main/resources/databaseChangelog.xml','2015-06-23 08:22:15',1,'EXECUTED','7:55cde64ab8e3dfd42efebf9b7aafc47c','createTable','',NULL,'3.3.2'),
	('1435018750749-2','lianghongbin (generated)','src/main/resources/databaseChangelog.xml','2015-06-23 08:22:15',2,'EXECUTED','7:5896d794ec94d329d966145cdaeda584','createTable','',NULL,'3.3.2'),
	('1435018750749-3','lianghongbin (generated)','src/main/resources/databaseChangelog.xml','2015-06-23 08:22:15',3,'EXECUTED','7:23b8392c08da5ab16f28906538c272c3','createTable','',NULL,'3.3.2'),
	('1435018750749-4','lianghongbin (generated)','src/main/resources/databaseChangelog.xml','2015-06-23 08:22:15',4,'EXECUTED','7:cd9a7abffabb2c80d2a1c5011e252d2f','createTable','',NULL,'3.3.2'),
	('1435018750749-5','lianghongbin (generated)','src/main/resources/databaseChangelog.xml','2015-06-23 08:22:15',5,'EXECUTED','7:a4284ba8ba4432a321d3973a86ec2066','createTable','',NULL,'3.3.2'),
	('1435018750749-6','lianghongbin (generated)','src/main/resources/databaseChangelog.xml','2015-06-23 08:22:15',6,'EXECUTED','7:60779de30f79425a99857512391be1a6','createTable','',NULL,'3.3.2'),
	('1435018750749-7','lianghongbin (generated)','src/main/resources/databaseChangelog.xml','2015-06-23 08:22:15',7,'EXECUTED','7:ee0d59b5fdae480e02fe57271608c1de','createTable','',NULL,'3.3.2'),
	('1435018750749-8','lianghongbin (generated)','src/main/resources/databaseChangelog.xml','2015-06-23 08:22:15',8,'EXECUTED','7:904b4793021839fa7606bd81aba82e2b','createTable','',NULL,'3.3.2'),
	('1435018750749-9','lianghongbin (generated)','src/main/resources/databaseChangelog.xml','2015-06-23 08:22:15',9,'EXECUTED','7:92614e1b2c9ec86e6e74f2aa4724fbb3','createTable','',NULL,'3.3.2'),
	('1435018750749-10','lianghongbin (generated)','src/main/resources/databaseChangelog.xml','2015-06-23 08:22:15',10,'EXECUTED','7:8814d43380e975e93a573974a3a9164e','createTable','',NULL,'3.3.2'),
	('1435018750749-11','lianghongbin (generated)','src/main/resources/databaseChangelog.xml','2015-06-23 08:22:15',11,'EXECUTED','7:7be392bed8d696e3d2563fef7848b8d5','createTable','',NULL,'3.3.2');

/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table DATABASECHANGELOGLOCK
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;

CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;

INSERT INTO `DATABASECHANGELOGLOCK` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`)
VALUES
	(1,00000000,NULL,NULL);

/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table feedback
# ------------------------------------------------------------

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '反馈人ID',
  `content` varchar(255) DEFAULT '' COMMENT '反馈内容',
  `contact` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `processed` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否处理过',
  `remark` varchar(255) DEFAULT NULL COMMENT '处理备注',
  `create_time` bigint(20) DEFAULT NULL COMMENT '反馈时间',
  `process_time` bigint(20) DEFAULT NULL COMMENT '处理时间',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户手机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;

INSERT INTO `feedback` (`id`, `user_id`, `content`, `contact`, `processed`, `remark`, `create_time`, `process_time`, `phone`)
VALUES
	(1,NULL,'asdfasdfasdf',NULL,00000001,'asdf',1434606217131,1434606217131,'13910661166');

/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Flow
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Flow`;

CREATE TABLE `Flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(11) DEFAULT NULL COMMENT '获取分数',
  `type` int(11) DEFAULT NULL COMMENT '流水类别: 0赚取，1支出',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户手机',
  `method` varchar(20) DEFAULT NULL COMMENT '交易方式',
  `create_time` bigint(20) DEFAULT NULL COMMENT '获取时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Flow` WRITE;
/*!40000 ALTER TABLE `Flow` DISABLE KEYS */;

INSERT INTO `Flow` (`id`, `score`, `type`, `user_id`, `phone`, `method`, `create_time`)
VALUES
	(1,100,0,1,'13910661166','WEIXIN_GAIN',1434617310612),
	(2,100,0,1,'13910661166','QQ_GAIN',1434617767642),
	(3,100,0,1,'13910661166','DAILY_GAIN',1434618360957),
	(4,100,0,1,'13910661166','DAILY_GAIN',1435223960914),
	(5,100,0,1,'13910661166','QQ_GAIN',1435224120458);

/*!40000 ALTER TABLE `Flow` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table focus
# ------------------------------------------------------------

DROP TABLE IF EXISTS `focus`;

CREATE TABLE `focus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL COMMENT '焦点图类别：小游戏/卡包',
  `photo` varchar(255) DEFAULT NULL COMMENT '代表图',
  `sort` int(11) DEFAULT NULL COMMENT '显示顺序：值越小越靠前',
  `item_id` int(11) DEFAULT NULL COMMENT '小游戏或礼包ID',
  `enabled` bit(1) DEFAULT NULL COMMENT '是否使用',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `focus` WRITE;
/*!40000 ALTER TABLE `focus` DISABLE KEYS */;

INSERT INTO `focus` (`id`, `type`, `photo`, `sort`, `item_id`, `enabled`, `create_time`)
VALUES
	(1,'GAME','http://kk.7k7k.com/b.jpg',3,1,00000001,1434458939073),
	(3,'GAME','http://kk.7k7k.com/c.jpg',3,3,00000001,1434458939073),
	(4,'CARD','http://kk.7k7k.com/d.jpg',2,4,00000001,1434458939073),
	(5,'GAME','aaa',2,12,00000001,1434458939073),
	(6,'CARD','sdf',3,12,00000001,1434458939073);

/*!40000 ALTER TABLE `focus` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table game
# ------------------------------------------------------------

DROP TABLE IF EXISTS `game`;

CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '小游戏名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '小游戏代表图',
  `description` text COMMENT '小游戏描述',
  `url` varchar(255) DEFAULT NULL COMMENT '小游戏下载地址',
  `ios_id` varchar(20) DEFAULT NULL COMMENT '苹果下载ID',
  `recommend` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否推荐',
  `identifier` varchar(100) DEFAULT NULL COMMENT '游戏唯一包名',
  `total` int(11) DEFAULT NULL COMMENT '该游戏拥有礼包款数',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `platform` varchar(10) DEFAULT NULL COMMENT '游戏平台',
  `score` int(11) DEFAULT NULL COMMENT '用户评分：1-5分',
  `closed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否上线',
  `create_time` bigint(20) DEFAULT NULL COMMENT '添加时间',
  `modify_time` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;

INSERT INTO `game` (`id`, `name`, `icon`, `description`, `url`, `ios_id`, `recommend`, `identifier`, `total`, `sort`, `platform`, `score`, `closed`, `create_time`, `modify_time`)
VALUES
	(1,'金刚葫芦娃','http://kk.7k7k.com/a.jpg','金刚葫芦娃','http://kk.7k7k.com/download/ddz.zip','32d',1,'com.qike.mobile.h5',1,12,'iOS',2,0,1434458939073,1434458939073),
	(2,'斗地主','http://kk.7k7k.com/a.jpg','斗地主真的很好玩','http://kk.7k7k.com/download/ddz.zip','234455',1,'com.7k7k.ddz',15,2,'android',3,0,1434458939073,1434458939073),
	(3,'史上最贱小游戏13关','http://kk.7k7k.com/a.jpg','史上最贱小游戏13关','http://kk.7k7k.com/download/ddz.zip','fse',1,'com.7k7k.zzqxyx',18,3,'ALL',4,0,1434458939073,1434458939073),
	(4,'老爹三明治店','http://kk.7k7k.com/a.jpg','老爹三明治店的很好玩','http://kk.7k7k.com/download/ddz.zip','d2e',1,'bcd',27,4,'ALL',5,0,1434458939073,1434458939073),
	(5,'TH穿越火线1.45','http://kk.7k7k.com/a.jpg','TH穿越火线1.453333aadfasdf','http://kk.7k7k.com/download/ddz.zip','2344dda55',1,'com.7k7k.cyhj',5,1,'android',2,0,1434458939073,1434458939073),
	(6,'冒险王之神兵传奇终极无敌速升版','http://kk.7k7k.com/a.jpg','冒险王之神兵传奇终极无敌速升版','http://kk.7k7k.com/download/ddz.zip','2dfadsf',1,'com.7k7k.mxw',33,6,'android',1,0,1434458939073,1434458939073),
	(7,'逗小猴开心忍者篇2','http://kk.7k7k.com/a.jpg','逗小猴开心忍者篇2','http://kk.7k7k.com/download/ddz.zip','httes',1,'com.7k7k.dxhkx',15,7,'android',3,0,1434458939073,1434458939073),
	(8,'护卫队队长','http://kk.7k7k.com/a.jpg','护卫队队长护卫队队长护卫队队长','http://kk.7k7k.com/download/ddz.zip','dfa',1,'com.7k7k.hwdz',150,8,'ALL',4,0,1434458939073,1434458939073),
	(9,'战车撞僵尸2加强版','http://kk.7k7k.com/a.jpg','战车撞僵尸2加强版战车撞僵尸2加强版','http://kk.7k7k.com/download/ddz.zip','fw244a',1,'com.7k7k.zczjs',125,9,'android',5,0,1434458939073,1434458939073),
	(10,'我的世界3','http://kk.7k7k.com/a.jpg','我的世界3我的世界3','http://kk.7k7k.com/download/ddz.zip','hsasdfw',1,'com.7k7k.wdsj',15,10,'iOS',4,0,1434458939073,1434458939073),
	(11,'弹弹堂2','http://kk.7k7k.com/a.jpg','弹弹堂2弹弹堂2弹弹堂2','http://kk.7k7k.com/download/ddz.zip','235aadf',1,'com.7k7k.ddt',15,11,'ALL',4,0,1434458939073,1434458939073),
	(12,'超级熊大冒险','http://kk.7k7k.com/a.jpg','超级熊大冒险真的很好玩','http://kk.7k7k.com/download/ddz.zip','ffw3156',1,'com.7k7k.ccxdmx',4,1,'ALL',5,0,1434458939073,1434458939073);

/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table photo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `photo`;

CREATE TABLE `photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) DEFAULT NULL COMMENT '图片所属类别：游戏、卡包',
  `name` varchar(20) DEFAULT NULL COMMENT '图片名称',
  `url` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `item_id` int(11) DEFAULT NULL COMMENT '游戏或卡包ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;

INSERT INTO `photo` (`id`, `type`, `name`, `url`, `item_id`)
VALUES
	(1,'GAME','p1','http://kk.7k7k.com/c.jpg',1),
	(2,'GAME','p2','http//kk.7k7k.com/a.jpg',1),
	(3,'CARD','p1','http://kk.7k7k.com/a.jpg',1),
	(4,'GAME','p2','http://kk.7k7k.com/b.jpg',2),
	(5,'GAME','P','http://img3.redocn.com/20091221/20091217_fa2a743db1f556f82b9asJ320coGmYFf.jpg',12),
	(6,'GAME',NULL,'http://kk.7k7k.com/upload/20150625164105_646.png',12),
	(7,'CARD',NULL,'http://kk.7k7k.com/upload/20150625164734_610.png',44);

/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table setting
# ------------------------------------------------------------

DROP TABLE IF EXISTS `setting`;

CREATE TABLE `setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `us` varchar(255) DEFAULT '' COMMENT '关于我们',
  `announce` varchar(255) DEFAULT NULL COMMENT '启动公告',
  `weixin` int(11) DEFAULT NULL COMMENT '每天分享微信得分',
  `qq` int(11) DEFAULT NULL COMMENT '每天分享QQ得分',
  `daily` int(11) DEFAULT NULL COMMENT '每天登录得分',
  `registry` int(11) DEFAULT NULL COMMENT '注册得分',
  `v` varchar(10) DEFAULT NULL COMMENT '系统版本',
  `download` int(11) DEFAULT NULL COMMENT '下载游戏得分',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;

INSERT INTO `setting` (`id`, `us`, `announce`, `weixin`, `qq`, `daily`, `registry`, `v`, `download`, `create_time`)
VALUES
	(1,'我们是一群喜欢游戏的年轻人，我们的发出点是使人们玩游戏更简单、方便',NULL,10,10,100,300,'1_0',NULL,NULL);

/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table share
# ------------------------------------------------------------

DROP TABLE IF EXISTS `share`;

CREATE TABLE `share` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '分享用户ID',
  `card_id` int(11) DEFAULT NULL COMMENT '分享礼品ID',
  `game_id` int(11) DEFAULT NULL COMMENT '分享小游戏ID',
  `score` int(11) DEFAULT NULL COMMENT '分享获得各分',
  `create_time` bigint(20) DEFAULT NULL COMMENT '分享时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table splash
# ------------------------------------------------------------

DROP TABLE IF EXISTS `splash`;

CREATE TABLE `splash` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `photo` varchar(255) DEFAULT NULL COMMENT '开屏图片',
  `url` varchar(255) DEFAULT NULL COMMENT '点击开屏图片连接',
  `enabled` bit(1) DEFAULT NULL COMMENT '是否可用',
  `sort` int(11) DEFAULT NULL COMMENT '排序：越小越靠前',
  `create_time` bigint(20) DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `splash` WRITE;
/*!40000 ALTER TABLE `splash` DISABLE KEYS */;

INSERT INTO `splash` (`id`, `photo`, `url`, `enabled`, `sort`, `create_time`)
VALUES
	(1,'http://kk.7k7k.com/first.jpg','http://kk.7k7k.com',00000000,1,1434458939073),
	(4,'a','a',00000001,3,1435058150811);

/*!40000 ALTER TABLE `splash` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名称',
  `password` varchar(64) DEFAULT '' COMMENT '密码',
  `score` int(11) DEFAULT NULL COMMENT '积分',
  `device` varchar(64) DEFAULT NULL COMMENT '唯一设备号',
  `type` varchar(20) DEFAULT NULL COMMENT '设备类别',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `head` varchar(255) DEFAULT NULL COMMENT '代表头象',
  `create_time` bigint(20) DEFAULT NULL COMMENT '初次登录/注册时间',
  `last_time` bigint(20) DEFAULT NULL COMMENT '最后一次登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `username`, `password`, `score`, `device`, `type`, `phone`, `head`, `create_time`, `last_time`)
VALUES
	(1,'13910661166',NULL,720,NULL,'iOS','13910661166','http://kk.7k7k.com/lhb/head.jpg',1434458939073,1435224069902),
	(3,'18500346345',NULL,260,NULL,NULL,'18500346345',NULL,1434524631588,1434524631588);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
