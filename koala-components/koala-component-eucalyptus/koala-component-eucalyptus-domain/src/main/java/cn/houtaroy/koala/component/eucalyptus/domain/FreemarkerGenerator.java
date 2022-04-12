package cn.houtaroy.koala.component.eucalyptus.domain;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class FreemarkerGenerator implements Generator {

  protected final Configuration cfg;
  protected final List<String> files;

  @Override
  public Map<String, String> generate(Domain domain) throws IOException, TemplateException {
    Map<String, String> result = new HashMap<>(files.size());
    for (String file : files) {
      result.put(file, generate(file, domain));
    }
    return result;
  }

  /**
   * 生成代码
   *
   * @param filename 模板文件名
   * @param domain   领域定义
   * @return 代码
   * @throws IOException       IO异常
   * @throws TemplateException 模板异常
   */
  protected String generate(String filename, Domain domain) throws IOException, TemplateException {
    Template template = cfg.getTemplate(filename);
    StringWriter writer = new StringWriter();
    template.process(domain, writer);
    return writer.toString();
  }
}
