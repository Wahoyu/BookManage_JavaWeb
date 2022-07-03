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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`bid`,`title`,`desc`,`price`) values 
(1,'山','山在哪儿，总有人去登的。',10.00),
(2,'活法','稻盛和夫将给年轻人的话',10.00),
(3,'汤文龙的日记','青春少年的故事',20.00),
(4,'罗向东的回家日记','回家路上与美少女的青春邂逅',20.00),
(5,'被讨厌的勇气','阿德勒的自我调节哲学课',20.00),
(6,'云边的小卖部','十三的心酸少年往事',9.90);

/*Table structure for table `borrow` */

DROP TABLE IF EXISTS `borrow`;

CREATE TABLE `borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sid_bid` (`sid`,`bid`) COMMENT '不能同时存在',
  KEY `f_bid` (`bid`),
  CONSTRAINT `f_bid` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`),
  CONSTRAINT `f_sid` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `borrow` */

insert  into `borrow`(`id`,`sid`,`bid`) values 
(1,1,1),
(4,1,2),
(8,4,4),
(5,6,3),
(13,8,3),
(12,9,3),
(14,11,1),
(11,12,3),
(10,13,3),
(7,14,3);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `sex` enum('男','女') NOT NULL,
  `grade` int(11) NOT NULL DEFAULT '2019',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`sid`,`name`,`sex`,`grade`) values 
(1,'小汤','男',2019),
(2,'小明','男',2019),
(3,'伟强','男',2019),
(4,'向东','男',2019),
(5,'王震','男',2019),
(6,'继玺','男',2019),
(7,'小黄','男',2019),
(8,'小刚','男',2019),
(9,'文杰','男',2019),
(10,'雄志','男',2019),
(11,'赵强','男',2019),
(12,'建安','男',2019),
(13,'泽宇','男',2019),
(14,'笨笨','女',2019);

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
