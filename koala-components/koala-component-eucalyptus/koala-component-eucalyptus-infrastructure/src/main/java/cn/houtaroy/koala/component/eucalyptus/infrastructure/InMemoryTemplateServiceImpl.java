package cn.houtaroy.koala.component.eucalyptus.infrastructure;

import cn.houtaroy.koala.component.eucalyptus.domain.Template;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class InMemoryTemplateServiceImpl implements TemplateService {

  protected final Map<String, Template> templates = new HashMap<>();

  @Override
  public List<Template> list(String name) {
    return templates.keySet().stream().filter(key -> key.contains(name)).map(templates::get)
      .collect(Collectors.toList());
  }

  @Override
  public Optional<Template> loadByName(String name) {
    return Optional.ofNullable(templates.get(name));
  }

  @Override
  public Template add(Template template) {
    templates.put(template.getName(), template);
    return template;
  }

  @Override
  public void delete(String name) {
    templates.remove(name);
  }
}
