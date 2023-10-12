package cn.koala.toolkit.name;

import com.google.common.base.CaseFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 名称类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Name {

  private NameStyle pascal;
  private NameStyle camel;
  private NameStyle kebab;
  private NameStyle snake;

  public static Name fromPascalSingular(String pascal) {
    return Name.builder()
      .pascal(NameStyle.fromSingular(pascal))
      .camel(NameStyle.fromSingular(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, pascal)))
      .kebab(NameStyle.fromSingular(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, pascal)))
      .snake(NameStyle.fromSingular(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, pascal)))
      .build();
  }

  public static Name fromCamelSingular(String camel) {
    return Name.builder()
      .pascal(NameStyle.fromSingular(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, camel)))
      .camel(NameStyle.fromSingular(camel))
      .kebab(NameStyle.fromSingular(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, camel)))
      .snake(NameStyle.fromSingular(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, camel)))
      .build();
  }

  public static Name fromKebabSingular(String kebab) {
    return Name.builder()
      .pascal(NameStyle.fromSingular(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, kebab)))
      .camel(NameStyle.fromSingular(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, kebab)))
      .kebab(NameStyle.fromSingular(kebab))
      .snake(NameStyle.fromSingular(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_UNDERSCORE, kebab)))
      .build();
  }

  public static Name fromSnakeSingular(String snake) {
    return Name.builder()
      .pascal(NameStyle.fromSingular(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, snake)))
      .camel(NameStyle.fromSingular(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, snake)))
      .kebab(NameStyle.fromSingular(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, snake)))
      .snake(NameStyle.fromSingular(snake))
      .build();
  }
}
