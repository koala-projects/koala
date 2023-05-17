# 模板组表
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

# 模板表
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
