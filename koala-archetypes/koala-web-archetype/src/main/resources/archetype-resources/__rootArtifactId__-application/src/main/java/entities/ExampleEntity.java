#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entities;

import cn.koala.persistence.Idable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 示例表数据实体
 *
 * @author Eucalyptus Generator
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "示例表数据实体")
public class ExampleEntity implements Idable<String> {
  @Schema(description = "示例主键")
  private String id;
  @Schema(description = "示例代码")
  private String code;
  @Schema(description = "示例名称")
  private String name;
  @Schema(description = "示例描述")
  private String description;
}
