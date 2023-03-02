package cn.koala.builder;

import cn.koala.builder.enjoy.EngineFactory;
import com.jfinal.template.Engine;
import lombok.RequiredArgsConstructor;

/**
 * 构建器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class Builder {
  protected final Engine engine;

  public Builder(String path) {
    engine = EngineFactory.create(path);
  }

  public void test() {
    System.out.println(engine.getTemplateByString("#('a_bc'.format('LOWER_UNDERSCORE', 'UPPER_CAMEL'))").renderToString());
    System.out.println(engine.getTemplateByString("#('word'.plural()) ").renderToString());
  }

  public static void main(String[] args) {
    new Builder("D://Temp").test();
  }
}
