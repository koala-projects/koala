-- 数据库表
CREATE TABLE t_database
(
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`        VARCHAR(100) NOT NULL COMMENT '数据库名称',
  `url`         VARCHAR(500) NOT NULL COMMENT '数据库连接',
  `username`    VARCHAR(100) NOT NULL COMMENT '数据库用户名',
  `password`    VARCHAR(100) NOT NULL COMMENT '数据库密码',
  `catalog`     VARCHAR(100) COMMENT '数据库目录',
  `schema`      VARCHAR(100) COMMENT '数据库模式',
  `is_enabled`  INT          NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_systemic` INT          NOT NULL DEFAULT 0 COMMENT '是否系统',
  `is_deleted`  INT          NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (id)
) COMMENT = '数据库表';

-- 模板组表
DROP TABLE IF EXISTS t_template_group;
CREATE TABLE t_template_group
(
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`        VARCHAR(100) NOT NULL COMMENT '模板组名称',
  `remark`      VARCHAR(500) COMMENT '模板组备注',
  `is_enabled`  INT          NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_systemic` INT          NOT NULL DEFAULT 0 COMMENT '是否系统',
  `is_deleted`  INT          NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (id)
) COMMENT = '模板组表';

-- 模板表
DROP TABLE IF EXISTS t_template;
CREATE TABLE t_template
(
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`        VARCHAR(100) NOT NULL COMMENT '模板名称',
  `content`     TEXT         NOT NULL COMMENT '模板内容',
  `remark`      VARCHAR(500) COMMENT '模板备注',
  `group_id`    BIGINT COMMENT '模板组id',
  `is_enabled`  INT          NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_systemic` INT          NOT NULL DEFAULT 0 COMMENT '是否系统',
  `is_deleted`  INT          NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (id)
) COMMENT = '模板表';

