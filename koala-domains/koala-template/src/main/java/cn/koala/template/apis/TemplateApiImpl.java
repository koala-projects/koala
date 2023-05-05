package cn.koala.template.apis;

import cn.koala.template.Template;
import cn.koala.template.entities.TemplateEntity;
import cn.koala.template.services.TemplateService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 模板接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class TemplateApiImpl implements TemplateApi {
  protected final TemplateService service;

  @Override
  public DataResponse<Page<Template>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<Template> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<Template> create(TemplateEntity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, TemplateEntity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(TemplateEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
