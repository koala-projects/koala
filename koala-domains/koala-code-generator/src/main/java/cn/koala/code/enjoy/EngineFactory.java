package cn.koala.code.enjoy;

import cn.koala.toolkit.jdbc.JavaTypeConverter;
import cn.koala.toolkit.jdbc.JsonTypeConverter;
import com.jfinal.template.Engine;

/**
 * Enjoy模板引擎工厂
 *
 * @author Houtaroy
 */
public abstract class EngineFactory {

  static {
    Engine.addExtensionMethod(String.class, StringExt.class);
  }

  public static Engine create(String path) {
    Engine result = Engine.create("koala-builder");
    result.setBaseTemplatePath(path);
    result.setDevMode(true);
    result.addSharedObject("JAVA_TYPE_CONVERTER", new JavaTypeConverter());
    result.addSharedObject("JSON_TYPE_CONVERTER", new JsonTypeConverter());
    return result;
  }
}
