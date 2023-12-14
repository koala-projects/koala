package cn.koala.codegen.context.type;

import cn.koala.mapping.Mapping;

/**
 * @author Houtaroy
 */
public interface JdbcTypeMapping extends Mapping<Integer, String> {

  String getName();
}
