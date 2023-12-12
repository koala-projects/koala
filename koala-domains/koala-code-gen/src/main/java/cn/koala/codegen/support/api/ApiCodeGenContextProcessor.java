package cn.koala.codegen.support.api;

import cn.koala.codegen.CodeGenContext;
import cn.koala.codegen.CodeGenContextProcessor;
import cn.koala.codegen.support.SimpleCodeGenContext;
import cn.koala.database.domain.DatabaseTable;

import java.util.Set;

/**
 * 接口代码生成上下文加工器
 *
 * @author Houtaroy
 */
public class ApiCodeGenContextProcessor implements CodeGenContextProcessor {

  private static final Set<String> PARAMETER_IGNORED_PROPERTY_NAMES = Set.of(
    "sortIndex",
    "isDeleted",
    "lastModifiedBy",
    "lastModifiedTime",
    "deletedBy",
    "deletedTime"
  );

  @Override
  public CodeGenContext process(DatabaseTable table) {
    SimpleCodeGenContext result = new SimpleCodeGenContext(1);
    result.put("parameterIgnoredPropertyNames", PARAMETER_IGNORED_PROPERTY_NAMES);
    return result;
  }
}
