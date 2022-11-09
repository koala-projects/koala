# 办公组件

提供了部分办公相关辅助工具

## Excel

组件内置了[EasyExcel](https://github.com/alibaba/easyexcel)和`ExcelHelper`

### 下载

直接推送至前端下载:

```java
public class DownloadTest {
  public void download(HttpServletResponse response) {
    List<Item> data = new ArrayList<>();
    // 数据...
    ExcelHelper.prepareResponse(response, "导出表格.xlsx");
    EasyExcel.write(response.getOutputStream(), Item.class).withTemplate(templateName).sheet().doFill(data);
  }
}
```

## PDF

组件提供了`PdfUtil`, 用于完成PDF部分操作

## 文档转换

使用[JODConverte](https://github.com/sbraconnier/jodconverter)实现, 需在运行服务器安装[LibreOffice](https://zh-cn.libreoffice.org/)或[Apache OpenOffice](https://www.openoffice.org/zh-cn/)

### 配置

```yaml
jodconverter:
  local:
    enabled: true
    # office安装目录
    office-home: /opt/LibreOffice
    format-options:
      html:
        store:
          TEXT:
            FilterOptions: EmbedImages
```

### 转换

```java
@Component
@RequiredArgsConstructor
public class Service {
  private final DocumentConverter converter;

  public void convert() {
    File source = new File("/tmp/test.doc");
    File target = new File("/tmp/test.html");
    converter.convert(source).to(target).execute();
  }
}
```

