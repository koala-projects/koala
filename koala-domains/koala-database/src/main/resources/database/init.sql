DROP TABLE IF EXISTS t_database;
CREATE TABLE t_database
(
  `id`        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`      VARCHAR(100) NOT NULL COMMENT '数据库名称',
  `url`       VARCHAR(500) NOT NULL COMMENT '数据库连接',
  `username`  VARCHAR(100) NOT NULL COMMENT '数据库用户名',
  `password`  VARCHAR(100) NOT NULL COMMENT '数据库密码',
  `catalog`   VARCHAR(100) COMMENT '数据库目录',
  `schema`    VARCHAR(100) COMMENT '数据库模式',
  `is_enable` INT          NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_system` INT          NOT NULL DEFAULT 0 COMMENT '是否系统',
  `is_delete` INT          NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (id)
) COMMENT = '数据库';

insert into t_database(name, url, username, password, catalog, `schema`)
values ('本地MySQL测试库', 'jdbc:mysql://localhost:3306/test', 'test', 'test', 'test', 'test')