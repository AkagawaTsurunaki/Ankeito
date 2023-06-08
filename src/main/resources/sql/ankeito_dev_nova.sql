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

 Date: 08/06/2023 13:47:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('74f40751-938c-4972-90d5-b7a67964e40d', 'TODO', '关于苏联修正主义对我国的影响', '这是一份历史调查问卷', 'admin', 'admin', '2023-06-08 13:36:05', '2023-06-08 13:36:05');
INSERT INTO `project` VALUES ('a73990ec-6277-4462-8e45-4bc687f27556', 'TODO', '你好吗', '第二个你好吗?', 'admin', 'admin', '2023-06-08 13:41:32', '2023-06-08 13:41:32');
INSERT INTO `project` VALUES ('e358c2e1-1670-48f8-b1f9-02fa05e29df0', 'TODO', '大学生恋爱调查问卷', '这是一份关于大学生恋爱的调查问卷', 'admin', 'admin', '2023-06-08 13:36:13', '2023-06-08 13:36:13');
INSERT INTO `project` VALUES ('fa952223-5e74-44c6-a3a9-f916c18b8fcf', 'TODO', '你好吗', '这是一份心理调查问卷', 'admin', 'admin', '2023-06-08 13:32:53', '2023-06-08 13:32:53');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('82697df6-4acb-7e7b-8812-df7e28eaf51c', 'admin', '0000', '2023-06-08 13:19:38', '2023-06-30 13:19:41', 'ADMIN', 'ENABLE', '82697df6-4acb-7e7b-8812-df7e28eaf51c', '2023-06-08 13:19:55', '82697df6-4acb-7e7b-8812-df7e28eaf51c', '2023-06-08');

SET FOREIGN_KEY_CHECKS = 1;
