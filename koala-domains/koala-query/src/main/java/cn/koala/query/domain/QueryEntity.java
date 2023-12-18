package cn.koala.query.domain;

import cn.koala.mybatis.domain.AbstractEntity;
import cn.koala.query.util.QueryConstants;
import cn.koala.validation.group.Create;
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
 * 查询数据实体类
 *
 * @author Koala Code Generator
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "查询数据实体类")
public class QueryEntity extends AbstractEntity<Long, Long> implements Query, Serializable {

  @Serial
  private static final long serialVersionUID = QueryConstants.SERIAL_VERSION_UID;

  @NotBlank(message = "查询名称不允许为空", groups = {Create.class})
  @Size(max = 20, message = "查询名称长度不能大于20", groups = {Create.class})
  @Schema(description = "查询名称")
  private String name;

  @Size(max = 200, message = "查询备注长度不能大于200", groups = {Create.class})
  @Schema(description = "查询备注")
  private String description;

  @NotBlank(message = "查询语句不允许为空", groups = {Create.class})
  @Schema(description = "查询语句")
  private String sql;
}
