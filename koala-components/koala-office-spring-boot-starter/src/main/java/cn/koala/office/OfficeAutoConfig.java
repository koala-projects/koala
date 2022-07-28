package cn.koala.office;

import org.jodconverter.core.DocumentConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * office自动配置
 *
 * @author Houtaroy
 */
@Configuration
public class OfficeAutoConfig {

  /**
   * office转换器的bean
   *
   * @param documentConverter jod文档转换器
   * @return office转换器
   */
  @Bean
  @ConditionalOnBean(DocumentConverter.class)
  @ConditionalOnMissingBean
  public OfficeConverter officeConverter(DocumentConverter documentConverter) {
    return new JodOfficeConverter(documentConverter);
  }

  /**
   * Excel Web读取器的bean
   *
   * @return Excel Web读取器
   */
  @Bean
  @ConditionalOnMissingBean
  public ExcelWebReader excelWebReader() {
    return new EasyExcelReader();
  }

  /**
   * Excel Web写入器的bean
   *
   * @return Excel Web写入器
   */
  @Bean
  @ConditionalOnMissingBean
  public ExcelWebWriter excelWebWriter() {
    return new EasyExcelWriter();
  }

  /**
   * office web服务类的bean
   *
   * @return office web服务类
   */
  @Bean
  @ConditionalOnMissingBean
  public ExcelWebService excelWebService(ExcelWebReader reader, ExcelWebWriter writer) {
    return new DefaultExcelWebService(reader, writer);
  }
}
