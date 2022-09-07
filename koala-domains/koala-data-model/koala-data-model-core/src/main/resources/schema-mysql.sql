DROP TABLE IF EXISTS t_metadata;
CREATE TABLE t_metadata
(
  id          VARCHAR(36) NOT NULL COMMENT '主键',
  code        VARCHAR(50) NOT NULL COMMENT '元数据代码',
  name        VARCHAR(50) NOT NULL COMMENT '元数据名称',
  description VARCHAR(500) COMMENT '元数据描述',
  PRIMARY KEY (id)
) COMMENT = '元数据表';

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

DROP TABLE IF EXISTS t_data;
CREATE TABLE t_data
(
  id          VARCHAR(36) NOT NULL COMMENT '主键',
  metadata_id VARCHAR(36) NOT NULL COMMENT '元数据id',
  PRIMARY KEY (id)
) COMMENT = '数据表';

DROP TABLE IF EXISTS t_data_element;
CREATE TABLE t_data_element
(
  id          VARCHAR(36) NOT NULL COMMENT '主键',
  code        VARCHAR(50) NOT NULL COMMENT '数据代码',
  content     VARCHAR(2000) COMMENT '数据内容',
  data_id     VARCHAR(36) NOT NULL COMMENT '数据id',
  property_id VARCHAR(36) NOT NULL COMMENT '属性id',
  PRIMARY KEY (id)
) COMMENT = '数据元表';
