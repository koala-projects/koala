package cn.koala.codegen;

import cn.koala.database.DatabaseTable;

/**
 * 代码生成上下文加工器
 * <p>
 * 将数据库表转换为代码生成上下文
 *
 * @author Houtaroy
 */
@FunctionalInterface
public interface CodeGenContextProcessor {

  CodeGenContext process(DatabaseTable table);
}
