#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repositories;

import ${package}.entities.ExampleEntity;
import cn.koala.mybatis.PageRepository;

/**
 * 示例表存储库
 *
 * @author Eucalyptus Generator
 */
public interface ExampleRepository extends PageRepository<String, ExampleEntity> {
}
