package cn.koala.toolkit.name;

import cn.koala.toolkit.WordHelper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 名称风格类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class NameStyle {

  private String singular;
  private String plural;

  public static NameStyle fromSingular(String singular) {
    return NameStyle.builder()
      .singular(singular)
      .plural(WordHelper.plural(singular))
      .build();
  }
}
