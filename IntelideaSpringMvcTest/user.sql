CREATE TABLE 'user' (
  'id' int(11) NOT NULL AUTO_INCREMENT,
  'username' varchar(32) NOT NULL COMMENT '用户名称',
  'birthday' date DEFAULT NULL COMMENT '生日',
  'sex' char(1) DEFAULT NULL COMMENT '性别',
  'address' varchar(256) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY ('id')
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

insert  into `user`(`id`,`username`,`birthday`,`sex`,`address`) values (1,'王五',NULL,'2',NULL),(10,'张三','2014-07-10','1','北京市'),(16,'张小明',NULL,'1','河南郑州'),(22,'陈小明',NULL,'1','河南郑州'),(24,'张三丰',NULL,'1','河南郑州'),(25,'陈小明',NULL,'1','河南郑州'),(26,'王五',NULL,NULL,NULL);