-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--@author Decap
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '课程名称',
  `score` int DEFAULT NULL COMMENT '学分',
  `times` varchar(50) DEFAULT NULL COMMENT '上课时间',
  `state` tinyint(1) DEFAULT NULL COMMENT '是否开课',
  `teacher_id` int DEFAULT NULL COMMENT '授课老师id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'SpringCloud',4,'40',0,2),(2,'SpringSecurity',4,'40',NULL,12),(3,'JPA',4,'40',NULL,12),(4,'RabbitMQ',4,'40',NULL,2),(5,'JVM',4,'40',NULL,6);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict`
--

DROP TABLE IF EXISTS `dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dict` (
  `name` varchar(25) DEFAULT NULL COMMENT '名称',
  `value` varchar(25) DEFAULT NULL COMMENT '内容',
  `type` varchar(25) DEFAULT NULL COMMENT '类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict`
--

LOCK TABLES `dict` WRITE;
/*!40000 ALTER TABLE `dict` DISABLE KEYS */;
INSERT INTO `dict` VALUES ('user','el-icon-user','icon'),('house','el-icon-house','icon'),('s-management','el-icon-s-management','icon'),('s-custorm','el-icon-s-custom','icon'),('document','el-icon-document','icon'),('menu','el-icon-menu','icon'),('data-line','el-icon-data-line','icon');
/*!40000 ALTER TABLE `dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) DEFAULT NULL COMMENT '文件类型',
  `size` bigint DEFAULT NULL COMMENT '文件大小(kb)',
  `url` varchar(255) DEFAULT NULL COMMENT '下载链接',
  `md5` varchar(255) DEFAULT NULL COMMENT '文件md5',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否禁用链接',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_md5` (`md5`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (39,'springclould.webp','webp',31,'http://localhost:8888/file/1ad29f5893514bf3be9e6df8af0e8185.webp','34d3eaa1ed007e9ed8b8d2eef3079c93',1,0),(40,'springclouddocker.webp','webp',30,'http://localhost:8888/file/ed77c5057fdf47e7a95c5285272601d6.webp','9832881430ba9836ce8919713cc8bc3f',1,0),(41,'JVM.webp','webp',37,'http://localhost:8888/file/20f205d3fc074acaaf0b5db5e3d36855.webp','d6a51a0e5f68f1038c4da8286fe06e55',1,0),(42,'python深度学习.webp','webp',18,'http://localhost:8888/file/af30cbda080d473cb90d1da77c17a857.webp','fd74cebcbd642e1871eccc08741689b3',1,0),(43,'python数据爬虫.webp','webp',78,'http://localhost:8888/file/da9e33fe779f45c4b3746f0b9b3290cf.webp','452144fb14aada716e748c31c0e6393b',1,0),(44,'python数据分析.webp','webp',32,'http://localhost:8888/file/7b4002e78a1f477c990bedc640fab041.webp','a64be953f612c496ab05c2675ccff55d',1,0);
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(25) DEFAULT NULL COMMENT '名称',
  `path` varchar(50) DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `pid` int DEFAULT NULL COMMENT '父级id',
  `page_path` varchar(255) DEFAULT NULL COMMENT '页面路径',
  `sort_num` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'主页','/home','el-icon-house','主页',NULL,'Home','0'),(2,'数据报表','/dashbord','el-icon-data-line','数据报表',NULL,'Dashbord','100'),(3,'系统管理','','el-icon-menu','系统管理',NULL,NULL,'200'),(4,'用户管理','/user','el-icon-user','用户管理',3,'User','201'),(5,'角色管理','/role','el-icon-s-custom','角色管理',3,'Role','202'),(6,'菜单管理','/menu','el-icon-s-management','菜单管理',3,'Menu','203'),(7,'文件管理','/file','el-icon-document','文件管理',3,'File','204'),(8,'课程管理','/course','el-icon-s-management','课程管理',NULL,'Course','101');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `flag` varchar(255) DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Decap','管理员','ROLE_ADMIN'),(7,'学生','学生','ROLE_STUDENT'),(8,'老师','老师','ROLE_TEACHER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_menu` (
  `role_id` int NOT NULL COMMENT '角色id',
  `menu_id` int NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`menu_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8);
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_course` (
  `student_id` int NOT NULL COMMENT '学生id',
  `course_id` int NOT NULL COMMENT '课程id',
  PRIMARY KEY (`course_id`,`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生课程关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES (1,1),(1,2),(7,2);
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `telephone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `integration` varchar(255) DEFAULT NULL,
  `role` varchar(60) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'王小美','123','王小美','wangxiaomei','提瓦特大陆璃月','2022-04-18 08:01:18','https://fastly.jsdelivr.net/gh/AXDecap/imagehost/img/IMG_20220517_203412.jpg','wxm123','王小美,wangxiaomei,wxm123,提瓦特大陆璃月','ROLE_STUDENT'),(2,'莫娜','123','莫娜','mona','提瓦特大陆蒙德','2022-04-19 03:00:59','monaurl','mn123','莫娜,mona,mn123,提瓦特大陆蒙德','ROLE_TEACHER'),(3,'刻晴','123','刻晴','keqing','提瓦特大陆璃月','2022-04-19 03:27:07','keqingurl','kq123','刻晴,keqing,kq123,提瓦特大陆璃月',NULL),(4,'神里绫华','123','神里绫华','shenlilinghua','提瓦特大陆稻妻','2022-04-20 01:05:35','shenlilinghuaurl','sllh123','神里绫华,shenlilinghua,sllh123,提瓦特大陆稻妻',NULL),(5,'凝光','123','凝光','ningguang','提瓦特大陆璃月','2022-04-21 16:09:48','ningguangurl','ng123','凝光,ningguang,ng123,提瓦特大陆璃月',NULL),(6,'罗莎莉亚','123','罗莎莉亚','luoshaliya','提瓦特大陆蒙德','2022-04-21 16:10:01','luoshaliyaurl','lsly123','罗莎莉亚,luoshaliya,lsly123,提瓦特大陆蒙德','ROLE_TEACHER'),(7,'可莉','123','可莉','keli','提瓦特大陆蒙德','2022-04-21 16:13:26','keliurl','keli123','可莉,keli,keli123,提瓦特大陆蒙德','ROLE_STUDENT'),(9,'尤拉','123','尤拉','youla','提瓦特大陆蒙德','2022-04-21 16:15:23','youlaurl','youla123','尤拉,youla,youla123,提瓦特大陆蒙德',NULL),(10,'安伯','123','安伯','anbo','提瓦特大陆蒙德','2022-04-21 16:15:23','anbourl','anbo123','安伯,anbo,anbo123,提瓦特大陆蒙德',NULL),(11,'芭芭拉','123','芭芭拉','babala','提瓦特大陆蒙德','2022-04-21 16:16:10','babalaurl','babala123','芭芭拉,babala,babala123,提瓦特大陆蒙德',NULL),(12,'钟离','123','钟离','zhongli','提瓦特大陆璃月','2022-04-27 00:45:36','zhongliurl','zhongli123','钟离,zhongli,zhongli123,提瓦特大陆璃月','ROLE_TEACHER'),(14,'魈','123','魈','xiao','提瓦特大陆璃月','2022-05-02 08:35:56','xiaourl','xiao123','魈,xiao,xiao123,提瓦特大陆璃月',NULL),(15,'admin','123','管理员Decap','admin','地球','2022-05-10 16:18:28','https://img-blog.csdnimg.cn/ed4c96e20156458c992b916cfffd6866.png','admin123','admin,admin,admin123,地球','ROLE_ADMIN'),(181,'夜澜','123','夜澜','yelan','提瓦特大陆稻妻','2022-05-16 08:58:29',NULL,'yelan123','夜澜',NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-10 19:43:54
