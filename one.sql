/*
SQLyog Community Edition- MySQL GUI v7.02 
MySQL - 5.1.59-community : Database - secure_bank
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`secure_bank` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `secure_bank`;

/*Table structure for table `deposite` */

DROP TABLE IF EXISTS `deposite`;

CREATE TABLE `deposite` (
  `byname` varchar(100) DEFAULT NULL,
  `acno` varchar(100) DEFAULT NULL,
  `ifsc` varchar(100) DEFAULT NULL,
  `branch` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `datetime` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `deposite` */

insert  into `deposite`(`byname`,`acno`,`ifsc`,`branch`,`amount`,`datetime`) values ('arun jose','780990022','SECURE0005980','chennai','950.0','2020/11/21 22:38:57');

/*Table structure for table `transfer` */

DROP TABLE IF EXISTS `transfer`;

CREATE TABLE `transfer` (
  `bname` varchar(100) DEFAULT NULL,
  `fromaccno` varchar(100) DEFAULT NULL,
  `toaccno` varchar(100) DEFAULT NULL,
  `ifsc` varchar(100) DEFAULT NULL,
  `branch` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `datetime` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `transfer` */

insert  into `transfer`(`bname`,`fromaccno`,`toaccno`,`ifsc`,`branch`,`amount`,`datetime`) values ('arun','780990022','24242424','SECURE0005980','chennai','50','2020/11/21 23:22:27');

/*Table structure for table `user_registration` */

DROP TABLE IF EXISTS `user_registration`;

CREATE TABLE `user_registration` (
  `bankname` varchar(100) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `age` varchar(100) DEFAULT NULL,
  `branchname` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `accno` varchar(100) DEFAULT NULL,
  `ifsc` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `datetime` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_registration` */

insert  into `user_registration`(`bankname`,`firstname`,`lastname`,`mail`,`gender`,`age`,`branchname`,`address`,`accno`,`ifsc`,`password`,`datetime`) values ('SECURE BANK','arun','jose','arunjs46@gmail.com','Male','27','chennai','Stigmata Techno solution, Vembuli amman koil street,palavanthangal','780990022','SECURE0005980','429129','2020/11/21 13:45:27');

/*Table structure for table `withdraw` */

DROP TABLE IF EXISTS `withdraw`;

CREATE TABLE `withdraw` (
  `acno` varchar(100) DEFAULT NULL,
  `ifsc` varchar(100) DEFAULT NULL,
  `branch` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `datetime` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `withdraw` */

insert  into `withdraw`(`acno`,`ifsc`,`branch`,`amount`,`datetime`) values ('780990022','SECURE0005980','chennai','1000.0','2020/11/21 15:44:01'),('780990022','SECURE0005980','chennai','1000.0','2020/11/21 23:03:24');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
