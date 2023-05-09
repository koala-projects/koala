package cn.koala.system.entities;

import cn.koala.mybatis.AbstractEntity;
import cn.koala.system.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 部门数据实体类
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "部门数据实体")
public class DepartmentEntity extends AbstractEntity implements Department {
  @Schema(description = "部门名称")
  protected String name;
  @Schema(description = "部门备注")
  protected String remark;
  @Schema(description = "上级部门id")
  protected Long parentId;
}
