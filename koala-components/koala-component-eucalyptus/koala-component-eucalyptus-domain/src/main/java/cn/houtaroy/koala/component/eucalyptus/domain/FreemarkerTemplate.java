package cn.houtaroy.koala.component.eucalyptus.domain;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class FreemarkerTemplate implements Template {
  protected String name;
  protected Configuration cfg;
  protected List<String> files;

  /**
   * 构造函数
   *
   * @param name  模板名称
   * @param cfg   freemarker配置
   * @param files 模板文件列表
   */
  public FreemarkerTemplate(String name, Configuration cfg, List<String> files) {
    Assert.hasLength(name, "模板名称不能为空");
    Assert.notNull(cfg, "Freemarker配置不能为空");
    Assert.notEmpty(files, "模板文件不能为空");
    this.name = name;
    this.cfg = cfg;
    this.files = files;
  }

  @Override
  public Map<String, String> generate(Domain domain) throws TemplateException, IOException {
    Map<String, String> result = new HashMap<>(files.size());
    for (String file : files) {
      result.put(FilenameUtils.getPath(file) + StringUtils.capitalize(domain.getName()) + FilenameUtils.getName(file),
        generate(file, domain));
    }
    return result;
  }

  protected String generate(String file, Domain domain) throws IOException, TemplateException {
    freemarker.template.Template template = cfg.getTemplate(file);
    StringWriter writer = new StringWriter();
    template.process(domain, writer);
    return writer.toString();
  }
}
