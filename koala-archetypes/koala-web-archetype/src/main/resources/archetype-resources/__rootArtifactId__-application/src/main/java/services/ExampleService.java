#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

import ${package}.entities.ExampleEntity;
import ${package}.repositories.ExampleRepository;
import cn.koala.mybatis.AbstractCrudService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 示例表服务
 *
 * @author Eucalyptus Generator
 */
@Component
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ExampleService extends AbstractCrudService<String, ExampleEntity> {
  protected final ExampleRepository repository;

}
