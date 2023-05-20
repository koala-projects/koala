-- 演示字典
insert into k_dict(id, code, name, sort_index, is_systemic, created_by, created_time)
values (1, 'gender', '性别', 1, 1, 1, now()),
       (2, 'camp', '阵营', 2, 0, 1, now());

-- 演示字典项
insert into k_dict_item(id, code, name, dictionary_id, sort_index, is_systemic, created_by, created_time)
values (1, 'man', '男', 1, 101, 1, 1, now()),
       (2, 'woman', '女', 1, 102, 1, 1, now()),
       (3, 'tribe', '部落', 2, 201, 0, 1, now()),
       (4, 'alliance', '联盟', 2, 202, 0, 1, now());

-- 演示部门
insert into k_department(id, name, parent_id, sort_index, is_systemic, created_by, created_time)
values (2, '研发部', 1, 101, 1, 1, now()),
       (3, '回家部', 1, 102, 0, 1, now());

-- 演示角色
insert into k_role(id, code, name, sort_index, is_systemic, created_by, created_time)
values (2, 'visitor', '访问者', 2, 0, 1, now());

-- 演示用户
insert into k_user(id, username, password, nickname, sort_index, is_systemic, created_by, created_time)
values (2, 'visitor', '{bcrypt}$2a$10$JaBR557MI9SM5jgvcYwKiOPXyecHOqDQtN7pSO9xasp5NNjGS8jmG', '访问者', 2, 0, 1, now());

-- 演示模板组
insert into t_template_group(id, name, remark)
values (2, '任务消息通知模板', '用于通知任务的执行情况');

-- 演示模板
insert into t_template(id, name, remark, content, group_id)
values (7, '开始执行', '任务开始执行通知', '任务[id=#(id)]开始', 2),
       (8, '执行成功', '任务执行成功通知', '任务[id=#(id)]执行成功', 2),
       (9, '执行失败', '任务执行失败通知', '任务[id=#(id)]执行失败, 原因为: #(message)', 2);