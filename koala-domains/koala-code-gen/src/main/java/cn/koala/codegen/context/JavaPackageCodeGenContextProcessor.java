package cn.koala.codegen.context;

import lombok.RequiredArgsConstructor;

/**
 * 包名代码生成上下文处理器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class JavaPackageCodeGenContextProcessor implements CodeGenContextProcessor {

  private final String packageName;

  @Override
  public CodeGenContext process(CodeGenContext context) {
    context.put("package", packageName);
    return context;
  }
}
