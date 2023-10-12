package cn.koala.codegen;

import cn.koala.database.DatabaseTable;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public interface CompositeCodeGenContextProcessor {

  CodeGenContext processAll(DatabaseTable table);
}
