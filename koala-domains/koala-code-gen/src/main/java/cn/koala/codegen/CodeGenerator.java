package cn.koala.codegen;

import cn.koala.database.domain.DatabaseTable;
import cn.koala.template.Template;

/**
 * 代码生成器接口
 *
 * @author Houtaroy
 */
public interface CodeGenerator {

  CodeGenResult generate(DatabaseTable table, Template template);
}
