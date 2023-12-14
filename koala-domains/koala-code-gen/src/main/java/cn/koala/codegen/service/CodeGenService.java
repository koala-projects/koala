package cn.koala.codegen.service;

import cn.koala.database.domain.DatabaseTable;
import cn.koala.template.domain.Template;

import java.util.List;

/**
 * 代码生成服务
 *
 * @author Houtaroy
 */
public interface CodeGenService {

  CodeGenResult generate(DatabaseTable table, Template template);

  default List<CodeGenResult> generate(DatabaseTable table, List<Template> templates) {
    return templates.stream().map(template -> generate(table, template)).toList();
  }

  default List<MultiCodeGenResult> generate(List<DatabaseTable> tables, List<Template> templates) {
    return tables.stream().map(table -> new MultiCodeGenResult(table.getName(), generate(table, templates))).toList();
  }
}
