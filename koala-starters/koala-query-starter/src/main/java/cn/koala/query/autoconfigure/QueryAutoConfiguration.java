package cn.koala.query.autoconfigure;

import cn.koala.persist.initializer.DataSourceInitializer;
import cn.koala.query.QueryApi;
import cn.koala.query.QueryService;
import cn.koala.query.repository.QueryRepository;
import cn.koala.query.support.DefaultQueryApi;
import cn.koala.query.support.JdbcQueryService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * 查询自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@MapperScan(basePackages = "cn.koala.query.repository")
public class QueryAutoConfiguration {

  @Bean
  public QueryService queryService(QueryRepository queryRepository, NamedParameterJdbcTemplate jdbcTemplate) {
    return new JdbcQueryService(queryRepository, jdbcTemplate);
  }

  @Bean
  public QueryApi queryApi(QueryService queryService) {
    return new DefaultQueryApi(queryService);
  }

  @Bean
  public DataSourceInitializer queryInitializer() {
    return new QueryInitializer();
  }
}
