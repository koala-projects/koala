package cn.koala.codegen.support;

import cn.koala.codegen.CodeGenContext;
import cn.koala.codegen.CodeGenContextProcessor;
import cn.koala.database.DatabaseTable;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public class TableCodeGenContextProcessor implements CodeGenContextProcessor {

  @Override
  public CodeGenContext process(DatabaseTable table) {
    SimpleCodeGenContext result = new SimpleCodeGenContext(1);
    result.put("table", table);
    return result;
  }
}
