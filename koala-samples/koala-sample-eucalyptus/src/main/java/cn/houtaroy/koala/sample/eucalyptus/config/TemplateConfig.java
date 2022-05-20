package cn.houtaroy.koala.sample.eucalyptus.config;

import cn.koala.eucalyptus.FreemarkerGenerator;
import cn.koala.eucalyptus.SimpleTemplate;
import cn.koala.eucalyptus.TemplateConfigurer;
import cn.koala.eucalyptus.TemplateService;
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
    FreemarkerGenerator apiImpl = new FreemarkerGenerator(cfg, "java/apis/ApiImpl.java");
    FreemarkerGenerator entity = new FreemarkerGenerator(cfg, "java/entities/Entity.java");
    FreemarkerGenerator service = new FreemarkerGenerator(cfg, "java/services/Service.java");
    FreemarkerGenerator repository = new FreemarkerGenerator(cfg, "java/repositories/Repository.ftl");
    SimpleTemplate template = new SimpleTemplate(
      "test", "测试",
      List.of(apiImpl, entity, service, repository)
    );
    templateService.add(template);
  }
}
