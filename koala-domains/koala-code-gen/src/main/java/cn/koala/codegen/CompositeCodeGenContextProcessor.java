package cn.koala.codegen;


import cn.koala.database.domain.DatabaseTable;

/**
 * 聚合代码生成上下文加工器
 *
 * @author Houtaroy
 */
public interface CompositeCodeGenContextProcessor {

  CodeGenContext processAll(DatabaseTable table);
}
