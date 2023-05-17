# Koala 考拉 :koala:

## 项目简介

考拉(Koala)是一款免费开源的管理系统**脚手架**, 无偿提供给开发者和**良心企业**使用, 并会持续不断的增加原型与组件

相较于其他同类产品, 考拉的不同之处在于:

- 考拉没有远大的目标, 不是所谓的框架或平台, 仅**致力于成为程序员的得力助手**
- 考拉将严格遵循通用标准, **不过度魔改或封装**
- 代码要跑, 文档先行

## 项目功能

- 用户管理: 增删改查/部门配置/角色配置
- 角色管理: 增删改查/权限配置
- 部门管理: 增删改查/树形结构
- 字典管理: 字典与字典项的增删改查
- 认证授权: 基于 Spring Authorization Server 的标准 OAuth2 认证授权体系
- 系统权限: 基于 RBAC 模型的权限分配
- 代码生成: 基于模板引擎, 灵活快速生成代码
- 启动模块: 提供多种 starter, 帮助项目快速集成高级功能

## 项目演示

- 演示地址: https://koala.dxl.pink
- 账号密码: admin/123456
- 每天6点定时重置数据

## 快速开始

开始之前, 您需要有以下技术储备

- SQL: 了解数据库, 拥有独立编写 SQL 语句的能力
- Java 17: 熟悉 Java 基本语法, 有一定程度的 Java 开发经验
- Spring Boot 3: 了解 Spring Boot, 接触过 Spring Boot 项目开发
- MyBatis: 了解 MyBatis 的基础使用方式
- IDE: 至少掌握一种集成开发环境

请参照: [快速开始文档](docs/guide/getting-started.md)

## 关联项目

- [koala-admin](https://github.com/koala-projects/koala-admin): 使用 Vben Admin 开发的适配考拉的前端项目

## 联系方式

如您有任何意见建议, 或想与作者交流, 可以发送邮件至`koala_projects@yeah.net`

## 许可

[MIT © Koala 2023](./LICENSE)