# Koala Task Starter

考拉任务启动模块

基于 [Spring Scheduling](https://docs.spring.io/spring-framework/reference/integration/scheduling.html) 开发, 实现了基于持久化的动态定时任务管理与执行功能

## 快速开始

### 数据库

请先参照[快速开始](../../docs/guide/getting-started.md#初始化数据库)初始化数据库

### 接口文档

实现了任务管理和任务日志管理接口, 可通过访问接口文档查看具体信息

接口文档地址: `http://${host}:${port}/swagger-ui.html`

### 创建任务

创建自定义任务`TestTask`:

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestTask implements Runnable {

  @Override
  public void run() {
    // 任务逻辑...
  }
}
```

### 配置任务

```sql
insert into k_task (name, task_config, trigger_config, is_enabled) values ("测试任务", "testTask", "0/5 * * * * ?", 1);
```

**默认任务执行器**对应的字段说明如下:

| 字段名称       | 字段含义                                                     | 内容示例        |
| -------------- | ------------------------------------------------------------ | --------------- |
| name           | 任务名称                                                     | 测试任务        |
| task_config    | 任务配置, 即任务在 Spring IOC 中的bean名称                   | `testTask`      |
| trigger_config | 触发器配置, 支持 cron 表达式和 [Duration 表达式](https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html#parse-java.lang.CharSequence-) | `0/5 * * * * ?` |
| is_enabled     | 是否启动, 如果为是则自动开始调度                             | 1               |

## 进阶

### 任务工厂

可实现`TaskFactory`自定义任务工厂:

```java
public class MyTaskFactory implements TaskFactory {

  @Override
  public Runnable create(Task task) {
    // 创建任务逻辑...
  }
}
```

模块内置了如下任务工厂:

- `DefaultTaskFactory`: 默认任务工厂, 从 Spring IOC 容器中获取任务 bean

### 触发器工厂

可实现`TriggerFactory`自定义触发器工厂:

```java
public class MyTriggerFactory implements TriggerFactory {

  @Override
  public Trigger create(Task task) {
    // 创建触发器逻辑...
  }
}
```

模块内置了如下触发器工厂:

- `DefaultTriggerFactory`: 默认触发器工厂, 支持 cron 表达式和 [Duration 表达式](https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html#parse-java.lang.CharSequence-)

### 任务执行器

可实现`TaskExecutor`自定义任务执行器:

```java
public class MyTaskExecutor implements TaskExecutor {

  @Override
  public void schedule(Task task) {
    // 调度逻辑...
  }

  @Override
  public void cancel(Task task) {
    // 取消逻辑...
  }
    
  @Override
  public void execute(Task task) {
    // 执行逻辑...
  }
}
```

模块内置了如下任务执行器:

- `DefaultTaskExecutor`: 默认任务执行器, 基于 Spring 提供的 `TaskScheduler` 实现任务调度与执行
