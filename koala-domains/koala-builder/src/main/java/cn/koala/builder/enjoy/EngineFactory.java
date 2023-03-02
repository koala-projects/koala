package cn.koala.builder.enjoy;

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
    return result;
  }
}
