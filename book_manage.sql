/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : book_manage

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 12/08/2022 10:56:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
BEGIN;
INSERT INTO `book` VALUES (3, '《东方快车谋杀案》', '这是阿加莎克里斯蒂的书', 24.00);
INSERT INTO `book` VALUES (4, '《ABC谋杀案》', '阿加莎克里斯蒂著作', 34.09);
INSERT INTO `book` VALUES (5, '《无人生还》', '阿加莎克里斯经典', 76.89);
INSERT INTO `book` VALUES (6, '《嫌疑人X的献身》', '你好', 34.44);
INSERT INTO `book` VALUES (9, '《此生未完成》', '这是一本描述了患癌后的经历', 88.80);
INSERT INTO `book` VALUES (11, '《这是一本新书》', '这是一本描述爱情故事的书', 88.80);
COMMIT;

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow
-- ----------------------------
BEGIN;
INSERT INTO `borrow` VALUES (4, 4, 6, '2022-08-11 21:36:53');
INSERT INTO `borrow` VALUES (5, 3, 6, '2022-08-12 10:17:27');
INSERT INTO `borrow` VALUES (6, 5, 6, '2022-08-12 10:17:32');
COMMIT;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` enum('男','女') DEFAULT '男',
  `grade` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  KEY `f_uid` (`uid`),
  CONSTRAINT `f_uid` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (4, 10, 'admin', '男', '2019');
INSERT INTO `student` VALUES (6, 12, '短短', '女', '2021');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, '图书管理员', 'admin', '$2a$10$fGiYf9IXK3DaPxmO2nlvT.l.ET55apDDmn1qnwypVqTnbV.qWBZuO');
INSERT INTO `users` VALUES (10, 'admin', 'user', '$2a$10$/7y5RbT3yllN2U/IBfvsnO56E5jt1RYPK/YP.HGOKw9I2VqIzD9Ba');
INSERT INTO `users` VALUES (12, '短短', 'user', '$2a$10$fGiYf9IXK3DaPxmO2nlvT.l.ET55apDDmn1qnwypVqTnbV.qWBZuO');
COMMIT;

-- ----------------------------
-- Triggers structure for table book
-- ----------------------------
DROP TRIGGER IF EXISTS `del_book`;
delimiter ;;
CREATE TRIGGER `del_book` BEFORE DELETE ON `book` FOR EACH ROW DELETE FROM borrow WHERE bid = old.bid
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
