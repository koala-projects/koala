package cn.koala.code.template;

import java.util.List;
import java.util.Map;

/**
 * 模板组服务接口
 *
 * @author Houtaroy
 */
public interface TemplateGroupService {
  List<TemplateGroup> list(Map<String, Object> parameters);

  TemplateGroup load(String name);
}
