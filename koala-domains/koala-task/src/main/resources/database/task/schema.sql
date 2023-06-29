DROP TABLE IF EXISTS k_task;
CREATE TABLE k_task
(
  `id`                 BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`               VARCHAR(100)  NOT NULL COMMENT '任务名称',
  `remark`             VARCHAR(500) COMMENT '任务备注',
  `task_config`        VARCHAR(2000) NOT NULL COMMENT '任务配置',
  `trigger_config`     VARCHAR(2000) NOT NULL COMMENT '触发配置',
  `sort_index`         INT           NOT NULL DEFAULT 0 COMMENT '排序索引',
  `is_enabled`         INT           NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_systemic`        INT           NOT NULL DEFAULT 0 COMMENT '是否系统',
  `is_deleted`         INT           NOT NULL DEFAULT 0 COMMENT '是否删除',
  `created_by`         BIGINT        NOT NULL COMMENT '创建人ID',
  `created_time`       DATETIME      NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_time` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_time`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '任务表';

DROP TABLE IF EXISTS k_task_log;
CREATE TABLE k_task_log
(
  `id`          BIGINT   NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id`     BIGINT   NOT NULL COMMENT '任务id',
  `execution`   INT      NOT NULL COMMENT '执行类型',
  `task_status` INT      NOT NULL COMMENT '任务状态',
  `task_error`  TEXT COMMENT '错误信息',
  `start_time`  DATETIME NOT NULL COMMENT '开始时间',
  `end_time`    DATETIME COMMENT '结束时间',
  PRIMARY KEY (id)
) COMMENT = '任务日志表';
