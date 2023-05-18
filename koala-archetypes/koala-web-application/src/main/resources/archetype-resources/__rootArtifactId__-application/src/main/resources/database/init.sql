#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
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
       (10, 'example', '示例管理', 1, null, null, 'system/example/index.vue', null, 3, 1, 1, now()),
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
       (39, 'attachment:download', '附件下载', 2, null, null, null, 9, 203, 1, 1, now()),
       (40, 'example:page', '示例列表', 2, null, null, null, 10, 301, 1, 1, now()),
       (41, 'example:load', '示例查询', 2, null, null, null, 10, 302, 1, 1, now()),
       (42, 'example:create', '示例创建', 2, null, null, null, 10, 303, 1, 1, now()),
       (43, 'example:update', '示例修改', 2, null, null, null, 10, 304, 1, 1, now()),
       (44, 'example:delete', '示例删除', 2, null, null, null, 10, 305, 1, 1, now());

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

# 数据库表
DROP TABLE IF EXISTS t_database;
CREATE TABLE t_database
(
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`        VARCHAR(100) NOT NULL COMMENT '数据库名称',
  `url`         VARCHAR(500) NOT NULL COMMENT '数据库连接',
  `username`    VARCHAR(100) NOT NULL COMMENT '数据库用户名',
  `password`    VARCHAR(100) NOT NULL COMMENT '数据库密码',
  `catalog`     VARCHAR(100) COMMENT '数据库目录',
  `schema`      VARCHAR(100) COMMENT '数据库模式',
  `is_enabled`  INT          NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_systemic` INT          NOT NULL DEFAULT 0 COMMENT '是否系统',
  `is_deleted`  INT          NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (id)
) COMMENT = '数据库';

# 模板组表
DROP TABLE IF EXISTS t_template_group;
CREATE TABLE t_template_group
(
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`        VARCHAR(100) NOT NULL COMMENT '模板组名称',
  `remark`      VARCHAR(500) COMMENT '模板组备注',
  `is_enabled`  INT          NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_systemic` INT          NOT NULL DEFAULT 0 COMMENT '是否系统',
  `is_deleted`  INT          NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (id)
) COMMENT = '模板组表';

# 模板表
DROP TABLE IF EXISTS t_template;
CREATE TABLE t_template
(
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`        VARCHAR(100) NOT NULL COMMENT '模板名称',
  `content`     TEXT         NOT NULL COMMENT '模板内容',
  `remark`      VARCHAR(500) COMMENT '模板备注',
  `group_id`    BIGINT COMMENT '模板组id',
  `is_enabled`  INT          NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_systemic` INT          NOT NULL DEFAULT 0 COMMENT '是否系统',
  `is_deleted`  INT          NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (id)
) COMMENT = '模板表';

# 演示数据库
insert into t_database(id, name, url, username, password, catalog, `schema`, is_systemic)
values (1, '${rootArtifactId}', 'jdbc:mysql://127.0.0.1:3306/${rootArtifactId}', '${rootArtifactId}',
        '${rootArtifactId}', '${rootArtifactId}', '${rootArtifactId}', 1);

# 考拉代码模板
insert into t_template_group(id, name, remark, is_systemic)
values (1, '考拉代码', '考拉代码生成模板', 1);

