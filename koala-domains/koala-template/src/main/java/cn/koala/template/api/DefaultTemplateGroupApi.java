package cn.koala.template.api;

import cn.koala.template.domain.TemplateGroup;
import cn.koala.template.domain.TemplateGroupEntity;
import cn.koala.template.service.TemplateGroupService;
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
@RestController
@RequiredArgsConstructor
public class DefaultTemplateGroupApi implements TemplateGroupApi {

  private final TemplateGroupService service;

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
    entity.setId(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(TemplateGroupEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
