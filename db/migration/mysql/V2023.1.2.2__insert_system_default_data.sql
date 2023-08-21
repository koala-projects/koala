-- 默认部门
insert into k_department(id, name, parent_id, sort_index, is_systemic, created_by, created_time)
values (1, '考拉开源', null, 1, 1, 1, now());

-- 管理员角色
insert into k_role(id, code, name, sort_index, is_systemic, created_by, created_time)
values (1, 'admin', '系统管理员', 1, 1, 1, now());

-- 管理员用户
insert into k_user(id, username, password, nickname, sort_index, is_systemic, created_by, created_time)
values (1, 'admin', '{bcrypt}$2a$10$COsN1rWGQydTaNKaZ5EfFeYY0fCunvlHO4ABvqQUqQZiiK.bzglK2', '管理员', 1, 1, 1, now());

-- 管理员用户部门关系
insert into k_user_department value (1, 1);

-- 管理员用户角色关系
insert into k_user_role
values (1, 1);