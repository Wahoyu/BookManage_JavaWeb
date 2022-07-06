/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 5.6.24-log : Database - book_manage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`book_manage` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `book_manage`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`nickname`,`password`) values 
(1,'admin','图书管理员','123456');

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `desc` varchar(255) NOT NULL COMMENT '书籍介绍',
  `price` decimal(10,2) NOT NULL DEFAULT '2.00',
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`bid`,`title`,`desc`,`price`) values 
(1,'山','山在那儿，总有人去登的。',10.00),
(3,'汤文龙的日记','青春少年的故事',20.00),
(4,'罗向东的回家日记','回家路上与美少女的青春邂逅',20.00),
(14,'世界尽头的咖啡馆','你为什么来这里？你害怕死亡吗？你满足吗？',30.15),
(15,'生活蒙太奇','有时候就是要静静感受生活。',30.00),
(17,'考研数学','一起探究脱发的奥秘。',15.00),
(18,'如何优雅的蹭兵线','发挥你的经济优势',9.90),
(19,'动力节点Java','月薪过万就找黑马程序员（bushi',0.00),
(20,'如何送对方律师进监狱','百战百胜的辩护技巧',998.00),
(21,'考研单词闪过','快速入睡',22.50),
(22,'表情包大全','付费使用',1.00),
(23,'男朋友不知道我是黑帮老大','少女爱情故事',25.00),
(24,'汉服审美与修养','汉民族传统服饰',25.00),
(25,'国服女娲的修养','中单上分秘籍',12.00),
(26,'如何拒绝朋友不科学的邀请','不想深夜掉分',250.00),
(27,'被讨厌的勇气','阿德勒的哲学课',20.00),
(28,'蛤蟆先生去看心理医生','快去自救',25.00);

/*Table structure for table `borrow` */

DROP TABLE IF EXISTS `borrow`;

CREATE TABLE `borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sid_bid` (`sid`,`bid`) COMMENT '不能同时存在',
  UNIQUE KEY `f_bid` (`bid`) COMMENT '同一本书不能同时借两次',
  CONSTRAINT `f_bid` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`),
  CONSTRAINT `f_sid` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `borrow` */

insert  into `borrow`(`id`,`sid`,`bid`,`time`) values 
(16,1,1,'2022-07-05 09:36:33'),
(17,16,18,'2022-07-05 16:43:40'),
(18,18,19,'2022-07-05 16:45:19'),
(19,20,20,'2022-07-05 16:45:46'),
(20,19,17,'2022-07-05 16:45:54'),
(21,14,21,'2022-07-05 16:48:06'),
(22,17,15,'2022-07-05 16:50:27'),
(23,21,22,'2022-07-05 16:51:49'),
(24,24,28,'2022-07-05 17:00:04'),
(25,22,26,'2022-07-05 17:00:10'),
(26,25,25,'2022-07-05 17:00:44'),
(27,23,24,'2022-07-05 17:00:50'),
(28,26,23,'2022-07-05 17:01:16');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `sex` enum('男','女') NOT NULL,
  `grade` int(11) NOT NULL DEFAULT '2019',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`sid`,`name`,`sex`,`grade`) values 
(1,'小汤','男',2019),
(3,'伟强','男',2019),
(14,'笨笨','女',2019),
(15,'尧尧','女',2019),
(16,'妞妞','男',2019),
(17,'驼哥','男',2019),
(18,'航哥','男',2019),
(19,'美丽','男',2019),
(20,'郭泽','男',2019),
(21,'华哥','男',2019),
(22,'腿子哥','男',2019),
(23,'菁菁姐','女',2019),
(24,'王熊二','男',2019),
(25,'小翔','男',2019),
(26,'韩老大','男',2019);

/* Trigger structure for table `book` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `del_book` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `del_book` BEFORE DELETE ON `book` FOR EACH ROW delete from borrow where bid = old.bid */$$


DELIMITER ;

/* Trigger structure for table `student` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `del_student` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `del_student` BEFORE DELETE ON `student` FOR EACH ROW DELETE FROM borrow where sid = old.sid */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
