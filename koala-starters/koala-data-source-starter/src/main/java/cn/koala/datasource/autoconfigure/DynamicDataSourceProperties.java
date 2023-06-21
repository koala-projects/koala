package cn.koala.datasource.autoconfigure;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 动态数据源配置类
 *
 * @author Houtaroy
 */
@Data
@ConfigurationProperties(prefix = "koala.datasource")
public class DynamicDataSourceProperties {

  private List<DataSourceProperties> dynamic;
}
