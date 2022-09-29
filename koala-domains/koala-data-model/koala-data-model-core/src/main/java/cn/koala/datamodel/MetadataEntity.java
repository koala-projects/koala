package cn.koala.datamodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 持久化元数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "元数据实体")
public class MetadataEntity implements Metadata {
  @Schema(description = "元数据ID")
  protected String id;
  @Schema(description = "元数据代码")
  protected String code;
  @Schema(description = "元数据名称")
  protected String name;
  @Schema(description = "元数据描述")
  protected String description;
  @Schema(description = "属性列表")
  protected List<PropertyEntity> properties;
}
