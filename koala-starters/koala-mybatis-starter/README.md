# Koala MyBatis Starter

考拉MyBatis启动模块

## 增强枚举

提供了`EnhancedEnum`接口, 增加 枚举名称 / 枚举值 属性, 例如枚举`YesNo`:

```java
@Getter
public enum YesNo implements EnhancedEnum {
  YES("是", 1),
  NO("否", 0);

  private final String name;
  private final int value;

  YesNo(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
```

在XML文件, 可以使用这种方式引用枚举的值: `t.is_delete = ${@cn.koala.mybatis.YesNo@NO.value}`

## 增强枚举拦截器

自动装配`EnhancedEnumTypeHandler`, 用于处理枚举在MyBatis中的值转换问题

## 通用模型

提供了部分以Model结尾的接口, 用于定义部分通用属性:

| 模型名称    | 模型用途                                   | 模型属性                          |
| ----------- | ------------------------------------------ | --------------------------------- |
| IdModel\<T> | 主键模型, 适用于使用主键标识的数据实体     | id: T                             |
| SortModel   | 排序模型, 适用于需要排序展示的数据实体     | sortIndex: Long                   |
| StateModel  | 状态模型, 适用于需要状态判断的数据实体     | isEnable/isSystem/isDelete: YesNo |
| AuditModel  | 审计模型, 适用于需要记录审计情况的数据实体 | 字段较多, 请查看接口源码          |

## 增删改查

针对常见的数据实体操作, 提供了如下几种接口和抽象类进行辅助:

- `CrudRepository`: 增删改查仓库接口, 定义了`insert`/`deleteById`/`updateById`/`findById`/`findAll`五种数据实体操作
- `CrudService`: 增删改查服务接口, 定义了`add`/`delete`/`update`/`load`/`list`/`save`六种服务方法
- `PagingService`: 分页服务接口, 定义了`page`服务方法
- `BaseService`: 基础服务抽象类, 实现了`CrudService`/`PagingService`定义的部分方法, 并针对不同的通用模型进行一定程度的业务处理

### BaseService抽象类

`BaseService`抽象类是本模块提供的主要实现类, 它包含了以下主要业务处理:

- 自定义主键生成策略
- 状态模型`StateModel`属性初始化
- 状态模型`StateModel`系统数据修改权限控制
- 审计模型`AuditModel`属性记录

具体的实现方式可查阅源代码

## 当前用户id

在审计模型中, 每次数据库修改操作都需要记录审计人员id, 即当前用户id

针对不同的安全框架, 获取当前人员id的方式可能会有所不同

使用本模块时, 需要开发人员手动实现`AuditorIdSupplier`接口, 并将实现类注入Spring IOC容器中

可参照安全模块中的对应实现: [`SecurityHelper`](../../koala-domains/koala-security/src/main/java/cn/koala/security/SecurityHelper.java)的`getCurrentUserId`方法
