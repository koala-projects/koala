#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import ${package}.entity.ExampleEntity;
import cn.koala.persist.CrudRepository;

/**
 * 示例仓库接口
 *
 * @author koala web application
 */
public interface ExampleRepository extends CrudRepository<ExampleEntity, Long> {

}
