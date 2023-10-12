package cn.koala.codegen;

import cn.koala.database.DatabaseTable;
import cn.koala.template.Template;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public interface CodeGenerator {

  CodeGenResult generate(DatabaseTable table, Template template);
}
