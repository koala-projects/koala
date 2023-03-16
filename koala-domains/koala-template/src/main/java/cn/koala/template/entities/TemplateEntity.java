package cn.koala.template.entities;

import cn.koala.mybatis.YesNo;
import cn.koala.template.Template;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 模板数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "模板数据实体")
public class TemplateEntity implements Template {
  @Schema(description = "主键")
  protected Long id;
  @Schema(description = "模板名称")
  protected String name;
  @Schema(description = "模板内容")
  protected String content;
  @Schema(description = "模板备注")
  protected String remark;
  @Schema(description = "模板组id")
  protected Long groupId;
  @Schema(description = "是否启用")
  protected YesNo isEnable;
  @Schema(description = "是否系统")
  protected YesNo isSystem;
  @Schema(description = "是否删除")
  protected YesNo isDelete;
}
