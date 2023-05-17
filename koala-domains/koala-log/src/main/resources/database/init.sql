# 日志表
DROP TABLE IF EXISTS k_log;
CREATE TABLE k_log
(
  `id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module`       VARCHAR(100) NOT NULL COMMENT '日志模块',
  `content`      VARCHAR(100) NOT NULL COMMENT '日志内容',
  `user_id`      BIGINT       NOT NULL COMMENT '用户id',
  `user_ip`      VARCHAR(100) NOT NULL COMMENT '用户ip',
  `is_succeeded` INT          NOT NULL COMMENT '是否成功',
  `request`      VARCHAR(2000) COMMENT '请求',
  `response`     VARCHAR(2000) COMMENT '响应',
  `error`        VARCHAR(2000) COMMENT '错误',
  `cost`         INT          NOT NULL COMMENT '消耗时间',
  `log_time`     DATETIME     NOT NULL COMMENT '日志时间',
  PRIMARY KEY (id)
) COMMENT = '日志表';
