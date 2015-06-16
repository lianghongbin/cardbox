
LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;

INSERT INTO `card` (`id`, `game_id`, `game_name`, `name`, `icon`, `description`, `flow`, `total`, `assign_total`, `type`, `score`, `recommend`, `platform`, `closed`, `create_time`, `close_time`, `expire_time`, `open_time`)
VALUES
	(1,1,'金刚葫芦娃','葫芦娃-周末大派送','http://kk.7k7k.com','金刚葫芦娃是一款flash经典小游戏','首先下载葫芦娃flash，然后获取激活码，进入 游戏激活',10,0,'SCORE',20,00000001,'ALL',00000000,1434458909073,1434458939073,1434458999073,1434458939073),
	(2,2,'斗地主','斗地主-金豆','http://kk.7k7k.com','斗地主礼包','首先下载斗地主flash，然后获取激活码，进入 游戏激活',10,0,'SCORE',20,00000001,'android',00000000,1434458909073,1434458939073,1434458999073,1434458939073),
	(3,3,'史上最贱小游戏13关','史上最强武器','http://kk.7k7k.com','13关是一款flash经典小游戏','首先下载13关flash，然后获取激活码，进入 游戏激活',8,0,'SCORE',20,00000001,'iOS',00000000,1434458909073,1434458939073,1434458999073,1434458939073),
	(4,4,'老爹三明治店','老爹三明治店-周末大派送','http://kk.7k7k.com','老爹三明治店是一款flash经典小游戏','首先下载老爹三明治店flash，然后获取激活码，进入 游戏激活',10,0,'SCORE',20,00000001,'ALL',00000000,1434458909073,1434458939073,1434458999073,1434458939073),
	(5,5,'TH穿越火线1.45','装备库','http://kk.7k7k.com','TH穿越火线1.45 装备库，相当的用用','先下载flash，然后输入装备库的激活码',5,0,'FREE',0,00000001,'ALL',00000000,1434458909073,1434458939073,1434458999073,1434458939073);

/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table focus
# ------------------------------------------------------------

LOCK TABLES `focus` WRITE;
/*!40000 ALTER TABLE `focus` DISABLE KEYS */;

INSERT INTO `focus` (`id`, `type`, `photo`, `sort`, `item_id`, `enabled`, `create_time`)
VALUES
	(1,'GAME','http://kk.7k7k.com/a.jpg',1,1,00000001,1434458939073),
	(2,'GAME','http://kk.7k7k.com/b.jpg',2,2,00000001,1434458939073),
	(3,'GAME','http://kk.7k7k.com/c.jpg',3,3,00000001,1434458939073),
	(4,'CARD','http://kk.7k7k.com/d.jpg',2,4,00000001,1434458939073);

/*!40000 ALTER TABLE `focus` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table game
# ------------------------------------------------------------

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;

INSERT INTO `game` (`id`, `name`, `icon`, `description`, `url`, `ios_id`, `recommend`, `identifier`, `total`, `sort`, `create_time`, `modify_time`)
VALUES
	(1,'金刚葫芦娃','http://kk.7k7k.com/a.jpg','金刚葫芦娃','http://kk.7k7k.com/download/ddz.zip','32d',00000001,'com.7k7k.jghlw',12,12,1434458939073,1434458939073),
	(2,'斗地主','http://kk.7k7k.com/a.jpg','斗地主真的很好玩','http://kk.7k7k.com/download/ddz.zip','234455',00000001,'com.7k7k.ddz',15,2,1434458939073,1434458939073),
	(3,'史上最贱小游戏13关','http://kk.7k7k.com/a.jpg','史上最贱小游戏13关','http://kk.7k7k.com/download/ddz.zip','fse',00000001,'com.7k7k.zzqxyx',18,3,1434458939073,1434458939073),
	(4,'老爹三明治店','http://kk.7k7k.com/a.jpg','老爹三明治店的很好玩','http://kk.7k7k.com/download/ddz.zip','d2e',00000001,'com.7k7k.ldsmz',25,4,1434458939073,1434458939073),
	(5,'TH穿越火线1.45','http://kk.7k7k.com/a.jpg','TH穿越火线1.453333aadfasdf','http://kk.7k7k.com/download/ddz.zip','2344dda55',00000001,'com.7k7k.cyhj',135,5,1434458939073,1434458939073),
	(6,'冒险王之神兵传奇终极无敌速升版','http://kk.7k7k.com/a.jpg','冒险王之神兵传奇终极无敌速升版','http://kk.7k7k.com/download/ddz.zip','2dfadsf',00000001,'com.7k7k.mxw',33,6,1434458939073,1434458939073),
	(7,'逗小猴开心忍者篇2','http://kk.7k7k.com/a.jpg','逗小猴开心忍者篇2','http://kk.7k7k.com/download/ddz.zip','httes',00000001,'com.7k7k.dxhkx',15,7,1434458939073,1434458939073),
	(8,'护卫队队长','http://kk.7k7k.com/a.jpg','护卫队队长护卫队队长护卫队队长','http://kk.7k7k.com/download/ddz.zip','dfa',00000001,'com.7k7k.hwdz',150,8,1434458939073,1434458939073),
	(9,'战车撞僵尸2加强版','http://kk.7k7k.com/a.jpg','战车撞僵尸2加强版战车撞僵尸2加强版','http://kk.7k7k.com/download/ddz.zip','fw244a',00000001,'com.7k7k.zczjs',125,9,1434458939073,1434458939073),
	(10,'我的世界3','http://kk.7k7k.com/a.jpg','我的世界3我的世界3','http://kk.7k7k.com/download/ddz.zip','hsasdfw',00000001,'com.7k7k.wdsj',15,10,1434458939073,1434458939073),
	(11,'弹弹堂2','http://kk.7k7k.com/a.jpg','弹弹堂2弹弹堂2弹弹堂2','http://kk.7k7k.com/download/ddz.zip','235aadf',00000001,'com.7k7k.ddt',15,11,1434458939073,1434458939073),
	(12,'超级熊大冒险','http://kk.7k7k.com/a.jpg','超级熊大冒险真的很好玩','http://kk.7k7k.com/download/ddz.zip','ffw3156',00000001,'com.7k7k.ccxdmx',20,1,1434458939073,1434458939073);

/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table photo
# ------------------------------------------------------------



# Dump of table setting
# ------------------------------------------------------------

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;

INSERT INTO `setting` (`id`, `us`, `weixin`, `qq`, `daily`, `registry`, `v`)
VALUES
	(1,'我们是一群喜欢游戏的年轻人，我们的发出点是使人们玩游戏更简单、方便',10,10,100,300,'1_0');

/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table share
# ------------------------------------------------------------



# Dump of table splash
# ------------------------------------------------------------

LOCK TABLES `splash` WRITE;
/*!40000 ALTER TABLE `splash` DISABLE KEYS */;

INSERT INTO `splash` (`id`, `photo`, `url`, `enabled`, `sort`, `create_time`)
VALUES
	(1,'http://kk.7k7k.com/first.jpg','http://kk.7k7k.com',00000001,1,1434458939073);

/*!40000 ALTER TABLE `splash` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `username`, `password`, `score`, `device`, `type`, `phone`, `head`, `create_time`, `last_time`)
VALUES
	(1,'13910661166',NULL,300,'adddbbd','iOS','13910661166','http://kk.7k7k.com/lhb/head.jpg',1434458939073,1434458939074);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