insert into t_template(name, remark, content, group_id, is_systemic)
values ('api/Api.java', '接口代码模板', 'package #(package).apis;

import #(package).entities.#(name)Entity;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.validation.group.Create;
import cn.koala.validation.group.Update;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * #(description)接口
 *
 * @author Koala Code Generator
 */
@RestController
@RequestMapping("/api/#(api.path)")
@Tag(name = "#(description)")
@SecurityRequirement(name = "spring-security")
public interface #(name)Api {

  /**
   * 根据条件分页查询#(description)
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return #(description)分页结果
   */
  @PreAuthorize("hasAuthority(''#(api.permission):page'')")
  @Operation(operationId = "list#(pluralName)", summary = "根据条件分页查询#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name)PageResult.class))}
  )
${symbol_pound}for(parameter: api.parameters.others)
  @Parameter(in = ParameterIn.QUERY, name = "#(parameter.name)", description = "#(parameter.description)", schema = @Schema(type = "#(parameter.type)"))
${symbol_pound}end
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<#(name)Entity>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                         @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询#(description)
   *
   * @param id #(description)id
   * @return #(description)数据实体
   */
  @PreAuthorize("hasAuthority(''#(api.permission):load'')")
  @Operation(operationId = "load#(name)", summary = "根据id查询#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name)Result.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "#(description)id", schema = @Schema(type = "#(api.parameters.id.type)"))
  @GetMapping("{id}")
  DataResponse<#(name)Entity> load(@PathVariable("id") #(entity.properties.id.type) id);

  /**
   * 创建#(description)
   *
   * @param entity #(description)数据实体
   * @return #(description)数据实体
   */
  @PreAuthorize("hasAuthority(''#(api.permission):create'')")
  @Operation(operationId = "create#(name)", summary = "创建#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name)Result.class))}
  )
  @PostMapping
  DataResponse<#(name)Entity> create(@Validated(Create.class) @RequestBody #(name)Entity entity);

  /**
   * 更新#(description)
   *
   * @param id     #(description)id
   * @param entity #(description)数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority(''#(api.permission):update'')")
  @Operation(operationId = "update#(name)", summary = "更新#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "#(description)id", schema = @Schema(type = "#(api.parameters.id.type)"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") #(entity.properties.id.type) id, @Validated(Update.class) @RequestBody #(name)Entity entity);

  /**
   * 删除#(description)
   *
   * @param id #(description)id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority(''#(api.permission):delete'')")
  @Operation(operationId = "delete#(name)", summary = "删除#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "#(description)id", schema = @Schema(type = "#(api.parameters.id.type)"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") #(entity.properties.id.type) id);

  class #(name)PageResult extends DataResponse<Page<#(name)Entity>> {

  }

  class #(name)Result extends DataResponse<#(name)Entity> {

  }
}
', 1, 1),
       ('api/ApiImpl.java', '接口实现类代码模板', 'package #(package).apis;

import #(package).entities.#(name)Entity;
import #(package).services.#(name)Service;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * #(description)接口实现类
 *
 * @author Koala Code Generator
 */
@RequiredArgsConstructor
@RestController
public class #(name)ApiImpl implements #(name)Api {

  protected final #(name)Service service;

  @Override
  public DataResponse<Page<#(name)Entity>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.read(parameters, pageable));
  }

  @Override
  public DataResponse<#(name)Entity> load(#(entity.properties.id.type) id) {
    return DataResponse.ok(service.read(id));
  }

  @Override
  public DataResponse<#(name)Entity> create(#(name)Entity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(#(entity.properties.id.type) id, #(name)Entity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(#(entity.properties.id.type) id) {
    service.delete(#(name)Entity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
', 1, 1),
       ('entity/Entity.java', '数据实体类代码模板', 'package #(package).entities;

${symbol_pound}for(import: entity.imports)
import #(import);
${symbol_pound}end
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * #(description)数据实体类
 *
 * @author Koala Code Generator
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "#(description)数据实体类")
public class #(name)Entity implements Persistable<#(entity.properties.id.type)>${symbol_pound}for(interface: entity.interfaces), #(interface)${symbol_pound}end  {

  @Schema(description = "#(entity.properties.id.description)")
  private #(entity.properties.id.type) id;
${symbol_pound}for(property: entity.properties.others)

${symbol_pound}for(validation: property.validations)
  @#(validation.name)(${symbol_pound}for(parameter : validation.parameters)#(parameter.key) = #(parameter.value), ${symbol_pound}end message = "#(validation.message)", groups = {${symbol_pound}for(group : validation.groups)#(group).class${symbol_pound}if(!for.last), ${symbol_pound}end ${symbol_pound}end})
${symbol_pound}end
  @Schema(description = "#(property.description)")
  private #(property.type) #(property.name);
${symbol_pound}end
}
', 1, 1),
       ('service/Service.java', '服务类代码模板', 'package #(package).services;

import #(package).#(name)Entity;
import #(package).#(name)Repository;

import cn.koala.mybatis.AbstractMyBatisService;

/**
 * #(description)服务类
 *
 * @author Koala Code Generator
 */
public class #(name)Service extends AbstractMyBatisService<#(name)Entity, #(entity.properties.id.type)> {
  /**
   * 构造函数
   *
   * @param repository 仓库接口
   */
  public #(name)Service(#(name)Repository repository) {
    super(repository);
  }
}
', 1, 1),
       ('repository/Repository.java', '仓库接口代码模板', 'package #(package).repositories;

import #(package).#(name)Entity;

import cn.koala.persist.CrudRepository;

/**
 * #(description)仓库接口
 *
 * @author Koala Code Generator
 */
public interface #(name)Repository extends CrudRepository<#(name)Entity, #(entity.properties.id.type)> {
}
', 1, 1),
       ('mapper/Mapper.xml', 'Mapper文件代码模板', '<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="#(package).repositories.#(name)Repository">

  <sql id="select#(name)">
    select
${symbol_pound}for(column : columns)
    t.#(column.name)${symbol_pound}if(!for.last),${symbol_pound}end
${symbol_pound}end
    from #(table.name) t
  </sql>

  <sql id="orderBy">
    <choose>
      <when test="orders != null and orders.size() > 0">
        <foreach collection="orders" item="order" index="index" open=" order by " close="" separator=",">
          <include refid="orderByField"/>
        </foreach>
      </when>
      <otherwise>
${symbol_pound}if(mybatis.isAuditable())
        order by t.created_time desc
${symbol_pound}else
		order by t.id asc
${symbol_pound}end
      </otherwise>
    </choose>
  </sql>

  <sql id="orderByField">
${symbol_pound}for(column: mybatis.columns)
    <if test="order.property == ''#(column.propertyName)''">
        t.#(column.columnName) <include refid="cn.koala.mybatis.repository.CommonRepository.orderDirection" />
    </if>
${symbol_pound}end
  </sql>

  <select id="list" resultType="#(package).entities.#(name)Entity">
    <include refid="select#(name)"/>
    <where>
${symbol_pound}if(mybatis.isStateful())
      t.is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value}
