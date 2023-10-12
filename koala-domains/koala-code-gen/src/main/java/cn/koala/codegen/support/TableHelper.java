package cn.koala.codegen.support;

import cn.koala.codegen.support.domain.JavaType;
import cn.koala.codegen.support.domain.JsonType;
import cn.koala.codegen.support.domain.TypeScriptType;
import cn.koala.codegen.support.domain.VbenComponentType;
import cn.koala.database.DatabaseTableColumn;

import java.util.HashMap;
import java.util.Map;

/**
 * 表辅助类
 *
 * @author Houtaroy
 */
public abstract class TableHelper {

  private static final Map<Integer, JavaType> JAVA_TYPE_MAPPING = new HashMap<>(12);

  static {
    JAVA_TYPE_MAPPING.put(-6, JavaType.INTEGER);
    JAVA_TYPE_MAPPING.put(5, JavaType.INTEGER);
    JAVA_TYPE_MAPPING.put(4, JavaType.INTEGER);
    JAVA_TYPE_MAPPING.put(-5, JavaType.LONG);
    JAVA_TYPE_MAPPING.put(6, JavaType.FLOAT);
    JAVA_TYPE_MAPPING.put(8, JavaType.DOUBLE);
    JAVA_TYPE_MAPPING.put(2, JavaType.DOUBLE);
    JAVA_TYPE_MAPPING.put(3, JavaType.DOUBLE);
    JAVA_TYPE_MAPPING.put(91, JavaType.DATE);
    JAVA_TYPE_MAPPING.put(92, JavaType.TIME);
    JAVA_TYPE_MAPPING.put(93, JavaType.DATE);
    JAVA_TYPE_MAPPING.put(16, JavaType.BOOLEAN);
  }

  private static final Map<Integer, JsonType> JSON_TYPE_MAPPING = new HashMap<>(12);

  static {
    JSON_TYPE_MAPPING.put(-6, JsonType.INTEGER);
    JSON_TYPE_MAPPING.put(5, JsonType.INTEGER);
    JSON_TYPE_MAPPING.put(4, JsonType.INTEGER);
    JSON_TYPE_MAPPING.put(-5, JsonType.INTEGER);
    JSON_TYPE_MAPPING.put(6, JsonType.NUMBER);
    JSON_TYPE_MAPPING.put(8, JsonType.NUMBER);
    JSON_TYPE_MAPPING.put(2, JsonType.NUMBER);
    JSON_TYPE_MAPPING.put(3, JsonType.NUMBER);
    JSON_TYPE_MAPPING.put(91, JsonType.DATE_TIME);
    JSON_TYPE_MAPPING.put(92, JsonType.TIME);
    JSON_TYPE_MAPPING.put(93, JsonType.DATE_TIME);
    JSON_TYPE_MAPPING.put(16, JsonType.BOOLEAN);
  }

  private static final Map<Integer, TypeScriptType> TYPE_SCRIPT_TYPE_MAPPING = new HashMap<>(12);

  static {
    TYPE_SCRIPT_TYPE_MAPPING.put(-6, TypeScriptType.NUMBER);
    TYPE_SCRIPT_TYPE_MAPPING.put(5, TypeScriptType.NUMBER);
    TYPE_SCRIPT_TYPE_MAPPING.put(4, TypeScriptType.NUMBER);
    TYPE_SCRIPT_TYPE_MAPPING.put(-5, TypeScriptType.NUMBER);
    TYPE_SCRIPT_TYPE_MAPPING.put(6, TypeScriptType.NUMBER);
    TYPE_SCRIPT_TYPE_MAPPING.put(8, TypeScriptType.NUMBER);
    TYPE_SCRIPT_TYPE_MAPPING.put(2, TypeScriptType.NUMBER);
    TYPE_SCRIPT_TYPE_MAPPING.put(3, TypeScriptType.NUMBER);
    TYPE_SCRIPT_TYPE_MAPPING.put(91, TypeScriptType.NUMBER);
    TYPE_SCRIPT_TYPE_MAPPING.put(92, TypeScriptType.NUMBER);
    TYPE_SCRIPT_TYPE_MAPPING.put(93, TypeScriptType.NUMBER);
    TYPE_SCRIPT_TYPE_MAPPING.put(16, TypeScriptType.BOOLEAN);
  }

  private static final Map<Integer, VbenComponentType> VBEN_COMPONENT_TYPE_MAPPING = new HashMap<>(12);

  static {
    VBEN_COMPONENT_TYPE_MAPPING.put(-6, VbenComponentType.INPUT_NUMBER);
    VBEN_COMPONENT_TYPE_MAPPING.put(5, VbenComponentType.INPUT_NUMBER);
    VBEN_COMPONENT_TYPE_MAPPING.put(4, VbenComponentType.INPUT_NUMBER);
    VBEN_COMPONENT_TYPE_MAPPING.put(-5, VbenComponentType.INPUT_NUMBER);
    VBEN_COMPONENT_TYPE_MAPPING.put(6, VbenComponentType.INPUT_NUMBER);
    VBEN_COMPONENT_TYPE_MAPPING.put(8, VbenComponentType.INPUT_NUMBER);
    VBEN_COMPONENT_TYPE_MAPPING.put(2, VbenComponentType.INPUT_NUMBER);
    VBEN_COMPONENT_TYPE_MAPPING.put(3, VbenComponentType.INPUT_NUMBER);
    VBEN_COMPONENT_TYPE_MAPPING.put(91, VbenComponentType.INPUT_NUMBER);
    VBEN_COMPONENT_TYPE_MAPPING.put(92, VbenComponentType.INPUT_NUMBER);
    VBEN_COMPONENT_TYPE_MAPPING.put(93, VbenComponentType.INPUT_NUMBER);
    VBEN_COMPONENT_TYPE_MAPPING.put(16, VbenComponentType.SWITCH);
  }

  public static String determinedJavaTypeName(DatabaseTableColumn column) {
    return JAVA_TYPE_MAPPING.getOrDefault(column.getType(), JavaType.STRING).getName();
  }

  public static String determinedJsonTypeName(DatabaseTableColumn column) {
    return JSON_TYPE_MAPPING.getOrDefault(column.getType(), JsonType.STRING).getName();
  }

  public static String determinedTypeScriptTypeName(DatabaseTableColumn column) {
    return TYPE_SCRIPT_TYPE_MAPPING.getOrDefault(column.getType(), TypeScriptType.STRING).getName();
  }

  public static String determinedVbenComponentTypeName(DatabaseTableColumn column) {
    return VBEN_COMPONENT_TYPE_MAPPING.getOrDefault(column.getType(), VbenComponentType.INPUT).getName();
  }
}
