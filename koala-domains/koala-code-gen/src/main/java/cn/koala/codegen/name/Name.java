package cn.koala.codegen.name;

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
}
