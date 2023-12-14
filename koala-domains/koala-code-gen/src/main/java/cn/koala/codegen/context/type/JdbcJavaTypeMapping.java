package cn.koala.codegen.context.type;

import com.google.common.base.CaseFormat;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Houtaroy
 */
public class JdbcJavaTypeMapping extends AbstractJdbcTypeMapping<JavaType> {

  private static final Map<Integer, JavaType> DEFAULT_MAP = new HashMap<>(12);

  static {
    DEFAULT_MAP.put(-6, JavaType.INTEGER);
    DEFAULT_MAP.put(5, JavaType.INTEGER);
    DEFAULT_MAP.put(4, JavaType.INTEGER);
    DEFAULT_MAP.put(-5, JavaType.LONG);
    DEFAULT_MAP.put(6, JavaType.FLOAT);
    DEFAULT_MAP.put(8, JavaType.DOUBLE);
    DEFAULT_MAP.put(2, JavaType.DOUBLE);
    DEFAULT_MAP.put(3, JavaType.DOUBLE);
    DEFAULT_MAP.put(91, JavaType.DATE);
    DEFAULT_MAP.put(92, JavaType.TIME);
    DEFAULT_MAP.put(93, JavaType.DATE);
    DEFAULT_MAP.put(16, JavaType.BOOLEAN);
  }

  public JdbcJavaTypeMapping() {
    super(DEFAULT_MAP, JavaType.STRING, CaseFormat.UPPER_CAMEL);
  }

  @Override
  public String getName() {
    return "java";
  }
}
