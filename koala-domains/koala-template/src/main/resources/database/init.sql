DROP TABLE IF EXISTS t_template_group;
CREATE TABLE t_template_group
(
  `id`        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`      VARCHAR(100) NOT NULL COMMENT '模板组名称',
  `remark`    VARCHAR(500) COMMENT '模板组备注',
  `is_enable` INT          NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_system` INT          NOT NULL DEFAULT 0 COMMENT '是否系统',
  `is_delete` INT          NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (id)
) COMMENT = '模板组表';

insert into t_template_group(name, remark)
values ('任务消息通知模板', '用于通知任务的执行情况');

DROP TABLE IF EXISTS t_template;
CREATE TABLE t_template
(
  `id`        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`      VARCHAR(100) NOT NULL COMMENT '模板名称',
  `content`   TEXT         NOT NULL COMMENT '模板内容',
  `remark`    VARCHAR(500) COMMENT '模板备注',
  `group_id`  BIGINT COMMENT '模板组id',
  `is_enable` INT          NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_system` INT          NOT NULL DEFAULT 0 COMMENT '是否系统',
  `is_delete` INT          NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (id)
) COMMENT = '模板表';


insert into t_template(name, remark, content, group_id)
values ('开始执行', '任务开始执行通知', '任务[id=#(id)]开始', 1),
       ('执行成功', '任务执行成功通知', '任务[id=#(id)]执行成功', 1),
       ('执行失败', '任务执行失败通知', '任务[id=#(id)]执行失败, 原因为: #(message)', 1);
