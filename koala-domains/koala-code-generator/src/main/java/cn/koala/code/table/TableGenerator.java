package cn.koala.code.table;

import cn.koala.code.ContextProcessor;
import cn.koala.code.Generator;
import com.jfinal.template.Engine;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 数据库表代码生成器
 *
 * @author Houtaroy
 */
@Data
@RequiredArgsConstructor
public class TableGenerator implements Generator<TableContext, String> {
  protected final Engine engine;
  protected final String template;
  protected final ContextProcessor processor;

  @Override
  public String generate(TableContext source) {
    return engine.getTemplate(template).renderToString(processor.process(source));
  }
}
