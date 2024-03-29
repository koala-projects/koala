package cn.koala.template.support;

import cn.koala.template.TemplateGroup;
import cn.koala.template.TemplateGroupApi;
import cn.koala.template.TemplateGroupService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 模板组接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DefaultTemplateGroupApi implements TemplateGroupApi {
  protected final TemplateGroupService service;

  @Override
  public DataResponse<Page<TemplateGroup>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<TemplateGroup> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<TemplateGroup> create(TemplateGroupEntity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, TemplateGroupEntity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(TemplateGroupEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
