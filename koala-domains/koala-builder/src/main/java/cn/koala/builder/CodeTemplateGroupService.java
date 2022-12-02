package cn.koala.builder;

import cn.koala.jdbc.Table;
import cn.koala.persistence.CrudService;

import java.util.List;
import java.util.Map;

/**
 * 代码模板组服务
 *
 * @author Houtaroy
 */
public interface CodeTemplateGroupService extends CrudService<String, CodeTemplateGroup> {
  /**
   * 构建
   *
   * @param id         代码模板组ID
   * @param table      表
   * @param properties 参数
   * @return 构建结果
   */
  List<CodeBuildResult> build(String id, Table table, Map<String, Object> properties);
}
