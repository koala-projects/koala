package cn.koala.system.model;

import cn.koala.Koala;
import cn.koala.mybatis.AbstractEntity;
import cn.koala.persist.validator.UniqueField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

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
@UniqueField(value = "name", message = "{department.name.unique}")
public class DepartmentEntity extends AbstractEntity<Long> implements Department, Serializable {

  @Serial
  private static final long serialVersionUID = Koala.SERIAL_VERSION_UID;

  @Schema(description = "部门名称")
  @NotBlank(message = "{department.name.not-blank}")
  @Size(min = 2, max = 20, message = "{department.name.size}")
  protected String name;

  @Schema(description = "部门备注")
  @Size(max = 100, message = "{department.remark.size}")
  protected String remark;

  @Schema(description = "上级部门id")
  protected Long parentId;
}
