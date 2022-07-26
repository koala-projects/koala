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

