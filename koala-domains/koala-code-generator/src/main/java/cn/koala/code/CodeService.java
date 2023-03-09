package cn.koala.code;


import cn.koala.code.template.Template;
import cn.koala.database.Table;

import java.util.List;
import java.util.Map;

/**
 * 代码服务接口
 *
 * @author Houtaroy
 */
public interface CodeService {
  Map<String, String> generate(List<Template> templates, Table table);
}
