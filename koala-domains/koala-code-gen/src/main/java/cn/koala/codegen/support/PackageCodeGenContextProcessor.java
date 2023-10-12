package cn.koala.codegen.support;

import cn.koala.codegen.CodeGenContext;
import cn.koala.codegen.CodeGenContextProcessor;
import cn.koala.database.DatabaseTable;
import lombok.RequiredArgsConstructor;

/**
 * 包名代码生成上下文处理器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class PackageCodeGenContextProcessor implements CodeGenContextProcessor {

  private final String packageName;

  @Override
  public CodeGenContext process(DatabaseTable table) {
    SimpleCodeGenContext result = new SimpleCodeGenContext(1);
    result.put("package", packageName);
    return result;
  }
}
