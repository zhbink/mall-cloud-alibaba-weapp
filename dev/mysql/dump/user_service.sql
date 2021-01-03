/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : user_service

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 21/12/2020 22:09:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `avatar_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `gender` int(1) NULL DEFAULT NULL,
  `country` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `province` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `city` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `language` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `register_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_open_id_uindex`(`open_id`) USING BTREE,
  INDEX `open_id`(`open_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (3, '1', '测试用户1', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2018-11-23 09:19:02');
INSERT INTO `user` VALUES (4, '2', '测试用户2', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2018-11-23 10:27:20');
INSERT INTO `user` VALUES (5, '3', '测试用户3', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2018-11-23 10:27:20');
INSERT INTO `user` VALUES (6, '4', '测试用户4', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2018-11-24 07:49:00');
INSERT INTO `user` VALUES (9, '5', '测试用户5', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2018-11-24 23:48:04');
INSERT INTO `user` VALUES (10, '6', '测试用户6', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2018-12-09 07:24:24');
INSERT INTO `user` VALUES (11, '7', '测试用户7', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2018-12-09 07:24:24');
INSERT INTO `user` VALUES (12, '8', '测试用户8', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2018-12-09 07:24:24');
INSERT INTO `user` VALUES (13, '9', '测试用户9', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2018-12-09 07:24:24');
INSERT INTO `user` VALUES (14, '10', '测试用户10', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2018-12-09 07:24:24');
INSERT INTO `user` VALUES (15, '11', '测试用户11', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2018-12-09 07:24:24');
INSERT INTO `user` VALUES (16, '12', '测试用户12', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2018-12-09 07:24:24');
INSERT INTO `user` VALUES (17, 'oeZju4hPXL3Ml7oBtBd2JdtvU0y4', 'zhb1nk', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/6ACA23C55BCC3D5B33FB6C0BE664E519.png', 1, 'China', 'Anhui', 'Bengbu', 'zh_CN', '2020-12-19 21:16:30');
INSERT INTO `user` VALUES (18, 'dgsdf', '测试用户18', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/DF7F524805A540724749F454204CCF01.png', NULL, NULL, NULL, NULL, NULL, '2020-12-15 10:29:39');
INSERT INTO `user` VALUES (19, 'sdfsdsd', '测试用户19', 'https://zhb1nk.oss-cn-beijing.aliyuncs.com/cbb/timg.jpg', NULL, NULL, NULL, NULL, NULL, '2020-12-11 10:31:31');

SET FOREIGN_KEY_CHECKS = 1;
