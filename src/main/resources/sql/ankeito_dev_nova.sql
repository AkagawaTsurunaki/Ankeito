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

 Date: 23/06/2023 16:17:18
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of option
-- ----------------------------
INSERT INTO `option` VALUES (0, 0, '如如', '30c14452-c16d-4a53-b356-ffbe6e1e0e64');
INSERT INTO `option` VALUES (0, 0, '是', '94eb677d-f8c8-41e7-b5fc-563c13867454');
INSERT INTO `option` VALUES (0, 1, '来了', '30c14452-c16d-4a53-b356-ffbe6e1e0e64');
INSERT INTO `option` VALUES (0, 1, '是似搜属', '94eb677d-f8c8-41e7-b5fc-563c13867454');
INSERT INTO `option` VALUES (0, 2, '1 + 11 = 哈哈哈', '94eb677d-f8c8-41e7-b5fc-563c13867454');
INSERT INTO `option` VALUES (1, 0, '如来', '30c14452-c16d-4a53-b356-ffbe6e1e0e64');
INSERT INTO `option` VALUES (1, 0, '不是', '94eb677d-f8c8-41e7-b5fc-563c13867454');
INSERT INTO `option` VALUES (1, 1, '没来', '30c14452-c16d-4a53-b356-ffbe6e1e0e64');
INSERT INTO `option` VALUES (1, 1, '锟斤拷平', '94eb677d-f8c8-41e7-b5fc-563c13867454');
INSERT INTO `option` VALUES (1, 2, '9 * 78 = 1212', '94eb677d-f8c8-41e7-b5fc-563c13867454');
INSERT INTO `option` VALUES (2, 0, '若至', '30c14452-c16d-4a53-b356-ffbe6e1e0e64');
INSERT INTO `option` VALUES (2, 1, '如来', '30c14452-c16d-4a53-b356-ffbe6e1e0e64');
INSERT INTO `option` VALUES (2, 1, '说的道理', '94eb677d-f8c8-41e7-b5fc-563c13867454');
INSERT INTO `option` VALUES (2, 2, '78 / 2213 = 0', '94eb677d-f8c8-41e7-b5fc-563c13867454');
INSERT INTO `option` VALUES (3, 0, '弱智', '30c14452-c16d-4a53-b356-ffbe6e1e0e64');
INSERT INTO `option` VALUES (3, 1, '好像来了', '30c14452-c16d-4a53-b356-ffbe6e1e0e64');
INSERT INTO `option` VALUES (3, 1, '肌肉紧张', '94eb677d-f8c8-41e7-b5fc-563c13867454');
INSERT INTO `option` VALUES (3, 2, '0 / 0 =INF', '94eb677d-f8c8-41e7-b5fc-563c13867454');

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
INSERT INTO `project` VALUES ('448ce5a6-4aaf-4b58-9e81-dede5918737e', 'TODO', '你是人类吗？', '本次调查目的在于调查参与者是否为人类。', 'AkagawaTsurunaki', 'AkagawaTsurunaki', '2023-06-23 15:47:41', '2023-06-23 15:47:41');
INSERT INTO `project` VALUES ('c955cebf-36b5-4dfa-82cf-d4115a933e09', 'TODO', '如来真来了吗?', '中国人认为宇宙万法...', 'AkagawaTsurunaki', 'AkagawaTsurunaki', '2023-06-23 15:31:49', '2023-06-23 15:31:49');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qnnre
-- ----------------------------
INSERT INTO `qnnre` VALUES ('30c14452-c16d-4a53-b356-ffbe6e1e0e64', 'c955cebf-36b5-4dfa-82cf-d4115a933e09', '关于如来是否真的来了的调查', '我们将进行一项关于如来是否真的来了的调查。此调查旨在了解人们对如来是否存在的看法和信仰。调查包括提问参与者是否相信如来的存在，并询问他们对如来来临的态度和期望。我们希望通过这项调查获得广泛的观点，并了解公众对如来来临的态度和意见。这项调查采用匿名方式，所有个人信息将被保密并仅用于统计分析。调查结果将帮助我们更好地理解人们对如来的看法，促进对相关话题的讨论和研究。感谢您参与我们的调查！', '2023-06-23 08:00:00', '2023-06-30 08:00:00', 'DRAFT');
INSERT INTO `qnnre` VALUES ('94eb677d-f8c8-41e7-b5fc-563c13867454', '448ce5a6-4aaf-4b58-9e81-dede5918737e', '你是人类吗1？', '01010100010111110111011110', '2023-06-23 08:00:00', '2023-06-28 08:00:00', 'PUBLISHED');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (0, '中国人认为宇宙万法的那个源头是什么？', 'REQUIRED', 'SINGLE_CHOICE_QUESTION', '30c14452-c16d-4a53-b356-ffbe6e1e0e64');
INSERT INTO `question` VALUES (0, '你是人类吗？', 'REQUIRED', 'SINGLE_CHOICE_QUESTION', '94eb677d-f8c8-41e7-b5fc-563c13867454');
INSERT INTO `question` VALUES (1, '如来真来了吗？', 'REQUIRED', 'SINGLE_CHOICE_QUESTION', '30c14452-c16d-4a53-b356-ffbe6e1e0e64');
INSERT INTO `question` VALUES (1, '请识别下列的词语，指出哪些不是人类常用词语？', 'OPTIONAL', 'SINGLE_CHOICE_QUESTION', '94eb677d-f8c8-41e7-b5fc-563c13867454');
INSERT INTO `question` VALUES (2, '下列算式中，那些是美味的？', 'REQUIRED', 'SINGLE_CHOICE_QUESTION', '94eb677d-f8c8-41e7-b5fc-563c13867454');

-- ----------------------------
-- Table structure for response_option
-- ----------------------------
DROP TABLE IF EXISTS `response_option`;
CREATE TABLE `response_option`  (
  `response_sheet_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `option_id` int NOT NULL,
  `question_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `qnnre_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of response_option
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of response_sheet
-- ----------------------------

-- ----------------------------
-- Table structure for response_sheet_detail
-- ----------------------------
DROP TABLE IF EXISTS `response_sheet_detail`;
CREATE TABLE `response_sheet_detail`  (
  `response_sheet_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `qnnre_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `question_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`response_sheet_id`, `qnnre_id`, `question_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of response_sheet_detail
-- ----------------------------

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
INSERT INTO `user` VALUES ('d87fe3a4-a2b4-1144-2d93-6f2ff968c8f1', 'AkagawaTsurunaki', '0', '2023-06-23 15:30:46', '2023-06-30 15:30:49', 'ADMIN', 'ENABLE', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
