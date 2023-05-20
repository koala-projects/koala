-- OAuth2注册客户端表
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

-- OAuth2授权表
CREATE TABLE oauth2_authorization_consent
(
  registered_client_id varchar(100)  NOT NULL,
  principal_name       varchar(200)  NOT NULL,
  authorities          varchar(1000) NOT NULL,
  PRIMARY KEY (registered_client_id, principal_name)
);

-- OAuth2授权信息表
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

-- 日志表
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

-- 字典表
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

-- 字典项表
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

-- 部门表
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

-- 权限表
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

-- 角色表
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

-- 用户表
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

-- 用户角色关系表
CREATE TABLE k_user_role
(
  `user_id` BIGINT NOT NULL COMMENT '用户id',
  `role_id` BIGINT NOT NULL COMMENT '角色id'
) COMMENT = '用户角色关系表';

-- 角色权限关系表
CREATE TABLE k_role_permission
(
  `role_id`         BIGINT NOT NULL COMMENT '角色id',
  `permission_id`   BIGINT NOT NULL DEFAULT 0 COMMENT '权限id',
  `is_half_checked` INT    NOT NULL COMMENT '是否半选'
) COMMENT = '角色权限关系表';

-- 用户部门关系表
CREATE TABLE k_user_department
(
  `user_id`       BIGINT NOT NULL COMMENT '用户id',
  `department_id` BIGINT NOT NULL COMMENT '部门id'
) COMMENT = '用户部门关系表';
