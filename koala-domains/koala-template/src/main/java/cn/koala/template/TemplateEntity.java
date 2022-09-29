package cn.koala.template;

import cn.koala.persistence.Idable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "模板实体")
public class TemplateEntity extends StringTemplate implements Idable<String> {
  protected String id;
  @Schema(description = "模板名称", required = true)
  protected String name;
  @Schema(description = "模板内容", required = true)
  protected String content;
}
