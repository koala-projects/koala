package cn.koala.template;

import java.util.Map;

/**
 * 模板渲染器
 *
 * @author Houtaroy
 */
public interface Renderer {
  /**
   * 渲染
   *
   * @param content 模板内容
   * @param data    数据
   * @return 渲染结果
   * @throws Exception 渲染异常
   */
  String render(String content, Map<String, Object> data) throws Exception;

  /**
   * 渲染或返回默认值
   *
   * @param content      模板内容
   * @param data         数据
   * @param defaultValue 默认值
   * @return 渲染结果
   */
  default String renderOrDefault(String content, Map<String, Object> data, String defaultValue) {
    try {
      return render(content, data);
    } catch (Exception e) {
      return defaultValue;
    }
  }
}
