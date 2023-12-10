#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

-- 示例表
DROP TABLE IF EXISTS t_example;
CREATE TABLE t_example
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`               VARCHAR(100) NOT NULL COMMENT '示例名称',
  `sort_index`         INT                   DEFAULT 0 COMMENT '排序索引',
  `is_enabled`         INT          NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_systemic`        INT          NOT NULL DEFAULT 0 COMMENT '是否系统',
  `is_deleted`         INT          NOT NULL DEFAULT 0 COMMENT '是否删除',
  `created_by`         BIGINT       NOT NULL COMMENT '创建人ID',
  `created_time`       DATETIME     NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_time` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_time`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '示例表';
