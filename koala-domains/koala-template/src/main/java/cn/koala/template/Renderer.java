package cn.koala.template;

import java.util.Map;

/**
 * 渲染器
 *
 * @author Houtaroy
 */
public interface Renderer {
  /**
   * 渲染
   *
   * @param template 模板
   * @param data     数据
   * @return 渲染结果
   * @throws Exception 渲染异常
   */
  Map<String, String> render(Template template, Map<String, Object> data) throws Exception;
}
