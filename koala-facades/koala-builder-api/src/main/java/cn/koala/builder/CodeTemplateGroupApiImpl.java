package cn.koala.builder;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
@RestController
@RequiredArgsConstructor
public class CodeTemplateGroupApiImpl implements CodeTemplateGroupApi {
  protected final CodeTemplateGroupService service;

  @Override
  public DataResponse<Page<CodeTemplateGroup>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.list(parameters, pageable));
  }

  @Override
  public DataResponse<CodeTemplateGroup> loadById(String id) {
    return DataResponse.ok(service.load(id).orElse(null));
  }

  @Override
  public DataResponse<CodeTemplateGroup> create(CodeTemplateGroupEntity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(String id, CodeTemplateGroupEntity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(String id) {
    service.delete(CodeTemplateGroupEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<List<CodeBuildResult>> build(String id, BuildRequest body) {
    return DataResponse.ok(service.build(id, body.getTable(), body.getProperties()));
  }
}
