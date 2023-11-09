package cn.koala.system.model;

import cn.koala.mybatis.AbstractEntity;
import cn.koala.validation.group.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 岗位数据实体类
 *
 * @author Koala Code Gen
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "岗位数据实体类")
public class DutyEntity extends AbstractEntity<Long> implements Duty {

  @NotBlank(message = "岗位代码不允许为空", groups = {Create.class})
  @Size(max = 100, message = "岗位代码长度不能大于100", groups = {Create.class})
  @Schema(description = "岗位代码")
  private String code;

  @NotBlank(message = "岗位名称不允许为空", groups = {Create.class})
  @Size(max = 100, message = "岗位名称长度不能大于100", groups = {Create.class})
  @Schema(description = "岗位名称")
  private String name;

  @Size(max = 500, message = "岗位描述长度不能大于500", groups = {Create.class})
  @Schema(description = "岗位描述")
  private String description;
}
