package cn.koala.codegen.context;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 考拉实体属性
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class KoalaEntityProperty {

  private String name;

  private String description;

  private String type;

  private List<String> validations;

  public static KoalaEntityProperty from(DomainProperty property) {
    return KoalaEntityProperty.builder()
      .name(property.getName().getCamel().getSingular())
      .description(property.getDescription())
      .type(property.getType().get("java"))
      .build();
  }
}
