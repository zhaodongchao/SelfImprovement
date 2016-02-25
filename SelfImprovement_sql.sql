/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.5.46-log : Database - selfimprovement
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`selfimprovement` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `selfimprovement`;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` varchar(36) NOT NULL COMMENT '编号',
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名',
  `level` int(1) NOT NULL COMMENT '菜单级别',
  `isleaf` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为叶子1代表true，0代表false',
  `menu_url` text COMMENT '菜单上对应的URL',
  `parentId` varchar(36) DEFAULT NULL COMMENT '上一级节点的ID，根节点为空',
  `created_time` datetime NOT NULL DEFAULT '2015-12-12 00:00:00' COMMENT '菜单创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`menu_name`,`level`,`isleaf`,`menu_url`,`parentId`,`created_time`) values ('2967f2c0-964f-11e5-b62f-54ee7544f762','我的办公桌',1,0,'','root','2015-12-12 05:00:00'),('a5f7eda4-a0ad-11e5-a2e3-54ee7544f762','菜单管理',2,1,'sm/menuManager/menuManager.jsp','fe35e686-9727-11e5-b112-54ee7544f762','2015-12-12 00:00:00'),('aabb9488-9650-11e5-b62f-54ee7544f762','公司邮件',2,1,'http://www.hao123.com','2967f2c0-964f-11e5-b62f-54ee7544f762','2015-12-12 00:00:00'),('dc1d41e4-9726-11e5-b112-54ee7544f762','EDS系统',2,1,'http://eds.newtouch.cn','2967f2c0-964f-11e5-b62f-54ee7544f762','2015-12-12 00:00:00'),('ea116802-9f42-11e5-9782-54ee7544f762','用户管理',2,1,'sm/userManager/userManager.jsp','fe35e686-9727-11e5-b112-54ee7544f762','2015-12-12 00:00:00'),('fe35e686-9727-11e5-b112-54ee7544f762','系统管理',1,0,'','root','2015-12-12 00:00:00');

/*Table structure for table `sys_permissions` */

DROP TABLE IF EXISTS `sys_permissions`;

CREATE TABLE `sys_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) NOT NULL,
  `permissioncode` varchar(50) NOT NULL,
  `permission_uri` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20002 DEFAULT CHARSET=utf8;

/*Data for the table `sys_permissions` */

insert  into `sys_permissions`(`id`,`pname`,`permissioncode`,`permission_uri`) values (20000,'用户登陆','USER_LOGIN','/sm/login/login'),(20001,'访问主界面','MAIN_PAGE','/sm/login/main');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  `status` tinyint(1) DEFAULT '0' COMMENT '状态，默认为false，标示禁用',
  `role_code` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10004 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`status`,`role_code`) values (10000,'董事长',1,'ROLE_ADMIN'),(10001,'经理',1,'ROLE_MANAGER'),(10002,'外来人员',0,'ROLE_GUI'),(10003,'普通员工',0,'ROLE_STAFF');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `roleId` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  KEY `FK2` (`roleId`),
  KEY `FK1` (`pid`),
  CONSTRAINT `FK1` FOREIGN KEY (`pid`) REFERENCES `sys_permissions` (`id`),
  CONSTRAINT `FK2` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限对应表';

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`roleId`,`pid`) values (10000,20001),(10001,20001);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT '1',
  `birthday` date DEFAULT '2099-01-01' COMMENT '生日',
  `telPhone` varchar(20) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL COMMENT '公司',
  `address` varchar(100) DEFAULT NULL COMMENT '住址',
  `mail` varchar(20) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0',
  `login_err_count` int(2) DEFAULT '0',
  `register_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `last_login_time` timestamp NULL DEFAULT NULL,
  `parentId` int(11) DEFAULT '11111111',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`username`,`password`,`nickname`,`age`,`sex`,`birthday`,`telPhone`,`company`,`address`,`mail`,`status`,`login_err_count`,`register_time`,`last_login_time`,`parentId`) values (0,'赵东朝','86ABAC37141D8BD46A1AA3155697D6DE','朝朝',22,1,'1993-06-10','18221040570','新致软件','湖北麻城','997546127@qq.com',0,0,'2015-12-11 14:01:38','2015-12-12 14:01:44',11111111),(1,'小楚','86ABAC37141D8BD46A1AA3155697D6DE','楚楚',23,0,'2099-01-01','13147164261','麻城国税局','湖北麻城','1965756390',0,0,'2015-12-11 14:04:08','2015-12-12 14:04:12',0);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `userId` int(5) NOT NULL,
  `roleId` int(5) NOT NULL,
  KEY `userId` (`userId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`userId`,`roleId`) values (0,10000),(0,10001),(0,10002),(0,10003);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
