package cn.koala.codegen.context.type;

import com.google.common.base.CaseFormat;

import java.util.HashMap;
import java.util.Map;

/**
 * JDBC TypeScript类型映射器
 *
 * @author Houtaroy
 */
public class JdbcTypeScriptTypeMapping extends AbstractJdbcTypeMapping<TypeScriptType> {

  private static final Map<Integer, TypeScriptType> DEFAULT_MAP = new HashMap<>(12);

  static {
    DEFAULT_MAP.put(-6, TypeScriptType.NUMBER);
    DEFAULT_MAP.put(5, TypeScriptType.NUMBER);
    DEFAULT_MAP.put(4, TypeScriptType.NUMBER);
    DEFAULT_MAP.put(-5, TypeScriptType.NUMBER);
    DEFAULT_MAP.put(6, TypeScriptType.NUMBER);
    DEFAULT_MAP.put(8, TypeScriptType.NUMBER);
    DEFAULT_MAP.put(2, TypeScriptType.NUMBER);
    DEFAULT_MAP.put(3, TypeScriptType.NUMBER);
    DEFAULT_MAP.put(91, TypeScriptType.NUMBER);
    DEFAULT_MAP.put(92, TypeScriptType.NUMBER);
    DEFAULT_MAP.put(93, TypeScriptType.NUMBER);
    DEFAULT_MAP.put(16, TypeScriptType.BOOLEAN);
  }


  public JdbcTypeScriptTypeMapping() {
    super(DEFAULT_MAP, TypeScriptType.STRING, CaseFormat.LOWER_CAMEL);
  }

  @Override
  public String getName() {
    return "ts";
  }
}
