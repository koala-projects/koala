package cn.koala.codegen.context;

/**
 * 代码生成上下文加工器
 *
 * @author Houtaroy
 */
@FunctionalInterface
public interface CodeGenContextProcessor {

  CodeGenContext process(CodeGenContext context);
}
