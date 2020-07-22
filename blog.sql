/*
 Navicat MySQL Data Transfer

 Source Server         : localhost8.0.19
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 22/07/2020 15:20:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `des` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章描述',
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES (5, 'SpringBoot从入门到精通', 'admin', '一起学习SpringBoot', '<pre style=\"background-color:#2b2b2b;color:#a9b7c6;font-family:\'Consolas\';font-size:12.0pt;\"><span style=\"color:#808080;\">// </span><span style=\"color:#808080;font-family:\'宋体\';\">登录<br></span><span style=\"color:#bbb529;\">@GetMapping</span><span style=\"color:#9876aa;font-style:italic;\">(</span><span style=\"color:#6a8759;\">\"/login\"</span><span style=\"color:#9876aa;font-style:italic;\">)<br></span><span style=\"color:#cc7832;\">public </span>String <span style=\"color:#ffc66d;\">login</span><span style=\"color:#9876aa;font-style:italic;\">(</span>HttpServletRequest request<span style=\"color:#9876aa;font-style:italic;\">){<br></span><span style=\"color:#9876aa;font-style:italic;\">    </span>request.getSession<span style=\"color:#9876aa;font-style:italic;\">()</span>.removeAttribute<span style=\"color:#9876aa;font-style:italic;\">(</span><span style=\"color:#6a8759;\">\"user\"</span><span style=\"color:#9876aa;font-style:italic;\">)</span><span style=\"color:#cc7832;\">;<br></span><span style=\"color:#cc7832;\">    return </span><span style=\"color:#6a8759;\">\"login\"</span><span style=\"color:#cc7832;\">;<br></span><span style=\"color:#9876aa;font-style:italic;\">}<br></span><span style=\"color:#9876aa;font-style:italic;\"><br></span><span style=\"color:#808080;\">// </span><span style=\"color:#808080;font-family:\'宋体\';\">注册<br></span><span style=\"color:#bbb529;\">@GetMapping</span><span style=\"color:#9876aa;font-style:italic;\">(</span><span style=\"color:#6a8759;\">\"/register\"</span><span style=\"color:#9876aa;font-style:italic;\">)<br></span><span style=\"color:#cc7832;\">public </span>String <span style=\"color:#ffc66d;\">register</span><span style=\"color:#9876aa;font-style:italic;\">(</span>HttpServletRequest request<span style=\"color:#9876aa;font-style:italic;\">){<br></span><span style=\"color:#9876aa;font-style:italic;\">    </span>request.getSession<span style=\"color:#9876aa;font-style:italic;\">()</span>.removeAttribute<span style=\"color:#9876aa;font-style:italic;\">(</span><span style=\"color:#6a8759;\">\"user\"</span><span style=\"color:#9876aa;font-style:italic;\">)</span><span style=\"color:#cc7832;\">;<br></span><span style=\"color:#cc7832;\">    return </span><span style=\"color:#6a8759;\">\"register\"</span><span style=\"color:#cc7832;\">;<br></span><span style=\"color:#9876aa;font-style:italic;\">}</span></pre>', '2020-07-22 11:35:20');

-- ----------------------------
-- Table structure for tb_article_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_category`;
CREATE TABLE `tb_article_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `article_id` bigint(0) NOT NULL COMMENT '文章ID',
  `category_id` bigint(0) NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章&&分类关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_article_category
-- ----------------------------
INSERT INTO `tb_article_category` VALUES (8, 5, 7);

-- ----------------------------
-- Table structure for tb_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_tag`;
CREATE TABLE `tb_article_tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `article_id` bigint(0) NOT NULL COMMENT '文章ID',
  `tag_id` bigint(0) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章&&标签关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_article_tag
-- ----------------------------
INSERT INTO `tb_article_tag` VALUES (8, 5, 8);
INSERT INTO `tb_article_tag` VALUES (9, 5, 9);
INSERT INTO `tb_article_tag` VALUES (10, 5, 7);
INSERT INTO `tb_article_tag` VALUES (11, 5, 5);

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (7, 'JAVA');
INSERT INTO `tb_category` VALUES (8, 'linux');
INSERT INTO `tb_category` VALUES (9, '分布式');
INSERT INTO `tb_category` VALUES (10, '微服务');

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `article_id` bigint(0) NULL DEFAULT NULL COMMENT '文章ID',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '给谁留言',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '留言内容',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `device` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '留言时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES (7, 5, 'acc', '哈哈哈哈哈', 'acc@126.com', '127.0.0.1', 'Firefox 7,Windows 10', '内网IP|0|0|内网IP|内网IP', '2020-07-22 12:31:46');

-- ----------------------------
-- Table structure for tb_link
-- ----------------------------
DROP TABLE IF EXISTS `tb_link`;
CREATE TABLE `tb_link`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '连接名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '连接URL',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '友链表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_link
-- ----------------------------
INSERT INTO `tb_link` VALUES (1, 'Blog', 'http://localhost:8081');
INSERT INTO `tb_link` VALUES (2, 'Github', 'https://github.com/Lon9z');
INSERT INTO `tb_link` VALUES (3, '我的邮箱', 'https://126.com');

-- ----------------------------
-- Table structure for tb_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `operation` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作描述',
  `time` bigint(0) NULL DEFAULT NULL COMMENT '耗时(毫秒)',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作方法',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作参数',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `location` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作地点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_log
-- ----------------------------
INSERT INTO `tb_log` VALUES (87, 'tycoding', '新增文章', 1, 'cn.tycoding.biz.controller.ArticleController.add()', ' sysArticle\"SysArticle(id=14, title=1, author=tycoding, des=1, content=1, create...', '127.0.0.1', '2020-06-28 23:07:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (88, 'tycoding', '更新文章', 1, 'cn.tycoding.biz.controller.ArticleController.update()', ' sysArticle\"SysArticle(id=14, title=123, author=tycoding, des=1, content=1, crea...', '127.0.0.1', '2020-06-28 23:07:55', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (94, 'admin', '新增文章', 0, 'com.lyzzz.blog.controller.ArticleController.add()', ' article\"Article(id=4, title=aaa, author=admin, des=aaa, content=aaa, createTime...', '127.0.0.1', '2020-07-22 09:00:00', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (95, 'admin', '修改文章', 0, 'com.lyzzz.blog.controller.ArticleController.update()', ' article\"Article(id=4, title=西红柿, author=admin, des=我爱吃西红柿<br>, content=您呢<br>, ...', '127.0.0.1', '2020-07-22 09:30:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (96, 'admin', '删除文章', 0, 'com.lyzzz.blog.controller.ArticleController.delete()', NULL, '127.0.0.1', '2020-07-22 09:30:29', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (97, 'admin', '删除分类', 0, 'com.lyzzz.blog.controller.CategoryController.delete()', NULL, '127.0.0.1', '2020-07-22 10:59:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (98, 'admin', '新增分类', 0, 'com.lyzzz.blog.controller.CategoryController.save()', NULL, '127.0.0.1', '2020-07-22 10:59:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (99, 'admin', '新增分类', 0, 'com.lyzzz.blog.controller.CategoryController.save()', NULL, '127.0.0.1', '2020-07-22 10:59:40', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (100, 'admin', '删除文章', 0, 'com.lyzzz.blog.controller.ArticleController.delete()', NULL, '127.0.0.1', '2020-07-22 11:25:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (101, 'admin', '删除文章', 0, 'com.lyzzz.blog.controller.ArticleController.delete()', NULL, '127.0.0.1', '2020-07-22 11:26:35', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (102, 'admin', '删除文章', 0, 'com.lyzzz.blog.controller.ArticleController.delete()', NULL, '127.0.0.1', '2020-07-22 11:26:37', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (103, 'admin', '删除标签', 0, 'com.lyzzz.blog.controller.TagController.delete()', NULL, '127.0.0.1', '2020-07-22 11:26:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (104, 'admin', '删除标签', 0, 'com.lyzzz.blog.controller.TagController.delete()', NULL, '127.0.0.1', '2020-07-22 11:27:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (105, 'admin', '删除分类', 0, 'com.lyzzz.blog.controller.CategoryController.delete()', NULL, '127.0.0.1', '2020-07-22 11:27:12', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (106, 'admin', '删除分类', 0, 'com.lyzzz.blog.controller.CategoryController.delete()', NULL, '127.0.0.1', '2020-07-22 11:27:14', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (107, 'admin', '删除分类', 0, 'com.lyzzz.blog.controller.CategoryController.delete()', NULL, '127.0.0.1', '2020-07-22 11:27:17', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (108, 'admin', '删除分类', 0, 'com.lyzzz.blog.controller.CategoryController.delete()', NULL, '127.0.0.1', '2020-07-22 11:27:19', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (109, 'admin', '删除分类', 0, 'com.lyzzz.blog.controller.CategoryController.delete()', NULL, '127.0.0.1', '2020-07-22 11:27:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (110, 'admin', '删除评论', 0, 'com.lyzzz.blog.controller.CommentController.delete()', NULL, '127.0.0.1', '2020-07-22 11:28:07', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (111, 'admin', '删除评论', 0, 'com.lyzzz.blog.controller.CommentController.delete()', NULL, '127.0.0.1', '2020-07-22 11:28:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (112, 'admin', '删除评论', 0, 'com.lyzzz.blog.controller.CommentController.delete()', NULL, '127.0.0.1', '2020-07-22 11:28:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (113, 'admin', '删除评论', 0, 'com.lyzzz.blog.controller.CommentController.delete()', NULL, '127.0.0.1', '2020-07-22 11:28:13', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (114, 'admin', '删除评论', 0, 'com.lyzzz.blog.controller.CommentController.delete()', NULL, '127.0.0.1', '2020-07-22 11:28:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (115, 'admin', '新增分类', 0, 'com.lyzzz.blog.controller.CategoryController.save()', NULL, '127.0.0.1', '2020-07-22 11:28:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (116, 'admin', '新增分类', 0, 'com.lyzzz.blog.controller.CategoryController.save()', NULL, '127.0.0.1', '2020-07-22 11:29:05', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (117, 'admin', '新增分类', 0, 'com.lyzzz.blog.controller.CategoryController.save()', NULL, '127.0.0.1', '2020-07-22 11:29:17', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (118, 'admin', '新增分类', 0, 'com.lyzzz.blog.controller.CategoryController.save()', NULL, '127.0.0.1', '2020-07-22 11:29:32', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (119, 'admin', '新增友链', 0, 'com.lyzzz.blog.controller.LinkController.save()', NULL, '127.0.0.1', '2020-07-22 11:31:32', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (120, 'admin', '新增标签', 0, 'com.lyzzz.blog.controller.TagController.add()', NULL, '127.0.0.1', '2020-07-22 11:32:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (121, 'admin', '新增标签', 0, 'com.lyzzz.blog.controller.TagController.add()', NULL, '127.0.0.1', '2020-07-22 11:32:24', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (122, 'admin', '新增标签', 0, 'com.lyzzz.blog.controller.TagController.add()', NULL, '127.0.0.1', '2020-07-22 11:32:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (123, 'admin', '新增标签', 0, 'com.lyzzz.blog.controller.TagController.add()', NULL, '127.0.0.1', '2020-07-22 11:32:40', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (124, 'admin', '新增标签', 0, 'com.lyzzz.blog.controller.TagController.add()', NULL, '127.0.0.1', '2020-07-22 11:32:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (125, 'admin', '新增标签', 0, 'com.lyzzz.blog.controller.TagController.add()', NULL, '127.0.0.1', '2020-07-22 11:33:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (126, 'admin', '新增标签', 0, 'com.lyzzz.blog.controller.TagController.add()', NULL, '127.0.0.1', '2020-07-22 11:33:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (127, 'admin', '新增标签', 0, 'com.lyzzz.blog.controller.TagController.add()', NULL, '127.0.0.1', '2020-07-22 11:33:20', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (128, 'admin', '新增文章', 0, 'com.lyzzz.blog.controller.ArticleController.add()', ' article\"Article(id=5, title=SpringBoot从入门到精通, author=admin, des=一起学习SpringBoot,...', '127.0.0.1', '2020-07-22 11:35:20', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (129, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:05', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (130, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (131, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:10', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (132, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:12', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (133, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (134, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (135, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:24', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (136, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:26', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (137, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:28', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (138, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (139, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:34', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (140, 'admin', '删除登录日志', 1, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:38', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (141, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (142, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (143, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:46', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (144, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:52', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (145, 'admin', '删除登录日志', 1, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:34:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (146, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (147, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:04', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (148, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:07', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (149, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (150, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (151, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:28', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (152, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:30', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (153, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:32', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (154, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:34', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (155, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:37', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (156, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:39', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (157, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (158, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (159, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (160, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:51', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (161, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:53', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (162, 'admin', '删除登录日志', 0, 'com.lyzzz.blog.controller.LoginLogController.delete()', NULL, '127.0.0.1', '2020-07-22 12:35:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (169, 'admin', '修改密码', 0, 'com.lyzzz.blog.controller.UserController.changePass()', ' user\"User(id=5, username=null, password=6bc7fe83ee1614cbb5d75235680679ef, avata...', '127.0.0.1', '2020-07-22 13:06:27', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (170, 'admin', '/更新用户信息', 0, 'com.lyzzz.blog.controller.UserController.update()', ' user\"User(id=5, username=null, password=null, avatar=/img/avatar/20180414165840...', '127.0.0.1', '2020-07-22 14:28:14', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (171, 'admin', '/更新用户信息', 0, 'com.lyzzz.blog.controller.UserController.update()', ' user\"User(id=5, username=admin, password=null, avatar=/img/avatar/default.jpg, ...', '127.0.0.1', '2020-07-22 14:28:43', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (172, 'admin', '修改密码', 0, 'com.lyzzz.blog.controller.UserController.changePass()', ' user\"User(id=5, username=null, password=750d7861e13f72940ca7a4f0c71354e6, avata...', '127.0.0.1', '2020-07-22 14:29:40', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (173, 'admin', '/更新用户信息', 0, 'com.lyzzz.blog.controller.UserController.update()', ' user\"User(id=5, username=null, password=null, avatar=/img/avatar/20180414165840...', '127.0.0.1', '2020-07-22 14:30:05', '内网IP|0|0|内网IP|内网IP');

-- ----------------------------
-- Table structure for tb_login_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_login_log`;
CREATE TABLE `tb_login_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录地点',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录设备',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 145 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_login_log
-- ----------------------------
INSERT INTO `tb_login_log` VALUES (146, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-07-22 12:56:41', 'Firefox 7--Windows 10');
INSERT INTO `tb_login_log` VALUES (147, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-07-22 12:58:20', 'Firefox 7--Windows 10');
INSERT INTO `tb_login_log` VALUES (148, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-07-22 13:06:07', 'Firefox 7--Windows 10');
INSERT INTO `tb_login_log` VALUES (149, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-07-22 13:06:34', 'Firefox 7--Windows 10');
INSERT INTO `tb_login_log` VALUES (150, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-07-22 14:00:28', 'Firefox 7--Windows 10');
INSERT INTO `tb_login_log` VALUES (151, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-07-22 14:15:36', 'Firefox 7--Windows 10');
INSERT INTO `tb_login_log` VALUES (152, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-07-22 14:26:32', 'Firefox 7--Windows 10');
INSERT INTO `tb_login_log` VALUES (153, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-07-22 14:28:48', 'Firefox 7--Windows 10');
INSERT INTO `tb_login_log` VALUES (154, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-07-22 14:29:48', 'Firefox 7--Windows 10');
INSERT INTO `tb_login_log` VALUES (155, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-07-22 14:30:24', 'Firefox 7--Windows 10');
INSERT INTO `tb_login_log` VALUES (156, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-07-22 14:49:45', 'Chrome 8--Windows 10');

-- ----------------------------
-- Table structure for tb_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE `tb_tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_tag
-- ----------------------------
INSERT INTO `tb_tag` VALUES (5, 'Spring');
INSERT INTO `tb_tag` VALUES (6, 'SpringCloud');
INSERT INTO `tb_tag` VALUES (7, 'SpringMVC');
INSERT INTO `tb_tag` VALUES (8, 'SpringBoot');
INSERT INTO `tb_tag` VALUES (9, 'Mabatis-Plus');
INSERT INTO `tb_tag` VALUES (10, 'SSM');
INSERT INTO `tb_tag` VALUES (11, 'Dubbo');
INSERT INTO `tb_tag` VALUES (12, 'Java');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `des` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'tycoding', '5a2e641ab5104fe4dd7814b148f16bac', '/img/avatar/20180414165815.jpg', 'tytumo@163.com', '兴趣使然的Coder', '2020-06-27 16:55:05');
INSERT INTO `tb_user` VALUES (2, 'test', 'd256d670b614b1f54cecfb3874c025f1', '/img/avatar/20180414165815.jpg', NULL, NULL, '2020-06-26 16:55:08');
INSERT INTO `tb_user` VALUES (3, 'test2', 'd256d670b614b1f54cecfb3874c025f1', '/img/avatar/20180414165815.jpg', '12tycoding@11.com', NULL, '2020-06-27 16:55:13');
INSERT INTO `tb_user` VALUES (4, '123', 'd256d670b614b1f54cecfb3874c025f1', '/img/avatar/20180414165815.jpg', '123@11.com', NULL, '2020-06-27 16:55:13');
INSERT INTO `tb_user` VALUES (5, 'admin', '750d7861e13f72940ca7a4f0c71354e6', '/img/avatar/20180414165840.jpg', 'lyzzz30@126.com', '攻城狮', '2020-07-20 11:19:26');

SET FOREIGN_KEY_CHECKS = 1;
