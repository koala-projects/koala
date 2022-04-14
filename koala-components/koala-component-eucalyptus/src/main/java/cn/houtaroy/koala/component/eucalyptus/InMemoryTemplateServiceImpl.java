package cn.houtaroy.koala.component.eucalyptus;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author Houtaroy
 */
public class InMemoryTemplateServiceImpl implements TemplateService {
  private static final String NAME_PARAM_KEY = "name";
  protected final List<Template> templates = new CopyOnWriteArrayList<>();

  @Override
  public List<Template> list(Map<String, Object> params) {
    if (params.containsKey(NAME_PARAM_KEY)) {
      return templates.stream().filter(t -> t.getName().equals(params.get(NAME_PARAM_KEY)))
        .collect(Collectors.toList());
    }
    return templates;
  }

  @Override
  public Optional<Template> loadByCode(String code) {
    return templates.stream().filter(t -> t.getCode().equals(code)).findFirst();
  }

  @Override
  public Template add(Template template) {
    templates.add(template);
    return template;
  }

  @Override
  public void delete(Template template) {
    templates.remove(template);
  }
}
