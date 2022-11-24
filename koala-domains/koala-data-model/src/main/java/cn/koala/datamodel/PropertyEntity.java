package cn.koala.datamodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 属性实体
 *
 * @author Houtaroy
 */
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
@Schema(description = "属性实体")
public class PropertyEntity implements Property {
  @Schema(description = "属性ID")
  protected String id;
  @Schema(description = "属性代码", required = true)
  protected String code;
  @Schema(description = "属性名称", required = true)
  protected String name;
  @Schema(description = "属性描述")
  protected String description;
  @Schema(description = "属性类型", required = true)
  protected String type;
  protected MetadataEntity metadata;
}
