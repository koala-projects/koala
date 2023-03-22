# 设置表
DROP TABLE IF EXISTS sys_setting;
CREATE TABLE sys_setting
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code`               VARCHAR(100) NOT NULL COMMENT '设置代码',
  `name`               VARCHAR(100) NOT NULL COMMENT '设置名称',
  `content`            VARCHAR(100) NOT NULL COMMENT '设置内容',
  `remark`             VARCHAR(500) COMMENT '设置备注',
  `sort_index`         INT          NOT NULL DEFAULT 0 COMMENT '排序索引',
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
) COMMENT = '系统设置表';

insert into sys_setting(code, name, content, created_by, created_time)
values ('system.default-password', '初始用户默认密码', '123456', 1, now());

# 字典表
DROP TABLE IF EXISTS sys_dict;
CREATE TABLE sys_dict
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code`               VARCHAR(100) NOT NULL COMMENT '字典代码',
  `name`               VARCHAR(100) NOT NULL COMMENT '字典名称',
  `remark`             VARCHAR(500) COMMENT '字典备注',
  `sort_index`         INT          NOT NULL DEFAULT 0 COMMENT '排序索引',
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
) COMMENT = '系统字典表';


insert into sys_dict(code, name, sort_index, is_systemic, created_by, created_time)
values ('gender', '性别', 1, 1, 1, now()),
       ('camp', '阵营', 2, 0, 1, now());

# 字典项表
DROP TABLE IF EXISTS sys_dict_item;
CREATE TABLE sys_dict_item
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code`               VARCHAR(100) NOT NULL COMMENT '字典项代码',
  `name`               VARCHAR(100) NOT NULL COMMENT '字典项名称',
  `remark`             VARCHAR(500) COMMENT '字典项备注',
  `dictionary_id`      BIGINT       NOT NULL COMMENT '字典id',
  `sort_index`         INT          NOT NULL DEFAULT 0 COMMENT '排序索引',
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
) COMMENT = '系统字典项表';

insert into sys_dict_item(code, name, dictionary_id, sort_index, is_systemic, created_by, created_time)
values ('man', '男', 1, 101, 1, 1, now()),
       ('woman', '女', 1, 102, 1, 1, now()),
       ('tribe', '部落', 2, 201, 0, 1, now()),
       ('alliance', '联盟', 2, 202, 0, 1, now());

# 部门表
DROP TABLE IF EXISTS sys_department;
CREATE TABLE sys_department
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`               VARCHAR(100) NOT NULL COMMENT '部门名称',
  `remark`             VARCHAR(500) COMMENT '部门备注',
  `parent_id`          BIGINT COMMENT '上级部门id',
  `sort_index`         INT          NOT NULL DEFAULT 0 COMMENT '排序索引',
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
) COMMENT = '系统部门表';

insert into sys_department(name, parent_id, sort_index, is_systemic, created_by, created_time)
values ('考拉开源', null, 1, 1, 1, now()),
       ('研发部', 1, 101, 1, 1, now()),
       ('回家部', 1, 102, 0, 1, now());

# 用户部门关系表
DROP TABLE IF EXISTS sys_user_department;
CREATE TABLE sys_user_department
(
  `user_id`       BIGINT NOT NULL COMMENT '用户id',
  `department_id` BIGINT NOT NULL COMMENT '部门id'
) COMMENT = '系统用户部门关系表';


insert into sys_user_department value (1, 1);

