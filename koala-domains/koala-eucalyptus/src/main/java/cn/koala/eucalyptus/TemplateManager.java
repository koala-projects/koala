package cn.koala.eucalyptus;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
public interface TemplateManager {
  /**
   * 获取模板列表
   *
   * @return 模板列表
   */
  List<Template> getTemplates();

  /**
   * 使用指定模板生成代码
   *
   * @param code    模板编码
   * @param context 领域上下文
   * @return 代码
   */
  Map<String, String> process(String code, DomainContext context);
}
