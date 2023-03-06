package cn.koala.code;

import cn.koala.code.database.Table;
import cn.koala.code.processors.ContextProcessor;
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
public class DatabaseGenerator implements Generator<Table, String> {
  protected final Engine engine;
  protected final String template;
  protected final ContextProcessor processor;

  @Override
  public String generate(Table source) throws Exception {
    return engine.getTemplate(template).renderToString(processor.process(source));
  }
}
