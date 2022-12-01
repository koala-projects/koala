package cn.koala.builder;

import cn.koala.jdbc.Table;

import java.util.List;
import java.util.Map;

/**
 * 代码构建器
 *
 * @author Houtaroy
 */
public interface CodeBuilder {
  /**
   * 构建
   *
   * @param table             数据库表
   * @param codeTemplateGroup 代码模板组
   * @param parameters        参数
   * @return 构建结果
   */
  List<CodeBuilderResult> build(Table table, CodeTemplateGroup codeTemplateGroup, Map<String, Object> parameters);
}
