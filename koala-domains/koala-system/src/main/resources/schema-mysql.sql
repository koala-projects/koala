DROP TABLE IF EXISTS t_dictionary;
CREATE TABLE t_dictionary
(
  id          VARCHAR(36) NOT NULL COMMENT '主键',
  code        VARCHAR(50) NOT NULL COMMENT '字典代码',
  name        VARCHAR(20) NOT NULL COMMENT '字典名称',
  description VARCHAR(500) COMMENT '字典描述',
  PRIMARY KEY (id)
) COMMENT = '字典';

DROP TABLE IF EXISTS t_dictionary_item;
CREATE TABLE t_dictionary_item
(
  id            VARCHAR(36) NOT NULL COMMENT '主键',
  name          VARCHAR(20) COMMENT '字典项名称',
  content       VARCHAR(50) COMMENT '字典项内容',
  dictionary_id VARCHAR(36) COMMENT '字典id',
  PRIMARY KEY (id)
) COMMENT = '字典项';

DROP TABLE IF EXISTS t_department;
CREATE TABLE t_department
(
  id          VARCHAR(36) NOT NULL COMMENT '部门ID',
  code        VARCHAR(50) COMMENT '部门代码',
  name        VARCHAR(20) COMMENT '部门名称',
  description VARCHAR(500) COMMENT '部门描述',
  parent_id   VARCHAR(36) COMMENT '上级部门ID',
  PRIMARY KEY (id)
) COMMENT = '部门';

DROP TABLE IF EXISTS t_permission;
CREATE TABLE t_permission
(
  id          VARCHAR(36) NOT NULL COMMENT '主键',
  code        VARCHAR(50) NOT NULL COMMENT '权限代码',
  name        VARCHAR(20) NOT NULL COMMENT '权限名称',
  description VARCHAR(500) COMMENT '权限描述',
  PRIMARY KEY (id)
) COMMENT = '权限';

DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role
(
  id          VARCHAR(36) NOT NULL COMMENT '角色主键',
  code        VARCHAR(50) NOT NULL COMMENT '角色代码',
  name        VARCHAR(20) NOT NULL COMMENT '角色名称',
  description VARCHAR(500) COMMENT '角色描述',
  PRIMARY KEY (id)
) COMMENT = '角色表';

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user
(
  id       VARCHAR(36)  NOT NULL COMMENT '用户ID',
  username VARCHAR(50)  NOT NULL COMMENT '用户登录名',
  password VARCHAR(500) NOT NULL COMMENT '用户密码',
  nickname VARCHAR(20)  NOT NULL COMMENT '用户昵称',
  avatar   VARCHAR(500) COMMENT '用户头像',
  PRIMARY KEY (id)
) COMMENT = '用户表';

DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role
(
  user_id VARCHAR(36) NOT NULL COMMENT '用户id',
  role_id VARCHAR(36) NOT NULL COMMENT '角色id'
) COMMENT = '用户角色关系';

DROP TABLE IF EXISTS t_user_department;
CREATE TABLE t_user_department
(
  user_id       VARCHAR(36) NOT NULL COMMENT '用户ID',
  department_id VARCHAR(36) NOT NULL COMMENT '部门ID'
) COMMENT = '用户部门关系';

DROP TABLE IF EXISTS t_role_permission;
CREATE TABLE t_role_permission
(
  role_id       VARCHAR(36) NOT NULL COMMENT '角色id',
  permission_id VARCHAR(36) NOT NULL COMMENT '权限id'
) COMMENT = '角色权限关系';

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

DROP TABLE IF EXISTS oauth2_authorization;
CREATE TABLE oauth2_authorization
(
  id                            varchar(100) NOT NULL,
  registered_client_id          varchar(100) NOT NULL,
  principal_name                varchar(200) NOT NULL,
  authorization_grant_type      varchar(100) NOT NULL,
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

DROP TABLE IF EXISTS oauth2_authorization_consent;
CREATE TABLE oauth2_authorization_consent
(
  registered_client_id varchar(100)  NOT NULL,
  principal_name       varchar(200)  NOT NULL,
  authorities          varchar(1000) NOT NULL,
  PRIMARY KEY (registered_client_id, principal_name)
);