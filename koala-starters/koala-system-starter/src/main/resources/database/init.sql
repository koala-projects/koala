# OAuth2注册客户端表
DROP TABLE IF EXISTS oauth2_registered_client;
CREATE TABLE oauth2_registered_client
(
  id                            varchar(100)                            NOT NULL,
  client_id                     varchar(100)                            NOT NULL,
  client_id_issued_at           timestamp     DEFAULT CURRENT_TIMESTAMP NOT NULL,
  client_secret                 varchar(200)  DEFAULT NULL,
  client_secret_expires_at      datetime      DEFAULT NULL,
  client_name                   varchar(200)                            NOT NULL,
  client_authentication_methods varchar(1000)                           NOT NULL,
  authorization_grant_types     varchar(1000)                           NOT NULL,
  redirect_uris                 varchar(1000) DEFAULT NULL,
  scopes                        varchar(1000)                           NOT NULL,
  client_settings               varchar(2000)                           NOT NULL,
  token_settings                varchar(2000)                           NOT NULL,
  PRIMARY KEY (id)
);

# OAuth2授权表
DROP TABLE IF EXISTS oauth2_authorization_consent;
CREATE TABLE oauth2_authorization_consent
(
  registered_client_id varchar(100)  NOT NULL,
  principal_name       varchar(200)  NOT NULL,
  authorities          varchar(1000) NOT NULL,
  PRIMARY KEY (registered_client_id, principal_name)
);

# OAuth2授权信息表
DROP TABLE IF EXISTS oauth2_authorization;
CREATE TABLE oauth2_authorization
(
  id                            varchar(100) NOT NULL,
  registered_client_id          varchar(100) NOT NULL,
  principal_name                varchar(200) NOT NULL,
  authorization_grant_type      varchar(100) NOT NULL,
  authorized_scopes             varchar(1000) DEFAULT NULL,
  attributes                    blob          DEFAULT NULL,
  state                         varchar(500)  DEFAULT NULL,
  authorization_code_value      blob          DEFAULT NULL,
  authorization_code_issued_at  datetime      DEFAULT NULL,
  authorization_code_expires_at datetime      DEFAULT NULL,
  authorization_code_metadata   blob          DEFAULT NULL,
  access_token_value            blob          DEFAULT NULL,
  access_token_issued_at        datetime      DEFAULT NULL,
  access_token_expires_at       datetime      DEFAULT NULL,
  access_token_metadata         blob          DEFAULT NULL,
  access_token_type             varchar(100)  DEFAULT NULL,
  access_token_scopes           varchar(1000) DEFAULT NULL,
  oidc_id_token_value           blob          DEFAULT NULL,
  oidc_id_token_issued_at       datetime      DEFAULT NULL,
  oidc_id_token_expires_at      datetime      DEFAULT NULL,
  oidc_id_token_metadata        blob          DEFAULT NULL,
  refresh_token_value           blob          DEFAULT NULL,
  refresh_token_issued_at       datetime      DEFAULT NULL,
  refresh_token_expires_at      datetime      DEFAULT NULL,
  refresh_token_metadata        blob          DEFAULT NULL,
  PRIMARY KEY (id)
);

# 表结构
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

# 字典表
DROP TABLE IF EXISTS k_dict;
CREATE TABLE k_dict
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
) COMMENT = '字典表';

# 字典项表
DROP TABLE IF EXISTS k_dict_item;
CREATE TABLE k_dict_item
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
) COMMENT = '字典项表';

# 部门表
DROP TABLE IF EXISTS k_department;
CREATE TABLE k_department
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
) COMMENT = '部门表';

# 权限表
DROP TABLE IF EXISTS k_permission;
CREATE TABLE k_permission
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
) COMMENT = '权限表';

# 角色表
DROP TABLE IF EXISTS k_role;
CREATE TABLE k_role
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
) COMMENT = '角色表';

# 用户表
DROP TABLE IF EXISTS k_user;
CREATE TABLE k_user
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
) COMMENT = '用户表';

# 用户角色关系表
DROP TABLE IF EXISTS k_user_role;
CREATE TABLE k_user_role
(
  `user_id` BIGINT NOT NULL COMMENT '用户id',
  `role_id` BIGINT NOT NULL COMMENT '角色id'
) COMMENT = '用户角色关系表';

# 角色权限关系表
DROP TABLE IF EXISTS k_role_permission;
CREATE TABLE k_role_permission
(
  `role_id`         BIGINT NOT NULL COMMENT '角色id',
  `permission_id`   BIGINT NOT NULL DEFAULT 0 COMMENT '权限id',
  `is_half_checked` INT    NOT NULL COMMENT '是否半选'
) COMMENT = '角色权限关系表';

# 用户部门关系表
DROP TABLE IF EXISTS k_user_department;
CREATE TABLE k_user_department
(
  `user_id`       BIGINT NOT NULL COMMENT '用户id',
  `department_id` BIGINT NOT NULL COMMENT '部门id'
) COMMENT = '用户部门关系表';

# 管理员用户
insert into k_user(id, username, password, nickname, sort_index, is_systemic, created_by, created_time)
values (1, 'admin', '{bcrypt}$2a$10$COsN1rWGQydTaNKaZ5EfFeYY0fCunvlHO4ABvqQUqQZiiK.bzglK2', '管理员', 1, 1, 1, now());

# 管理员角色
insert into k_role(id, code, name, sort_index, is_systemic, created_by, created_time)
values (1, 'admin', '系统管理员', 1, 1, 1, now());

# 所有权限
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
       (9, 'attachment', '附件管理', 1, null, null, 'system/attachment/index.vue', null, 2, 1, 1, now()),
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
       (36, 'system:log:load', '日志查询', 2, null, null, null, 8, 10702, 1, 1, now()),
       (37, 'attachment:delete', '附件删除', 2, null, null, null, 9, 201, 1, 1, now()),
       (38, 'attachment:upload', '附件上传', 2, null, null, null, 9, 202, 1, 1, now()),
       (39, 'attachment:download', '附件下载', 2, null, null, null, 9, 203, 1, 1, now());

# 管理部门
insert into k_department(name, parent_id, sort_index, is_systemic, created_by, created_time)
values ('考拉开源', null, 1, 1, 1, now());

# 管理员用户角色关系
insert into k_user_role
values (1, 1);

# 管理员角色权限关系
insert into k_role_permission
select 1, id, 0
from k_permission;

# 管理员用户部门关系
insert into k_user_department value (1, 1);
