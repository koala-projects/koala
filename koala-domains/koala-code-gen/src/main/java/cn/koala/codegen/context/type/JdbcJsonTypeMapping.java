package cn.koala.codegen.context.type;

import com.google.common.base.CaseFormat;

import java.util.HashMap;
import java.util.Map;

/**
 * JDBC Json类型映射器
 *
 * @author Houtaroy
 */
public class JdbcJsonTypeMapping extends AbstractJdbcTypeMapping<JsonType> {

  private static final Map<Integer, JsonType> DEFAULT_MAP = new HashMap<>(12);

  static {
    DEFAULT_MAP.put(-6, JsonType.INTEGER);
    DEFAULT_MAP.put(5, JsonType.INTEGER);
    DEFAULT_MAP.put(4, JsonType.INTEGER);
    DEFAULT_MAP.put(-5, JsonType.INTEGER);
    DEFAULT_MAP.put(6, JsonType.NUMBER);
    DEFAULT_MAP.put(8, JsonType.NUMBER);
    DEFAULT_MAP.put(2, JsonType.NUMBER);
    DEFAULT_MAP.put(3, JsonType.NUMBER);
    DEFAULT_MAP.put(91, JsonType.DATE_TIME);
    DEFAULT_MAP.put(92, JsonType.TIME);
    DEFAULT_MAP.put(93, JsonType.DATE_TIME);
    DEFAULT_MAP.put(16, JsonType.BOOLEAN);
  }


  public JdbcJsonTypeMapping() {
    super(DEFAULT_MAP, JsonType.STRING, CaseFormat.LOWER_HYPHEN);
  }

  @Override
  public String getName() {
    return "json";
  }
}
