package cn.koala.system.apis;

import cn.koala.log.annotations.Log;
import cn.koala.system.Dictionary;
import cn.koala.system.entities.DictionaryEntity;
import cn.koala.system.services.DictionaryService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 字典接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DictionaryApiImpl implements DictionaryApi {
  protected final DictionaryService service;

  @Override
  @Log(module = "字典管理", content = "查询字典列表")
  public DataResponse<Page<Dictionary>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.read(parameters, pageable));
  }

  @Override
  @Log(module = "字典管理", content = "查看字典[id=${#id}]")
  public DataResponse<Dictionary> load(Long id) {
    return DataResponse.ok(service.read(id));
  }

  @Override
  @Log(module = "字典管理", content = "创建字典[code=${#entity.code}]")
  public DataResponse<Dictionary> add(DictionaryEntity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  @Log(module = "字典管理", content = "更新字典[id=${#id}]")
  public Response update(Long id, DictionaryEntity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  @Log(module = "字典管理", content = "删除字典[id=${#id}]")
  public Response delete(Long id) {
    service.delete(DictionaryEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
