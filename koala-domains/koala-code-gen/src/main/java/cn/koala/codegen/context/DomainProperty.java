package cn.koala.codegen.context;

import cn.koala.codegen.name.Name;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * 领域属性类
 *
 * @author Houtaroy
 */
@Getter
@SuperBuilder(toBuilder = true)
public class DomainProperty {

  private final Name name;

  private final String description;

  private final Map<String, String> type;
}
