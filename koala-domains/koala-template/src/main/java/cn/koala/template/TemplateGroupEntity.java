package cn.koala.template;

import cn.koala.enhancement.YesNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Houtaroy
 */
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
@Schema(description = "模板组数据实体")
public class TemplateGroupEntity implements TemplateGroup {
  @Schema(description = "模板组ID", required = true)
  protected String id;
  @Schema(description = "模板组名称", required = true)
  protected String name;
  @Schema(description = "模板列表", required = true)
  protected List<TemplateEntity> templates;
  @Schema(description = "是否系统")
  protected YesNo isSystem;
  @Schema(description = "是否删除")
  protected YesNo isDelete;
}
