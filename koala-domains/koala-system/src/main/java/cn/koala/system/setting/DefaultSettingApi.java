package cn.koala.system.setting;


import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 设置接口实现类
 *
 * @author Koala Code Gen
 */
@RestController
@RequiredArgsConstructor
public class DefaultSettingApi implements SettingApi {

  private final SettingService service;

  @Override
  public DataResponse<Page<Setting>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<Setting> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  // @Override
  // public DataResponse<SettingEntity> create(SettingEntity entity) {
  //   service.create(entity);
  //   return DataResponse.ok(entity);
  // }

  @Override
  public Response update(Long id, SettingEntity entity) {
    entity.setId(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  // @Override
  // public Response delete(Long id) {
  //   service.delete(SettingEntity.builder().id(id).build());
  //   return Response.SUCCESS;
  // }
}
