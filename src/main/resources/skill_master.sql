/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 112.124.30.158:3306
 Source Schema         : skill_master

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 23/06/2023 17:23:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blogcomments
-- ----------------------------
DROP TABLE IF EXISTS `blogcomments`;
CREATE TABLE `blogcomments`  (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `receiver` int NOT NULL COMMENT '回复的对象',
  `commentor` int NOT NULL COMMENT '评论者',
  `commentee` int NOT NULL COMMENT '被回复者',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  `likes` int NOT NULL DEFAULT 0 COMMENT '点赞数量',
  `comment` int NOT NULL DEFAULT 0 COMMENT '评论数量',
  `state` enum('N','C','D') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论的状态',
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `commentee`(`commentee`) USING BTREE,
  INDEX `commentor`(`commentor`) USING BTREE,
  CONSTRAINT `blogcomments_ibfk_1` FOREIGN KEY (`commentor`) REFERENCES `user` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `blogcomments_ibfk_2` FOREIGN KEY (`commentee`) REFERENCES `user` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blogs
-- ----------------------------
DROP TABLE IF EXISTS `blogs`;
CREATE TABLE `blogs`  (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `owner` int NOT NULL COMMENT '博主',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发表时间',
  `likes` int NOT NULL DEFAULT 0 COMMENT '点赞数量',
  `comment` int NOT NULL DEFAULT 0 COMMENT '评论数量',
  `state` enum('N','C','D') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '博客的状态',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '博客内容',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '无标题',
  `tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `img` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'DefaultBlogImg.png',
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `owner`(`owner`) USING BTREE,
  CONSTRAINT `blogs_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `user` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ccomments
-- ----------------------------
DROP TABLE IF EXISTS `ccomments`;
CREATE TABLE `ccomments`  (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `receiver` int NOT NULL COMMENT '回复的对象',
  `commentor` int NOT NULL COMMENT '评论者',
  `commentee` int NOT NULL COMMENT '被回复者',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  `likes` int NOT NULL DEFAULT 0 COMMENT '点赞数量',
  `comment` int NOT NULL DEFAULT 0 COMMENT '评论数量',
  `state` enum('N','C','D') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论的状态',
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `commentee`(`commentee`) USING BTREE,
  INDEX `commentor`(`commentor`) USING BTREE,
  CONSTRAINT `ccomments_ibfk_1` FOREIGN KEY (`commentor`) REFERENCES `user` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ccomments_ibfk_2` FOREIGN KEY (`commentee`) REFERENCES `user` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow`  (
  `blogger` int NOT NULL COMMENT '博主',
  `follower` int NOT NULL COMMENT '关注者',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '关注时间',
  INDEX `blogger`(`blogger`) USING BTREE,
  INDEX `follower`(`follower`) USING BTREE,
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`blogger`) REFERENCES `user` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`follower`) REFERENCES `user` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for keywords
-- ----------------------------
DROP TABLE IF EXISTS `keywords`;
CREATE TABLE `keywords`  (
  `uid` int NOT NULL COMMENT '博客',
  `keyword` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关键词',
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `keywords_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `blogs` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags`  (
  `uid` int NOT NULL COMMENT '博客',
  `tag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置时间',
  `state` enum('N','C','D') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签的状态',
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `tags_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `blogs` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `a` int NULL DEFAULT NULL,
  `b` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `account` int NOT NULL AUTO_INCREMENT COMMENT '账号',
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `gender` enum('M','F','O') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `signature` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '该用户未设置签名' COMMENT '用户签名',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '/DefaultAvatar.png' COMMENT '用户头像',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户创建时间',
  `state` enum('N','E','C') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号状态',
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
