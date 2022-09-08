package cn.koala.datamodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 持久化属性实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "属性")
public class PropertyEntity implements PersistentProperty {
  @Schema(description = "属性ID")
  protected String id;
  @Schema(description = "属性代码")
  protected String code;
  @Schema(description = "属性名称")
  protected String name;
  @Schema(description = "属性描述")
  protected String description;
  @Schema(description = "属性类型")
  protected PropertyType type;
  protected MetadataEntity metadata;
}
