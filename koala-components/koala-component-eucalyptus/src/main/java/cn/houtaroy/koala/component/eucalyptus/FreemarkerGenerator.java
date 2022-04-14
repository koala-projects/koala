package cn.houtaroy.koala.component.eucalyptus;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author Houtaroy
 */
public class FreemarkerGenerator implements Generator {
  protected Configuration cfg;
  protected String filename;

  /**
   * 构造方法
   *
   * @param cfg      Freemarker配置
   * @param filename 模板文件名
   */
  public FreemarkerGenerator(Configuration cfg, String filename) {
    Assert.notNull(cfg, "freemarker配置不能为空");
    Assert.hasLength(filename, "模板文件名不能为空");
    this.cfg = cfg;
    this.filename = filename;
  }

  @Override
  public String generate(Domain domain, Map<String, Object> options) throws IOException, TemplateException {
    Template template = cfg.getTemplate(filename);
    StringWriter writer = new StringWriter();
    template.process(data(domain, options), writer);
    return writer.toString();
  }

  /**
   * 生成数据模型
   *
   * @param domain  领域定义
   * @param options 选项
   * @return 数据模型
   */
  protected Map<String, Object> data(Domain domain, Map<String, Object> options) {
    return Map.of("domain", domain, "options", options);
  }
}