# 权限表
DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code`               VARCHAR(100) NOT NULL COMMENT '权限代码',
  `name`               VARCHAR(100) NOT NULL COMMENT '权限名称',
  `type`               INT          NOT NULL COMMENT '权限类型',
  `icon`               VARCHAR(100) COMMENT '权限图标',
  `url`                VARCHAR(500) COMMENT '权限路径',
  `component`          VARCHAR(100) COMMENT '权限组件',
  `remark`             VARCHAR(500) COMMENT '权限备注',
  `parent_id`          BIGINT COMMENT '上级权限id',
  `sort_index`         INT          NOT NULL DEFAULT 0 COMMENT '排序索引',
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
) COMMENT = '系统权限表';

insert into sys_permission(code, name, type, icon, url, component, parent_id, sort_index, is_systemic, created_by,
                           created_time)
values ('system', '系统管理', 1, 'ion:settings-outline', null, null, null, 1, 1, 1, now()),
       ('system:user', '用户管理', 1, null, null, 'system/user/index.vue', 1, 101, 1, 1, now()),
       ('system:role', '角色管理', 1, null, null, 'system/role/index.vue', 1, 102, 1, 1, now()),
       ('system:permission', '权限管理', 1, null, null, 'system/permission/index.vue', 1, 103, 1, 1, now()),
       ('system:department', '部门管理', 1, null, null, 'system/department/index.vue', 1, 104, 1, 1, now()),
       ('system:dictionary', '字典管理', 1, null, null, 'system/dictionary/index.vue', 1, 105, 1, 1, now()),
       ('system:setting', '设置管理', 1, null, null, 'system/setting/index.vue', 1, 106, 1, 1, now()),
       ('system:log', '日志管理', 1, null, null, 'system/log/index.vue', 1, 107, 1, 1, now()),
       ('system:user:page', '用户列表', 2, null, null, null, 2, 10101, 1, 1, now()),
       ('system:user:load', '用户查询', 2, null, null, null, 2, 10102, 1, 1, now()),
       ('system:user:create', '用户创建', 2, null, null, null, 2, 10103, 1, 1, now()),
       ('system:user:update', '用户修改', 2, null, null, null, 2, 10104, 1, 1, now()),
       ('system:user:delete', '用户删除', 2, null, null, null, 2, 10105, 1, 1, now()),
       ('system:role:page', '角色列表', 2, null, null, null, 3, 10201, 1, 1, now()),
       ('system:role:load', '角色查询', 2, null, null, null, 3, 10202, 1, 1, now()),
       ('system:role:create', '角色创建', 2, null, null, null, 3, 10203, 1, 1, now()),
       ('system:role:update', '角色修改', 2, null, null, null, 3, 10204, 1, 1, now()),
       ('system:role:delete', '角色删除', 2, null, null, null, 3, 10205, 1, 1, now()),
       ('system:permission:tree', '权限列表', 2, null, null, null, 4, 10301, 1, 1, now()),
       ('system:department:tree', '部门列表', 2, null, null, null, 5, 10401, 1, 1, now()),
       ('system:department:load', '部门查询', 2, null, null, null, 5, 10402, 1, 1, now()),
       ('system:department:create', '部门创建', 2, null, null, null, 5, 10403, 1, 1, now()),
       ('system:department:update', '部门修改', 2, null, null, null, 5, 10404, 1, 1, now()),
       ('system:department:delete', '部门删除', 2, null, null, null, 5, 10405, 1, 1, now()),
       ('system:dictionary:page', '字典列表', 2, null, null, null, 6, 10501, 1, 1, now()),
       ('system:dictionary:load', '字典查询', 2, null, null, null, 6, 10502, 1, 1, now()),
       ('system:dictionary:create', '字典创建', 2, null, null, null, 6, 10503, 1, 1, now()),
       ('system:dictionary:update', '字典修改', 2, null, null, null, 6, 10504, 1, 1, now()),
       ('system:dictionary:delete', '字典删除', 2, null, null, null, 6, 10505, 1, 1, now()),
       ('system:setting:create', '设置创建', 2, null, null, null, 7, 10601, 1, 1, now()),
       ('system:setting:update', '设置修改', 2, null, null, null, 7, 10602, 1, 1, now()),
       ('system:setting:delete', '设置删除', 2, null, null, null, 7, 10603, 1, 1, now()),
       ('system:log:page', '日志列表', 2, null, null, null, 8, 10701, 1, 1, now());

# 角色权限关系表
DROP TABLE IF EXISTS sys_role_permission;
CREATE TABLE sys_role_permission
(
  `role_id`         BIGINT NOT NULL COMMENT '角色id',
  `permission_id`   BIGINT NOT NULL DEFAULT 0 COMMENT '权限id',
  `is_half_checked` INT    NOT NULL COMMENT '是否半选'
) COMMENT = '系统角色权限关系表';

insert into sys_role_permission
select 1, id, 0
from sys_permission;

# 角色表
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code`               VARCHAR(100) NOT NULL COMMENT '角色代码',
  `name`               VARCHAR(100) NOT NULL COMMENT '角色名称',
  `remark`             VARCHAR(500) COMMENT '角色备注',
  `sort_index`         INT          NOT NULL DEFAULT 0 COMMENT '排序索引',
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
) COMMENT = '系统角色表';


insert into sys_role(code, name, sort_index, is_systemic, created_by, created_time)
values ('admin', '系统管理员', 1, 1, 1, now()),
       ('visitor', '访问者', 2, 0, 1, now());

# 用户角色关系表
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role
(
  `user_id` BIGINT NOT NULL COMMENT '用户id',
  `role_id` BIGINT NOT NULL COMMENT '角色id'
) COMMENT = '系统用户角色关系表';


insert into sys_user_role
values (1, 1);

# 用户表
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username`           VARCHAR(100) NOT NULL COMMENT '用户名',
  `password`           VARCHAR(500) NOT NULL COMMENT '密码',
  `nickname`           VARCHAR(100) NOT NULL COMMENT '昵称',
  `avatar`             VARCHAR(500) COMMENT '头像',
  `email`              VARCHAR(100) COMMENT '邮箱',
  `mobile`             VARCHAR(100) COMMENT '手机号',
  `remark`             VARCHAR(500) COMMENT '备注',
  `sort_index`         INT          NOT NULL DEFAULT 0 COMMENT '排序索引',
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
) COMMENT = '系统用户表';

insert into sys_user(username, password, nickname, sort_index, is_systemic, created_by, created_time)
values ('koala', '{bcrypt}$2a$10$JaBR557MI9SM5jgvcYwKiOPXyecHOqDQtN7pSO9xasp5NNjGS8jmG', '考拉', 1, 1, 1, now()),
       ('visitor', '{bcrypt}$2a$10$JaBR557MI9SM5jgvcYwKiOPXyecHOqDQtN7pSO9xasp5NNjGS8jmG', '访问者', 2, 0, 1, now());
