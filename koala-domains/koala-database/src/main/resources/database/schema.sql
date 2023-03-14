DROP TABLE IF EXISTS t_database;
CREATE TABLE t_database
(
  `id`       BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`     VARCHAR(100) NOT NULL COMMENT '数据库名称',
  `url`      VARCHAR(500) NOT NULL COMMENT '数据库连接',
  `username` VARCHAR(100) NOT NULL COMMENT '数据库用户名',
  `password` VARCHAR(100) NOT NULL COMMENT '数据库密码',
  `catalog`  VARCHAR(100) COMMENT '数据库目录',
  `schema`   VARCHAR(100) COMMENT '数据库模式',
  PRIMARY KEY (id)
) COMMENT = '数据库';
