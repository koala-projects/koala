package cn.koala.datasource.support;

import cn.koala.datasource.support.mapper.HikariMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * Hikari连接池动态数据源工厂
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class HikariDynamicDataSourceFactory extends AbstractDynamicDataSourceFactory<HikariDataSource> {

  private final Environment environment;
  private final HikariMapper mapper = Mappers.getMapper(HikariMapper.class);

  @Override
  public HikariDataSource create(DataSourceProperties properties) {

    HikariDataSource dataSource = createDataSource(properties, HikariDataSource.class);

    Binder binder = Binder.get(environment);
    binder.bind("spring.datasource.hikari", HikariConfig.class).ifBound(config -> mapper.copy(config, dataSource));

    if (StringUtils.hasText(properties.getName())) {
      dataSource.setPoolName(properties.getName());
    }

    return dataSource;
  }
}
