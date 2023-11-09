package cn.koala.system.api;

import cn.koala.system.model.Duty;
import cn.koala.system.model.DutyEntity;
import cn.koala.system.service.DutyService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 岗位接口实现类
 *
 * @author Koala Code Gen
 */
@RestController
@RequiredArgsConstructor
public class DutyApiImpl implements DutyApi {

  protected final DutyService service;

  @Override
  public DataResponse<Page<Duty>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<Duty> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<Duty> create(DutyEntity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, DutyEntity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(DutyEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
