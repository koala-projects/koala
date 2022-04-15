# koala-druid-mqtt-spring-boot-starter

基于Druid拓展启动类, 提供查询语句解析和SQL检查功能

## 引入依赖

```xml

<dependencies>
  <dependency>
    <groupId>cn.houtaroy.koala</groupId>
    <artifactId>koala-druid-spring-boot-starter</artifactId>
    <version>2022.0.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```

## SQL语句解析

提供了`SelectASTVisitor`用于解析查询SQL语句:

```java
public class TableService {

  public List<SelectTable> tables(DbType dbType, String sql) {
    SQLStatement statement = SQLUtils.parseSingleStatement(sql, dbType);
    SelectASTVisitor visitor = new SelectASTVisitor();
    statement.accept(visitor);
    return visitor.getSelectTables();
  }
} 
```

注意: **当前功能不支持复杂查询**

## SQL注入检查

自动装配了`SqlService`的实现`InMemorySqlService`

支持以下数据库检查:

- MySQL
- Oracle
- SQLServer
- Postgresql
- DB2

`InMemorySqlService`提供了`addWallProvider`方法用于自定义SQL检查防火墙提供者

```java
public class TableService {
  private final SqlService sqlService;

  public boolean isInject(DbType dbType, String sql) {
    return sqlService.isInjection(dbType, sql);
  }
} 
```