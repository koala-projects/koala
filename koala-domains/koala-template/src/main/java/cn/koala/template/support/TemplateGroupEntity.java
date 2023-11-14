package cn.koala.template.support;

import cn.koala.common.Koala;
import cn.koala.persist.domain.YesNo;
import cn.koala.template.TemplateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * 模板组数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "模板组数据实体")
public class TemplateGroupEntity implements TemplateGroup, Serializable {

  @Serial
  private static final long serialVersionUID = Koala.SERIAL_VERSION_UID;

  @Schema(description = "主键")
  protected Long id;

  @Schema(description = "模板组名称")
  protected String name;

  @Schema(description = "模板组备注")
  protected String remark;

  @Schema(description = "是否启用")
  protected YesNo isEnabled;

  @Schema(description = "是否系统")
  protected YesNo isSystemic;

  @Schema(description = "是否删除")
  protected YesNo isDeleted;
}
