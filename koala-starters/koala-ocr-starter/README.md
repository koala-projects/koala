# Koala Ocr Starter

考拉OCR启动模块, 基于 [AIAS](https://github.com/mymagicpower/AIAS/tree/main) SDK 快速集成OCR功能

## 快速开始

### 下载模型

地址: https://pan.baidu.com/s/1AGKdyvVeRONOhAHu-Ot7RA?pwd=3m2f

### 配置

```yaml
koala:
  ocr:
    # 模型根目录
    model-path: "/tmp/koala/ocr/models"
```

### 接口文档

实现了OCR管理接口, 可通过访问接口文档查看具体信息

接口文档地址: `http://${host}:${port}/swagger-ui.html`

### 识别图片

```http
POST http://127.0.0.1:4200/api/ocr/image
Content-Type: multipart/form-data
file=123.png
```

### 识别PDF

```http
POST http://127.0.0.1:4200/api/ocr/pdf
Content-Type: multipart/form-data
file=123.pdf
```