${symbol_pound}end
${symbol_pound}for(column: mybatis.columns)
${symbol_pound}if(column.columnName != ''id'')
      <if test="#(column.propertyName) != null and #(column.propertyName) != ''''">
       and t.#(column.columnName) = #{#(column.propertyName)}
      </if>
${symbol_pound}end
${symbol_pound}end
    </where>
	<include refid="orderBy"/>
  </select>

  <select id="load" resultType="#(package).entities.#(name)Entity">
    <include refid="select#(name)"/>
    where${symbol_pound}if(mybatis.isStateful()) t.is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value} and${symbol_pound}end  t.id=#{id}
  </select>

  <insert id="create" parameterType="#(package).entities.#(name)Entity"  useGeneratedKeys="true" keyProperty="id">
    insert into #(table.name)
	value (
${symbol_pound}for(column: mybatis.columns)
    #{#(column.propertyName)}${symbol_pound}if(!for.last),${symbol_pound}end
${symbol_pound}end
    )
  </insert>

  <update id="update" parameterType="#(package).entities.#(name)Entity">
    update #(table.name)
    <trim prefix="set" suffixOverrides=",">
${symbol_pound}for(column: mybatis.columns)
${symbol_pound}if(column.columnName != ''id'')
      <if test="#(column.propertyName) != null">#(column.columnName)=#{#(column.propertyName)},</if>
${symbol_pound}end
${symbol_pound}end
    </trim>
    where${symbol_pound}if(mybatis.isStateful()) is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value} and${symbol_pound}end  id=#{id}
  </update>

${symbol_pound}if(mybatis.isStateful())
  <update id="delete" parameterType="#(package).entities.#(name)Entity">
    update #(table.name)
    set is_deleted   = ${@cn.koala.persist.domain.YesNo@YES.value}${symbol_pound}if(mybatis.isAuditable()),${symbol_pound}end
${symbol_pound}if(mybatis.isAuditable())
        deleted_by   = #{deletedBy},
        deleted_time = #{deletedTime}
${symbol_pound}end
    where id = #{id}
  </update>
${symbol_pound}else
  <delete id="delete" parameterType="#(package).entities.#(name)Entity">
    delete from #(table.name) where id = #{id}
  </delete>
${symbol_pound}end
</mapper>
', 1, 1);

# 示例表
DROP TABLE IF EXISTS t_example;
CREATE TABLE t_example
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`               VARCHAR(100) NOT NULL COMMENT '示例名称',
  `sort_index`         INT                   DEFAULT 0 COMMENT '排序索引',
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
) COMMENT = '示例表';
