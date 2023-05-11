# Koala PowerJob Worker Starter

考拉 PowerJob Worker 启动模块

## 快速开始

### 配置

```yaml
powerjob:
  worker:
    app-name: koala
    server-address: 10.10.10.184:7700
    protocol: http
```

其余参数请参照[PowerJob官方文档](https://www.yuque.com/powerjob/guidence/ygonln#VjLCQ)

## 进阶

### Groovy脚本任务

模块内置了支持 Groovy 脚本的任务处理器`GroovyProcessor`, 会根据任务参数执行Worker本机的脚本文件:

![groovy-task](https://raw.githubusercontent.com/koala-projects/image-hosting-service/main/koala/koala-starters/koala-powerjob-worker-starter/groovy-task.png)
