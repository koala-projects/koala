DROP TABLE IF EXISTS t_attachment;
CREATE TABLE t_attachment
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `original_filename`  VARCHAR(100) NOT NULL COMMENT '原始文件名',
  `content_type`       VARCHAR(100) NOT NULL COMMENT '文件类型',
  `size`               INT          NOT NULL COMMENT '文件大小(KB)',
  `storage_path`       VARCHAR(500) NOT NULL COMMENT '存储路径',
  `created_by`         BIGINT       NOT NULL COMMENT '创建人ID',
  `created_time`       DATETIME     NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_time` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_time`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '附件表';
