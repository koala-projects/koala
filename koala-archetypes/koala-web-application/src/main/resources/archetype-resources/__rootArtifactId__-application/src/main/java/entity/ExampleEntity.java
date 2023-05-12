#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entity;

import cn.koala.mybatis.AbstractEntity;
import cn.koala.persist.validator.UniqueField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 用户数据实体类
 *
 * @author koala web application
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@UniqueField("name")
@Schema(description = "示例实体")
public class ExampleEntity extends AbstractEntity {

  @Schema(description = "示例名称")
  String name;
}
