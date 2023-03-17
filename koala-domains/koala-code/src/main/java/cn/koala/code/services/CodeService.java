package cn.koala.code.services;

import cn.koala.code.Code;
import cn.koala.database.DatabaseTable;
import cn.koala.template.Template;

import java.util.List;
import java.util.Map;

/**
 * 代码服务接口
 *
 * @author Houtaroy
 */
public interface CodeService {
  Map<String, List<Code>> preview(List<DatabaseTable> tables, List<Template> templates);

  String download(List<DatabaseTable> tables, List<Template> templates);
}
