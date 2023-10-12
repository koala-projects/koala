package cn.koala.codegen.support;

import cn.koala.codegen.CodeGenContext;

import java.util.HashMap;

/**
 * 简易代码生成上下文, 继承HashMap
 *
 * @author Houtaroy
 */
public class SimpleCodeGenContext extends HashMap<String, Object> implements CodeGenContext {

  public SimpleCodeGenContext() {
    super();
  }

  public SimpleCodeGenContext(int size) {
    super(size);
  }
}
