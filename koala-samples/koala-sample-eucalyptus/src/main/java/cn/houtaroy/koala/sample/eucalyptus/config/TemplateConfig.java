package cn.houtaroy.koala.sample.eucalyptus.config;

import cn.houtaroy.koala.component.eucalyptus.FreemarkerGenerator;
import cn.houtaroy.koala.component.eucalyptus.SimpleTemplate;
import cn.houtaroy.koala.component.eucalyptus.TemplateConfigurer;
import cn.houtaroy.koala.component.eucalyptus.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Houtaroy
 */
@Configuration
@RequiredArgsConstructor
public class TemplateConfig implements TemplateConfigurer {
  private final freemarker.template.Configuration cfg;

  @Override
  public void configure(TemplateService templateService) {
    FreemarkerGenerator generator = new FreemarkerGenerator(cfg, "java/entities/Entity.java");
    SimpleTemplate template = new SimpleTemplate("test", "测试", List.of(generator));
    templateService.add(template);
  }
}
