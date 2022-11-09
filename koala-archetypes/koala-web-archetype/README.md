# Web原型

## 原型结构

```
business 
  |--business-application
     |--src.main
        |--java
           |--apis
           |--entities
           |--repositories
           |--services
           |--Application.java
        |--resources
           |--database
              |--data.sql
              |--schema.sql
           |--mappers
           |--application.yml
     |--pom.xml
  |--business-parent
  |--checkstyle
     |--checkstyle-suppressions.xml
  |--.editorconfig
  |--lombok.config
  |--pom.xml
```

| 目录名称                    | 目录描述                     |
| --------------------------- | ---------------------------- |
| apis                        | 接口类包, 下级为接口类文件   |
| entities                    | 实体类包, 下级为实体类文件   |
| repositories                | 存储库包, 下级为存储库类文件 |
| services                    | 服务包, 下级为服务类文件     |
| Application.java            | 应用启动类                   |
| data.sql                    | 数据库数据初始化脚本         |
| schema.sql                  | 数据库结构初始化脚本         |
| mappers                     | MyBatis XML文件目录          |
| application.yml             | 应用配置文件                 |
| checkstyle-suppressions.xml | 代码检查忽略规则配置文件     |
| .editorconfig               | 编辑器配置文件               |
| lombok.config               | Lombok配置文件               |

## 代码生成

[桉树](https://eucalyptus.dxl.pink/)提供了本原型适配的模板, 选择`Koala业务模板`即可生成从接口至XML的全部代码