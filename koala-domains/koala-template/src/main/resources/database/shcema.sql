DROP TABLE IF EXISTS t_template;
CREATE TABLE t_template
(
  id        VARCHAR(36) NOT NULL COMMENT '模板主键',
  name      VARCHAR(20) NOT NULL COMMENT '模板名称',
  content   TEXT        NOT NULL COMMENT '模板内容',
  group_id  VARCHAR(36) COMMENT '模板组ID',
  is_system INT         NOT NULL DEFAULT 0 COMMENT '是否系统',
  is_delete INT         NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (id)
) COMMENT = '模板表';

DROP TABLE IF EXISTS t_template_group;
CREATE TABLE t_template_group
(
  id          VARCHAR(36) NOT NULL COMMENT '主键',
  name        VARCHAR(20) NOT NULL COMMENT '模板组名称',
  description VARCHAR(500) COMMENT '模板组描述',
  is_system   INT         NOT NULL DEFAULT 0 COMMENT '是否系统',
  is_delete   INT         NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (id)
) COMMENT = '模板组表';
