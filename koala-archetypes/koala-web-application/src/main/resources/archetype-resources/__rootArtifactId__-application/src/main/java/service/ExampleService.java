#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.entity.ExampleEntity;
import ${package}.repository.ExampleRepository;
import cn.koala.mybatis.AbstractMyBatisService;
import org.springframework.stereotype.Component;

/**
 * 示例服务
 *
 * @author koala web application
 */
@Component
public class ExampleService extends AbstractMyBatisService<ExampleEntity, Long> {

  public ExampleService(ExampleRepository repository) {
    super(repository);
  }
}
