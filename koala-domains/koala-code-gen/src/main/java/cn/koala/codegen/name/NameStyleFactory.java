package cn.koala.codegen.name;

import lombok.RequiredArgsConstructor;

/**
 * 名称风格工厂
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class NameStyleFactory {

  private final PluralService pluralService;

  public NameStyle fromSingular(String singular) {
    return NameStyle.builder()
      .singular(singular)
      .plural(pluralService.plural(singular))
      .build();
  }
}
