package cn.koala.template;

import cn.koala.enhancement.YesNo;
import cn.koala.persistence.Deletable;
import cn.koala.persistence.Systemic;
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
@Schema(description = "模板实体")
public class TemplateEntity implements Template, Systemic, Deletable {
  @Schema(description = "模板ID")
  protected String id;
  @Schema(description = "模板名称", required = true)
  protected String name;
  @Schema(description = "模板内容", required = true)
  protected String content;
  @Schema(description = "模板组")
  protected TemplateGroupEntity group;
  @Schema(description = "是否系统")
  protected YesNo isSystem;
  @Schema(description = "是否删除")
  protected YesNo isDelete;
}
