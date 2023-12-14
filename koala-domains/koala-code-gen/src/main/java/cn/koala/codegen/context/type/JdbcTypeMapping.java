package cn.koala.codegen.context.type;

import cn.koala.mapping.Mapping;

/**
 * JDBC类型映射器
 *
 * @author Houtaroy
 */
public interface JdbcTypeMapping extends Mapping<Integer, String> {

  String getName();
}
