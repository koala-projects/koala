package cn.koala.query.support;

import cn.koala.mybatis.AbstractEntity;
import cn.koala.query.Query;
import cn.koala.validation.group.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 查询数据实体类
 *
 * @author Koala Code Generator
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "查询数据实体类")
public class QueryEntity extends AbstractEntity<Long> implements Query {

  @NotBlank(message = "查询名称不允许为空", groups = {Create.class})
  @Size(max = 20, message = "查询名称长度不能大于20", groups = {Create.class})
  @Schema(description = "查询名称")
  private String name;

  @Size(max = 500, message = "查询备注长度不能大于500", groups = {Create.class})
  @Schema(description = "查询备注")
  private String remark;

  @NotBlank(message = "查询语句不允许为空", groups = {Create.class})
  @Size(max = 5000, message = "查询语句长度不能大于5000", groups = {Create.class})
  @Schema(description = "查询语句")
  private String sql;
}
