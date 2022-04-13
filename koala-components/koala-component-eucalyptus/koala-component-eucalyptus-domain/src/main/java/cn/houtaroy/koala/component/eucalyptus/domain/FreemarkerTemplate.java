package cn.houtaroy.koala.component.eucalyptus.domain;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class FreemarkerTemplate implements Template {
  protected String name;
  protected Configuration cfg;
  protected List<String> files;

  public FreemarkerTemplate(String name, Configuration cfg, List<String> files) {
    Assert.hasLength(name, "模板名称不能为空");
    Assert.notNull(cfg, "Freemarker配置不能为空");
    Assert.notEmpty(files, "模板文件不能为空");
    this.name = name;
    this.cfg = cfg;
    this.files = files;
  }

  @Override
  public List<GenerateResult> generate(Domain domain) throws TemplateException, IOException {
    List<GenerateResult> result = new ArrayList<>(files.size());
    for (String file : files) {
      result.add(generate(file, domain));
    }
    return result;
  }

  protected GenerateResult generate(String file, Domain domain) throws IOException, TemplateException {
    freemarker.template.Template template = cfg.getTemplate(file);
    StringWriter writer = new StringWriter();
    template.process(domain, writer);
    return new GenerateResult(domain.getName(), writer.toString());
  }
}
