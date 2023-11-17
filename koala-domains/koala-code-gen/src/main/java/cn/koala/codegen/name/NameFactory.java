package cn.koala.codegen.name;

import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;

/**
 * 名称工厂
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class NameFactory {

  private final NameStyleFactory nameStyleFactory;

  public Name fromPascalSingular(String pascal) {
    return Name.builder()
      .pascal(nameStyleFactory.fromSingular(pascal))
      .camel(nameStyleFactory.fromSingular(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, pascal)))
      .kebab(nameStyleFactory.fromSingular(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, pascal)))
      .snake(nameStyleFactory.fromSingular(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, pascal)))
      .build();
  }

  public Name fromCamelSingular(String camel) {
    return Name.builder()
      .pascal(nameStyleFactory.fromSingular(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, camel)))
      .camel(nameStyleFactory.fromSingular(camel))
      .kebab(nameStyleFactory.fromSingular(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, camel)))
      .snake(nameStyleFactory.fromSingular(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, camel)))
      .build();
  }

  public Name fromKebabSingular(String kebab) {
    return Name.builder()
      .pascal(nameStyleFactory.fromSingular(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, kebab)))
      .camel(nameStyleFactory.fromSingular(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, kebab)))
      .kebab(nameStyleFactory.fromSingular(kebab))
      .snake(nameStyleFactory.fromSingular(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_UNDERSCORE, kebab)))
      .build();
  }

  public Name fromSnakeSingular(String snake) {
    return Name.builder()
      .pascal(nameStyleFactory.fromSingular(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, snake)))
      .camel(nameStyleFactory.fromSingular(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, snake)))
      .kebab(nameStyleFactory.fromSingular(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, snake)))
      .snake(nameStyleFactory.fromSingular(snake))
      .build();
  }
}
