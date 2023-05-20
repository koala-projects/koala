-- 默认部门
insert into k_department(id, name, parent_id, sort_index, is_systemic, created_by, created_time)
values (1, '考拉开源', null, 1, 1, 1, now());

-- 所有权限
insert into k_permission(id, code, name, type, icon, url, component, parent_id, sort_index, is_systemic, created_by,
                         created_time)
values (1, 'system', '系统管理', 1, 'ion:settings-outline', null, null, null, 1, 1, 1, now()),
       (2, 'system:user', '用户管理', 1, null, null, 'system/user/index.vue', 1, 101, 1, 1, now()),
       (3, 'system:role', '角色管理', 1, null, null, 'system/role/index.vue', 1, 102, 1, 1, now()),
       (4, 'system:permission', '权限管理', 1, null, null, 'system/permission/index.vue', 1, 103, 1, 1, now()),
       (5, 'system:department', '部门管理', 1, null, null, 'system/department/index.vue', 1, 104, 1, 1, now()),
       (6, 'system:dictionary', '字典管理', 1, null, null, 'system/dictionary/index.vue', 1, 105, 1, 1, now()),
       (7, 'system:setting', '设置管理', 1, null, null, 'system/setting/index.vue', 1, 106, 1, 1, now()),
       (8, 'system:log', '日志管理', 1, null, null, 'system/log/index.vue', 1, 107, 1, 1, now()),
       (11, 'system:user:page', '用户列表', 2, null, null, null, 2, 10101, 1, 1, now()),
       (12, 'system:user:load', '用户查询', 2, null, null, null, 2, 10102, 1, 1, now()),
       (13, 'system:user:create', '用户创建', 2, null, null, null, 2, 10103, 1, 1, now()),
       (14, 'system:user:update', '用户修改', 2, null, null, null, 2, 10104, 1, 1, now()),
       (15, 'system:user:delete', '用户删除', 2, null, null, null, 2, 10105, 1, 1, now()),
       (16, 'system:role:page', '角色列表', 2, null, null, null, 3, 10201, 1, 1, now()),
       (17, 'system:role:load', '角色查询', 2, null, null, null, 3, 10202, 1, 1, now()),
       (18, 'system:role:create', '角色创建', 2, null, null, null, 3, 10203, 1, 1, now()),
       (19, 'system:role:update', '角色修改', 2, null, null, null, 3, 10204, 1, 1, now()),
       (20, 'system:role:delete', '角色删除', 2, null, null, null, 3, 10205, 1, 1, now()),
       (21, 'system:permission:tree', '权限列表', 2, null, null, null, 4, 10301, 1, 1, now()),
       (22, 'system:department:tree', '部门列表', 2, null, null, null, 5, 10401, 1, 1, now()),
       (23, 'system:department:load', '部门查询', 2, null, null, null, 5, 10402, 1, 1, now()),
       (24, 'system:department:create', '部门创建', 2, null, null, null, 5, 10403, 1, 1, now()),
       (25, 'system:department:update', '部门修改', 2, null, null, null, 5, 10404, 1, 1, now()),
       (26, 'system:department:delete', '部门删除', 2, null, null, null, 5, 10405, 1, 1, now()),
       (27, 'system:dictionary:page', '字典列表', 2, null, null, null, 6, 10501, 1, 1, now()),
       (28, 'system:dictionary:load', '字典查询', 2, null, null, null, 6, 10502, 1, 1, now()),
       (29, 'system:dictionary:create', '字典创建', 2, null, null, null, 6, 10503, 1, 1, now()),
       (30, 'system:dictionary:update', '字典修改', 2, null, null, null, 6, 10504, 1, 1, now()),
       (31, 'system:dictionary:delete', '字典删除', 2, null, null, null, 6, 10505, 1, 1, now()),
       (32, 'system:setting:create', '设置创建', 2, null, null, null, 7, 10601, 1, 1, now()),
       (33, 'system:setting:update', '设置修改', 2, null, null, null, 7, 10602, 1, 1, now()),
       (34, 'system:setting:delete', '设置删除', 2, null, null, null, 7, 10603, 1, 1, now()),
       (35, 'system:log:page', '日志列表', 2, null, null, null, 8, 10701, 1, 1, now()),
       (36, 'system:log:load', '日志查询', 2, null, null, null, 8, 10702, 1, 1, now());

-- 管理员角色
insert into k_role(id, code, name, sort_index, is_systemic, created_by, created_time)
values (1, 'admin', '系统管理员', 1, 1, 1, now());

-- 管理员角色权限关系
insert into k_role_permission
select 1, id, 0
from k_permission;

-- 管理员用户
insert into k_user(id, username, password, nickname, sort_index, is_systemic, created_by, created_time)
values (1, 'admin', '{bcrypt}$2a$10$COsN1rWGQydTaNKaZ5EfFeYY0fCunvlHO4ABvqQUqQZiiK.bzglK2', '管理员', 1, 1, 1, now());

-- 管理员用户部门关系
insert into k_user_department value (1, 1);

-- 管理员用户角色关系
insert into k_user_role
values (1, 1);
