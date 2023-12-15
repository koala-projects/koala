package cn.koala.system.domain;

import cn.koala.mybatis.domain.AbstractEntity;
import cn.koala.system.util.Versions;
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
 * 部门实体类
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "部门实体")
public class DepartmentEntity extends AbstractEntity<Long, Long> implements Department, Serializable {

  @Serial
  private static final long serialVersionUID = Versions.SERIAL;

  @Schema(description = "上级部门id")
  protected Long parentId;

  @Schema(description = "部门名称")
  @NotBlank(message = "部门名称不能为空")
  @Size(min = 2, max = 20, message = "部门名称长度必须在2-20个字符之间")
  protected String name;

  @Schema(description = "部门备注")
  @Size(max = 200, message = "部门备注长度不能超过200个字符")
  protected String description;
}
