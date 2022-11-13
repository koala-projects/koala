package cn.koala.system;

import cn.koala.enhancement.YesNo;
import cn.koala.persistence.Idable;
import cn.koala.persistence.Stateable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class AbstractSystemEntity implements Idable<String>, Stateable {
  @Schema(description = "主键ID")
  protected String id;
  @JsonIgnore
  private YesNo isEnable;
  @Schema(description = "是否系统")
  private YesNo isSystem;
  @Schema(description = "是否删除")
  private YesNo isDelete;
}
