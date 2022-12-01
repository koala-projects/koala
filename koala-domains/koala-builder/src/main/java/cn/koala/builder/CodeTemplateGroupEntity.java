package cn.koala.builder;

import cn.koala.datamodel.PropertyEntity;
import cn.koala.enhancement.YesNo;
import cn.koala.template.TemplateEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Schema(name = "代码模板组实体")
public class CodeTemplateGroupEntity implements CodeTemplateGroup {
  @Schema(description = "主键ID")
  private String id;
  @JsonIgnore
  private String code;
  @Schema(description = "代码模板组名称")
  private String name;
  @Schema(description = "代码模板组描述")
  private String description;
  @Schema(description = "领域转换器ID")
  private String domainConverterId;
  @Schema(description = "属性列表")
  private List<PropertyEntity> properties;
  @Schema(description = "模板列表")
  private List<TemplateEntity> templates;
  @Schema(description = "是否系统")
  private YesNo isSystem;
  @JsonIgnore
  private YesNo isDelete;
}
