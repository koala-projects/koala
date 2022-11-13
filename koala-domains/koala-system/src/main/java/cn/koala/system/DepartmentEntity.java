package cn.koala.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 部门数据实体
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "部门数据实体")
public class DepartmentEntity extends AbstractSystemEntity implements Department {
  @Schema(description = "部门代码")
  private String code;
  @Schema(description = "部门名称")
  private String name;
  @Schema(description = "部门描述")
  private String description;
  @Schema(description = "上级部门")
  private DepartmentEntity parent;
}
