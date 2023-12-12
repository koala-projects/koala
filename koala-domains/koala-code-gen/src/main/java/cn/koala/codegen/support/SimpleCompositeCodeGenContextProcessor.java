package cn.koala.codegen.support;

import cn.koala.codegen.CodeGenContext;
import cn.koala.codegen.CodeGenContextProcessor;
import cn.koala.codegen.CompositeCodeGenContextProcessor;
import cn.koala.database.domain.DatabaseTable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 简单的聚合代码生成上下文加工器
 *
 * @author Houtaroy
 */
@Slf4j
public class SimpleCompositeCodeGenContextProcessor implements CompositeCodeGenContextProcessor {

  private final List<CodeGenContextProcessor> processors;

  public SimpleCompositeCodeGenContextProcessor(List<CodeGenContextProcessor> processors) {
    this.processors = processors;
  }

  @Override
  public CodeGenContext processAll(DatabaseTable table) {
    SimpleCodeGenContext result = new SimpleCodeGenContext();
    processors.forEach(processor ->
      processor.process(table).forEach((key, value) -> {
        if (result.containsKey(key)) {
          LOGGER.warn("上下文加工器[name={}]覆盖了属性[{}]", processor.getClass().getName(), key);
        }
        result.put(key, value);
      })
    );
    return result;
  }
}
