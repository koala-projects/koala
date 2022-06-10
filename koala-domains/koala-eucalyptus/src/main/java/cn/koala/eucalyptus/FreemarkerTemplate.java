package cn.koala.eucalyptus;

import freemarker.template.Configuration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
@Slf4j
public class FreemarkerTemplate implements Template {
  protected final String code;
  protected final String name;
  protected final Configuration cfg;
  protected final List<String> filenames;

  @Override
  public Map<String, String> process(Domain domain) {
    return filenames.stream().collect(Collectors.toMap(filename -> filename, filename -> process(filename, domain)));
  }

  /**
   * 生成代码
   *
   * @param filename 模板文件名称
   * @param domain   领域模型
   * @return 代码
   */
  public String process(String filename, Domain domain) {
    StringBuilder sb = new StringBuilder();
    try {
      StringWriter writer = new StringWriter();
      cfg.getTemplate(filename).process(Map.of("domain", domain), writer);
      sb.append(writer);
    } catch (Exception e) {
      LOGGER.error("模板[{}]生成代码失败", filename, e);
    }
    return sb.toString();
  }
}
