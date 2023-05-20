package cn.koala.persist.initializer;

import org.springframework.core.Ordered;

/**
 * 模块初始化器
 * <p>
 * 模块初始化需要提供结构脚本和数据脚本
 *
 * @author Houtaroy
 */
public interface ModuleInitializer extends DataSourceInitializer, Ordered {

  String getSchema();

  String getData();
}
