package cn.houtaroy.koala.sample.eucalyptus.apis;

import cn.houtaroy.koala.component.eucalyptus.Template;
import cn.houtaroy.koala.component.eucalyptus.TemplateService;
import cn.houtaroy.koala.sample.eucalyptus.models.KoalaDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Houtaroy
 */
@RequestMapping("/code")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CodeApi {
  private final TemplateService templateService;

  /**
   * 指定代码模板生成代码
   *
   * @param templateCode 模板代码
   * @param domain       领域定义
   * @return 生成的代码
   */
  @PostMapping("{templateCode}/generate")
  public List<String> generate(@PathVariable("templateCode") String templateCode, @RequestBody KoalaDomain domain) {
    Optional<Template> optional = templateService.loadByCode(templateCode);
    if (optional.isEmpty()) {
      LOGGER.info("模板[代码: {}]不存在", templateCode);
      return new ArrayList<>();
    }
    Template template = optional.get();
    List<String> result = new ArrayList<>(template.getGenerators().size());
    for (var generator : template.getGenerators()) {
      try {
        result.add(generator.generate(domain, Map.of("package", "cn.houtaroy.koala.test")));
      } catch (Exception e) {
        LOGGER.error("代码生成失败: {}", e.getMessage());
      }
    }
    return result;
  }
}
