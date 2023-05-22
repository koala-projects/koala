package cn.koala.persist.initializer;

import java.util.List;

/**
 * 脚本初始化器接口
 *
 * @author Houtaroy
 */
public interface ScriptInitializer extends DataSourceInitializer {

  List<String> getScripts();
}
