package cn.koala.eucalyptus;

import freemarker.template.Configuration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.StringWriter;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Freemarker代码生成模板
 *
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
@Slf4j
public class FreemarkerTemplate implements Template {
  protected final String code;
  protected final String name;
  protected final Configuration cfg;
  protected final Map<String, String> files;

  @Override
  public Map<String, String> process(DomainContext context) {
    return files.keySet().stream().collect(Collectors.toMap(files::get, filename -> process(filename, context)));
  }

  /**
   * 生成代码
   *
   * @param filename 模板文件名称
   * @param context  领域上下文
   * @return 代码
   */
  public String process(String filename, DomainContext context) {
    StringBuilder sb = new StringBuilder();
    try {
      StringWriter writer = new StringWriter();
      cfg.getTemplate(filename).process(context, writer);
      sb.append(writer);
    } catch (Exception e) {
      LOGGER.error("模板[{}]生成代码失败", filename, e);
    }
    return sb.toString();
  }
}
