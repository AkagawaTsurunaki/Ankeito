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

 Date: 23/06/2023 10:53:02
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
INSERT INTO `project` VALUES ('ceb85cfd-406a-491d-a8a0-e15148dffe29', 'TODO', 'jfczlhnwzijibmbt', 'zscdndihwqxzbmfxphjgmiytoluidrzxelifsrhyeiirpaqcjclimjrlkqgawwklhncqlnheislgyjzfnuijkhakikahfxhknfpqwidlfmqtqxoeuyzxtgislnnvakmesauvoavmtqxyaoidjmqakdnmxzbiosgghyfoqsnhnzwocrxxzdqdpecwjokvhwewccdspbng', 'njkrnwyf', 'mzfhgtpi', '2023-06-23 10:49:18', '2023-06-23 10:49:18');

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
INSERT INTO `qnnre` VALUES ('26114385-1ae2-4679-91ee-b146b5869d3b', '3a64b27a-2f52-4cfa-82eb-115abbf7de41', '测试调查问卷', '这是一段调查问卷说明', '2023-06-21 08:00:00', '2023-06-30 08:00:00', 'PUBLISHED');
INSERT INTO `qnnre` VALUES ('b8030ee6-b95a-4102-b00e-f3edd66dfe6d', '3a64b27a-2f52-4cfa-82eb-115abbf7de41', '如来真来了吗', '中国人认为, 宇宙万法...', '2023-05-31 08:00:00', '2023-06-29 08:00:00', 'PUBLISHED');

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
INSERT INTO `question` VALUES (1, '下列哪个字词组合不是人类可读的?', 'OPTIONAL', 'MULTIPLE_CHOICE_QUESTION', '26114385-1ae2-4679-91ee-b146b5869d3b');

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
INSERT INTO `response_option` VALUES ('73ec6a40-5201-450d-a7d0-59eeaafda18d', 0, '0', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `response_option` VALUES ('73ec6a40-5201-450d-a7d0-59eeaafda18d', 0, '1', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `response_option` VALUES ('3cd0572c-6d75-4117-be34-4df06e2b9f60', 0, '0', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `response_option` VALUES ('3cd0572c-6d75-4117-be34-4df06e2b9f60', 1, '1', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `response_option` VALUES ('3cd0572c-6d75-4117-be34-4df06e2b9f60', 2, '1', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `response_option` VALUES ('3cd0572c-6d75-4117-be34-4df06e2b9f60', 3, '1', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `response_option` VALUES ('7268cc8c-b331-4b31-b8bb-5f2cc4967148', 1, '0', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `response_option` VALUES ('7268cc8c-b331-4b31-b8bb-5f2cc4967148', 1, '1', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `response_option` VALUES ('7268cc8c-b331-4b31-b8bb-5f2cc4967148', 2, '1', '26114385-1ae2-4679-91ee-b146b5869d3b');
INSERT INTO `response_option` VALUES ('7268cc8c-b331-4b31-b8bb-5f2cc4967148', 3, '1', '26114385-1ae2-4679-91ee-b146b5869d3b');

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
INSERT INTO `response_sheet` VALUES ('3cd0572c-6d75-4117-be34-4df06e2b9f60', '26114385-1ae2-4679-91ee-b146b5869d3b', '测试调查问卷', '4c22ca63-5a5f-4cd0-85b6-a9c13212619d', 'TempUser26758657703982927244', '2023-06-23 00:21:22');
INSERT INTO `response_sheet` VALUES ('46348560-78c6-4957-8d49-eb6b2579f1ff', '26114385-1ae2-4679-91ee-b146b5869d3b', '测试调查问卷', '8971ab77-e4e7-421e-a701-1343ec1431f6', 'TempUser32524058565133072452', '2023-06-23 01:50:33');
INSERT INTO `response_sheet` VALUES ('7268cc8c-b331-4b31-b8bb-5f2cc4967148', '26114385-1ae2-4679-91ee-b146b5869d3b', '测试调查问卷', '723a7b89-6dde-4257-b31d-951d6fe7b41a', 'TempUser80819914814811362027', '2023-06-23 00:21:31');
INSERT INTO `response_sheet` VALUES ('73ec6a40-5201-450d-a7d0-59eeaafda18d', '26114385-1ae2-4679-91ee-b146b5869d3b', '测试调查问卷', 'f51a7cf5-3951-4465-8dcd-715150ebb013', 'TempUser09249458663454980685', '2023-06-22 15:54:42');
INSERT INTO `response_sheet` VALUES ('bfa68783-ebbb-44f2-a5e0-e7ebca1f68b7', '26114385-1ae2-4679-91ee-b146b5869d3b', '测试调查问卷', '742754be-e388-4ade-aef7-fad5b4be0b3f', 'TempUser93743575811549322943', '2023-06-22 16:04:20');

-- ----------------------------
-- Table structure for response_sheet_detail
-- ----------------------------
DROP TABLE IF EXISTS `response_sheet_detail`;
CREATE TABLE `response_sheet_detail`  (
  `response_sheet_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `qnnre_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `question_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`response_sheet_id`, `qnnre_id`, `question_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of response_sheet_detail
-- ----------------------------
INSERT INTO `response_sheet_detail` VALUES ('3cd0572c-6d75-4117-be34-4df06e2b9f60', '26114385-1ae2-4679-91ee-b146b5869d3b', '0');
INSERT INTO `response_sheet_detail` VALUES ('3cd0572c-6d75-4117-be34-4df06e2b9f60', '26114385-1ae2-4679-91ee-b146b5869d3b', '1');
INSERT INTO `response_sheet_detail` VALUES ('7268cc8c-b331-4b31-b8bb-5f2cc4967148', '26114385-1ae2-4679-91ee-b146b5869d3b', '0');
INSERT INTO `response_sheet_detail` VALUES ('7268cc8c-b331-4b31-b8bb-5f2cc4967148', '26114385-1ae2-4679-91ee-b146b5869d3b', '1');
INSERT INTO `response_sheet_detail` VALUES ('73ec6a40-5201-450d-a7d0-59eeaafda18d', '26114385-1ae2-4679-91ee-b146b5869d3b', '0');
INSERT INTO `response_sheet_detail` VALUES ('73ec6a40-5201-450d-a7d0-59eeaafda18d', '26114385-1ae2-4679-91ee-b146b5869d3b', '1');

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
  `user_role` enum('ADMIN','NO_ROLE','STUDENT','TEACHER') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
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
