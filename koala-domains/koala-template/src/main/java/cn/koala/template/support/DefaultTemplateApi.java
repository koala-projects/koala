package cn.koala.template.support;

import cn.koala.template.Template;
import cn.koala.template.TemplateApi;
import cn.koala.template.TemplateRenderer;
import cn.koala.template.TemplateService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 模板接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DefaultTemplateApi implements TemplateApi {

  protected final TemplateService service;
  protected final TemplateRenderer renderer;

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

  @Override
  public DataResponse<String> render(Long id, Map<String, Object> parameters) {
    Template template = service.load(id);
    Assert.notNull(template, "模板不存在");
    return new DataResponse<>(HttpStatus.OK.value(), "请求成功", renderer.render(template, parameters));
  }
}
