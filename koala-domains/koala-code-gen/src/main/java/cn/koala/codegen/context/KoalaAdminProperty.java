package cn.koala.codegen.context;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Koala Admin属性
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class KoalaAdminProperty {

  private String name;

  private String description;

  private String type;

  public static KoalaAdminProperty from(DomainProperty property) {
    return KoalaAdminProperty.builder()
      .name(property.getName().getCamel().getSingular())
      .description(property.getDescription())
      .type(property.getType().get("ts"))
      .build();
  }
}
