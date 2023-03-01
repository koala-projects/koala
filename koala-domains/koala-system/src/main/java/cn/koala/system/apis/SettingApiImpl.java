package cn.koala.system.apis;

import cn.koala.log.annotations.Log;
import cn.koala.system.Setting;
import cn.koala.system.entities.SettingEntity;
import cn.koala.system.services.SettingService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 设置接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class SettingApiImpl implements SettingApi {
  protected final SettingService service;

  @Override
  @Log(module = "设置管理", content = "查询设置列表")
  public DataResponse<List<Setting>> list(Map<String, Object> parameters) {
    return DataResponse.ok(service.list(parameters));
  }

  @Override
  @Log(module = "设置管理", content = "查看设置[id=${#id}]")
  public DataResponse<Setting> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  @Log(module = "设置管理", content = "创建设置[code=${#entity.code}]")
  public DataResponse<Setting> create(SettingEntity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  @Log(module = "设置管理", content = "更新设置[id=${#id}]")
  public Response update(Long id, SettingEntity entity) {
    entity.setIdIfAbsent(id);
    service.save(entity);
    return Response.SUCCESS;
  }

  @Override
  @Log(module = "设置管理", content = "删除设置[id=${#id}]")
  public Response delete(Long id) {
    service.delete(SettingEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
