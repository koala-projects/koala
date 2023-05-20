package cn.koala.persist.initializer.support;

import cn.koala.persist.initializer.DataSourceInitializer;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 复合初始化器
 * <p>
 * 整合所有数据源初始化器, 并按照排序依次执行
 *
 * @author Houtaroy
 */
@Order(4210)
@AllArgsConstructor
public class CompositeInitializer implements DataSourceInitializer, ApplicationRunner {

  private final DataSource dataSource;
  private final Map<String, Boolean> config;
  private final List<DataSourceInitializer> initializers;

  @Override
  public String getName() {
    return CompositeInitializer.class.getName();
  }

  @Override
  public void init(DataSource dataSource) throws Exception {
    for (DataSourceInitializer initializer : initializers) {
      if (config.getOrDefault(initializer.getName(), false)) {
        initializer.init(dataSource);
      }
    }
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    this.init(dataSource);
  }
}
