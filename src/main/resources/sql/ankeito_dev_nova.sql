/*
 Navicat Premium Data Transfer

 Source Server         : ROOT
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : ankeito_dev

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 21/06/2023 22:38:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for option
-- ----------------------------
DROP TABLE IF EXISTS `option`;
CREATE TABLE `option`  (
  `id` int NOT NULL,
  `question_id` int NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `qnnre_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`, `question_id`, `qnnre_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of option
-- ----------------------------
INSERT INTO `option` VALUES (0, 0, 'A 是', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `option` VALUES (0, 1, 'A 深及硪员', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `option` VALUES (1, 0, 'B 不是', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `option` VALUES (1, 1, 'B 搜噩西从', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `option` VALUES (2, 1, 'C 婆罗门教', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `option` VALUES (3, 1, 'D 派三类牌', '26114385-1ae2-4679-91ee-b146b5869d3b');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `person_in_charge` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `project_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `project_content` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `created_by` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `last_updated_by` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_date` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('3a64b27a-2f52-4cfa-82eb-115abbf7de41', 'TODO', '测试项目', '该项目用于测试', 'admin', 'admin', '2023-06-20 23:52:30', '2023-06-20 23:52:30');

-- ----------------------------
-- Table structure for qnnre
-- ----------------------------
DROP TABLE IF EXISTS `qnnre`;
CREATE TABLE `qnnre`  (
  `id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `project_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `start_time` datetime NOT NULL,
  `stop_time` datetime NOT NULL,
  `qnnre_status` enum('DRAFT','PUBLISHED','CLOSED','DELETED') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'DRAFT',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qnnre
-- ----------------------------
INSERT INTO `qnnre` VALUES ('26114385-1ae2-4679-91ee-b146b5869d3b', '3a64b27a-2f52-4cfa-82eb-115abbf7de41', '测试调查问卷', '这是一段调查问卷说明', '2023-06-21 08:00:00', '2023-06-30 08:00:00', 'DRAFT');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `required` enum('REQUIRED','OPTIONAL') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'OPTIONAL',
  `type` enum('SINGLE_CHOICE_QUESTION','MULTIPLE_CHOICE_QUESTION') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'MULTIPLE_CHOICE_QUESTION',
  `qnnre_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`, `qnnre_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (0, '你是人类吗?', 'REQUIRED', 'SINGLE_CHOICE_QUESTION', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `question` VALUES (1, '下列哪个字词组合不是人类可读的?', 'REQUIRED', 'SINGLE_CHOICE_QUESTION', '26114385-1ae2-4679-91ee-b146b5869d3b');

-- ----------------------------
-- Table structure for response_option
-- ----------------------------
DROP TABLE IF EXISTS `response_option`;
CREATE TABLE `response_option`  (
  `response_sheet_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `option_id` int NOT NULL,
  `question_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `qnnre_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of response_option
-- ----------------------------
INSERT INTO `response_option` VALUES ('41675c81-4464-8b26-2332-b7376203e026', 3, '1', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `response_option` VALUES ('41675c81-4464-8b26-2332-b7376203e026', 3, '1', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `response_option` VALUES ('41675c81-4464-8b26-2332-b7376203e026', 3, '1', '26114385-1ae2-4679-91ee-b146b5869d3b');

-- ----------------------------
-- Table structure for response_sheet
-- ----------------------------
DROP TABLE IF EXISTS `response_sheet`;
CREATE TABLE `response_sheet`  (
  `id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `qnnre_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `qnnre_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `respondent_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `respondent_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `finished_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of response_sheet
-- ----------------------------
INSERT INTO `response_sheet` VALUES ('1eee2956-d4e9-2096-6b50-661846c86b6c', '26114385-1ae2-4679-91ee-b146b5869d3b', '测试调查问卷', '815fc4ca-c344-26eb-980a-5f755f76eeca', '张三', '2023-06-21 21:40:55');
INSERT INTO `response_sheet` VALUES ('44180c27-a198-6e68-fdb5-b4c7a890dec2', '26114385-1ae2-4679-91ee-b146b5869d3b', '测试调查问卷', 'at2023', 'AkagawaTsurunaki', '2023-06-21 20:46:37');

-- ----------------------------
-- Table structure for response_sheet_detail
-- ----------------------------
DROP TABLE IF EXISTS `response_sheet_detail`;
CREATE TABLE `response_sheet_detail`  (
  `response_sheet_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `qnnre_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `question_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`response_sheet_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of response_sheet_detail
-- ----------------------------
INSERT INTO `response_sheet_detail` VALUES ('41675c81-4464-8b26-2332-b7376203e026', '26114385-1ae2-4679-91ee-b146b5869d3b', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `username` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `start_time` datetime NOT NULL,
  `stop_time` datetime NOT NULL,
  `user_role` enum('ADMIN','NO_ROLE') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `user_status` enum('ENABLE','DISABLE') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `created_by` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `creation_time` datetime NULL DEFAULT NULL,
  `last_updated_by` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `last_update_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '0', '2023-06-20 13:31:17', '2023-06-22 13:31:22', 'ADMIN', 'ENABLE', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('815fc4ca-c344-26eb-980a-5f755f76eeca', '张三', '132', '2023-06-21 21:40:25', '2023-06-30 21:40:29', 'NO_ROLE', 'ENABLE', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('at2023', 'AkagawaTsurunaki', '123', '2023-06-21 20:42:40', '2023-06-21 20:42:43', 'ADMIN', 'ENABLE', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
