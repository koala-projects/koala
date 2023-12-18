-- Authorization Server
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

-- Attachment
-- 附件表
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

-- Query
-- 查询表
CREATE TABLE k_query
(
  `id`                 BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`               VARCHAR(100)  NOT NULL COMMENT '查询名称',
  `remark`             VARCHAR(500) COMMENT '查询备注',
  `sql`                VARCHAR(5000) NOT NULL COMMENT '查询语句',
  `sort_index`         INT                    DEFAULT 0 COMMENT '排序索引',
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
) COMMENT = '查询表';

-- System
-- 用户表
CREATE TABLE k_user
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username`           VARCHAR(20)  NOT NULL COMMENT '用户名',
  `password`           VARCHAR(200) NOT NULL COMMENT '密码',
  `nickname`           VARCHAR(20)  NOT NULL COMMENT '昵称',
  `avatar`             VARCHAR(200) COMMENT '头像',
  `email`              VARCHAR(50) COMMENT '邮箱',
  `mobile`             VARCHAR(20) COMMENT '手机号',
  `description`        VARCHAR(200) COMMENT '描述',
  `sort_index`         INT COMMENT '排序索引',
  `enabled`            VARCHAR(20)  NOT NULL DEFAULT 'YES' COMMENT '是否启用',
  `systemic`           VARCHAR(20)  NOT NULL DEFAULT 'NO' COMMENT '是否系统',
  `deleted`            VARCHAR(20)  NOT NULL DEFAULT 'NO' COMMENT '是否删除',
  `created_by`         BIGINT       NOT NULL COMMENT '创建人ID',
  `created_date`       DATETIME     NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_date` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_date`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '用户表';

-- 角色表
CREATE TABLE k_role
(
  `id`                 BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code`               VARCHAR(20) NOT NULL COMMENT '角色代码',
  `name`               VARCHAR(20) NOT NULL COMMENT '角色名称',
  `description`        VARCHAR(200) COMMENT '角色描述',
  `sort_index`         INT COMMENT '排序索引',
  `enabled`            VARCHAR(20) NOT NULL DEFAULT 'YES' COMMENT '是否启用',
  `systemic`           VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否系统',
  `deleted`            VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否删除',
  `created_by`         BIGINT      NOT NULL COMMENT '创建人ID',
  `created_date`       DATETIME    NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_date` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_date`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '角色表';

-- 权限表
CREATE TABLE k_permission
(
  `id`                 BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id`          BIGINT COMMENT '上级权限id',
  `code`               VARCHAR(20) NOT NULL COMMENT '权限代码',
  `name`               VARCHAR(20) NOT NULL COMMENT '权限名称',
  `description`        VARCHAR(200) COMMENT '权限描述',
  `sort_index`         INT COMMENT '排序索引',
  `enabled`            VARCHAR(20) NOT NULL DEFAULT 'YES' COMMENT '是否启用',
  `systemic`           VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否系统',
  `deleted`            VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否删除',
  `created_by`         BIGINT      NOT NULL COMMENT '创建人ID',
  `created_date`       DATETIME    NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_date` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_date`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '权限表';

-- 部门表
CREATE TABLE k_department
(
  `id`                 BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id`          BIGINT COMMENT '上级部门id',
  `name`               VARCHAR(20) NOT NULL COMMENT '部门名称',
  `description`        VARCHAR(200) COMMENT '部门描述',
  `sort_index`         INT COMMENT '排序索引',
  `enabled`            VARCHAR(20) NOT NULL DEFAULT 'YES' COMMENT '是否启用',
  `systemic`           VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否系统',
  `deleted`            VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否删除',
  `created_by`         BIGINT      NOT NULL COMMENT '创建人ID',
  `created_date`       DATETIME    NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_date` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_date`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '部门表';

-- 岗位表
CREATE TABLE k_duty
(
  `id`                 BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code`               VARCHAR(20) NOT NULL COMMENT '岗位代码',
  `name`               VARCHAR(20) NOT NULL COMMENT '岗位名称',
  `description`        VARCHAR(200) COMMENT '岗位描述',
  `sort_index`         INT COMMENT '排序索引',
  `enabled`            VARCHAR(20) NOT NULL DEFAULT 'YES' COMMENT '是否启用',
  `systemic`           VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否系统',
  `deleted`            VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否删除',
  `created_by`         BIGINT      NOT NULL COMMENT '创建人ID',
  `created_date`       DATETIME    NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_date` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_date`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '岗位表';


-- 用户角色关系表
CREATE TABLE k_user_role
(
  `user_id` BIGINT NOT NULL COMMENT '用户id',
  `role_id` BIGINT NOT NULL COMMENT '角色id'
) COMMENT = '用户角色关系表';

-- 角色权限关系表
CREATE TABLE k_role_permission
(
  `role_id`         BIGINT      NOT NULL COMMENT '角色id',
  `permission_id`   BIGINT      NOT NULL DEFAULT 0 COMMENT '权限id',
  `is_half_checked` VARCHAR(20) NOT NULL COMMENT '是否半选'
) COMMENT = '角色权限关系表';

-- 用户部门关系表
CREATE TABLE k_user_department
(
  `user_id`       BIGINT NOT NULL COMMENT '用户id',
  `department_id` BIGINT NOT NULL COMMENT '部门id'
) COMMENT = '用户部门关系表';

-- 用户岗位关系表
CREATE TABLE k_user_duty
(
  `user_id` BIGINT NOT NULL COMMENT '用户id',
  `duty_id` BIGINT NOT NULL COMMENT '岗位id'
) COMMENT = '用户岗位关系表';

-- 字典表
CREATE TABLE k_dictionary
(
  `id`                 BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code`               VARCHAR(20) NOT NULL COMMENT '字典代码',
  `name`               VARCHAR(20) NOT NULL COMMENT '字典名称',
  `description`        VARCHAR(200) COMMENT '字典描述',
  `sort_index`         INT COMMENT '排序索引',
  `enabled`            VARCHAR(20) NOT NULL DEFAULT 'YES' COMMENT '是否启用',
  `systemic`           VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否系统',
  `deleted`            VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否删除',
  `created_by`         BIGINT      NOT NULL COMMENT '创建人ID',
  `created_date`       DATETIME    NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_date` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_date`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '字典表';

-- 字典项表
CREATE TABLE k_dictionary_item
(
  `id`                 BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dictionary_id`      BIGINT      NOT NULL COMMENT '字典id',
  `code`               VARCHAR(20) NOT NULL COMMENT '字典项代码',
  `name`               VARCHAR(20) NOT NULL COMMENT '字典项名称',
  `description`        VARCHAR(200) COMMENT '字典项描述',
  `sort_index`         INT COMMENT '排序索引',
  `enabled`            VARCHAR(20) NOT NULL DEFAULT 'YES' COMMENT '是否启用',
  `systemic`           VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否系统',
  `deleted`            VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否删除',
  `created_by`         BIGINT      NOT NULL COMMENT '创建人ID',
  `created_date`       DATETIME    NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_date` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_date`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '字典项表';

-- Log
-- 日志表
CREATE TABLE k_log
(
  `id`         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module`     VARCHAR(20)  NOT NULL COMMENT '日志模块',
  `content`    VARCHAR(200) NOT NULL COMMENT '日志内容',
  `user_id`    BIGINT       NOT NULL COMMENT '用户id',
  `user_ip`    VARCHAR(20)  NOT NULL COMMENT '用户ip',
  `successful` VARCHAR(20)  NOT NULL COMMENT '是否成功',
  `request`    TEXT COMMENT '请求',
  `response`   TEXT COMMENT '响应',
  `error`      TEXT COMMENT '错误',
  `cost`       INT          NOT NULL COMMENT '消耗时间',
  `log_time`   DATETIME     NOT NULL COMMENT '日志时间',
  PRIMARY KEY (id)
) COMMENT = '日志表';

-- Database
-- 数据库表
CREATE TABLE k_database
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`               VARCHAR(20)  NOT NULL COMMENT '数据库名称',
  `url`                VARCHAR(200) NOT NULL COMMENT '数据库连接',
  `username`           VARCHAR(20)  NOT NULL COMMENT '数据库用户名',
  `password`           VARCHAR(20)  NOT NULL COMMENT '数据库密码',
  `catalog`            VARCHAR(20) COMMENT '数据库目录',
  `schema`             VARCHAR(20) COMMENT '数据库模式',
  `sort_index`         INT COMMENT '排序索引',
  `enabled`            VARCHAR(20)  NOT NULL DEFAULT 'YES' COMMENT '是否启用',
  `systemic`           VARCHAR(20)  NOT NULL DEFAULT 'NO' COMMENT '是否系统',
  `deleted`            VARCHAR(20)  NOT NULL DEFAULT 'NO' COMMENT '是否删除',
  `created_by`         BIGINT       NOT NULL COMMENT '创建人ID',
  `created_date`       DATETIME     NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_date` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_date`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '数据库表';


-- Task
-- 任务表
CREATE TABLE k_task
(
  `id`                 BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`               VARCHAR(100)  NOT NULL COMMENT '任务名称',
  `remark`             VARCHAR(500) COMMENT '任务备注',
  `task_config`        VARCHAR(2000) NOT NULL COMMENT '任务配置',
  `trigger_config`     VARCHAR(2000) NOT NULL COMMENT '触发配置',
  `sort_index`         INT                    DEFAULT 0 COMMENT '排序索引',
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

-- 任务日志表
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

-- Template
-- 模板组表
CREATE TABLE k_template_group
(
  `id`                 BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`               VARCHAR(20) NOT NULL COMMENT '模板组名称',
  `description`        VARCHAR(200) COMMENT '模板组描述',
  `sort_index`         INT COMMENT '排序索引',
  `enabled`            VARCHAR(20) NOT NULL DEFAULT 'YES' COMMENT '是否启用',
  `systemic`           VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否系统',
  `deleted`            VARCHAR(20) NOT NULL DEFAULT 'NO' COMMENT '是否删除',
  `created_by`         BIGINT      NOT NULL COMMENT '创建人ID',
  `created_date`       DATETIME    NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_date` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_date`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '模板组表';

-- 模板表
CREATE TABLE k_template
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id`           BIGINT COMMENT '模板组id',
  `name`               VARCHAR(100) NOT NULL COMMENT '模板名称',
  `description`        VARCHAR(200) COMMENT '模板描述',
  `content`            TEXT         NOT NULL COMMENT '模板内容',
  `sort_index`         INT COMMENT '排序索引',
  `enabled`            VARCHAR(20)  NOT NULL DEFAULT 'YES' COMMENT '是否启用',
  `systemic`           VARCHAR(20)  NOT NULL DEFAULT 'NO' COMMENT '是否系统',
  `deleted`            VARCHAR(20)  NOT NULL DEFAULT 'NO' COMMENT '是否删除',
  `created_by`         BIGINT       NOT NULL COMMENT '创建人ID',
  `created_date`       DATETIME     NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_date` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_date`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '模板表';

-- Wechat Mini App
-- 微信小程序用户表
CREATE TABLE wechat_mini_app_user
(
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid`      VARCHAR(100) NOT NULL COMMENT '用户唯一标识',
  `unionid`     VARCHAR(100) COMMENT '用户在开放平台的唯一标识符',
  `session_key` VARCHAR(100) NOT NULL COMMENT '会话密钥',
  `user_id`     INT          NOT NULL COMMENT '系统用户id',
  PRIMARY KEY (id)
) COMMENT = '微信小程序用户表';

-- Security
-- 认证日志表
CREATE TABLE k_authenticate_log
(
  `id`                BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
  `remote_address`    VARCHAR(20) COMMENT '远程地址',
  `session_id`        VARCHAR(50) COMMENT 'SessionId',
  `authentication`    TEXT        NOT NULL COMMENT '认证信息',
  `successful`        VARCHAR(20) NOT NULL COMMENT '是否成功',
  `exception_message` TEXT COMMENT '异常信息',
  `log_time`          DATETIME    NOT NULL COMMENT '日志时间',
  PRIMARY KEY (id)
) COMMENT = '认证日志表';


-- 登录日志表
CREATE TABLE k_login_log
(
  `id`                BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
  `remote_address`    VARCHAR(20) COMMENT '远程地址',
  `session_id`        VARCHAR(50) COMMENT 'SessionId',
  `user_id`           INT COMMENT '用户id',
  `username`          VARCHAR(20) NOT NULL COMMENT '用户名',
  `password`          VARCHAR(200) COMMENT '密码',
  `successful`        VARCHAR(20) NOT NULL COMMENT '是否成功',
  `exception_message` TEXT COMMENT '异常信息',
  `log_time`          DATETIME    NOT NULL COMMENT '日志时间',
  PRIMARY KEY (id)
) COMMENT = '登录日志表';


