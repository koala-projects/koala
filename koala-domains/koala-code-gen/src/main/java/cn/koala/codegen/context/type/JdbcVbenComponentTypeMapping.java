package cn.koala.codegen.context.type;

import com.google.common.base.CaseFormat;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Houtaroy
 */
public class JdbcVbenComponentTypeMapping
  extends AbstractJdbcTypeMapping<VbenComponentType> {

  private static final Map<Integer, VbenComponentType> DEFAULT_MAP = new HashMap<>(12);

  static {
    DEFAULT_MAP.put(-6, VbenComponentType.INPUT_NUMBER);
    DEFAULT_MAP.put(5, VbenComponentType.INPUT_NUMBER);
    DEFAULT_MAP.put(4, VbenComponentType.INPUT_NUMBER);
    DEFAULT_MAP.put(-5, VbenComponentType.INPUT_NUMBER);
    DEFAULT_MAP.put(6, VbenComponentType.INPUT_NUMBER);
    DEFAULT_MAP.put(8, VbenComponentType.INPUT_NUMBER);
    DEFAULT_MAP.put(2, VbenComponentType.INPUT_NUMBER);
    DEFAULT_MAP.put(3, VbenComponentType.INPUT_NUMBER);
    DEFAULT_MAP.put(91, VbenComponentType.INPUT_NUMBER);
    DEFAULT_MAP.put(92, VbenComponentType.INPUT_NUMBER);
    DEFAULT_MAP.put(93, VbenComponentType.INPUT_NUMBER);
    DEFAULT_MAP.put(16, VbenComponentType.SWITCH);
  }


  public JdbcVbenComponentTypeMapping() {
    super(DEFAULT_MAP, VbenComponentType.INPUT, CaseFormat.UPPER_CAMEL);
  }

  @Override
  public String getName() {
    return "vben";
  }
}
