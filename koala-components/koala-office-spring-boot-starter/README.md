# office组件

提供office部分操作

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

定义了部分Excel操作

当前默认实现基于[EasyExcel](https://github.com/alibaba/easyexcel)

### 读取

```java

@Component
@RequiredArgsConstructor
public class ExcelServiceTest {

  private final WebExcelService excelService;

  public void read(MultipartFile uploadFile) throws IOException {
    // 读取文件
    List<Item> fileData = excelService.read("/tmp/read.xlsx", Item.class);
    // 读取上传文件
    List<Item> uploadData = excelService.read(uploadFile.getInputStream(), Item.class);
  }
}
```

### 写入

```java

@Component
@RequiredArgsConstructor
public class ExcelServiceTest {

  private final WebExcelService excelService;

  public void write(HttpServletResponse response, List<Item> data) throws IOException {
    // 写入文件
    excelService.write("/tmp/write.xlsx", data, Item.class);
    // 写入响应
    excelService.write(response.getOutputStream(), data, Item.class);
    // 使用模板写入
    excelService.template("/tmp/template.xlsx", response.getOutputStream(), data, Item.class);
  }
}
```

模板书写方式请参照[EasyExcel文档](https://easyexcel.opensource.alibaba.com/docs/current/quickstart/fill)

## 文档转换

当前文档转换使用[JODConverte](https://github.com/sbraconnier/jodconverter)实现, 需要在服务器安装[LibreOffice](https://zh-cn.libreoffice.org/)或[Apache OpenOffice](https://www.openoffice.org/zh-cn/)

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
  private final OfficeConverter officeConverter;

  public void convert() {
    File source = new File("/tmp/test.doc");
    File target = new File("/tmp/test.html");
    officeConverter.excel2html(source, outputFile);
  }
}
```

