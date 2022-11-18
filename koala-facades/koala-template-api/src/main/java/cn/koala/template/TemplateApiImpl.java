package cn.koala.template;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * @author Houtaroy
 * @date 2022/4/10
 */
@RequiredArgsConstructor
@RestController
public class TemplateApiImpl implements TemplateApi {
  protected final TemplateService service;
  protected final Renderer renderer;

  @Override
  public DataResponse<Page<Template>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.list(parameters, pageable));
  }

  @Override
  public DataResponse<Template> loadById(String id) {
    return DataResponse.ok(service.load(id).orElse(null));
  }

  @Override
  public DataResponse<Template> create(TemplateEntity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(String id, TemplateEntity entity) {
    entity.setId(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(String id) {
    service.delete(TemplateEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<String> render(String id, Map<String, Object> data) {
    Optional<Template> optionalTemplate = service.load(id);
    Assert.isTrue(optionalTemplate.isPresent(), "模板数据异常");
    try {
      return DataResponse.of(HttpStatus.OK.value(), "请求成功", renderer.render(optionalTemplate.get(), data));
    } catch (Exception e) {
      throw new IllegalStateException("模板渲染失败");
    }
  }
}
