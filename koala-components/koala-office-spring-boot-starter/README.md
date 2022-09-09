# 办公组件

提供办公文档部分操作

## 引入依赖

```xml
<dependencies>
  <dependency>
    <groupId>cn.koala</groupId>
    <artifactId>koala-office-spring-boot-starter</artifactId>
    <version>2022.0.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```

## Excel

组件内置了[EasyExcel](https://github.com/alibaba/easyexcel)

### 下载

组件提供了`ExcelHelper`, 用于将文件直接推送至前端下载:

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

当前文档转换使用[JODConverte](https://github.com/sbraconnier/jodconverter)实现,
需要在服务器安装[LibreOffice](https://zh-cn.libreoffice.org/)或[Apache OpenOffice](https://www.openoffice.org/zh-cn/)

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

