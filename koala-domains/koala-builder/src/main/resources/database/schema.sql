DROP TABLE IF EXISTS t_code_template_group;
CREATE TABLE t_code_template_group
(
  id                  VARCHAR(36) NOT NULL COMMENT '主键',
  name                VARCHAR(20) NOT NULL COMMENT '代码模板组名称',
  description         VARCHAR(500) COMMENT '代码模板组描述',
  domain_converter_id VARCHAR(50) NOT NULL COMMENT '领域转换器ID',
  is_system           INT         NOT NULL DEFAULT 0 COMMENT '是否系统',
  is_delete           INT         NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (id)
) COMMENT = '代码模板组';

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

DROP TABLE IF EXISTS t_property;
CREATE TABLE t_property
(
  id          VARCHAR(36) NOT NULL COMMENT '主键',
  code        VARCHAR(50) NOT NULL COMMENT '属性代码',
  name        VARCHAR(50) NOT NULL COMMENT '属性名称',
  description VARCHAR(500) COMMENT '属性描述',
  type        INT         NOT NULL COMMENT '属性类型',
  metadata_id VARCHAR(36) NOT NULL COMMENT '元数据id',
  PRIMARY KEY (id)
) COMMENT = '属性表';
