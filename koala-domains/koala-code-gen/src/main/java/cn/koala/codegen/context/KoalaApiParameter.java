package cn.koala.codegen.context;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 考拉接口查询参数
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class KoalaApiParameter {

  private String name;

  private String description;

  private String type;

  public static KoalaApiParameter from(DomainProperty property) {
    return KoalaApiParameter.builder()
      .name(property.getName().getCamel().getSingular())
      .description(property.getDescription())
      .type(property.getType().get("json"))
      .build();
  }
}
