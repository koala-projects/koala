package cn.koala.eucalyptus;

import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class TemplateApiImpl implements TemplateApi {
  private final TemplateManager templateManager;

  @Override
  public DataResponse<List<Template>> list() {
    return DataResponse.ok(templateManager.getTemplates());
  }

  @Override
  public DataResponse<Map<String, String>> generate(String code, DefaultDomainContext domainContext) {
    return DataResponse.ok(templateManager.process(code, domainContext));
  }
}
